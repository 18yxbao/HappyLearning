package com.example.happylearning.Student.Class;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.API.HomeWork.DownLoadFileAPI;
import com.example.happylearning.API.HomeWork.GetWorkDetailAPI;
import com.example.happylearning.API.HomeWork.SubmitWorkAPI;
import com.example.happylearning.API.NoticeAPI.AddNoticeAPI;
import com.example.happylearning.Bean.HomeWorkList;
import com.example.happylearning.Bean.SubmitHomeWorkList;
import com.example.happylearning.Data.AccountUtil;
import com.example.happylearning.Data.Filedata;
import com.example.happylearning.Data.TimeUtil;
import com.example.happylearning.Login.LoginActivity;
import com.example.happylearning.Login.RegisterActivity;
import com.example.happylearning.R;
import com.example.happylearning.Teacher.PublishActivity;

public class ContentDetailActivity extends AppCompatActivity {

    private static final int REQUEST_CODE=1;


    private HomeWorkList homeWorkList;
    private SubmitHomeWorkList submitHomeWorkList;
    private String classID;
    private String className;



    private TextView title;
    private TextView public_time;
    private TextView content;

    private TextView limit_time;
    private TextView limit_time2;
    private TextView file_name;
    private TextView homework_name;
    private TextView submit_time;

    private LinearLayout submit_layout;
    private LinearLayout ppt_layout;
    private LinearLayout check_layout;
    private LinearLayout is_submit_layout;
    private LinearLayout already_submit_layout;

    private RecyclerView recyclerView;

    private Button download;
    private Button update;
    private Button choose;

