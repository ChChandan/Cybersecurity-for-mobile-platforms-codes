package com.example.encdec;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SecondScreen extends AppCompatActivity {
Button decBt,GobackBt;
TextView inputPw,outputTxt;
String decryptedtext;
String AES = "AES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second_screen);
        decBt=(Button) findViewById(R.id.decBtn);
        inputPw = (EditText) findViewById(R.id.inputPass);
        outputTxt = (TextView) findViewById(R.id.outputText);
        GobackBt = (Button) findViewById(R.id.GobackBtn);

        final Intent intent=getIntent();
        outputTxt.setText(intent.getStringExtra("message"));
        decBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    decryptedtext= Decrypt(outputTxt.getText().toString(),inputPw.getText().toString());
                    if((inputPw.getText().toString()).equals(intent.getStringExtra("password"))){
                        outputTxt.setText(decryptedtext);

                    }


                } catch (Exception e) {

                    Toast.makeText(SecondScreen.this,"Wrong Password",Toast.LENGTH_SHORT).show();

                }

            }
        });

        GobackBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("ERORRRRRR","Something is baddddd");

                Intent homepage= new Intent(SecondScreen.this,MainActivity.class);
                startActivity(homepage);

            }
        });




    }
    private SecretKeySpec generateKey(String psw) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = psw.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }
    private String Decrypt (String secrettext, String psw) throws Exception{
        SecretKeySpec key = generateKey ( psw ) ;
        Cipher c = Cipher . getInstance ( AES ) ;
        c . init ( Cipher . DECRYPT_MODE , key ) ;
        byte [] decodedValue = Base64. decode ( secrettext , Base64 . DEFAULT ) ;
        byte [] decValue = c . doFinal ( decodedValue ) ;
        String finaloutput = new String ( decValue ) ;
        return finaloutput ;
    }
}