package com.example.happylearning.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.happylearning.API.LoginAPI;
import com.example.happylearning.Bean.UserInfo;
import com.example.happylearning.Data.AccountUtil;
import com.example.happylearning.Data.Util;
import com.example.happylearning.R;
import com.example.happylearning.Student.main.MainActivity;
import com.example.happylearning.Teacher.TeacherMainActivity;

import okhttp3.OkHttpClient;


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
    public static OkHttpClient client = new OkHttpClient();
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
                    user = T_account.getText().toString();
                    pwd = T_password.getText().toString();

                    ATask_LoginAPI ak = new ATask_LoginAPI();
                    ak.execute();
                    break;
            }
        }
    };

    private class ATask_LoginAPI extends AsyncTask<LoginAPI,LoginAPI,LoginAPI > {

        //后台线程执行时
        @Override
        protected LoginAPI doInBackground(LoginAPI... params) {
            LoginAPI login = new LoginAPI(user, pwd, account_type);
            Log.d("Login", "doInBackground: "+login.getResponseData());
            return login;
        }

        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
        @Override
        protected void onPostExecute(LoginAPI result) {
            super.onPostExecute(result);
            Log.d("Login", "onPostExecute: "+result.getResponseData());
            String login_result = result.getResponseData();
            if(login_result.equals("")){
                Toast.makeText(LoginActivity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
            }
            else if (login_result.equals("success")) {

                //文件储存
                AccountUtil.setLog(getApplicationContext(),true);
                AccountUtil.setAccount(getApplicationContext(),user);
                AccountUtil.setAccount_type(getApplicationContext(),account_type);

                ATask_Login aTask_login = new ATask_Login();
                aTask_login.execute();
            }
            else if (login_result.equals("fail")) {
                Toast.makeText(LoginActivity.this,
                        "密码错误！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class ATask_Login extends AsyncTask<UserInfo,UserInfo,UserInfo > {

        //后台线程执行时
        @Override
        protected UserInfo doInBackground(UserInfo... params) {
            String account = AccountUtil.getAccount(getApplicationContext());
            String account_type = AccountUtil.getAccount_type(getApplicationContext());
            UserInfo result = Util.getUserInfo(getApplicationContext(),account, account_type);
            Log.d("doInBackground", "doInBackground: "+result.toString());
            if(result!=null) {
                AccountUtil.setUserInfo(getApplicationContext(), result.getName(), result.getUserIco(), result.getSchoolId(),
                        result.getMajor(), result.getSchool(), result.getGender());
            }
            return  result;
        }

        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
        @Override
        protected void onPostExecute(UserInfo result) {
            super.onPostExecute(result);
            Intent intent=null;
            if(AccountUtil.getAccount_type(getApplicationContext()).equals("0")) {
                intent = new Intent(LoginActivity.this, MainActivity.class);
            }
            else {
                intent = new Intent(LoginActivity.this, TeacherMainActivity.class);
            }
            startActivity(intent);
            finish();
        }
    }

    //是否跳过登陆
    private void IsPass() {
        if (AccountUtil.getLog(getApplicationContext())) {
            ATask_Login aTask_login = new ATask_Login();
            aTask_login.execute();
        }
    }



    @Override
    public void onBackPressed() {

    }



}
