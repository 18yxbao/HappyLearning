package com.example.happylearning.Teacher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.happylearning.API.HomeWork.AddHomeWorkAPI;
import com.example.happylearning.API.NoticeAPI.AddNoticeAPI;
import com.example.happylearning.Data.Filedata;
import com.example.happylearning.Data.TimeUtil;
import com.example.happylearning.R;

import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PublishActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private static final int REQUEST_CODE=1;

    private String className;
    private String classID;
    private String type;



    private EditText E_title;
    private EditText E_content;
    private TextView T_time;
    private TextView T_path;
    private Switch file_switch;
    private Switch update_switch;
    private LinearLayout choose_file_layout;
    private LinearLayout update_time_layout;
    private LinearLayout file_part;

    private String title;
    private String content;
    private String path="0";
    private String time="0";

    DateFormat format= DateFormat.getDateTimeInstance();
    Calendar calendar= Calendar.getInstance(Locale.CHINA);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        className = getIntent().getStringExtra("class");
        classID=getIntent().getStringExtra("classID");
        type=getIntent().getStringExtra("type");

        toolbar = findViewById(R.id.publish_toolbar);
        toolbar.setTitle(className);
        getMenuInflater().inflate(R.menu.menu_publish,toolbar.getMenu());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        E_title =findViewById(R.id.publish_title);
        E_content =findViewById(R.id.publish_content);
        T_path=findViewById(R.id.publish_file_name);
        T_time=findViewById(R.id.publish_update_time);
        file_switch=findViewById(R.id.publish_switch_file);
        update_switch=findViewById(R.id.publish_switch_update);
        choose_file_layout=findViewById(R.id.publish_choose_file_layout);
        update_time_layout=findViewById(R.id.publish_update_time_layout);
        file_part=findViewById(R.id.publish_homework_part);

        if(type.equals("0")) {
            file_part.setVisibility(View.GONE);
        }

        else {
            T_path.setText("");
            T_time.setText("");
            choose_file_layout.setVisibility(View.GONE);
            update_time_layout.setVisibility(View.GONE);
        }


        file_switch.setOnCheckedChangeListener(switch_click);
        update_switch.setOnCheckedChangeListener(switch_click);

        choose_file_layout.setOnClickListener(click);
        update_time_layout.setOnClickListener(click);


    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch(view.getId()){
                case R.id.publish_choose_file_layout:
                    //获取读写权限
                    if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(PublishActivity.this,new String[]{ "Manifest.permission.WRITE_EXTERNAL_STORAGE"},1);
                    }
                    //ACTION_GET_CONTENT
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    //表示显示所有类型的文件
                    intent.setType("*/*");
                    startActivityForResult(intent, REQUEST_CODE);
                    break;
                case R.id.publish_update_time_layout:
                    showDatePickerDialog(PublishActivity.this,  4, calendar);
                    break;
            }


        }
    };

    private CompoundButton.OnCheckedChangeListener switch_click =  new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch(compoundButton.getId()) {
                case R.id.publish_switch_update:
                    if (b) {
                        update_time_layout.setVisibility(View.VISIBLE);
                    } else {
                        update_time_layout.setVisibility(View.GONE);
                        time="0";
                        T_time.setText("");
                    }
                    break;
                case R.id.publish_switch_file:
                    if (b) {
                        choose_file_layout.setVisibility(View.VISIBLE);
                    } else {
                        choose_file_layout.setVisibility(View.GONE);
                        path="0";
                        T_path.setText("");
                    }
                    break;
            }
        }
    };

    private void showDatePickerDialog(final Activity activity, int themeResId, final Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                time=year + "年" + String.format("%02d",(monthOfYear + 1)) + "月" + String.format("%02d",dayOfMonth) + "日";
                showTimePickerDialog(activity,  4, calendar);
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimePickerDialog(Activity activity,int themeResId, Calendar calendar) {
        // Calendar c = Calendar.getInstance();
        // 创建一个TimePickerDialog实例，并把它显示出来
        // 解释一哈，Activity是context的子类
        new TimePickerDialog( activity,themeResId,
                // 绑定监听器
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time=time+" "+ String.format("%02d",hourOfDay)+ ":" + String.format("%02d",minute);
                        T_time.setText(time);
                    }
                }
                // 设置初始时间
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                // true表示采用24小时制
                ,true).show();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_publish,menu); // 参数1为布局文件(menu_main.xml)
        return true;
    }

    //Toolbar item 按键响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_publish_add:
                if(type.equals("0")) {
                    ATask_AddNoticeAPI aTaskAddNoticeAPI = new ATask_AddNoticeAPI();
                    aTaskAddNoticeAPI.execute();
                }else{
                    ATask_AddHomeWork aTask_addHomeWork = new ATask_AddHomeWork();
                    aTask_addHomeWork.execute();
                }

                break;
            case R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }

    //startActivityForResult回调
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (data == null) {
                    // 用户未选择任何文件，直接返回
                    return;
                }
                Uri uri = data.getData();
                if (uri!=null)
                {
                    path = Filedata.getPath(this,uri);//使用工具类对uri进行转化
                    T_path.setText(path);
                }

                break;
        }
    }






    private class ATask_AddNoticeAPI extends AsyncTask<AddNoticeAPI,AddNoticeAPI,AddNoticeAPI > {

        //后台线程执行时
        @Override
        protected AddNoticeAPI doInBackground(AddNoticeAPI... params) {
            title=E_title.getText().toString();
            content=E_content.getText().toString();
            Log.d("Time123456", "doInBackground: "+TimeUtil.getTime());
            AddNoticeAPI login = new AddNoticeAPI(classID,title,content, TimeUtil.getTime());
            return login;
        }

        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
        @Override
        protected void onPostExecute(AddNoticeAPI result) {
            super.onPostExecute(result);
            String login_result = result.getResponseData();
            Toast.makeText(PublishActivity.this, login_result, Toast.LENGTH_SHORT).show();
            if(login_result.equals("success")){
                PublishActivity.this.finish();
            }
        }
    }

    private class ATask_AddHomeWork extends AsyncTask<AddHomeWorkAPI, AddHomeWorkAPI, AddHomeWorkAPI> {

        //后台线程执行时
        @Override
        protected AddHomeWorkAPI doInBackground(AddHomeWorkAPI... params) {
            title=E_title.getText().toString();
            content=E_content.getText().toString();
            String work_type="1";
            String input_time=time;
            String input_path=path;

            if(time.equals("0")){
                work_type="0";
                input_time="0";
            }
            Log.d("TAGpath", "doInBackground: "+path);
            if(path.equals("0")){
                input_path="0";
            }
            AddHomeWorkAPI addHomeWorkAPI = new AddHomeWorkAPI(title, content, input_time,TimeUtil.getTime(), classID, work_type, input_path);
            return addHomeWorkAPI;
        }

        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
        @Override
        protected void onPostExecute(AddHomeWorkAPI result) {
            super.onPostExecute(result);
            String login_result = result.getResponseData();
            Toast.makeText(PublishActivity.this, login_result, Toast.LENGTH_SHORT).show();
            if(login_result.equals("success")){
                PublishActivity.this.finish();
            }
        }
    }



}