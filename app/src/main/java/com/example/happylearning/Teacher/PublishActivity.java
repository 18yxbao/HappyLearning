package com.example.happylearning.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.happylearning.API.AddNoticeAPI;
import com.example.happylearning.API.LoginAPI;
import com.example.happylearning.Data.AccountUtil;
import com.example.happylearning.Data.TimeUtil;
import com.example.happylearning.Login.LoginActivity;
import com.example.happylearning.R;
import com.example.happylearning.Student.MainActivity;

import java.sql.Time;

public class PublishActivity extends AppCompatActivity {
    private Toolbar toolbar;


    private String className;
    private String classID;
    private EditText E_title;
    private EditText E_content;

    private String title;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        className = getIntent().getStringExtra("class");
        classID=getIntent().getStringExtra("classID");
        toolbar = findViewById(R.id.publish_toolbar);
        toolbar.setTitle(className);
        getMenuInflater().inflate(R.menu.menu_publish,toolbar.getMenu());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        E_title =findViewById(R.id.publish_title);
        E_content =findViewById(R.id.publish_content);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_publish,menu); // 参数1为布局文件(menu_main.xml)
        return true;
    }

    //Toolbar item 按键响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_publish_add:
                ATask aTask=new ATask();
                aTask.execute();
                break;
            case R.id.home:
                onBackPressed();
                break;

            default:
                break;
        }
        return true;
    }


    private class ATask extends AsyncTask<AddNoticeAPI,AddNoticeAPI,AddNoticeAPI > {

        //后台线程执行时
        @Override
        protected AddNoticeAPI doInBackground(AddNoticeAPI... params) {
            title=E_title.getText().toString();
            content=E_content.getText().toString();
            Log.d("Time123456", "doInBackground: "+TimeUtil.getTime());
            AddNoticeAPI login = new AddNoticeAPI(classID,title,content, TimeUtil.getTime());
            return login;
        }

        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
        @Override
        protected void onPostExecute(AddNoticeAPI result) {
            super.onPostExecute(result);
            String login_result = result.getResponseData();
            Toast.makeText(PublishActivity.this, login_result, Toast.LENGTH_SHORT).show();
            if(login_result.equals("success")){
                PublishActivity.this.finish();
            }
        }
    }
}