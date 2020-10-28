package com.example.happylearning.Student.Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.happylearning.R;

public class ClassActivity extends AppCompatActivity {

    private FragmentManager fm = this.getFragmentManager();
    private NoticeFragment noticeFragment;
    private ClassMemberFragment classMemberFragment;
    private PostFragment postFragment;

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
                    setClassMemberFragment();
                    break;
                case R.id.class_talk:
                    Toast.makeText(getApplicationContext(),"讨论",Toast.LENGTH_SHORT).show();
                    setTalkFragment();
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

    private void setClassMemberFragment() {
        FragmentTransaction transaction = fm.beginTransaction();
        classMemberFragment = new ClassMemberFragment(classID,title);
        transaction.replace(R.id.class_content, classMemberFragment);
        transaction.commit();
    }

    private void setTalkFragment(){
        FragmentTransaction transaction = fm.beginTransaction();
        postFragment = new PostFragment(classID,title);
        transaction.replace(R.id.class_content,postFragment);
        transaction.commit();
    }

}
