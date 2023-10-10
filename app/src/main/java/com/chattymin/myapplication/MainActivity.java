package com.chattymin.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chattymin.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        calc();

    }

    private void init() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private void calc() {
        binding.buttonPlus.setOnClickListener(view -> {
            String plusNum = String.valueOf(Integer.parseInt(binding.editTextFirstNum.getText().toString()) + Integer.parseInt(binding.editTextSecondNum.getText().toString()));
            setText(plusNum);
            keyboardHide();
            focuseClear();
        });
        binding.buttonMinus.setOnClickListener(view -> {
            String minusNum = String.valueOf(Integer.parseInt(binding.editTextFirstNum.getText().toString()) - Integer.parseInt(binding.editTextSecondNum.getText().toString()));
            setText(minusNum);
            keyboardHide();
            focuseClear();
        });
        binding.buttonMultiply.setOnClickListener(view -> {
            String multiplyNum = String.valueOf(Integer.parseInt(binding.editTextFirstNum.getText().toString()) * Integer.parseInt(binding.editTextSecondNum.getText().toString()));
            setText(multiplyNum);
            keyboardHide();
            focuseClear();
        });
        binding.buttonDivide.setOnClickListener(view -> {
            String divideNum;

            try {
                divideNum = String.valueOf(Integer.parseInt(binding.editTextFirstNum.getText().toString()) / Integer.parseInt(binding.editTextSecondNum.getText().toString()));
            }catch (Exception e){
                divideNum = "-";
                makeToast("0으로 나눌 수 없습니다.");
            }

            setText(divideNum);
            keyboardHide();
            focuseClear();
        });
    }

    private void setText(String text) {
        binding.textViewResult.setText("Result : " + text);
    }

    private void keyboardHide() {
        if (imm != null)
            imm.hideSoftInputFromWindow(binding.getRoot().getWindowToken(), 0);
    }

    private void focuseClear() {
        if (getCurrentFocus() != null)
            getCurrentFocus().clearFocus();
    }

    private void makeToast(String text){
        Toast.makeText(
                this,
                text,
                Toast.LENGTH_SHORT
        ).show();
    }

    /*
    private void getID(){
        binding.buttonID.setOnClickListener(view -> {
            EditText editTextID = binding.editTextID;
            String id = editTextID.getText().toString();

            keyboardHide();
            focuseClear();

            binding.textViewID.setText(id);
            makeToast(id);
        });
    }

     */

    /*
//    class ClickListener implements View.OnClickListener{
//        @Override
//        public void onClick(View view) {
//            Toast.makeText(MainActivity.this, "Click!", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button1);
        //ClickListener clickListener = new ClickListener();
        //button.setOnClickListener(clickListener);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Click!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickButton(View view) {
        Toast.makeText(this, "CLick!", Toast.LENGTH_SHORT).show();

        TextView textView = findViewById(R.id.textView);
        textView.setText("Changed!");
    }

    public void onClickButton2(View view) {
        Toast.makeText(this, "Change Text", Toast.LENGTH_SHORT).show();

        TextView textView = findViewById(R.id.textView2);
        textView.setText("Changed!");
        textView.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(30);
    }

    public void onClickButton3(View view) {
        Toast.makeText(this, "Change Text", Toast.LENGTH_SHORT).show();

        TextView textView = findViewById(R.id.textView4);
        textView.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        textView.setTextColor(Color.rgb(255, 0, 0));
        textView.setSingleLine();
    }

     */
}