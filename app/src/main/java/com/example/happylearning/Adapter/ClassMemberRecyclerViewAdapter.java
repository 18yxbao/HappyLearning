package com.example.happylearning.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.Bean.ClassMember;
import com.example.happylearning.Bean.UserInfo;
import com.example.happylearning.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ClassMemberRecyclerViewAdapter extends RecyclerView.Adapter<ClassMemberRecyclerViewAdapter.ViewHolder> {
    private List<UserInfo> classMemberList;
    private boolean clickable;
    private List<Integer> deleteMember= new ArrayList<Integer>();

    public List<Integer> getDeleteMember(){
        List<Integer> temp = deleteMember;
        deleteMember.clear();
        return temp;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View classView;
        LinearLayout layout;
        TextView name;
        TextView msg;
        ImageView image;

        public ViewHolder(View view){
            super(view);
            classView = view;
            layout = (LinearLayout)view.findViewById(R.id.item_class_member_layout);
            name = (TextView)view.findViewById(R.id.item_class_member_name);
            msg = (TextView)view.findViewById(R.id.item_class_member_msg);
            image = view.findViewById(R.id.item_class_member_icon);
        }
    }

    public ClassMemberRecyclerViewAdapter(List<UserInfo> classMemberList,boolean clickable){
        this.classMemberList = classMemberList;
        this.clickable=clickable;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_menber,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.classView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickable) {
                        int position = holder.getAdapterPosition();
                        if(deleteMember.contains((Integer)position)) {
                            holder.image.setBackgroundResource(R.color.color_sky_blue);
                            deleteMember.remove((Integer) position);
                        }else{
                            holder.image.setBackgroundResource(R.color.color_red);
                            deleteMember.add(position);
                        }
                    }
                }
            });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        UserInfo classMember= classMemberList.get(position);
        holder.name.setText(classMember.getName());
        holder.msg.setText(classMember.getNumber());

    }

    @Override
    public int getItemCount(){
        return classMemberList.size();
    }

}