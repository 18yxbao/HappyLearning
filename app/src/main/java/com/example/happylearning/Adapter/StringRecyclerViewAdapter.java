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

public class StringRecyclerViewAdapter extends RecyclerView.Adapter<StringRecyclerViewAdapter.ViewHolder> {
    private List<String> stringList;

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
    public StringRecyclerViewAdapter(List<String> stringList){
        this.stringList = stringList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class,parent,false);




        final ViewHolder holder = new ViewHolder(view);
        holder.classView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = holder.getAdapterPosition();
                String classes = stringList.get(position);
                Context context = view.getContext();
                Toast.makeText(context,classes,Toast.LENGTH_SHORT).show();


            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        String name= stringList.get(position);
        holder.classname.setText(name);
        holder.showmsg.setText(name);

    }

    @Override
    public int getItemCount(){
        return stringList.size();
    }

}