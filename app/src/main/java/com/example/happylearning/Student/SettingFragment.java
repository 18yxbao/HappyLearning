package com.example.happylearning.Student;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.happylearning.Login.LoginActivity;
import com.example.happylearning.R;

import org.w3c.dom.Text;

@SuppressLint("ValidFragment")
public class SettingFragment extends Fragment {

    private LinearLayout mysetting;
    private TextView account_text;
    private TextView name_text;
    private String account;

    public SettingFragment(String account) {
        this.account=account;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        mysetting=(LinearLayout) view.findViewById(R.id.main_setting_mysetting);
        account_text=(TextView) view.findViewById(R.id.main_setting_account);
        name_text=(TextView) view.findViewById(R.id.main_setting_name);

        account_text.setText(account);
        name_text.setText(account);

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

