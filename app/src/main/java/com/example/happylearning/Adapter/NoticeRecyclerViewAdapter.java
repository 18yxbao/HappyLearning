package com.example.happylearning.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.R;
import java.util.List;

public class NoticeRecyclerViewAdapter extends RecyclerView.Adapter<NoticeRecyclerViewAdapter.ViewHolder> {
    private List<String> titleList;
    private List<String> timeList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleT;
        TextView timeT;
        LinearLayout noticeLayout;
        View noticeView;

        public ViewHolder(View view){
            super(view);
            noticeLayout = (LinearLayout)view.findViewById(R.id.item_notice_layout);
            titleT = (TextView)view.findViewById(R.id.item_notice_title);
            timeT = (TextView)view.findViewById(R.id.item_notice_time);
        }
    }

    public NoticeRecyclerViewAdapter (List<String> timeList, List<String> titleList) {
        this.titleList=titleList;
        this.timeList=timeList;
    }

    @Override
    public NoticeRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice,parent,false);

        final NoticeRecyclerViewAdapter.ViewHolder holder = new NoticeRecyclerViewAdapter.ViewHolder(view);
        holder.noticeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = holder.getAdapterPosition();
                String title = titleList.get(position);
                Context context = view.getContext();
                Toast.makeText(context,title,Toast.LENGTH_SHORT).show();

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        String title= titleList.get(position);
        String time= timeList.get(position);
        holder.timeT.setText(title);
        holder.timeT.setText(time);

    }

    @Override
    public int getItemCount(){
        return titleList.size();
    }

}
