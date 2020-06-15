package com.example.dishycloud.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dishycloud.R;
import com.example.dishycloud.presenters.RegisterPresenter;
import com.example.dishycloud.views.RegisterView;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, RegisterView {

    private EditText txt_username, txt_password, txt_confirm_password, txt_fullname;
    private Button btn_sign_up;
    private RegisterPresenter mRegisterPresenter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
        initData();
    }

    private void initView() {
        txt_username = findViewById(R.id.txt_sua_username);
        txt_fullname = findViewById(R.id.txt_sua_fullname);
        txt_password = findViewById(R.id.txt_sua_password);
        txt_confirm_password = findViewById(R.id.txt_sua_confirm_password);
        btn_sign_up = findViewById(R.id.btn_sign_up);
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmPassword();
            }
        });

    }

    private void confirmPassword(){
        if(!txt_password.getText().toString().equals(txt_confirm_password.getText().toString())){
            showAlertDialogCheckPassword();
        }else{
            register();
        }
    }

    private void initData() {
        mRegisterPresenter = new RegisterPresenter(this, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up:
                register();
                break;
        }
    }

    private void register() {
        String username = txt_username.getText().toString().trim();
        String password = txt_password.getText().toString().trim();
        String fullname = txt_fullname.getText().toString().trim();
        if(username.equals("") || password.equals("") || fullname.equals("")){
            showAlertDialogFail();
        }
        mRegisterPresenter.registerAccount(username, password, fullname);
    }


    public void showAlertDialogCheckPassword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Mật khẩu nhập lại không đúng!");
        builder.setCancelable(false);
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                txt_password.setText("");
                txt_confirm_password.setText("");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showAlertDialogSuccess() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Đăng ký thành công");
        builder.setCancelable(false);
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showAlertDialogFail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Đăng kí thất bại !!");
        builder.setCancelable(false);
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                txt_username.setText("");
                txt_username.findFocus();
                txt_fullname.setText("");
                txt_password.setText("");
                txt_confirm_password.setText("");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public void onRegisterSuccess() {
        showAlertDialogSuccess();
    }

    @Override
    public void onRegisterFail(String message) {
        showAlertDialogFail();
    }
}
