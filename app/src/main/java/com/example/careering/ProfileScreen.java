package com.example.careering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;

public class ProfileScreen extends Base {

    EditText name;
    EditText username;
    EditText password;

    ParseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        name = findViewById(R.id.nameTextProfile);
        username = findViewById(R.id.usernameTextProfile);
        password = findViewById(R.id.passwordProfile);
        currentUser = ParseUser.getCurrentUser();

        name.setText(currentUser.getString("name"));
        username.setText(currentUser.getUsername());

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout3);
        constraintLayout.setOnClickListener(this);
    }

    public void saveProfileInfo(View view)
    {
        try
        {
            currentUser.setUsername(username.getText().toString());
            currentUser.put("name", name.getText().toString());
            password.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                currentUser.setPassword(password.getText().toString());
                return true;
            }
            });
            currentUser.saveInBackground();

            Toast.makeText(getApplicationContext(), R.string.changed_success, Toast.LENGTH_SHORT).show();

        }
        catch (Exception exception)
        {
            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
