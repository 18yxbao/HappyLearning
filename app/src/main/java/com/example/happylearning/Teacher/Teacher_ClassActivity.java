package com.example.happylearning.Teacher;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.API.SeeNoticeListAPI;
import com.example.happylearning.Adapter.NoticeListRecyclerViewAdapter;
import com.example.happylearning.Bean.NoticeList;
import com.example.happylearning.R;
import com.example.happylearning.Student.ClassActivity;

import java.util.ArrayList;
import java.util.List;

public class Teacher_ClassActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private Button add_meg;
    private List<NoticeList> noticeLists=new ArrayList<NoticeList>();
    private NoticeListRecyclerViewAdapter adapter;

    private String title;
    private String classID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        toolbar = findViewById(R.id.class_toolbar);
        title = getIntent().getStringExtra("class");
        classID=getIntent().getStringExtra("classID");

        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.class_recycleView);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(Teacher_ClassActivity.this);
        recyclerView.setLayoutManager(LayoutManager);
        adapter=new NoticeListRecyclerViewAdapter(noticeLists,title,classID);
        recyclerView.setAdapter(adapter);
        Atask atask=new Atask();
        atask.execute();

        add_meg=findViewById(R.id.class_add);
        add_meg.setVisibility(View.VISIBLE);

        add_meg.setOnClickListener(click);


    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.class_add:
                    Intent intent=new Intent(Teacher_ClassActivity.this,PublishActivity.class);
                    intent.putExtra("class",title);
                    intent.putExtra("classID",classID);
                    startActivity(intent);
                    break;

            }

        }
    };

    private class Atask extends AsyncTask<SeeNoticeListAPI,SeeNoticeListAPI,SeeNoticeListAPI> {

        @Override
        protected SeeNoticeListAPI doInBackground(SeeNoticeListAPI... seeNoticeListAPIS) {

            return new SeeNoticeListAPI(classID);
        }

        @Override
        protected void onPostExecute(SeeNoticeListAPI result) {
            super.onPostExecute(result);
            String seeNoticeListAPI_result = result.getResponseData();
            if(seeNoticeListAPI_result.equals("")){
                Toast.makeText(Teacher_ClassActivity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
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


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
        onBackPressed();
        return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
