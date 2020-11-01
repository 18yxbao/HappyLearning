package com.example.happylearning.Teacher;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.Adapter.HomeWorkRecyclerViewAdapter;
import com.example.happylearning.Bean.HomeWorkList;
import com.example.happylearning.Data.AccountUtil;
import com.example.happylearning.Data.TimeUtil;
import com.example.happylearning.Data.Util;
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
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeWorkRecyclerViewAdapter(homeWorkLists,title,classID);
        recyclerView.setAdapter(adapter);

        Atask atask = new Atask();
        atask.execute();
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


    private class Atask extends AsyncTask<List<HomeWorkList>,List<HomeWorkList>,List<HomeWorkList>>{

        @Override
        protected List<HomeWorkList> doInBackground(List<HomeWorkList>... seeNoticeListAPIS) {
            return Util.getHomeWorkLists(getContext(),classID, AccountUtil.getAccount(getContext()) , AccountUtil.getAccount_type(getContext()));
        }

        @Override
        protected void onPostExecute(List<HomeWorkList> result) {
            super.onPostExecute(result);
            homeWorkLists.clear();
            homeWorkLists.addAll(result);
            adapter.notifyDataSetChanged();
        }
    }



}

