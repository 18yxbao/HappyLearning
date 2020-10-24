package com.example.happylearning.Teacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.R;

import java.util.List;

public class Teacher_ClassActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private Button add_meg;
    private List<String> SettingList;

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


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
        onBackPressed();
        return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
