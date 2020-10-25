package com.example.happylearning.Student;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.happylearning.Data.PictureUtil;
import com.example.happylearning.Login.SetUserInfoActivity;
import com.example.happylearning.R;
import com.example.happylearning.Setting.PhotoPopupWindow;
import java.io.File;

import static android.app.Activity.RESULT_OK;

@SuppressLint("ValidFragment")
public class SettingFragment extends Fragment {


    private LinearLayout mysetting;
    private LinearLayout setting;
    private TextView account_text;
    private TextView name_text;

    private String account;
    private Button logout;
    private ImageView imageView;


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
        setting=(LinearLayout) view.findViewById(R.id.main_setting_detailed_setting);
        account_text=(TextView) view.findViewById(R.id.main_setting_account);
        name_text=(TextView) view.findViewById(R.id.main_setting_name);

        account_text.setText(account);
        name_text.setText(account);

        mysetting.setOnClickListener(click);
        setting.setOnClickListener(click);


        //logout=view.findViewById(R.id.main_setting_logout);
        imageView=view.findViewById(R.id.main_setting_icon);
        PictureUtil.readIconFromFile(getContext(),imageView);
        account_text.setText(account);
        name_text.setText(account);


        return view;
    }



    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            Intent intent;
            switch(view.getId()){
                case R.id.main_setting_mysetting:
                    intent=new Intent(view.getContext(), SetUserInfoActivity.class);
                    intent.putExtra("userNum",account);
                    view.getContext().startActivity(intent);
                    break;
                case R.id.main_setting_detailed_setting:
                    intent=new Intent(view.getContext(), DetailedSettingsActivity.class);
                    view.getContext().startActivity(intent);
                    break;

            }
        }
    };



}

