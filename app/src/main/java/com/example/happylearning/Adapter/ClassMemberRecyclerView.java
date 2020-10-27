package com.example.happylearning.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.Bean.ClassMember;
import com.example.happylearning.R;

import java.util.List;

public class ClassMemberRecyclerView extends RecyclerView.Adapter<ClassMemberRecyclerView.ViewHolder> {
    private List<ClassMember> classMemberList;


    static class ViewHolder extends RecyclerView.ViewHolder{
        View classView;
        LinearLayout layout;
        TextView name;
        TextView msg;

        public ViewHolder(View view){
            super(view);
            classView = view;
            layout = (LinearLayout)view.findViewById(R.id.item_class_member_layout);
            name = (TextView)view.findViewById(R.id.item_class_member_name);
            msg = (TextView)view.findViewById(R.id.item_class_member_msg);
        }
    }


    public ClassMemberRecyclerView(List<ClassMember> classMemberList){
        this.classMemberList = classMemberList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class,parent,false);
        final ViewHolder holder = new ViewHolder(view);


        holder.classView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        ClassMember classMember= classMemberList.get(position);
        holder.name.setText(classMember.getName());
        holder.msg.setText(classMember.getNumber());

    }

    @Override
    public int getItemCount(){
        return classMemberList.size();
    }

}