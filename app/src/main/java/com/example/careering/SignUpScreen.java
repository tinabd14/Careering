package com.example.careering;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpScreen extends Base {

    EditText name;
    EditText username;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_screen);

        name = findViewById(R.id.nameTextProfile);
        username = findViewById(R.id.usernameSignUpText);
        email = findViewById(R.id.emailSignUp);
        password = findViewById(R.id.passwordProfile);

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout2);
        constraintLayout.setOnClickListener(this);
    }

    public void signUpUser(View view) {
        if (!isConnected(SignUpScreen.this))
            Toast.makeText(getApplicationContext(), "No Internet Connection...\nPlease, check your internet connection", Toast.LENGTH_LONG).show();
        else {
            email.setError(null);
            password.setError(null);

            ParseUser user = new ParseUser();
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            user.setEmail(email.getText().toString());
            user.put("name", name.getText().toString());
            user.put("email2", email.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        ParseUser.logOut();
                        Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.word_accCreated), Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), LoginScreen.class);
                        startActivity(i);
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.word_error) + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
