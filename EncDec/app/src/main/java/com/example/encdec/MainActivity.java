package com.example.encdec;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends Activity {
    EditText inputTxt, inputPw;
    Button encBt, decBt;
    String outputString, encryptedValue, decryptedValue;
    String AES = "AES";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTxt = (EditText) findViewById(R.id.inputText);
        inputPw = (EditText) findViewById(R.id.inputPass);
        encBt = (Button) findViewById(R.id.encBtn);
        // reflect for the decrypt button

        encBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(inputPw.getText().length()!=0 && inputTxt.getText().length()!=0){
                        outputString = encrypt(inputTxt.getText().toString(), inputPw.getText().toString());
                        Intent intent= new Intent(MainActivity.this,SecondScreen.class);
                        intent.putExtra("message",outputString);
                        intent.putExtra("password",inputPw.getText().toString());
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Please enter password",Toast.LENGTH_SHORT).show();

                    }


                    //outputTxt.setText(outputString);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this,"Please enter password",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

    }

    // Encrypt function that encrypts the message with key as the input password
    private String encrypt(String Data, String pass) throws Exception {
        SecretKeySpec key = generateKey(pass);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptedValue;
    }

    // generateKey will create key via AES encryption
    private SecretKeySpec generateKey(String pass) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = pass.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }

}
