package com.example.careering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class ForgetPasswordScreen extends AppCompatActivity {

    EditText email;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        email = (EditText) findViewById(R.id.emailText);
        submit = (Button) findViewById(R.id.sendCodeButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ParseUser.requestPasswordResetInBackground(email.getText().toString(),
                new RequestPasswordResetCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(ForgetPasswordScreen.this, "Password is sent to your email.", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ForgetPasswordScreen.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            );
            }
        });
    }
}
