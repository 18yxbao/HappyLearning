package com.example.happylearning.Student.Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.happylearning.Adapter.TiebaRecyclerViewAdapter;
import com.example.happylearning.Bean.PostBean;
import com.example.happylearning.R;

import java.util.ArrayList;
import java.util.List;

public class ClassActivity extends AppCompatActivity {

    private FragmentManager fm = this.getFragmentManager();
    private NoticeFragment noticeFragment;

    private Toolbar toolbar;
    private String classID;
    private String title;

    private LinearLayout set_notice;
    private LinearLayout set_member;
    private LinearLayout set_talk;
    private LinearLayout set_data;
    private LinearLayout set_homework;


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

        set_notice=findViewById(R.id.class_notice);
        set_member=findViewById(R.id.class_member);
        set_talk=findViewById(R.id.class_talk);
        set_data=findViewById(R.id.class_data);
        set_homework=findViewById(R.id.class_homework);

        set_notice.setOnClickListener(click);
        set_member.setOnClickListener(click);
        set_talk.setOnClickListener(click);
        set_data.setOnClickListener(click);
        set_homework.setOnClickListener(click);

    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            switch(view.getId()){
                case R.id.class_notice:
                    setNoticeFragment();
                    break;
                case R.id.class_member:

                    break;
                case R.id.class_talk:

                    break;
                case R.id.class_data:

                    break;
                case R.id.class_homework:

                    break;

            }
        }
    };

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

    private void talk(){
        List<PostBean> postBeanList =new ArrayList<PostBean>();
        TiebaRecyclerViewAdapter adapter;

//        LinearLayoutManager LayoutManager = new LinearLayoutManager(ClassActivity.this);
//        recyclerView.setLayoutManager(LayoutManager);
//        adapter=new TiebaRecyclerViewAdapter(postBeanList);
//        recyclerView.setAdapter(adapter);

    }

}
