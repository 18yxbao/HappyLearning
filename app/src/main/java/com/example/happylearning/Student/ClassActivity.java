package com.example.happylearning.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.happyleaning.R;

public class ClassActivity extends AppCompatActivity {

    private static final String TAG = "log";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        toolbar = findViewById(R.id.class_toolbar);

        String title = getIntent().getStringExtra("class");
        toolbar.setTitle(title);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
        onBackPressed();
        return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
