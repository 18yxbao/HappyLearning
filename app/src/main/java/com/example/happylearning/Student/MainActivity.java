package com.example.happylearning.Student;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


import com.example.happylearning.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private FragmentManager fm = this.getFragmentManager();
    private SettingFragment mSettingFragment;
    private HomeFragment mHomeFragment;
    private MessageFragment mmessageFragment;
    private Toolbar toolbar;

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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.main_toolbar);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setHomeFragment();
        setSupportActionBar(toolbar);


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_join_class_item1:
                        alert_edit();
                        break;

                }

                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_join,menu); // 参数1为布局文件(menu_main.xml)
        return true;
    }

    //输入课程数据对话框
    public void alert_edit(){
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
                            Toast.makeText(getApplicationContext(), "搜索内容不能为空！" + input, Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),input,Toast.LENGTH_SHORT).show();
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
        mHomeFragment = HomeFragment.newInstance();
        transaction.replace(R.id.main_tb, mHomeFragment);
        transaction.commit();
    }

    private void setMessageFragment() {
        toolbar.getMenu().clear();
        toolbar.setBackgroundResource(R.color.color_background_grey);
        toolbar.setTitle("消息");
        FragmentTransaction transaction = fm.beginTransaction();
        mmessageFragment = MessageFragment.newInstance("消息");
        transaction.replace(R.id.main_tb, mmessageFragment);
        transaction.commit();
    }

    private void setSettingFragment() {
        toolbar.getMenu().clear();
        toolbar.setBackgroundResource(R.color.color_white);
        toolbar.setTitle("");
        FragmentTransaction transaction = fm.beginTransaction();
        mSettingFragment = SettingFragment.newInstance("设置");
        transaction.replace(R.id.main_tb, mSettingFragment);
        transaction.commit();
    }



}
