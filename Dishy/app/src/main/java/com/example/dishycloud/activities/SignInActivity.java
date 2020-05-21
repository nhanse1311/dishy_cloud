package com.example.dishycloud.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dishycloud.R;

public class SignInActivity extends AppCompatActivity {

    private EditText txt_email, txt_password;
    private Button btn_SignIn;
    private TextView btn_SignUp;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initView();
        initData();
    }

    private void initView() {
        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);
        btn_SignIn = findViewById(R.id.btn_sign_in);
        btn_SignUp = findViewById(R.id.btn_sign_up);

        txt_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    txt_email.setText("");
                } else {
                    txt_email.setText("Enter password");
                }
            }
        });

        txt_password.setTransformationMethod(new PasswordTransformationMethod());

        txt_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    txt_password.setText("");
                } else {
                    txt_password.setText("Enter password");
                }
            }
        });
    }

    private void initData() {
        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void clickToHome(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
