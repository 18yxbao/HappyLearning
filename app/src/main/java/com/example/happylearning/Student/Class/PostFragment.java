package com.example.happylearning.Student.Class;

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

import com.example.happylearning.API.PostAPI.GetPostListAPI;
import com.example.happylearning.Adapter.TiebaRecyclerViewAdapter;
import com.example.happylearning.Bean.PostBean;
import com.example.happylearning.Data.AccountUtil;
import com.example.happylearning.Data.Filedata;
import com.example.happylearning.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class PostFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<PostBean> postBeanList= new ArrayList<PostBean>();
    private TiebaRecyclerViewAdapter adapter;
    private Button sendPostButton;
    private String classID;
    private String className;


    public PostFragment(String classID, String className){
        this.classID=classID;
        this.className=className;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_post, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_post_recycleView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new TiebaRecyclerViewAdapter(postBeanList,classID,AccountUtil.getAccount(getContext()),getContext());
        recyclerView.setAdapter(adapter);

        sendPostButton=view.findViewById(R.id.fragment_post_add);
        sendPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getActivity().getIntent();
                intent.putExtra("classID",classID);
                intent.putExtra("className",className);
                intent.setClass(getContext(),PublicPostActivity.class);
                startActivity(intent);
            }
        });
        GetPostListAtask atask=new GetPostListAtask(classID, AccountUtil.getAccount(getContext()));
        atask.execute();
        return view;
    }

    private class GetPostListAtask extends AsyncTask<GetPostListAPI,GetPostListAPI,GetPostListAPI>{
        String classID;
        String userNum;
        public GetPostListAtask(String classID,String userNum) {
            this.classID=classID;
            this.userNum=userNum;
        }

        @Override
        protected GetPostListAPI doInBackground(GetPostListAPI... seeNoticeListAPIS) {

            return new GetPostListAPI(classID,userNum);
        }

        @Override
        protected void onPostExecute(GetPostListAPI result) {
            super.onPostExecute(result);
            String seeNoticeListAPI_result = result.getResponseData();
            if(seeNoticeListAPI_result.equals("")){
                Toast.makeText(getContext(), "连接服务器失败！", Toast.LENGTH_SHORT).show();
            }
            else {
                postBeanList.clear();
                String[] spiltResult=seeNoticeListAPI_result.split("\n");

                for(int i=0;i<spiltResult.length;i++){
                    Log.d("123456789", "onPostExecute: "+spiltResult[i]);
                    String[] temp=spiltResult[i].split(",@.#");
                    PostBean postBean=new PostBean();
                    if(temp[0].equals("like")){
                        postBean.setStar("1");
                    }else if(temp[0].equals("dislike")){
                        postBean.setStar("0");
                    }else{
                        Toast.makeText(getContext(),"解析出错",Toast.LENGTH_SHORT).show();
                    }
                    int j=1;
                    postBean.setUserType(temp[j++]);
                    String iconStr=temp[j++];
                    postBean.setIcon(Filedata.stringtoBitmap(iconStr));

                    postBean.setId(temp[j++]);
                    postBean.setUserid(temp[j++]);
                    postBean.setUsername(temp[j++]);
                    postBean.setContent(temp[j++]);
                    postBean.setPictureNum(temp[j++]);
                    postBean.setCommentNum(temp[j++]);
                    postBean.setTime(temp[j++]);
                    postBean.setStarNum(temp[j++]);

                    postBeanList.add(postBean);

                }
                Log.d("12345678", "onPostExecute: "+postBeanList.toString());
                adapter.notifyDataSetChanged();
            }
        }
    }
}