    private Toolbar toolbar;
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail);

        homeWorkList = new HomeWorkList();
        submitHomeWorkList = new SubmitHomeWorkList();

        classID=getIntent().getStringExtra("classNum");
        className=getIntent().getStringExtra("className");
        limit_time=findViewById(R.id.content_detail_limit_time);
        limit_time2=findViewById(R.id.content_detail_limit_time2);
        submit_time=findViewById(R.id.content_detail_submit_time);
        title=findViewById(R.id.content_detail_title);
        public_time =findViewById(R.id.content_detail_time);
        content=findViewById(R.id.content_detail_content);

        download=findViewById(R.id.content_detail_download);
        update=findViewById(R.id.content_detail_submit);
        choose=findViewById(R.id.content_detail_choose_file);

        file_name=findViewById(R.id.content_detail_file_name);
        submit_layout=findViewById(R.id.content_detail_submit_layout);
        ppt_layout=findViewById(R.id.content_detail_ppt_layout);
        homework_name=findViewById(R.id.content_detail_homework_name);
        is_submit_layout=findViewById(R.id.content_detail_is_submit);
        check_layout=findViewById(R.id.content_detail_check_layout);
        already_submit_layout=findViewById(R.id.content_detail_already_submit_layout);
        recyclerView=findViewById(R.id.content_detail_recyclerview);

        Intent intent=getIntent();

        toolbar = findViewById(R.id.content_toolbar);
        toolbar.setBackgroundResource(R.color.color_background_grey);
        toolbar.setTitle(intent.getExtras().getString("className"));
        setSupportActionBar(toolbar);
        type=intent.getExtras().getString("action_type");
        choose.setOnClickListener(click);
        download.setOnClickListener(click);
        update.setOnClickListener(click);







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
            }else{
                update.setVisibility(View.GONE);
                choose.setVisibility(View.GONE);
            }
            ATask_GetWorkDetailAPI aTask_getWorkDetailAPI= new ATask_GetWorkDetailAPI();
            aTask_getWorkDetailAPI.execute();

        }
    }


    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            switch(view.getId()){
                case R.id.content_detail_download:
                    ATask_DownLoadFileAPI aTask_downLoadFileAPI = new ATask_DownLoadFileAPI();
                    aTask_downLoadFileAPI.execute();

                    break;
                case R.id.content_detail_submit:
                    if(submitHomeWorkList.getFile_path()!=null){
                        ATask_SubmitWorkAPI aTask_submitWorkAPI = new ATask_SubmitWorkAPI();
                        aTask_submitWorkAPI.execute();
                    }

                    break;
                case R.id.content_detail_choose_file:
                    //获取读写权限
                    if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(ContentDetailActivity.this,new String[]{ "Manifest.permission.WRITE_EXTERNAL_STORAGE"},1);
                    }
                    //ACTION_GET_CONTENT
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    //表示显示所有类型的文件
                    intent.setType("*/*");
                    startActivityForResult(intent, REQUEST_CODE);
                    break;


            }
        }
    };

    private class ATask_DownLoadFileAPI extends AsyncTask<String,String,String > {


        //后台线程执行时
        @Override
        protected String doInBackground(String... params) {

            DownLoadFileAPI  downLoadFileAPI=new DownLoadFileAPI(homeWorkList.getFile_path());


            String temp= Filedata.writeFile(getApplicationContext(),downLoadFileAPI.getResponse(),"ClassData/"+classID+"/"+"HomeWork",homeWorkList.getFile_name());
            return temp;
        }

        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
        }
    }




    private class ATask_SubmitWorkAPI extends AsyncTask<SubmitWorkAPI,SubmitWorkAPI,SubmitWorkAPI > {

        //后台线程执行时
        @Override
        protected SubmitWorkAPI doInBackground(SubmitWorkAPI... params) {

            return new SubmitWorkAPI(submitHomeWorkList.getFile_name(), AccountUtil.getAccount(getApplicationContext()),
                    AccountUtil.getName(getApplicationContext()), TimeUtil.getTime(), classID, homeWorkList.getID(), submitHomeWorkList.getFile_path());
        }

        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
        @Override
        protected void onPostExecute(SubmitWorkAPI result) {
            super.onPostExecute(result);
            String temp=result.getResponseData();
            if(temp.equals("success")){
                Toast.makeText(getApplicationContext(),"提交作业成功",Toast.LENGTH_SHORT).show();
                homeWorkList.setIsSubmit("1");
                is_submit_layout.setVisibility(View.VISIBLE);
                update.setVisibility(View.GONE);
                choose.setVisibility(View.GONE);
            }
        }
    }



    private class ATask_GetWorkDetailAPI extends AsyncTask<GetWorkDetailAPI,GetWorkDetailAPI,GetWorkDetailAPI > {

        //后台线程执行时
        @Override
        protected GetWorkDetailAPI doInBackground(GetWorkDetailAPI... params) {
            return new GetWorkDetailAPI(classID,homeWorkList.getID(),AccountUtil.getAccount(getApplicationContext()),AccountUtil.getAccount_type(getApplicationContext()));
        }

        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
        @Override
        protected void onPostExecute(GetWorkDetailAPI result) {
            super.onPostExecute(result);
            String temp=result.getResponseData();
            if(temp.equals("0")){
                Toast.makeText(getApplicationContext(),"访问失败",Toast.LENGTH_SHORT).show();
            }
            String[] data=temp.split(",");
            homeWorkList.setContent(data[0]);
            homeWorkList.setLimit_time(data[1]);
            homeWorkList.setFile_name(data[2]);
            homeWorkList.setFile_path(data[3]);
            if(homeWorkList.getIsSubmit().equals("1")) {
                submitHomeWorkList.setFile_name(data[4]);
                submitHomeWorkList.setFile_path(data[5]);
                submitHomeWorkList.setSubmit_time(data[6]);
            }
            content.setText(homeWorkList.getContent());
            limit_time.setText(homeWorkList.getLimit_time());
            limit_time2.setText(homeWorkList.getLimit_time());
            file_name.setText(homeWorkList.getFile_name());

            homework_name.setText(submitHomeWorkList.getFile_name());
            submit_time.setText(submitHomeWorkList.getSubmit_time());
        }
    }

    //startActivityForResult回调
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (data == null) {
                    // 用户未选择任何文件，直接返回
                    return;
                }
                Uri uri = data.getData();
                if (uri!=null)
                {
                    String path =Filedata.getPath(this,uri);
                    submitHomeWorkList.setFile_path(path); //使用工具类对uri进行转化
                    String[] temp=path.split("/");
                    submitHomeWorkList.setFile_name(temp[temp.length-1]);
                    submitHomeWorkList.setSubmit_time(TimeUtil.getTime());

                    homework_name.setText(submitHomeWorkList.getFile_name());

                }

                break;
        }
    }

}
