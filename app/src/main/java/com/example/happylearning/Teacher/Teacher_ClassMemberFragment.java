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
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.API.GetClassMatesAPI;
import com.example.happylearning.API.GetClassTeacherAPI;
import com.example.happylearning.API.OutClassMatesAPI;
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

    private TextView teacher_name;
    private TextView teacher_number;

    private Button manage;
    private boolean clickable = false;
    private int BUTTON_MOD = 0;
    private int MENU_MOD = 0;
    private int DELETE_MOD = 1;

    public Teacher_ClassMemberFragment(String classID, String title) {
        this.classID = classID;
        this.title = title;
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
        manage = (Button) view.findViewById(R.id.fragment_class_member_manage);
        teacher_name = view.findViewById(R.id.fragment_class_member_name);
        teacher_number = view.findViewById(R.id.fragment_class_member_number);
        manage.setVisibility(View.VISIBLE);


        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        classMemberRecyclerViewAdapter = new ClassMemberRecyclerViewAdapter(classMemberList, clickable);
        recyclerView.setAdapter(classMemberRecyclerViewAdapter);

        Atask_GetClassTeacher atask_getClassTeacher = new Atask_GetClassTeacher();
        atask_getClassTeacher.execute();

        Atask_GetClassMates ataskGetClassMates = new Atask_GetClassMates();
        ataskGetClassMates.execute();


        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BUTTON_MOD == MENU_MOD) {
                    showPopupMenu(manage);
                } else if (BUTTON_MOD == DELETE_MOD) {
                    List<String> deleteMember = classMemberRecyclerViewAdapter.getDeleteMember();
                    Atask_DeleteMember atask_deleteMember = new Atask_DeleteMember(deleteMember);
                    atask_deleteMember.execute();
                    change_button();
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
                switch (item.getItemId()) {
                    case R.id.menu_class_member_add:
                        break;
                    case R.id.menu_class_member_off:
                        change_button();
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

    private void change_button() {
        if (BUTTON_MOD == MENU_MOD) {
            clickable = true;
            classMemberRecyclerViewAdapter = new ClassMemberRecyclerViewAdapter(classMemberList, clickable);
            recyclerView.setAdapter(classMemberRecyclerViewAdapter);
            classMemberRecyclerViewAdapter.notifyDataSetChanged();
            manage.setText("删除");
            BUTTON_MOD = DELETE_MOD;
        } else {
            clickable = false;
            classMemberRecyclerViewAdapter = new ClassMemberRecyclerViewAdapter(classMemberList, clickable);
            recyclerView.setAdapter(classMemberRecyclerViewAdapter);
            classMemberRecyclerViewAdapter.notifyDataSetChanged();
            manage.setText("管理");
            BUTTON_MOD = MENU_MOD;
        }
    }

    private class Atask_DeleteMember extends AsyncTask<OutClassMatesAPI, OutClassMatesAPI, OutClassMatesAPI> {
        private List<String> deleteMember;

        public Atask_DeleteMember(List<String> deleteMember) {
            this.deleteMember = deleteMember;
        }

        @Override
        protected OutClassMatesAPI doInBackground(OutClassMatesAPI... seeNoticeListAPIS) {
            OutClassMatesAPI outClassMatesAPI;
            Log.d("TAG45679", "doInBackground: " + deleteMember);
            if (deleteMember == null || deleteMember.size() == 0) {
                return null;
            } else {
                outClassMatesAPI = new OutClassMatesAPI(classID, deleteMember.size() + "", deleteMember);
            }
            return outClassMatesAPI;
        }

        @Override
        protected void onPostExecute(OutClassMatesAPI result) {
            super.onPostExecute(result);
            result.getResponseData();
            Log.d("OutClassMatesAPI", "result: " + result.getResponseData());
            if (result.getResponseData() == null) {
                Toast.makeText(getContext(), "删除失败", Toast.LENGTH_SHORT).show();
            } else {
                Atask_GetClassMates atask_getClassMates = new Atask_GetClassMates();
                atask_getClassMates.execute();
            }
        }
    }

    private class Atask_GetClassMates extends AsyncTask<GetClassMatesAPI, GetClassMatesAPI, GetClassMatesAPI> {

        @Override
        protected GetClassMatesAPI doInBackground(GetClassMatesAPI... seeNoticeListAPIS) {
            return new GetClassMatesAPI(classID);
        }

        @Override
        protected void onPostExecute(GetClassMatesAPI result) {
            super.onPostExecute(result);
            String seeNoticeListAPI_result = result.getResponseData();
            Log.d("TAG123456", "onPostExecute: " + seeNoticeListAPI_result);

            if (seeNoticeListAPI_result.equals("")) {
                Toast.makeText(getContext(), "连接服务器失败！", Toast.LENGTH_SHORT).show();
            } else if (seeNoticeListAPI_result.equals("fail")) {
                classMemberList.clear();
                classMemberRecyclerViewAdapter.notifyDataSetChanged();

            } else {
                classMemberList.clear();
                String[] spiltResult = seeNoticeListAPI_result.split(",");
                for (int i = 0; i < spiltResult.length; i += 2) {
                    UserInfo user = new UserInfo();
                    user.setName(spiltResult[i]);
                    user.setNumber(spiltResult[i + 1]);
                    classMemberList.add(user);
                }
                classMemberRecyclerViewAdapter.notifyDataSetChanged();
            }
        }
    }
    private class Atask_GetClassTeacher extends AsyncTask<GetClassTeacherAPI, GetClassTeacherAPI, GetClassTeacherAPI> {

        @Override
        protected GetClassTeacherAPI doInBackground(GetClassTeacherAPI...getClassTeacherAPIS) {
            Log.d("TAG123456", "onPostExecute: " );
            return new GetClassTeacherAPI(classID);
        }
        @Override
        protected void onPostExecute(GetClassTeacherAPI result) {
            super.onPostExecute(result);
            String Tresult = result.getResponseData();
            Log.d("TAG123456", "onPostExecute: " + Tresult);

            if (Tresult.equals("")) {
                Toast.makeText(getContext(), "连接服务器失败！", Toast.LENGTH_SHORT).show();
            } else if (Tresult.equals("fail")) {

            } else {
                String[] spiltResult = Tresult.split(",");
                teacher_name.setText(spiltResult[0]);
                teacher_number.setText(spiltResult[1]);
            }
        }
    }



}

