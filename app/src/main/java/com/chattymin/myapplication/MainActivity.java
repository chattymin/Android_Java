package com.chattymin.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
}