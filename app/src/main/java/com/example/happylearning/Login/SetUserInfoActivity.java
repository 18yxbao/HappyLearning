package com.example.happylearning.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.happylearning.R;
import com.example.happylearning.Setting.PhotoPopupWindow;

public class SetUserInfoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imageView;
    private PhotoPopupWindow mPhotoPopupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_info);
        toolbar = findViewById(R.id.class_toolbar);
        toolbar.setTitle("设置个人信息");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPhotoPopupWindow=(PhotoPopupWindow) getIntent().getSerializableExtra("Ser");
        imageView=findViewById(R.id.set_user_info_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View rootView = LayoutInflater.from(SetUserInfoActivity.this).inflate(R.layout.activity_set_user_info, null);
                mPhotoPopupWindow.showAtLocation(rootView,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}