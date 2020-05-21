package com.example.dishycloud.activities;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dishycloud.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText txt_email, txt_password, txt_confirm_password;
    private TextView btn_login_in;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();
        initData();
    }

    private void initView() {
        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);
        txt_confirm_password = findViewById(R.id.txt_confirm_password);
        btn_login_in = findViewById(R.id.btn_login_in);

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

        txt_confirm_password.setTransformationMethod(new PasswordTransformationMethod());
        txt_confirm_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    txt_confirm_password.setText("");
                } else {
                    txt_confirm_password.setText("Confirm password");
                }
            }
        });
    }

    private void initData() {
        btn_login_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
