package com.example.happylearning.Student;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.happylearning.Adapter.Home_RecyclerViewAdapter;
import com.example.happylearning.Login.Util;
import com.example.happylearning.R;

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
        List<Classes> classesList= Util.getClassList("17817922657");
        Log.d("123456", "onCreateView: "+classesList.toString());


        //RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.main_home_recyclerview);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(LayoutManager);
        adapter = new Home_RecyclerViewAdapter(classesList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}

