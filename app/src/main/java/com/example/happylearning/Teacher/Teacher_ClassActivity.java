package com.example.happylearning.Teacher;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.happylearning.Student.Class.NoticeFragment;
import com.example.happylearning.R;

public class Teacher_ClassActivity extends AppCompatActivity {

    private FragmentManager fm = this.getFragmentManager();
    private NoticeFragment noticeFragment;

    private Toolbar toolbar;
    private String classID;
    private String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        toolbar = findViewById(R.id.class_toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        classID=getIntent().getStringExtra("classID");
        title = getIntent().getStringExtra("class");

        setNoticeFragment();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNoticeFragment() {
        FragmentTransaction transaction = fm.beginTransaction();
        noticeFragment = new NoticeFragment(classID,title);
        transaction.replace(R.id.class_content, noticeFragment);
        transaction.commit();
    }



}

/*
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
            /*
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
    */


