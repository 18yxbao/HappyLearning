package com.example.happylearning.Login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happylearning.R;
import com.example.happylearning.Student.MainActivity;


public class LoginActivity extends AppCompatActivity {

    private TextView T_Title;
    private Button B_register;
    private Button B_login;
    private Button B_change;
    private EditText T_account;
    private EditText T_password;
    private int account_type =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IsPass();//是否跳过

        setContentView(R.layout.activity_login);
        B_login =(Button) findViewById(R.id.login_login);
        B_register =(Button) findViewById(R.id.login_register);
        B_change =(Button) findViewById(R.id.login_change);
        T_account =(EditText) findViewById(R.id.login_account);
        T_password =(EditText) findViewById(R.id.login_password);
        T_Title= (TextView) findViewById(R.id.login_title);


        //测试用
        //T_account.setText("不改按下即可登陆");

        B_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user = T_account.getText().toString();
                final String pwd = T_password.getText().toString();

                LoginAPI login = new LoginAPI(user,pwd,account_type);
                login.start();
                try {
                    login.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String login_result = login.getResponseData();
                Log.d("LoginTest", "onClick: "+login_result);

                if(login_result.equals("success"))
                {
                    SharedPreferences.Editor editor = getSharedPreferences("logindate", MODE_PRIVATE).edit();
                    editor.putBoolean("islogin", true);
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }
                else if(login_result.equals("fail"))
                {
                    Toast.makeText(LoginActivity.this,
                            "输入密码错误！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        B_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("account_type",account_type);
                startActivity(intent);
            }
        });

        B_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //B_teacher.setVisibility(View.INVISIBLE);
                if(account_type ==0) {
                    account_type =1;
                    T_Title.setText("教师登陆");
                    B_change.setText("学生端登陆");
                }
                else{
                    account_type =0;
                    T_Title.setText("学生登陆");
                    B_change.setText("教师端登陆");
                }

            }
        });


    }

    private void IsPass(){
        SharedPreferences sprfMain= getSharedPreferences("logindate",MODE_PRIVATE);
        if(sprfMain.getBoolean("islogin",false)){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        }
    }

    @Override
    public void onBackPressed() {

    }


}
