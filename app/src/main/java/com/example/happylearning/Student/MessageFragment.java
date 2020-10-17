package com.example.happylearning.Student;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.happyleaning.R;


public class MessageFragment extends Fragment {

    public static MessageFragment newInstance(String param1) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

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

        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        TextView tv = (TextView)view.findViewById(R.id.message_text);
        tv.setText(agrs1);

        return view;
    }
}
