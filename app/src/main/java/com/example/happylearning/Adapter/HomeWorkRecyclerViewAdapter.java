package com.example.happylearning.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.Bean.Classes;
import com.example.happylearning.Bean.HomeWorkList;
import com.example.happylearning.Data.AccountUtil;
import com.example.happylearning.R;
import com.example.happylearning.Student.Class.ClassActivity;
import com.example.happylearning.Teacher.Teacher_ClassActivity;

import java.util.List;

public class HomeWorkRecyclerViewAdapter extends RecyclerView.Adapter<HomeWorkRecyclerViewAdapter.ViewHolder> {
    private List<HomeWorkList> homeWorkLists;
    private String account_type;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View Hview;
        TextView time;
        TextView type;
        TextView title;
        TextView data_name;
        TextView isSubmit;
        LinearLayout isSubmit_layout;
        LinearLayout data_name_layout;
        public ViewHolder(View view){
            super(view);
            Hview = view;
            time= view.findViewById(R.id.item_homework_time);
            type=view.findViewById(R.id.item_homework_type);
            title=view.findViewById(R.id.item_homework_title);
            data_name=view.findViewById(R.id.item_homework_data_name);
            data_name_layout=view.findViewById(R.id.item_homework_data_name_layout);
            isSubmit=view.findViewById(R.id.item_homework_submit);
            isSubmit_layout=view.findViewById(R.id.item_homework_submit_layout);
        }
    }

    public HomeWorkRecyclerViewAdapter(List<HomeWorkList> homeWorkLists){
        this.homeWorkLists = homeWorkLists;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homework,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        account_type=AccountUtil.getAccount_type(view.getContext());


        holder.Hview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        HomeWorkList homeWorkList= homeWorkLists.get(position);
        holder.time.setText(homeWorkList.getTime());
        holder.title.setText(homeWorkList.getTitle());
        //设置类型
        if(homeWorkList.getType().equals("0")){
            holder.type.setText("课件");
        }else{
            holder.type.setText("作业");
        }
        //设置是否显示文件名
        if(homeWorkList.getContent().equals("NULL")) {
            holder.data_name_layout.setVisibility(View.GONE);
        }else{
            holder.data_name.setText(homeWorkList.getContent());
        }
        //设置是否显示已提交
        if(homeWorkList.getIsSubmit().equals("0")) {
            holder.isSubmit_layout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount(){
        return homeWorkLists.size();
    }

}