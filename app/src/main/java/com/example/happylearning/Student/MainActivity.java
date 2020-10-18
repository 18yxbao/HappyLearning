package com.example.happylearning.Student;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.happylearning.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;


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




    }
    private void setHomeFragment() {

        toolbar.setBackgroundResource(R.color.color_background_grey);
        toolbar.setTitle("首页");
        FragmentTransaction transaction = fm.beginTransaction();
        mHomeFragment = HomeFragment.newInstance();
        transaction.replace(R.id.main_tb, mHomeFragment);
        transaction.commit();
    }
    private void setMessageFragment() {
        toolbar.setBackgroundResource(R.color.color_background_grey);
        toolbar.setTitle("消息");
        FragmentTransaction transaction = fm.beginTransaction();
        mmessageFragment = MessageFragment.newInstance("消息");
        transaction.replace(R.id.main_tb, mmessageFragment);
        transaction.commit();
    }
    private void setSettingFragment() {
        toolbar.setBackgroundResource(R.color.color_white);
        toolbar.setTitle("");
        FragmentTransaction transaction = fm.beginTransaction();
        mSettingFragment = SettingFragment.newInstance("设置");
        transaction.replace(R.id.main_tb, mSettingFragment);
        transaction.commit();
    }

}
