package com.example.happylearning.Student;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.happylearning.R;

public class ContentDetailActivity extends AppCompatActivity {
    private TextView className;
    private TextView classNum;
    private TextView title;
    private TextView time;
    private TextView content;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail);
        className=findViewById(R.id.content_detail_classname);
        classNum=findViewById(R.id.content_detail_classnum);
        title=findViewById(R.id.content_detail_title);
        time=findViewById(R.id.content_detail_time);
        content=findViewById(R.id.content_detail_content);

        Intent intent=getIntent();
        className.setText(intent.getExtras().getString("className"));
        classNum.setText(intent.getExtras().getString("classNum"));
        title.setText(intent.getExtras().getString("title"));
        time.setText(intent.getExtras().getString("time"));
        content.setText(intent.getExtras().getString("content"));

    }
}
