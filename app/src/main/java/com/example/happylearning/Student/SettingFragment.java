package com.example.happylearning.Student;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.happyleaning.R;

public class SettingFragment extends Fragment {
    public static SettingFragment newInstance(String param1) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
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
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");

        return view;
    }
}

