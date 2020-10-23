package com.example.happylearning.Student;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.happylearning.Adapter.Home_RecyclerViewAdapter;
import com.example.happylearning.Bean.Classes;
import com.example.happylearning.Data.Util;
import com.example.happylearning.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private Home_RecyclerViewAdapter adapter;
    private List<Classes> classesList = new ArrayList<Classes>();
    private String str;
    public HomeFragment(String str) {
        this.str = str;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_home_recyclerview);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(LayoutManager);
        adapter = new Home_RecyclerViewAdapter(classesList);
        recyclerView.setAdapter(adapter);
        ATask ak = new ATask(str);
        ak.execute();

        return view;
    }

    private class ATask extends AsyncTask<List<Classes> ,List<Classes> ,List<Classes> > {
        //后台线程执行时
        private String name;

        public ATask(String str){
            this.name=str;
        }
        @Override
        protected List<Classes> doInBackground(List<Classes>... params) {

            SharedPreferences sprfMain= getActivity().getSharedPreferences("logindate",getActivity().MODE_PRIVATE);
            String account_type=sprfMain.getString("account_type","0");

            List<Classes>  newClassList= Util.getClassList(name,account_type);
            return newClassList;
        }
        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
        @Override
        protected void onPostExecute(List<Classes> result) {
            super.onPostExecute(result);
            if (result != null) {
                classesList.addAll(result);
                adapter.notifyDataSetChanged();
            }
        }
    }
}

