package com.example.happylearning.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.happyleaning.R;

public class DetailedSettingsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_settings);
        toolbar = findViewById(R.id.detailed_setting_toolbar);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);



    }
}
