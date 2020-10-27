package com.example.happylearning.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.Bean.PostBean;
import com.example.happylearning.R;

import java.util.List;

public class TiebaRecyclerViewAdapter extends RecyclerView.Adapter<TiebaRecyclerViewAdapter.ViewHolder> {
    private List<PostBean> postBeanList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView userT;
        TextView timeT;
        TextView content;
        ImageView picture1;
        ImageView picture2;
        ImageView picture3;
        TextView star;
        TextView comment;

        LinearLayout tiebaLayout;
        View tiebaView;

        public ViewHolder(View view){
            super(view);
            tiebaView=view;
            tiebaLayout = (LinearLayout)view.findViewById(R.id.item_notice_layout);
            icon=view.findViewById(R.id.item_message_icon);
            userT = (TextView)view.findViewById(R.id.item_message_username);
            timeT = (TextView)view.findViewById(R.id.item_message_time);
            content=view.findViewById(R.id.item_message_content);
            picture1=view.findViewById(R.id.item_message_picture1);
            picture2=view.findViewById(R.id.item_message_picture2);
            picture3=view.findViewById(R.id.item_message_picture3);
            star=view.findViewById(R.id.item_message_star);
            comment=view.findViewById(R.id.item_message_comment);
        }
    }

    public TiebaRecyclerViewAdapter(List<PostBean> postBeanList) {
        this.postBeanList = postBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_massage,parent,false);

        final ViewHolder holder = new ViewHolder(view);
        holder.tiebaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Context context = view.getContext();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        PostBean postBean = postBeanList.get(position);
        holder.icon.setImageBitmap(postBean.getIcon());
        holder.userT.setText(postBean.getUsername());
        holder.timeT.setText(postBean.getTime());
        if (postBean.getPicture1()!=null){
            holder.picture1.setImageBitmap(postBean.getPicture1());
            holder.picture1.setVisibility(View.VISIBLE);
        }else{
            holder.picture1.setVisibility(View.INVISIBLE);
        }
        if (postBean.getPicture2()!=null){
            holder.picture2.setImageBitmap(postBean.getPicture2());
            holder.picture2.setVisibility(View.VISIBLE);
        }else{
            holder.picture2.setVisibility(View.INVISIBLE);
        }
        if (postBean.getPicture3()!=null){
            holder.picture3.setImageBitmap(postBean.getPicture3());
            holder.picture3.setVisibility(View.VISIBLE);
        }else{
            holder.picture3.setVisibility(View.INVISIBLE);
        }
        holder.star.setText(postBean.getStarNum()+"点赞");
        holder.comment.setText(postBean.getCommentNum()+"评论");
    }

    @Override
    public int getItemCount(){
        return postBeanList.size();
    }


}
