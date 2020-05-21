package com.example.dishycloud.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dishycloud.R;

public class EditAccountActivity extends AppCompatActivity {
    private Button mBtnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        initView();
    }

    private void initView() {
        mBtnConfirm = findViewById(R.id.btn_update_account);
    }

    public void clickToAccount() {
        finish();
    }
}
