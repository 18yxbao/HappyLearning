package com.example.happylearning.Teacher;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.happylearning.Student.Class.ClassMemberFragment;
import com.example.happylearning.Student.Class.NoticeFragment;
import com.example.happylearning.R;
import com.example.happylearning.Student.Class.PostFragment;

public class Teacher_ClassActivity extends AppCompatActivity {

    private FragmentManager fm = this.getFragmentManager();

    private Teacher_NoticeFragment noticeFragment;
    private Teacher_ClassMemberFragment classMemberFragment;
    private Teacher_HomeWorkFragment homeWorkFragment;
    private PostFragment postFragment;

    private Toolbar toolbar;
    private String classID;
    private String title;

    private LinearLayout set_notice;
    private LinearLayout set_member;
    private LinearLayout set_talk;
    private LinearLayout set_data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_class);
        classID=getIntent().getStringExtra("classID");
        title = getIntent().getStringExtra("class");
        toolbar = findViewById(R.id.class_toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setNoticeFragment();

        set_notice=findViewById(R.id.class_notice);
        set_member=findViewById(R.id.class_member);
        set_talk=findViewById(R.id.class_talk);
        set_data=findViewById(R.id.class_data);


        set_notice.setOnClickListener(click);
        set_member.setOnClickListener(click);
        set_talk.setOnClickListener(click);
        set_data.setOnClickListener(click);


    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            switch(view.getId()){
                case R.id.class_notice:
                    setNoticeFragment();
                    break;
                case R.id.class_member:
                    setClassMemberFragment();
                    break;
                case R.id.class_talk:
                    setTalkFragment();
                    break;
                case R.id.class_data:
                    setHomeWorkFragment();
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
        noticeFragment = new Teacher_NoticeFragment(classID,title);
        transaction.replace(R.id.class_content, noticeFragment);
        transaction.commit();
    }

    private void setClassMemberFragment() {
        FragmentTransaction transaction = fm.beginTransaction();
        classMemberFragment = new Teacher_ClassMemberFragment(classID,title);
        transaction.replace(R.id.class_content, classMemberFragment);
        transaction.commit();
    }
    private void setTalkFragment(){
        FragmentTransaction transaction = fm.beginTransaction();
        postFragment = new PostFragment(classID,title);
        transaction.replace(R.id.class_content,postFragment);
        transaction.commit();
    }

    private void setHomeWorkFragment() {
        FragmentTransaction transaction = fm.beginTransaction();
        homeWorkFragment = new Teacher_HomeWorkFragment(classID,title);
        transaction.replace(R.id.class_content, homeWorkFragment);
        transaction.commit();
    }
}




