package com.example.happylearning.Login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happylearning.API.LoginAPI;
import com.example.happylearning.Data.Filedata;
import com.example.happylearning.R;
import com.example.happylearning.Student.MainActivity;
import com.example.happylearning.Teacher.TeacherMainActivity;


public class LoginActivity<click> extends AppCompatActivity {

    private TextView T_Title;
    private Button B_register;
    private Button B_login;
    private Button B_change;
    private EditText T_account;
    private EditText T_password;
    private String account_type ="0";
    private String user;
    private String pwd;

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

        B_register.setOnClickListener(click);
        B_change.setOnClickListener(click);
        B_login.setOnClickListener(click);
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            switch(view.getId()){
                case R.id.login_register:

                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    intent.putExtra("account_type",account_type);
                    startActivity(intent);
                    break;
                case R.id.login_change:

                    if(account_type.equals("0")) {
                        account_type ="1";
                        T_Title.setText("教师登陆");
                        B_change.setText("学生端登陆");
                    }
                    else{
                        account_type ="0";
                        T_Title.setText("学生登陆");
                        B_change.setText("教师端登陆");
                    }
                    break;
                case R.id.login_login:
                    aTask ak = new aTask();
                    ak.execute();
                    break;
            }
        }
    };

    private class aTask extends AsyncTask<LoginAPI,LoginAPI,LoginAPI > {

        //后台线程执行时
        @Override
        protected LoginAPI doInBackground(LoginAPI... params) {
            user = T_account.getText().toString();
            pwd = T_password.getText().toString();
            LoginAPI login = new LoginAPI(user, pwd, account_type);
            return login;
        }

        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
        @Override
        protected void onPostExecute(LoginAPI result) {
            super.onPostExecute(result);
            String login_result = result.getResponseData();
            if(login_result.equals("")){
                Toast.makeText(LoginActivity.this,
                        "连接服务器失败！"+result, Toast.LENGTH_SHORT).show();
            }
            else if (login_result.equals("success")) {
                SharedPreferences.Editor editor = getSharedPreferences("logindate", MODE_PRIVATE).edit();
                editor.putBoolean("islogin", true);

                //文件储存
                Filedata.save("name", user, getApplicationContext());
                Intent intent=null;

                if(account_type.equals("0")) {
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    editor.putString("account_type", account_type);
                    Log.d("login TAG", "IsPass: 1");
                }
                else{
                    intent = new Intent(LoginActivity.this, TeacherMainActivity.class);
                    editor.putString("account_type", account_type);
                    Log.d("login TAG", "IsPass: 2");
                }
                editor.apply();
                startActivity(intent);
                LoginActivity.this.finish();
            }
            else if (login_result.equals("fail")) {
                Toast.makeText(LoginActivity.this,
                        "输入密码错误！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //是否跳过登陆
    private void IsPass(){
        SharedPreferences sprfMain= getSharedPreferences("logindate",MODE_PRIVATE);
        Log.d("login TAG", "IsPass: 0");
        if(sprfMain.getBoolean("islogin",false)){
            Log.d("login TAG", "IsPass: 1");
            Intent intent=null;
            if(sprfMain.getString("account_type","0").equals("0")) {
                intent = new Intent(LoginActivity.this, MainActivity.class);
                Log.d("login TAG", "IsPass: 3");
            }
            else {
                intent = new Intent(LoginActivity.this, TeacherMainActivity.class);
                Log.d("login TAG", "IsPass: 4");
            }
            startActivity(intent);
            LoginActivity.this.finish();
        }
    }

    @Override
    public void onBackPressed() {

    }








}
