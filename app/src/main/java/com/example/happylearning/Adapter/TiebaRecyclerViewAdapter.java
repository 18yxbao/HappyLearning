package com.example.happylearning.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.API.PostAPI.AddPostListAPI;
import com.example.happylearning.API.PostAPI.DeletePostListAPI;
import com.example.happylearning.API.PostAPI.StarPostListAPI;
import com.example.happylearning.Bean.PostBean;
import com.example.happylearning.R;
import com.example.happylearning.Student.main.MainActivity;

import java.util.List;
import java.util.concurrent.BlockingDeque;

public class TiebaRecyclerViewAdapter extends RecyclerView.Adapter<TiebaRecyclerViewAdapter.ViewHolder> {
    private List<PostBean> postBeanList;
    private String classID;
    private String userID;
    private Context context;

    public TiebaRecyclerViewAdapter(List<PostBean> postBeanList,String classID,String userID,Context context) {
        this.postBeanList = postBeanList;
        this.classID=classID;
        this.userID=userID;
        this.context=context;
    }

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
        TextView delete;
        View tiebaView;

        public ViewHolder(final View view){
            super(view);
            tiebaView=view;
            icon=view.findViewById(R.id.item_post_icon);
            userT = (TextView)view.findViewById(R.id.item_post_username);
            timeT = (TextView)view.findViewById(R.id.item_post_time);
            content=view.findViewById(R.id.item_post_content);
            picture1=view.findViewById(R.id.item_post_picture1);
            picture2=view.findViewById(R.id.item_post_picture2);
            picture3=view.findViewById(R.id.item_post_picture3);
            star=view.findViewById(R.id.item_post_star);
            comment=view.findViewById(R.id.item_post_comment);
            delete=view.findViewById(R.id.item_post_delete);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,parent,false);

        final ViewHolder holder = new ViewHolder(view);
        holder.tiebaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Context context = view.getContext();
            }
        });

        //删除
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog aldg;
                AlertDialog.Builder adBd=new AlertDialog.Builder(parent.getContext());
                adBd.setTitle("删除消息");
                adBd.setMessage("确定要删除这条消息吗？");
                adBd.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String postID=postBeanList.get(holder.getAdapterPosition()).getId();
                        DeleteAtask deleteAtask=new DeleteAtask(classID,postID);
                        deleteAtask.execute();
                    }
                });
                adBd.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                aldg=adBd.create();
                aldg.show();
            }
        });

        //点赞
        holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String if_like;
                String postID=postBeanList.get(holder.getAdapterPosition()).getId();
                int starnum=Integer.parseInt(postBeanList.get(holder.getAdapterPosition()).getStarNum());
                if(postBeanList.get(holder.getAdapterPosition()).isStar().equals("0")){
                    postBeanList.get(holder.getAdapterPosition()).setStar("1");
                    holder.star.setTextColor(Color.BLUE);
                    starnum++;
                    holder.star.setText(starnum+"点赞");
                    if_like="1";
                }else{
                    postBeanList.get(holder.getAdapterPosition()).setStar("0");
                    holder.star.setTextColor(Color.GRAY);
                    holder.star.setText(starnum+"点赞");
                    if_like="0";
                }
                StarAtask starAtask=new StarAtask(classID,userID,postID,if_like);
                starAtask.execute();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        PostBean postBean = postBeanList.get(position);
//        holder.icon.setImageBitmap(postBean.getIcon());
        holder.userT.setText(postBean.getUsername());
        holder.timeT.setText(postBean.getTime());
        if (postBean.getPicture1()!=null){
            holder.picture1.setImageBitmap(postBean.getPicture1());
            holder.picture1.setVisibility(View.VISIBLE);
        }else{
            holder.picture1.setVisibility(View.GONE);
        }
        if (postBean.getPicture2()!=null){
            holder.picture2.setImageBitmap(postBean.getPicture2());
            holder.picture2.setVisibility(View.VISIBLE);
        }else{
            holder.picture2.setVisibility(View.GONE);
        }
        if (postBean.getPicture3()!=null){
            holder.picture3.setImageBitmap(postBean.getPicture3());
            holder.picture3.setVisibility(View.VISIBLE);
        }else{
            holder.picture3.setVisibility(View.GONE);
        }

        holder.content.setText(postBean.getContent());
        if(postBean.isStar().equals("1")){
            holder.star.setTextColor(Color.BLUE);
        }else {
            holder.star.setTextColor(Color.GRAY);
        }
        holder.star.setText(postBean.getStarNum()+"点赞");
        holder.comment.setText(postBean.getCommentNum()+"评论");

    }

    @Override
    public int getItemCount(){
        return postBeanList.size();
    }

    private class DeleteAtask extends AsyncTask<DeletePostListAPI,DeletePostListAPI,DeletePostListAPI> {
        String classNum;
        String postID;

        public DeleteAtask(String class_number,String post_id){
            this.classNum=class_number;
            this.postID=post_id;
        }

        @Override
        protected DeletePostListAPI doInBackground(DeletePostListAPI... deletePostListAPIS) {
            return new DeletePostListAPI(classNum, postID);
        }

        @Override
        protected void onPostExecute(DeletePostListAPI result) {
            super.onPostExecute(result);
            String resultText = result.getResponseData();
//            Log.d("12345678", "resultText: "+resultText);
            Toast.makeText(context,resultText,Toast.LENGTH_SHORT).show();
        }
    }

    private class StarAtask extends AsyncTask<StarPostListAPI,StarPostListAPI,StarPostListAPI> {
        String classNum;
        String userNum;
        String postID;
        String if_like;

        public StarAtask(String class_number,String userNum,String post_id,String if_like){
            this.classNum=class_number;
            this.postID=post_id;
            this.userNum=userNum;
            this.if_like=if_like;
        }

        @Override
        protected StarPostListAPI doInBackground(StarPostListAPI... starPostListAPIS) {
            return new StarPostListAPI(classNum,userNum, postID,if_like);
        }

        @Override
        protected void onPostExecute(StarPostListAPI result) {
            super.onPostExecute(result);
            String resultText = result.getResponseData();
//            Log.d("12345678", "resultText: "+resultText);
            Toast.makeText(context,resultText,Toast.LENGTH_SHORT).show();
        }
    }

}
