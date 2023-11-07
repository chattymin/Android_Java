package com.chattymin.mineswipperproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.chattymin.mineswipperproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private BlockButton[][] buttons = new BlockButton[9][9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        onClick();
    }

    private void init() {
        TableLayout table = binding.table;
        buttons = new BlockButton[9][9];

        for (int i = 0; i < 9; i++) {
            TableRow tableRow = new TableRow(this);

            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1.0f
            );

            for (int j = 0; j < 9; j++) {
                buttons[i][j] = new BlockButton(this, i, j);

                buttons[i][j].setLayoutParams(layoutParams);
                tableRow.addView(buttons[i][j]);
            }

            table.addView(tableRow);
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
