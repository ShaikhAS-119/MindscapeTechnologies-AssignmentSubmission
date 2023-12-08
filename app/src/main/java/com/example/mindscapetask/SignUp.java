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

public class SignUp extends AppCompatActivity {
    Button button;
    TextInputLayout tiuser,tipass,ticpass,timail;
    TextInputEditText etuser,etpass,etcpass,etmail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tiuser=findViewById(R.id.user);
        tipass=findViewById(R.id.password);
        timail=findViewById(R.id.email);
        ticpass=findViewById(R.id.cnfpassword);

        etuser=findViewById(R.id.etname);
        etpass=findViewById(R.id.etpass);
        etmail=findViewById(R.id.etmail);
        etcpass=findViewById(R.id.etcpass);

        button=findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpValidation();
            }
        });
    }
    public void signUpValidation(){

        String fullName = etuser.getText().toString().trim();
        String email = etmail.getText().toString().trim();
        String password = etpass.getText().toString();
        String confirmPassword = etcpass.getText().toString();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);

        tiuser.setError(null);
        timail.setError(null);
        tipass.setError(null);
        ticpass.setError(null);

        if(password.toString().length()>8 && password.matches(matcher.toString())){

        }else{
            tipass.setError("not valid password");
            button.setEnabled(false);
        }

        if(!email.matches(emailPattern)){
            timail.setError("Email pattern invalid");
        }
        if (fullName.isEmpty()){
            tiuser.setError("required");
        }
        else if (email.isEmpty()){
            timail.setError("required");
        }
        else if (password.isEmpty()){
            tipass.setError("required");
        }
        else if (confirmPassword.isEmpty()){
            ticpass.setError("required");
        }

    }
}