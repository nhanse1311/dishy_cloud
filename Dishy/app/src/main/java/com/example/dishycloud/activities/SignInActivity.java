package com.example.dishycloud.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dishycloud.R;
import com.example.dishycloud.presenters.LoginPresenter;
import com.example.dishycloud.views.LoginView;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    private EditText mTxtUsername, mTxtPassword;
    private Button btn_SignIn;
    private LoginPresenter mLoginPresenter;
    private TextView btn_SignUp;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initView();
        initData();
    }

    private void initView() {
        mTxtUsername = findViewById(R.id.txt_username_login);
        mTxtPassword = findViewById(R.id.txt_password_login);
        btn_SignIn = findViewById(R.id.btn_sign_in);
        btn_SignUp = findViewById(R.id.btn_sign_up_login);
    }

    private void initData() {
        btn_SignIn.setOnClickListener(this);

        mLoginPresenter = new LoginPresenter(this,this);
    }


    private void login() {
        String username = mTxtUsername.getText().toString().trim();
        String password = mTxtPassword.getText().toString().trim();
        mLoginPresenter.onLogin(username,password);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:
                login();
                break;
        }
    }

    @Override
    public void onLoginSuccess() {
        Intent homeIntent = new Intent(SignInActivity.this, HomeActivity.class);
        this.startActivity(homeIntent);
        this.finish();
    }

    @Override
    public void onLoginFail(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }


}
