package com.example.mindscapetask;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextInputLayout tiuser,tipass;
    TextInputEditText etuser,etpass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tiuser=findViewById(R.id.user);
        tipass=findViewById(R.id.password);

        etuser=findViewById(R.id.etuser);
        etpass=findViewById(R.id.etpass);

        button=findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loginvalidation();
            }
        });

    }
    public void loginvalidation(){
        String username=etuser.getText().toString();
        String password=etpass.getText().toString();
         
        tiuser.setError(null);
        tipass.setError(null);

        if(username.isEmpty()){
            tiuser.setError("requied");

        } else if(password.isEmpty()) {
            tipass.setError("reuired");
        }else{
            Intent intent=new Intent(MainActivity.this, SignUp.class);
            startActivity(intent);
        }

    }
}