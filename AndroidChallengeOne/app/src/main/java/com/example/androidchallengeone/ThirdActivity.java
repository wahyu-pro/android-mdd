package com.example.androidchallengeone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThirdActivity extends AppCompatActivity {

    EditText email, password;
    Button login;
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static Pattern pattern;
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        email = findViewById(R.id.etxEmail);
        password = findViewById(R.id.etxPassword);
        login = findViewById(R.id.btn_login);
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(email.getText().toString());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().length()==0){
                    //jika form Email belum di isi / masih kosong
                    email.setError("Email diperlukan!");
                }else if(matcher.matches() == false){
                    email.setError("Gunakan format email");
                }else if(password.getText().toString().length()==0){
                    //jika form Passwrod belum di isi / masih kosong
                    password.setError("Password diperlukan!");
                }else if (password.getText().toString().length() > 8){
                    password.setError("tidak boleh lebih dari 8 karakter");
                }else if (!password.getText().toString().matches("[A-Za-z0-9]+")){
                    password.setError("Password harus alphanumeric");
                }else {
                    Intent i = new Intent(ThirdActivity.this, HomeActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}