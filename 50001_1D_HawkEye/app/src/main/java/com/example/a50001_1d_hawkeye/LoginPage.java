package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    private EditText LoginId;
    private EditText Password;
    private Button Login;
    private ImageButton Exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginId = findViewById(R.id.editStudentId);
        Password = findViewById(R.id.editPassword);
        Login = findViewById(R.id.buttonLogin);
        Exit = findViewById(R.id.exitButton);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(LoginId.getText().toString(),Password.getText().toString());
            }
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });


    }

    private void validate(String userId, String userPassword){
        if (userId.equals("1003565") && userPassword.equals("12345")){
            Intent intent = new Intent(LoginPage.this, SearchType.class);
            userId="";
            userPassword="";
            startActivity(intent);


        }
        else if (userId.length()==0) {
            Toast.makeText(LoginPage.this, "Please key in a valid Student ID", Toast.LENGTH_SHORT).show();
        }
        else if (userPassword.length()==0) {
            Toast.makeText(LoginPage.this, "Please key in a valid Password", Toast.LENGTH_SHORT).show();
        }
        else if (!userId.equals("1003565") || !userPassword.equals("12345")){
            Toast.makeText(LoginPage.this, "Invalid Student ID/Password", Toast.LENGTH_SHORT).show();
        }
    }
}
