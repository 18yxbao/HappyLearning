package com.example.happylearning.Student.main;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.Adapter.TiebaRecyclerViewAdapter;
import com.example.happylearning.Bean.PostBean;
import com.example.happylearning.R;

import java.util.ArrayList;
import java.util.List;


public class MessageFragment extends Fragment {

    List<PostBean> postBeanList =new ArrayList<PostBean>();
    RecyclerView recyclerView;
    TiebaRecyclerViewAdapter adapter;

    public MessageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_message, container, false);
//        Context context=getContext();
//        recyclerView=container.findViewById(R.id.main_message_recycleview);
//        LinearLayoutManager LayoutManager = new LinearLayoutManager(context);
//        recyclerView.setLayoutManager(LayoutManager);
//        adapter=new TiebaRecyclerViewAdapter(tiebaList);
//        recyclerView.setAdapter(adapter);

        return view;
    }
}
