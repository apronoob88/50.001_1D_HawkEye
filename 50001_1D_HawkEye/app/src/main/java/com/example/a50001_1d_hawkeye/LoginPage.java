package com.example.a50001_1d_hawkeye;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
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

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginPage.this);
                builder.setTitle("Exit");
                builder.setMessage("Do you wanna exit App??");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                        System.exit(0);

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(LoginId.getText().toString(),Password.getText().toString());
            }
        });
    }

    private void validate(String userId, String userPassword){
        final String id=userId;
        final String password = userPassword;
        reffStudent = FirebaseDatabase.getInstance().getReference().child("Students");
        reffStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

                if (id.length()==0){
                    Toast.makeText(LoginPage.this, "Please key in your Student ID", Toast.LENGTH_SHORT).show();
                    vibrator.vibrate(500);

                }
                else if(password.length()==0){
                    Toast.makeText(LoginPage.this, "Please key in your Password", Toast.LENGTH_SHORT).show();
                    vibrator.vibrate(500);

                }
                else if (dataSnapshot.child(id).getValue()==null ){
                    Toast.makeText(LoginPage.this, "Invalid Student ID", Toast.LENGTH_SHORT).show();
                    vibrator.vibrate(500);
                    Password.getText().clear();
                    LoginId.getText().clear();

                }
                else if(dataSnapshot.child(id).child("password").getValue().toString().equals(password)==false){
                    Toast.makeText(LoginPage.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    vibrator.vibrate(500);
                    LoginId.getText().clear();
                    Password.getText().clear();

                }
                else if(dataSnapshot.child(id).child("password").getValue().toString().equals(password)){
                    Intent intent = new Intent(LoginPage.this, SearchType.class);
                    LoginId.getText().clear();
                    Password.getText().clear();
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
