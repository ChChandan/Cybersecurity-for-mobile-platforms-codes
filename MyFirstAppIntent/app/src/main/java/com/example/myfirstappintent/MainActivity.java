package com.example.myfirstappintent;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends Activity {
    EditText edtTxt;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTxt = (EditText) findViewById(R.id.editText1);
        btnSend = (Button) findViewById(R.id.button1);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make new intent to Hello Activity
                Intent intent = new Intent(MainActivity.this, HelloActivity.class);

                // Declare message and get the string from edtTxt
                String message = edtTxt.getText().toString();

                // Put the message in the intent to send to the next activity
                intent.putExtra("data", message);

                // Start the Hello Activity
                startActivity(intent);
            }
        });
    }
}
