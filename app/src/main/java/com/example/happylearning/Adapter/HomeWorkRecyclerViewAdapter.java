package com.example.happylearning.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.Bean.HomeWorkList;
import com.example.happylearning.Data.AccountUtil;
import com.example.happylearning.R;
import com.example.happylearning.Student.Class.ContentDetailActivity;

import java.util.List;

public class HomeWorkRecyclerViewAdapter extends RecyclerView.Adapter<HomeWorkRecyclerViewAdapter.ViewHolder> {
    private List<HomeWorkList> homeWorkLists;
    private String account_type;
    private String className;
    private String classNum;

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
            isSubmit=view.findViewById(R.id.item_homework_submit);

            data_name_layout=view.findViewById(R.id.item_homework_data_name_layout);
            isSubmit_layout=view.findViewById(R.id.item_homework_submit_layout);
        }
    }

    public HomeWorkRecyclerViewAdapter(List<HomeWorkList> homeWorkLists,String className,String classNum){
        this.homeWorkLists = homeWorkLists;
        this.className=className;
        this.classNum=classNum;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homework,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.Hview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(view.getContext(), ContentDetailActivity.class);
//                    private String type;
//                    private String title;
//                    private String public_time;
//                    private String ID;
//                    private String isSubmit;
//                    private String file_name;
                intent.putExtra("className", className);
                intent.putExtra("classNum", classNum);
                intent.putExtra("type", homeWorkLists.get(position).getType());
                intent.putExtra("title", homeWorkLists.get(position).getTitle());
                intent.putExtra("public_time", homeWorkLists.get(position).getPublic_time());
                intent.putExtra("ID", homeWorkLists.get(position).getID());
                intent.putExtra("isSubmit", homeWorkLists.get(position).getIsSubmit());
                intent.putExtra("file_name", homeWorkLists.get(position).getFile_name());
                intent.putExtra("action_type", "1");
                view.getContext().startActivity(intent);

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        HomeWorkList homeWorkList= homeWorkLists.get(position);
        holder.time.setText(homeWorkList.getPublic_time());
        holder.title.setText(homeWorkList.getTitle());
        //设置类型
        Log.d("TAG123456", "onBindViewHolder: "+homeWorkList.toString());
        if(homeWorkList.getType().equals("0")){
            holder.type.setText("课件");
            if(homeWorkList.getFile_name().equals("0")){
                holder.type.setText("通知");
            }
        }else{
            holder.type.setText("作业");
        }
        //设置是否显示文件名
        if(homeWorkList.getFile_name().equals("0")){
            holder.data_name_layout.setVisibility(View.GONE);
        }else{
            holder.data_name.setText(homeWorkList.getFile_name());
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