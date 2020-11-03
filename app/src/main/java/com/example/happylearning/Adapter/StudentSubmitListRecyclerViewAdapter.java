package com.example.happylearning.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.API.HomeWork.DownLoadFileAPI;
import com.example.happylearning.Bean.SubmitHomeWorkList;
import com.example.happylearning.Bean.UserInfo;
import com.example.happylearning.Data.Filedata;
import com.example.happylearning.R;

import java.util.ArrayList;
import java.util.List;

public class StudentSubmitListRecyclerViewAdapter extends RecyclerView.Adapter<StudentSubmitListRecyclerViewAdapter.ViewHolder> {
    private List<SubmitHomeWorkList> submitHomeWorkLists;
    private String classID;
    private String HomeWorkID;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View classView;
        LinearLayout layout;
        TextView name;
        TextView time;
        TextView file_name;
        Button download;
        ImageView image;

        public ViewHolder(View view){
            super(view);
            classView = view;
            layout = (LinearLayout)view.findViewById(R.id.item_homework_submiter_layout);
            name = (TextView)view.findViewById(R.id.item_homework_submiter_name);
            time = (TextView)view.findViewById(R.id.item_homework_submiter_time);
            image = view.findViewById(R.id.item_homework_submiter_icon);
            file_name=(TextView)view.findViewById(R.id.item_homework_submiter_file_name);
            download=(Button) view.findViewById(R.id.item_homework_submiter_download);


        }
    }

    public StudentSubmitListRecyclerViewAdapter(List<SubmitHomeWorkList> submitHomeWorkLists,String classID,String HomeWorkID){
        this.submitHomeWorkLists = submitHomeWorkLists;
        this.classID=classID;
        this.HomeWorkID=HomeWorkID;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_menber,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    SubmitHomeWorkList submitHomeWorkList= submitHomeWorkLists.get(position);
                    ATask_DownLoadFileAPI aTask_downLoadFileAPI = new ATask_DownLoadFileAPI(submitHomeWorkList);
                    aTask_downLoadFileAPI.execute();
                }
            });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        SubmitHomeWorkList submitHomeWorkList= submitHomeWorkLists.get(position);
        holder.name.setText(submitHomeWorkList.getSubmitter_name());
        holder.time.setText(submitHomeWorkList.getSubmit_time());
        holder.file_name.setText(submitHomeWorkList.getFile_name());
    }

    @Override
    public int getItemCount(){
        return submitHomeWorkLists.size();
    }


    private class ATask_DownLoadFileAPI extends AsyncTask<String,String,String > {
        SubmitHomeWorkList submitHomeWorkList;
        Context context;

        public ATask_DownLoadFileAPI(SubmitHomeWorkList submitHomeWorkList) {
            this.submitHomeWorkList=submitHomeWorkList;
        }

        //后台线程执行时
        @Override
        protected String doInBackground(String... params) {

            DownLoadFileAPI downLoadFileAPI=new DownLoadFileAPI(submitHomeWorkList.getFile_path());
            String temp= Filedata.writeFile(context,downLoadFileAPI.getResponse(),"ClassData/"+classID+"/"+"HomeWork"+HomeWorkID,submitHomeWorkList.getFile_name());
            return temp;
        }

        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
        }
    }
}