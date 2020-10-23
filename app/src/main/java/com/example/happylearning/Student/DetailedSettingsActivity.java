package com.example.happylearning.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.happylearning.Login.LoginActivity;
import com.example.happylearning.Login.RegisterActivity;
import com.example.happylearning.R;


public class DetailedSettingsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button load_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_settings);
        toolbar = findViewById(R.id.detailed_setting_toolbar);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);
        load_out= (Button) findViewById(R.id.detailed_setting_loadout);
        load_out.setOnClickListener(click);
    }


    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            switch(view.getId()){
                case R.id.detailed_setting_loadout:

                    SharedPreferences.Editor editor = view.getContext().getSharedPreferences("logindate", view.getContext().MODE_PRIVATE).edit();
                    editor.putBoolean("islogin", false);
                    editor.apply();
                    Intent intent=new Intent(view.getContext(), LoginActivity.class);
                    view.getContext().startActivity(intent);
                    DetailedSettingsActivity.this.finish();
                    break;

            }
        }
    };


}
