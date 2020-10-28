package com.example.happylearning.Teacher;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.API.NoticeAPI.SeeNoticeListAPI;
import com.example.happylearning.Adapter.NoticeListRecyclerViewAdapter;
import com.example.happylearning.Bean.NoticeList;
import com.example.happylearning.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class Teacher_NoticeFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<NoticeList> noticeLists=new ArrayList<NoticeList>();
    private NoticeListRecyclerViewAdapter noticeList_adapter;
    private String classID;
    private String title;
    private Button add_meg;


    public Teacher_NoticeFragment(String classID, String title){
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
        final View view = inflater.inflate(R.layout.fragment_notice, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_notice_recycleView);

        add_meg=view.findViewById(R.id.fragment_notice_add);
        add_meg.setVisibility(View.VISIBLE);
        add_meg.setOnClickListener(click);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        noticeList_adapter =new NoticeListRecyclerViewAdapter(noticeLists,title,classID);
        recyclerView.setAdapter(noticeList_adapter);

        Atask atask=new Atask();
        atask.execute();

        return view;
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch(view.getId()){
                case R.id.fragment_notice_add:
                    Intent intent=new Intent(getContext(),PublishActivity.class);
                    intent.putExtra("class",title);
                    intent.putExtra("classID",classID);
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
            String seeNoticeListAPI_result = result.getResponseData();
            if(seeNoticeListAPI_result.equals("")){
                Toast.makeText(getContext(), "连接服务器失败！", Toast.LENGTH_SHORT).show();
            }else if(seeNoticeListAPI_result.equals("fail")){

            }
            else {
                noticeLists.clear();
                String[] spiltResult=seeNoticeListAPI_result.split(",");
                for(int i=0;i<spiltResult.length;i+=3){
                    NoticeList notice=new NoticeList();
                    notice.setTitle(spiltResult[i]);
                    notice.setContent(spiltResult[i+1]);
                    notice.setTime(spiltResult[i+2]);
                    noticeLists.add(notice);
                }
                Log.d("12345678", "onPostExecute: "+noticeLists.toString());
                noticeList_adapter.notifyDataSetChanged();
            }
        }
    }
}

