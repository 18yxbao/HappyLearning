package com.example.happylearning.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happyleaning.R;

public class RegisterActivity extends AppCompatActivity {

    private TextView T_Title;
    private Button B_sumbit;
    private EditText T_account;
    private EditText T_password;
    private EditText T_password2;

    private int account_type = 0;

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


        B_sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = T_account.getText().toString();
                String pwd = T_password.getText().toString();
                String pwd2 = T_password2.getText().toString();

                if (!pwd.equals(pwd2)) {
                    Toast.makeText(RegisterActivity.this,
                            "两次密码不一致！", Toast.LENGTH_SHORT).show();
                } else {
                    RegisterAPI register = new RegisterAPI(user, pwd);
                    register.start();
                    try {
                        register.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String result = register.getResponseData();
                    if (result.equals("success")) {
                        Toast.makeText(RegisterActivity.this,
                                "注册成功！", Toast.LENGTH_SHORT).show();
                    } else if (result.equals("fail")) {
                        Toast.makeText(RegisterActivity.this,
                                "该用户名已注册！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
