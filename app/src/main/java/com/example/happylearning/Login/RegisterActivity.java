package com.example.happylearning.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.happyleaning.R;

public class RegisterActivity extends AppCompatActivity {

    private TextView T_Title;
    private Button B_sumbit;

    private EditText T_account;
    private EditText T_password;
    private EditText T_password2;

    private int account_type =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        account_type = getIntent().getIntExtra("account_type",-1);
        T_Title= findViewById(R.id.register_title);
        if(account_type==0){
            T_Title.setText("学生注册");
        }
        else{
            T_Title.setText("教师注册");
        }


    }
}
