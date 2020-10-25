package com.example.happylearning.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.happylearning.API.SeeNoticeListAPI;
import com.example.happylearning.Adapter.NoticeListRecyclerViewAdapter;
import com.example.happylearning.Bean.NoticeList;
import com.example.happylearning.Data.AccountUtil;
import com.example.happylearning.Login.LoginActivity;
import com.example.happylearning.R;
import com.example.happylearning.Teacher.TeacherMainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;

public class ClassActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<NoticeList> noticeLists=new ArrayList<NoticeList>();
    private NoticeListRecyclerViewAdapter adapter;
    private String classID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        toolbar = findViewById(R.id.class_toolbar);

        String title = getIntent().getStringExtra("class");
        toolbar.setTitle(title);
        classID=getIntent().getStringExtra("classID");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.class_recycleView);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(ClassActivity.this);
        recyclerView.setLayoutManager(LayoutManager);
        adapter=new NoticeListRecyclerViewAdapter(noticeLists,title,classID);
        recyclerView.setAdapter(adapter);
        Atask atask=new Atask();
        atask.execute();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
        onBackPressed();
        return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private class Atask extends AsyncTask<SeeNoticeListAPI,SeeNoticeListAPI,SeeNoticeListAPI>{

        @Override
        protected SeeNoticeListAPI doInBackground(SeeNoticeListAPI... seeNoticeListAPIS) {

            return new SeeNoticeListAPI(classID);
        }

        @Override
        protected void onPostExecute(SeeNoticeListAPI result) {
            super.onPostExecute(result);
            String seeNoticeListAPI_result = result.getResponseData();
            if(seeNoticeListAPI_result.equals("")){
                Toast.makeText(ClassActivity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
            }
            else {
                noticeLists.clear();
                String[] spiltResult=seeNoticeListAPI_result.split(",");
                for(int i=0;i<spiltResult.length;i+=3){
                    NoticeList notice=new NoticeList();
                    notice.setTitle(spiltResult[i]);
                    notice.setContent(spiltResult[i+1]);
                    notice.setTime(spiltResult[i+2]);
                    noticeLists.add(notice);
                }
                Log.d("12345678", "onPostExecute: "+noticeLists.toString());
                adapter.notifyDataSetChanged();
            }
        }
    }

}
