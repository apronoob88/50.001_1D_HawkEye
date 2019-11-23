package com.example.a50001_1d_hawkeye;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    private EditText LoginId;
    private EditText Password;
    private Button Login;
    private ImageButton Exit;

    private DatabaseReference reffStudent;
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
        final String id=userId;
        final String password = userPassword;
        reffStudent = FirebaseDatabase.getInstance().getReference().child("Students");
        if (userId.length()==0) {
            Toast.makeText(LoginPage.this, "Please key in a valid Student ID", Toast.LENGTH_SHORT).show();
        }
        else if (userPassword.length()==0) {
            Toast.makeText(LoginPage.this, "Please key in a valid Password", Toast.LENGTH_SHORT).show();
        }

        reffStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (id.length()==0){
                    Toast.makeText(LoginPage.this, "Please key in a Student ID", Toast.LENGTH_SHORT).show();

                }
                else if(password.length()==0){
                    Toast.makeText(LoginPage.this, "Please key in a Password", Toast.LENGTH_SHORT).show();

                }
                else if (dataSnapshot.child(id).getValue()==null ){
                    Toast.makeText(LoginPage.this, "Invalid Student ID", Toast.LENGTH_SHORT).show();

                }
                else if(dataSnapshot.child(id).child("password").getValue().toString().equals(password)==false){
                    Toast.makeText(LoginPage.this, "Invalid Password", Toast.LENGTH_SHORT).show();

                }
                else if(dataSnapshot.child(id).child("password").getValue().toString().equals(password)){
                    Intent intent = new Intent(LoginPage.this, SearchType.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
