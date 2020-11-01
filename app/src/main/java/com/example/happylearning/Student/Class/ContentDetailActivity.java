package com.example.happylearning.Student.Class;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.happylearning.Bean.HomeWorkList;
import com.example.happylearning.Data.AccountUtil;
import com.example.happylearning.R;

public class ContentDetailActivity extends AppCompatActivity {

    HomeWorkList homeWorkList;
    private TextView title;
    private TextView public_time;
    private TextView content;

    private LinearLayout submit_layout;
    private LinearLayout ppt_layout;
    private LinearLayout check_layout;
    private LinearLayout is_submit_layout;

    private Button download;
    private Button update;
    private TextView file_name;
    private TextView homework_name;

    private Toolbar toolbar;
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail);

        homeWorkList = new HomeWorkList();

        title=findViewById(R.id.content_detail_title);
        public_time =findViewById(R.id.content_detail_time);
        content=findViewById(R.id.content_detail_content);

        download=findViewById(R.id.content_detail_download);
        update=findViewById(R.id.content_detail_submit);
        file_name=findViewById(R.id.content_detail_file_name);
        submit_layout=findViewById(R.id.content_detail_submit_layout);
        ppt_layout=findViewById(R.id.content_detail_ppt_layout);
        homework_name=findViewById(R.id.content_detail_homework_name);
        is_submit_layout=findViewById(R.id.content_detail_is_submit);
        check_layout=findViewById(R.id.content_detail_check_layout);

        Intent intent=getIntent();

        toolbar = findViewById(R.id.content_toolbar);
        toolbar.setBackgroundResource(R.color.color_background_grey);
        toolbar.setTitle(intent.getExtras().getString("className"));
        setSupportActionBar(toolbar);

        type=intent.getExtras().getString("action_type");

        if(type.equals("0")) {
            submit_layout.setVisibility(View.GONE);
            ppt_layout.setVisibility(View.GONE);
            check_layout.setVisibility(View.GONE);
            title.setText(intent.getExtras().getString("title"));
            public_time.setText(intent.getExtras().getString("time"));
            content.setText(intent.getExtras().getString("content"));
        }
        else {
            homeWorkList.setType(intent.getExtras().getString("type"));
            homeWorkList.setTitle(intent.getExtras().getString("title"));
            homeWorkList.setPublic_time(intent.getExtras().getString("public_time"));
            homeWorkList.setID(intent.getExtras().getString("ID"));
            homeWorkList.setIsSubmit(intent.getExtras().getString("isSubmit"));
            homeWorkList.setFile_name(intent.getExtras().getString("file_name"));

            Log.d("TAG456789", "onCreate: "+homeWorkList.toString());

            title.setText(homeWorkList.getTitle());
            public_time.setText(homeWorkList.getPublic_time());


            if(AccountUtil.getAccount_type(getApplicationContext()).equals("0")){
                check_layout.setVisibility(View.GONE);
            }else{
                submit_layout.setVisibility(View.GONE);
            }

            //如果是课件, 将提交作业隐藏
            if(homeWorkList.getType().equals("0")){
                check_layout.setVisibility(View.GONE);
                submit_layout.setVisibility(View.GONE);
            }

            //如果没有附件, 隐藏附件
            if(homeWorkList.getFile_name().equals("0")){
                ppt_layout.setVisibility(View.GONE);
            }
            else{
                file_name.setText(homeWorkList.getFile_name());
            }

            //如果没有提交作业, 隐藏已提交
            if(homeWorkList.getIsSubmit().equals("0")){
                is_submit_layout.setVisibility(View.GONE);
            }


        }
    }

//    private class ATask_GetHomeWorkDetail extends AsyncTask<AddNoticeAPI,AddNoticeAPI,AddNoticeAPI > {
//
//        //后台线程执行时
//        @Override
//        protected AddNoticeAPI doInBackground(AddNoticeAPI... params) {
//
//            return ;
//        }
//
//        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
//        @Override
//        protected void onPostExecute(AddNoticeAPI result) {
//            super.onPostExecute(result);
//
//        }
//    }



}
