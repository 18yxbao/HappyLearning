package com.example.happylearning.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.Data.AccountUtil;
import com.example.happylearning.R;
import com.example.happylearning.Bean.Classes;
import com.example.happylearning.Student.Class.ClassActivity;
import com.example.happylearning.Teacher.Teacher_ClassActivity;


import java.util.List;


public class Home_RecyclerViewAdapter extends RecyclerView.Adapter<Home_RecyclerViewAdapter.ViewHolder> {
    private List<Classes> classList;
    private String account_type;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View classView;
        LinearLayout classLayout;
        TextView classname;
        TextView showmsg;

        public ViewHolder(View view){
            super(view);
            classView = view;
            classLayout = (LinearLayout)view.findViewById(R.id.item_class_layout);
            classname = (TextView)view.findViewById(R.id.item_class_name);
            showmsg = (TextView)view.findViewById(R.id.item_class_msg);
        }
    }


    public Home_RecyclerViewAdapter(List<Classes> classList){
        this.classList = classList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        account_type= AccountUtil.getAccount_type(view.getContext());

        holder.classView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Classes classes = classList.get(position);
                Context context = view.getContext();

                Intent intent ;
                if(account_type.equals("0")) {
                    intent = new Intent(context, ClassActivity.class);
                }
                else{
                    intent = new Intent(context, Teacher_ClassActivity.class);
                }
                intent.putExtra("classID",classes.getClassNum());
                intent.putExtra("class",classes.getClassName());

                context.startActivity(intent);
            }
        });

        holder.classView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position = holder.getAdapterPosition();
                showPopupMenu(view,position);
                return false;
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Classes name= classList.get(position);
        holder.classname.setText(name.getClassName());
        holder.showmsg.setText("班号"+name.getClassNum());

    }

    @Override
    public int getItemCount(){
        return classList.size();
    }


    private void showPopupMenu(View view, int position) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        // menu布局
        popupMenu.getMenuInflater().inflate(R.menu.menu_long_click_class, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_long_click_class_item1:

                        break;
                }
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
            }
        });

        popupMenu.show();
    }


}
