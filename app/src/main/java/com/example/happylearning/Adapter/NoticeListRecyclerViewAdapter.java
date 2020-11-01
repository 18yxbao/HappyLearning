package com.example.happylearning.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.Bean.NoticeList;
import com.example.happylearning.R;
import com.example.happylearning.Student.Class.ContentDetailActivity;

import java.util.List;

public class NoticeListRecyclerViewAdapter extends RecyclerView.Adapter<NoticeListRecyclerViewAdapter.ViewHolder> {
    private List<NoticeList> noticeLists;
    private String className;
    private String classNum;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleT;
        TextView timeT;
        LinearLayout noticeLayout;
        View noticeView;

        public ViewHolder(View view){
            super(view);
            noticeView=view;
            noticeLayout = (LinearLayout)view.findViewById(R.id.item_notice_layout);
            titleT = (TextView)view.findViewById(R.id.item_notice_title);
            timeT = (TextView)view.findViewById(R.id.item_notice_time);
        }
    }

    public NoticeListRecyclerViewAdapter(List<NoticeList> noticeLists,String className,String classNum) {
        this.noticeLists=noticeLists;
        this.className=className;
        this.classNum=classNum;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice,parent,false);

        final ViewHolder holder = new ViewHolder(view);
        holder.noticeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                String title = noticeLists.get(position).getTitle();
                Context context = view.getContext();
                Toast.makeText(context,title,Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(context,ContentDetailActivity.class);
                intent.putExtra("className",className);
                intent.putExtra("classNum",classNum);
                intent.putExtra("title",noticeLists.get(position).getTitle());
                intent.putExtra("time",noticeLists.get(position).getTime());
                intent.putExtra("content",noticeLists.get(position).getContent());
                intent.putExtra("action_type","0");
                context.startActivity(intent);
            }
        });

        holder.noticeView.setLongClickable(true);
        holder.noticeView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(view.getContext(),"你长按了它",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        String title= noticeLists.get(position).getTitle();
        String time= noticeLists.get(position).getTime();
        holder.timeT.setText(time);
        holder.titleT.setText(title);

    }

    @Override
    public int getItemCount(){
        return noticeLists.size();
    }

}
