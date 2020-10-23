package com.example.happylearning.Teacher;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.happylearning.API.CreateClassAPI;
import com.example.happylearning.API.JoinClassAPI;
import com.example.happylearning.Bean.Classes;
import com.example.happylearning.Data.Filedata;
import com.example.happylearning.R;
import com.example.happylearning.Student.HomeFragment;
import com.example.happylearning.Student.MessageFragment;
import com.example.happylearning.Student.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class TeacherMainActivity extends AppCompatActivity {

    private FragmentManager fm = this.getFragmentManager();
    private SettingFragment mSettingFragment;
    private HomeFragment mHomeFragment;
    private MessageFragment mmessageFragment;
    private Toolbar toolbar;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Teacher123", "onCreate: 1");
        str = Filedata.load("name",getApplicationContext());

        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.main_toolbar);
        setHomeFragment();
        setSupportActionBar(toolbar);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_class,menu); // 参数1为布局文件(menu_main.xml)
        return true;
    }

    //Toolbar item 按键响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_create_class_item1:
                alert_edit();
                break;
            default:
                break;
        }
        return true;
    }

    //下导航栏点击事件
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setHomeFragment();
                    return true;
                case R.id.navigation_dashboard:
                    setMessageFragment();
                    return true;
                case R.id.navigation_notifications:
                    setSettingFragment();
                    return true;
            }
            return false;
        }
    };

    //输入课程数据对话框
    private void alert_edit(){
        final LinearLayout layout = new LinearLayout(this);
        final EditText et = new EditText(this);
        final EditText et2 = new EditText(this);
        et.setHint("课程名                                        ");
        et2.setHint("验证码                                        ");
        layout.addView(et);
        layout.addView(et2);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        new AlertDialog.Builder(this)
                .setTitle("创建课程")
                .setView(layout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String class_name = et.getText().toString();
                        String class_password = et2.getText().toString();
                        if (class_name.equals("")) {
                            Toast.makeText(getApplicationContext(), "不能为空！" + class_name, Toast.LENGTH_LONG).show();
                        }
                        else {
                            ATask asyncTask = new ATask(class_name,class_password,str);
                            asyncTask.execute();

                        }
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void setHomeFragment() {
        toolbar.getMenu().clear();
        getMenuInflater().inflate(R.menu.menu_create_class,toolbar.getMenu());
        toolbar.setBackgroundResource(R.color.color_background_grey);
        toolbar.setTitle("教师端");
        FragmentTransaction transaction = fm.beginTransaction();
        mHomeFragment = new HomeFragment(str);
        transaction.replace(R.id.main_tb, mHomeFragment);
        transaction.commit();
    }

    private void setMessageFragment() {
        toolbar.getMenu().clear();
        toolbar.setBackgroundResource(R.color.color_background_grey);
        toolbar.setTitle("消息");
        FragmentTransaction transaction = fm.beginTransaction();
        mmessageFragment = new MessageFragment();
        transaction.replace(R.id.main_tb, mmessageFragment);
        transaction.commit();
    }

    private void setSettingFragment() {
        toolbar.getMenu().clear();
        toolbar.setBackgroundResource(R.color.color_white);
        toolbar.setTitle("");
        FragmentTransaction transaction = fm.beginTransaction();
        mSettingFragment = new SettingFragment(str);
        transaction.replace(R.id.main_tb, mSettingFragment);
        transaction.commit();
    }

    //创建课程请求异步处理
    private class ATask extends AsyncTask<CreateClassAPI,CreateClassAPI ,CreateClassAPI > {
        //后台线程执行时
        private String class_name ;
        private String class_password ;
        private String account;
        public ATask(String class_name,String class_password,String account){
            this.class_name=class_name;
            this.class_password=class_password;
            this.account=account;
        }
        @Override
        protected CreateClassAPI doInBackground(CreateClassAPI... params) {
            CreateClassAPI createClassAPI = new CreateClassAPI("123456",class_password,class_name,account);
            return createClassAPI;
        }
        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
        @Override
        protected void onPostExecute(CreateClassAPI result) {
            super.onPostExecute(result);
            String login_result = result.getResponseData();
            Toast.makeText(getApplicationContext(), login_result, Toast.LENGTH_SHORT).show();
            Log.d("TAG123456", "onPostExecute: "+login_result);


        }
    }


}