package com.example.happylearning.Student;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happyleaning.R;
import java.util.List;


public class Home_RecyclerViewAdapter extends RecyclerView.Adapter<Home_RecyclerViewAdapter.ViewHolder> {
    private List<String> classList;

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
    public Home_RecyclerViewAdapter(List<String> classList){
        this.classList = classList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.classView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                String classes = classList.get(position);
                Context context = view.getContext();
                Toast.makeText(context,classes,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ClassActivity.class);
                intent.putExtra("class",classes);
                context.startActivity(intent);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        String name= classList.get(position);
        holder.classname.setText(name);
        holder.showmsg.setText(name);

    }

    @Override
    public int getItemCount(){
        return classList.size();
    }

}
