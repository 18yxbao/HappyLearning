package com.example.happylearning.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happylearning.API.RegisterAPI;
import com.example.happylearning.R;


public class RegisterActivity extends AppCompatActivity {

    private TextView T_Title;
    private Button B_sumbit;
    private EditText T_account;
    private EditText T_password;
    private EditText T_password2;

    private int account_type = 0;
    private String user;
    private String pwd;
    private String pwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        account_type = getIntent().getIntExtra("account_type", -1);

        T_Title = findViewById(R.id.register_title);
        T_account = (EditText) findViewById(R.id.register_account);
        T_password = (EditText) findViewById(R.id.register_password);
        T_password2 = (EditText) findViewById(R.id.register_password2);
        B_sumbit = (Button) findViewById(R.id.register_submit);


        if (account_type == 0) {
            T_Title.setText("学生注册");
        } else {
            T_Title.setText("教师注册");
        }
        B_sumbit.setOnClickListener(B_sumbit_click);
    }

    private View.OnClickListener B_sumbit_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            user = T_account.getText().toString();
            pwd = T_password.getText().toString();
            pwd2 = T_password2.getText().toString();
            if (!pwd.equals(pwd2)) {
                Toast.makeText(RegisterActivity.this,
                        "两次密码不一致！", Toast.LENGTH_SHORT).show();
            }
            else {
                ATask atask = new ATask();
                atask.execute();
            }
        }
    };

    private class ATask extends AsyncTask<RegisterAPI, RegisterAPI, RegisterAPI> {
        //后台线程执行时
        @Override
        protected RegisterAPI doInBackground(RegisterAPI... params) {
            RegisterAPI register = new RegisterAPI(user, pwd, account_type);
            return register;
        }

        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
        @Override
        protected void onPostExecute(RegisterAPI register) {
            super.onPostExecute(register);
            String result = register.getResponseData();
            if (result.equals("success")) {
                Toast.makeText(RegisterActivity.this,
                        "注册成功！", Toast.LENGTH_SHORT).show();
                RegisterActivity.this.finish();
            } else if (result.equals("fail")) {
                Toast.makeText(RegisterActivity.this,
                        "该用户名已注册！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

