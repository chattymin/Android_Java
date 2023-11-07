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
        if (isMine) {
            MainActivity.totalMines--;
            super.setClickable(false);
            super.setText(String.valueOf(neighborMineCount));
            super.setBackgroundColor(Color.RED);
        } else {
            super.setClickable(false);
            super.setText(String.valueOf(neighborMineCount));
            super.setBackgroundColor(Color.LTGRAY);
        }
    }
}
