package com.example.happylearning.Teacher;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.API.NoticeAPI.SeeNoticeListAPI;
import com.example.happylearning.Adapter.ClassMemberRecyclerViewAdapter;
import com.example.happylearning.Adapter.HomeWorkRecyclerViewAdapter;
import com.example.happylearning.Adapter.NoticeListRecyclerViewAdapter;
import com.example.happylearning.Bean.HomeWorkList;
import com.example.happylearning.Bean.NoticeList;
import com.example.happylearning.Data.TimeUtil;
import com.example.happylearning.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class Teacher_HomeWorkFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button add_meg;

    private String classID;
    private String title;

    private List<HomeWorkList> homeWorkLists = new ArrayList<HomeWorkList>();
    private HomeWorkRecyclerViewAdapter adapter;


    public Teacher_HomeWorkFragment(String classID, String title){
        this.classID=classID;
        this.title=title;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_homework, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_homework_recyclerview);
        add_meg=view.findViewById(R.id.fragment_homework_add);
        add_meg.setVisibility(View.VISIBLE);

        add_meg.setOnClickListener(click);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        HomeWorkList homeWorkList = new HomeWorkList();
        homeWorkList.setContent("小黄书.txt");
        homeWorkList.setIsSubmit("1");
        homeWorkList.setTime(TimeUtil.getTime());
        homeWorkList.setTitle("观看小黄书");
        homeWorkList.setType("1");

        homeWorkLists.add(homeWorkList);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeWorkRecyclerViewAdapter(homeWorkLists);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch(view.getId()){
                case R.id.fragment_homework_add:
                    Intent intent=new Intent(getContext(),PublishActivity.class);
                    intent.putExtra("class",title);
                    intent.putExtra("classID",classID);
                    intent.putExtra("type","1");
                    startActivity(intent);
                    break;
            }
        }
    };


    private class Atask extends AsyncTask<SeeNoticeListAPI,SeeNoticeListAPI,SeeNoticeListAPI>{

        @Override
        protected SeeNoticeListAPI doInBackground(SeeNoticeListAPI... seeNoticeListAPIS) {

            return new SeeNoticeListAPI(classID);
        }

        @Override
        protected void onPostExecute(SeeNoticeListAPI result) {
            super.onPostExecute(result);


        }
    }



}

