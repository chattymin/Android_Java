package com.chattymin.mineswipperproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.chattymin.mineswipperproject.databinding.ActivityMainBinding;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public static boolean CLICKTYPE = true;
    public static int BLOCKS = 81;
    public static int TOTAL_MINES_COUNT = 10;
    public static int SIZE = 9;
    private BlockButton[][] buttons = new BlockButton[SIZE][SIZE];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        countNeighborMines();
        setMineButtonClickListener();
        setClickTypeBtnClickListener();
    }

    private void init() {
        TableLayout table = binding.table;
        buttons = new BlockButton[SIZE][SIZE];

        Set<Pair<Integer, Integer>> mineSet = new HashSet<>();
        setMines(mineSet);

        for (int i = 0; i < SIZE; i++) {
            TableRow tableRow = new TableRow(this);

            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1.0f
            );

            for (int j = 0; j < SIZE; j++) {
                if (mineSet.contains(new Pair<>(i, j)))
                    buttons[i][j] = new BlockButton(this, i, j, true);
                else
                    buttons[i][j] = new BlockButton(this, i, j, false);

                buttons[i][j].setLayoutParams(layoutParams);
                tableRow.addView(buttons[i][j]);
            }

            table.addView(tableRow);
        }

        setTotalMines();
    }

    private void setMines(@NonNull Set<Pair<Integer, Integer>> mineSet) {
        while (mineSet.size() < TOTAL_MINES_COUNT) {
            int x = (int) (Math.random() * SIZE);
            int y = (int) (Math.random() * SIZE);

            mineSet.add(new Pair<>(x, y));
        }
    }

    private void setTotalMines() {
        String text = "Mines : ";
        binding.tvMainMineCount.setText(text + TOTAL_MINES_COUNT);
    }

    private void countNeighborMines() {
        int[] searchX = {-1, 0, 1, 0};
        int[] searchY = {0, -1, 0, 1};

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < 4; k++) {
                    int newX = i + searchX[k];
                    int newY = j + searchY[k];

                    if (checkValidArea(newX, newY)) {
                        if (buttons[newX][newY].isMine)
                            buttons[i][j].neighborMineCount++;
                    }
                }
            }
        }
    }

    private boolean checkValidArea(int newX, int newY) {
        return newX >= 0 && newY >= 0 && newX < 9 && newY < 9;
    }

    private void setMineButtonClickListener() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                clickButton(i, j);
            }
        }
    }

    private void clickButton(int x, int y){
        buttons[x][y].setOnClickListener(
                view -> {
                    if (CLICKTYPE){
                        if (!buttons[x][y].isFlag)
                            breakBlock(view, x, y);
                    }
                    else
                        toggleFlag(view);
                }
        );
    }

    private void toggleFlag(View view){
        ((BlockButton) view).toggleFlag();
    }

    private void breakBlock(View view, int x, int y){
        ((BlockButton) view).breakBlock();
        setTotalMines();
        if (((BlockButton) view).isMine) {
            // game over
        }else{
            breakNeighborBlocks(x, y);
        }
    }

    private void breakNeighborBlocks(int x, int y) {
        int[] searchX = {-1, 0, 1, 0};
        int[] searchY = {0, -1, 0, 1};

        for (int k = 0; k < 4; k++) {
            int newX = x + searchX[k];
            int newY = y + searchY[k];

            if (checkValidArea(newX, newY)) {
                if (!buttons[newX][newY].isMine && buttons[newX][newY].isClickable() && !buttons[newX][newY].isFlag)
                    breakBlock(buttons[newX][newY], newX, newY);
            }
        }
    }

    void setClickTypeBtnClickListener(){
        binding.btnMainToggle.setOnClickListener(view -> {
            if(CLICKTYPE){
                CLICKTYPE = false;
                binding.btnMainToggle.setText("Flag");
            }else{
                CLICKTYPE = true;
                binding.btnMainToggle.setText("Break");
            }
        });
    }

    void gameWin(){
        makeDialog("Game Win", "You Win");
    }

    void gameOver(){
        makeDialog("Game Over", "You Lose");
    }

    void makeDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            TOTAL_MINES_COUNT = 10;
            BLOCKS = 81;
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        });
        builder.show();
    }
}
