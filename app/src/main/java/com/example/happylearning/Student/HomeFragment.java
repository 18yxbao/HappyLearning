package com.example.happylearning.Student;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happyleaning.Adapter.Home_RecyclerViewAdapter;
import com.example.happyleaning.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private Home_RecyclerViewAdapter adapter;
    private List<String> ClassList = new ArrayList<>();


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //暂时填入数据

        ClassList.add("计算方法");
        ClassList.add("应用密码学");

        //RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.main_home_recyclerview);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(LayoutManager);
        adapter = new Home_RecyclerViewAdapter(ClassList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}

