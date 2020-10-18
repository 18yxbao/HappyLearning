package com.example.happylearning.Student;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happylearning.Login.LoginActivity;
import com.example.happylearning.R;

public class SettingFragment extends Fragment {

    private LinearLayout mysetting;


    public static SettingFragment newInstance(String param1) {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    public SettingFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        mysetting=(LinearLayout) view.findViewById(R.id.main_setting_mysetting);


        mysetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = view.getContext().getSharedPreferences("logindate", view.getContext().MODE_PRIVATE).edit();
                editor.putBoolean("islogin", false);
                editor.apply();
                Intent intent=new Intent(view.getContext(), LoginActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        return view;
    }




}

