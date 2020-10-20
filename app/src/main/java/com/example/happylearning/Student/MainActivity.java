package com.example.happylearning.Student;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;


import com.example.happylearning.API.JoinClassAPI;
import com.example.happylearning.Bean.Classes;
import com.example.happylearning.Data.Filedata;
import com.example.happylearning.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private FragmentManager fm = this.getFragmentManager();
    private SettingFragment mSettingFragment;
    private HomeFragment mHomeFragment;
    private MessageFragment mmessageFragment;
    private Toolbar toolbar;
    private List<Classes> classesList=null;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        getMenuInflater().inflate(R.menu.menu_join,menu); // 参数1为布局文件(menu_main.xml)
        return true;
    }

    //Toolbar item 按键响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_join_class_item1:
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
        et.setHint("课程号                                        ");
        et2.setHint("验证码                                        ");
        layout.addView(et);
        layout.addView(et2);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        new AlertDialog.Builder(this)
                .setTitle("课程号")
                .setView(layout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String input = et.getText().toString();
                        String input2 = et2.getText().toString();
                        if (input.equals("")) {
                            Toast.makeText(getApplicationContext(), "不能为空！" + input, Toast.LENGTH_LONG).show();
                        }
                        else {
                            ATask atask = new ATask(input,input2,str);
                            atask.execute();
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void setHomeFragment() {
        toolbar.getMenu().clear();
        getMenuInflater().inflate(R.menu.menu_join,toolbar.getMenu());
        toolbar.setBackgroundResource(R.color.color_background_grey);
        toolbar.setTitle("首页");
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


    //加入课程请求异步处理
    private class ATask extends AsyncTask<JoinClassAPI ,JoinClassAPI ,JoinClassAPI > {
        //后台线程执行时
        private String input ;
        private String input2 ;
        private String name;
        public ATask(String input,String input2,String name){
            this.input=input;
            this.input2=input2;
            this.name=name;
        }
        @Override
        protected JoinClassAPI doInBackground(JoinClassAPI... params) {
            JoinClassAPI join = new JoinClassAPI(input,input2,str);
            return join;
        }
        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
        @Override
        protected void onPostExecute(JoinClassAPI result) {
            super.onPostExecute(result);
            String login_result = result.getResponseData();
            Toast.makeText(getApplicationContext(),login_result,Toast.LENGTH_SHORT).show();
        }
    }


}
