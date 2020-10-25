package com.example.happylearning.Student;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.R;

public class ContentDetailActivity extends AppCompatActivity {
    private TextView className;
    private TextView classNum;
    private TextView title;
    private TextView time;
    private TextView content;
    private Toolbar toolbar;
    //private RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail);
//        className=findViewById(R.id.content_detail_classname);
//        classNum=findViewById(R.id.content_detail_classnum);
        title=findViewById(R.id.content_detail_title);
        time=findViewById(R.id.content_detail_time);
        content=findViewById(R.id.content_detail_content);

        Intent intent=getIntent();

        toolbar = findViewById(R.id.content_toolbar);
        toolbar.setBackgroundResource(R.color.color_background_grey);
        toolbar.setTitle(intent.getExtras().getString("className"));
        setSupportActionBar(toolbar);

//        className.setText(intent.getExtras().getString("className"));
//        classNum.setText("班号："+intent.getExtras().getString("classNum"));
        title.setText(intent.getExtras().getString("title"));
        time.setText(intent.getExtras().getString("time"));
        content.setText(intent.getExtras().getString("content"));


    }
}
