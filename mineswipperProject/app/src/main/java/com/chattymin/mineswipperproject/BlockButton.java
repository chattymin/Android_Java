package com.chattymin.mineswipperproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.widget.Button;

@SuppressLint({"AppCompatCustomView", "ViewConstructor"})
public class BlockButton extends Button {
    int x;
    int y;
    boolean isMine;
    boolean isFlag;
    int neighborMineCount;
    static int flags;
    static int blocks;

    public BlockButton(Context context, int x, int y, boolean isMine) {
        super(context);
        this.x = x;
        this.y = y;
        this.isMine = isMine;
        this.isFlag = false;
        this.neighborMineCount = 0;
        flags = 0;
        blocks = 0;
    }

    public void toggleFlag(){
        if(isFlag){
            isFlag = false;
            super.setText("");
        }else{
            isFlag = true;
            super.setText("+");
        }
    }

    public void breakBlock(){
        MainActivity activity = (MainActivity) getContext();
        // flag이면 클릭 안되게 해야함.
        if (isMine) {
            MainActivity.TOTAL_MINES_COUNT--;
            super.setClickable(false);
            super.setText("X");
            super.setBackgroundColor(Color.RED);
            activity.gameOver();
        } else {
            MainActivity.BLOCKS--;
            super.setClickable(false);
            super.setBackgroundColor(Color.LTGRAY);

            if (neighborMineCount == 0){
                super.setText("");
            }else{
                super.setText(String.valueOf(neighborMineCount));
            }

            if (MainActivity.BLOCKS == MainActivity.TOTAL_MINES_COUNT) {
                activity.gameWin();
            }
        }
    }
}
