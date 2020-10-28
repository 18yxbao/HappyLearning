package com.example.happylearning.Teacher;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.API.GetClassMatesAPI;
import com.example.happylearning.Adapter.ClassMemberRecyclerViewAdapter;
import com.example.happylearning.Bean.UserInfo;
import com.example.happylearning.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class Teacher_ClassMemberFragment extends Fragment {

    private RecyclerView recyclerView;

    private String classID;
    private String title;
    private ClassMemberRecyclerViewAdapter classMemberRecyclerViewAdapter;
    private List<UserInfo> classMemberList = new ArrayList<UserInfo>();

    private Button manage;
    private boolean clickable=false;
    private int BUTTON_MOD=0;
    private int MENU_MOD=0;
    private int DELETE_MOD=1;

    public Teacher_ClassMemberFragment(String classID, String title){
        this.classID=classID;
        this.title=title;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_classmember, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_class_member_recycleView);
        manage=(Button) view.findViewById(R.id.fragment_class_member_manage);
        manage.setVisibility(View.VISIBLE);


        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        classMemberRecyclerViewAdapter =new ClassMemberRecyclerViewAdapter(classMemberList,clickable);
        recyclerView.setAdapter(classMemberRecyclerViewAdapter);

        Atask_GetClassMates ataskGetClassMates =new Atask_GetClassMates();
        ataskGetClassMates.execute();

        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(BUTTON_MOD==MENU_MOD) {
                    showPopupMenu(manage);
                }else if(BUTTON_MOD==DELETE_MOD){
                    List<Integer> deleteMember = classMemberRecyclerViewAdapter.getDeleteMember();


                }
            }
        });



        return view;
    }

    private void showPopupMenu(View view) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        // menu布局
        popupMenu.getMenuInflater().inflate(R.menu.menu_class_member_manage, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_class_member_add:
                        break;
                    case R.id.menu_class_member_off:
                        clickable=true;
                        classMemberRecyclerViewAdapter =new ClassMemberRecyclerViewAdapter(classMemberList,clickable);
                        recyclerView.setAdapter(classMemberRecyclerViewAdapter);
                        classMemberRecyclerViewAdapter.notifyDataSetChanged();

                        manage.setText("删除");
                        BUTTON_MOD=DELETE_MOD;

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

    private class Atask_DeleteMember extends AsyncTask<GetClassMatesAPI, GetClassMatesAPI, GetClassMatesAPI>{

        @Override
        protected GetClassMatesAPI doInBackground(GetClassMatesAPI... seeNoticeListAPIS) {
            return new GetClassMatesAPI(classID);
        }

        @Override
        protected void onPostExecute(GetClassMatesAPI result) {
            super.onPostExecute(result);



        }
    }

    private class Atask_GetClassMates extends AsyncTask<GetClassMatesAPI, GetClassMatesAPI, GetClassMatesAPI>{

        @Override
        protected GetClassMatesAPI doInBackground(GetClassMatesAPI... seeNoticeListAPIS) {
            return new GetClassMatesAPI(classID);
        }

        @Override
        protected void onPostExecute(GetClassMatesAPI result) {
            super.onPostExecute(result);
            String seeNoticeListAPI_result = result.getResponseData();
            Log.d("TAG123456", "onPostExecute: "+seeNoticeListAPI_result);

            if(seeNoticeListAPI_result.equals("")){
                Toast.makeText(getContext(), "连接服务器失败！", Toast.LENGTH_SHORT).show();
            }else if(seeNoticeListAPI_result.equals("fail")){

            }
            else {
                classMemberList.clear();
                String[] spiltResult=seeNoticeListAPI_result.split(",");
                for(int i=0;i<spiltResult.length;i+=2){
                    UserInfo user=new UserInfo();
                    user.setName(spiltResult[i]);
                    user.setNumber(spiltResult[i+1]);
                    classMemberList.add(user);
                }
                Log.d("12345678", "onPostExecute: "+classMemberList.toString());
                classMemberRecyclerViewAdapter.notifyDataSetChanged();
            }
        }
    }
}

