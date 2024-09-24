package com.example.firstapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView ;
import static java.text.DateFormat.getDateInstance;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText text1;
    TextView view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1=(EditText) findViewById(R.id.editText1);
        view1 = (TextView) findViewById (R.id.textView1) ;
    }
    public void onclick ( View view ) {
        String currentDateTimeString = getDateInstance().format(new Date());
        view1.setText("Hello !"+text1.getText().toString()+"\n Today is"+currentDateTimeString);
    }
}