package com.chattymin.mineswipperproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.chattymin.mineswipperproject.databinding.ActivityMainBinding;

import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private BlockButton[][] buttons = new BlockButton[9][9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        countNeighborMines();
        onClick();
    }

    private void init() {
        TableLayout table = binding.table;
        buttons = new BlockButton[9][9];

        Set<Pair<Integer, Integer>> mineSet = new HashSet<>();
        setMines(mineSet);

        for (int i = 0; i < 9; i++) {
            TableRow tableRow = new TableRow(this);

            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1.0f
            );

            for (int j = 0; j < 9; j++) {
                if (mineSet.contains(new Pair<>(i, j)))
                    buttons[i][j] = new BlockButton(this, i, j, true);
                else
                    buttons[i][j] = new BlockButton(this, i, j, false);

                buttons[i][j].setLayoutParams(layoutParams);
                tableRow.addView(buttons[i][j]);
            }

            table.addView(tableRow);
        }
    }
    
    private void setMines(@NonNull Set<Pair<Integer, Integer>> mineSet){
        while (mineSet.size() < 10) {
            int x = (int) (Math.random() * 9);
            int y = (int) (Math.random() * 9);

            mineSet.add(new Pair<>(x, y));
        }
    }

    private void countNeighborMines(){
        int[] searchX = {-1, 0, 1, 0};
        int[] searchY = {0, -1, 0, 1};

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 4; k++) {
                    int newX = i + searchX[k];
                    int newY = j + searchY[k];

                    if (newX >= 0 && newY >= 0 && newX < 9 && newY < 9) {
                        if (buttons[newX][newY].isMine)
                            buttons[i][j].neighborMineCount++;
                    }
                }
            }
        }
    }

    private void onClick(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                buttons[i][j].setOnClickListener(
                        view -> ((BlockButton)view).toggleFlag()
                );
            }
        }
    }
}
