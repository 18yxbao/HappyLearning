package com.example.happylearning.Student.Class;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.Adapter.TiebaRecyclerViewAdapter;
import com.example.happylearning.Data.AccountUtil;
import com.example.happylearning.R;

public class CommentDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText editText;
    private Button sendComment;

    private ImageView icon;
    private TextView userT;
    private TextView timeT;
    private TextView content;
    private ImageView picture1;
    private ImageView picture2;
    private ImageView picture3;
//    private TextView star;
//    private TextView commentNumText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_comment);

        icon=findViewById(R.id.detailed_comment_icon);
        userT =findViewById(R.id.detailed_comment_username);
        timeT = findViewById(R.id.detailed_comment_time);
        content=findViewById(R.id.detailed_comment_content);
        picture1=findViewById(R.id.detailed_comment_picture1);
        picture2=findViewById(R.id.detailed_comment_picture2);
        picture3=findViewById(R.id.detailed_comment_picture3);

        recyclerView = findViewById(R.id.detailed_comment_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(CommentDetailActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        editText=findViewById(R.id.detailed_comment_edittext);
        sendComment=findViewById(R.id.detailed_comment_send);
        sendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
