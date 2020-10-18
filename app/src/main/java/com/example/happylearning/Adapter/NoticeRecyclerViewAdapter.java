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
    private List<String> contentList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView content;
        LinearLayout noticeLayout;
        View noticeView;

        public ViewHolder(View view){
            super(view);
            noticeLayout = (LinearLayout)view.findViewById(R.id.item_notice_layout);
            title = (TextView)view.findViewById(R.id.item_notice_title);
            content = (TextView)view.findViewById(R.id.item_notice_content);
        }
    }

    public NoticeRecyclerViewAdapter (List<String> titleList, List<String> contentList) {
        this.titleList=titleList;
        this.contentList=contentList;
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
        String content= contentList.get(position);
        holder.title.setText(title);
        holder.content.setText(content);

    }

    @Override
    public int getItemCount(){
        return titleList.size();
    }

}
