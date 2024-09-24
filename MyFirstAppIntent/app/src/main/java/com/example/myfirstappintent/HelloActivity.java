package com.example.myfirstappintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Date;
import static java.text.DateFormat.getDateInstance;

public class HelloActivity extends Activity {
    TextView txtV;
    Button btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        txtV = (TextView) findViewById(R.id.textView1);
        btnMain = (Button) findViewById(R.id.btMain);

        // Get the Intent from Main and get the string from the message name "data"
        final Intent intent = getIntent();
        String message = intent.getStringExtra("data");

        // Display the text view with a greeting and today's date
        String currentDateTimeString = getDateInstance().format(new Date());
        txtV.setText("Hello! " + message + "\nToday is " + currentDateTimeString);

        // Go back to the main activity when the button is clicked
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HelloActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}
