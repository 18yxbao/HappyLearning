package com.example.happylearning.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happylearning.API.UpdateIconAPI;
import com.example.happylearning.API.UpdateUserInfoAPI;
import com.example.happylearning.Bean.UserInfo;
import com.example.happylearning.Data.AccountUtil;
import com.example.happylearning.Data.PictureUtil;
import com.example.happylearning.R;
import com.example.happylearning.Setting.PhotoPopupWindow;

import java.io.File;

public class SetUserInfoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imageView;
    private Button savemsg;

    private LinearLayout set_icon;
    private LinearLayout set_name;
    private LinearLayout set_gender;
    private LinearLayout set_account;
    private LinearLayout set_school;
    private LinearLayout set_schoolId;
    private LinearLayout set_major;


    private TextView T_name;
    private TextView T_gender;
    private TextView T_account;
    private TextView T_school;
    private TextView T_schoolid;
    private TextView T_major;

    private String name = "";
    private String gender = "";
    private String account = "";
    private String account_type = "";
    private String school = "";
    private String schoolid = "";
    private String major = "";


    private PhotoPopupWindow mPhotoPopupWindow;
    private PhotoPopupWindow genderPhotoPopupWindow;

    private static final int REQUEST_IMAGE_GET = 0;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_SMALL_IMAGE_CUTTING = 2;
    private static final int REQUEST_BIG_IMAGE_CUTTING = 3;

    private static final int REQUEST_NAME_GET = 4;
    private static final int REQUEST_GENDER_GET = 5;
    private static final int REQUEST_ACCOUNT_GET = 6;
    private static final int REQUEST_SCHOOL_GET = 7;
    private static final int REQUEST_SCHOOLID_GET = 8;
    private static final int REQUEST_MAJOR_GET = 9;


    private static final String IMAGE_FILE_NAME = "icon.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_info);
        toolbar = findViewById(R.id.class_toolbar);
        toolbar.setTitle("设置个人信息");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView = findViewById(R.id.set_user_info_icon);
        PictureUtil.readIconFromFile(getApplicationContext(), imageView);

        account = AccountUtil.getAccount(getApplicationContext());
        account_type= AccountUtil.getAccount_type(getApplicationContext());
        name=AccountUtil.getName(getApplicationContext());
        gender=AccountUtil.getGender(getApplicationContext());
        school=AccountUtil.getSchool(getApplicationContext());
        schoolid=AccountUtil.getSchoolId(getApplicationContext());
        major=AccountUtil.getMajor(getApplicationContext());

        set_icon = (LinearLayout) findViewById(R.id.set_user_info_seticon);
        set_name = (LinearLayout) findViewById(R.id.set_user_info_setname);
        set_gender = (LinearLayout) findViewById(R.id.set_user_info_setgender);
        set_account = (LinearLayout) findViewById(R.id.set_user_info_setaccount);
        set_school = (LinearLayout) findViewById(R.id.set_user_info_setschool);
        set_schoolId = (LinearLayout) findViewById(R.id.set_user_info_setschoolid);
        set_major = (LinearLayout) findViewById(R.id.set_user_info_setmajor);

        T_name = (TextView) findViewById(R.id.set_user_info_name);
        T_gender = (TextView) findViewById(R.id.set_user_info_gender);
        T_account = (TextView) findViewById(R.id.set_user_info_account);
        T_school = (TextView) findViewById(R.id.set_user_info_school);
        T_schoolid = (TextView) findViewById(R.id.set_user_info_schoolid);
        T_major = (TextView) findViewById(R.id.set_user_info_major);

        savemsg = (Button) findViewById(R.id.set_user_info_save);

        T_name.setText(name);
        T_gender.setText(gender);
        T_account.setText(account);
        T_school.setText(school);
        T_schoolid.setText(schoolid);
        T_major.setText(major);


        //设置头像点击事件
        set_icon.setOnClickListener(icon_click);
        set_name.setOnClickListener(click);
        set_gender.setOnClickListener(click);
        //set_account.setOnClickListener(click);
        set_school.setOnClickListener(click);
        set_schoolId.setOnClickListener(click);
        set_major.setOnClickListener(click);
        savemsg.setOnClickListener(click);


        set_account.setClickable(false);


        genderPhotoPopupWindow = new PhotoPopupWindow(SetUserInfoActivity.this
                , new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "女";
                T_gender.setText(gender);
                genderPhotoPopupWindow.dismiss();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "男";
                T_gender.setText(gender);
                genderPhotoPopupWindow.dismiss();
            }
        }
        );
        genderPhotoPopupWindow.btn_camera.setText("男");
        genderPhotoPopupWindow.btn_select.setText("女");
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), InputActivity.class);
            switch (view.getId()) {
                case R.id.set_user_info_setname:
                    intent.putExtra("title", "修改姓名");
                    intent.putExtra("content", name);
                    startActivityForResult(intent, REQUEST_NAME_GET);
                    break;
                case R.id.set_user_info_setgender:

//                    intent.putExtra("title", "修改性别");
//                    intent.putExtra("content", gender);
//                    startActivityForResult(intent, REQUEST_GENDER_GET);
                    View rootView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_setting, null);
                    genderPhotoPopupWindow.showAtLocation(rootView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                    break;
                case R.id.set_user_info_setaccount:
                    intent.putExtra("title", "修改手机");
                    intent.putExtra("content", account);
                    startActivityForResult(intent, REQUEST_ACCOUNT_GET);
                    break;
                case R.id.set_user_info_setschool:
                    intent.putExtra("title", "修改学校");
                    intent.putExtra("content", school);
                    startActivityForResult(intent, REQUEST_SCHOOL_GET);
                    break;
                case R.id.set_user_info_setschoolid:
                    intent.putExtra("title", "修改学号");
                    intent.putExtra("content", schoolid);
                    startActivityForResult(intent, REQUEST_SCHOOLID_GET);
                    break;
                case R.id.set_user_info_setmajor:
                    intent.putExtra("title", "修改专业");
                    intent.putExtra("content", major);
                    startActivityForResult(intent, REQUEST_MAJOR_GET);
                    break;
                case R.id.set_user_info_save:

                    if(name.equals("") ||gender.equals("")||account.equals("")||school.equals("")||schoolid.equals("")||major.equals("")){
                        Toast.makeText(getApplicationContext(),"内容不能为空",Toast.LENGTH_SHORT).show();
                    }else{
                        ATask_UpdataUserInfo aTask_Updata_userInfo =new ATask_UpdataUserInfo();
                        aTask_Updata_userInfo.execute();
                    }
                    break;

            }
        }

    };





    private View.OnClickListener icon_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mPhotoPopupWindow = new PhotoPopupWindow(SetUserInfoActivity.this, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 进入相册选择
                    // 文件权限检查
                    Log.d("pic", "你选了相册");
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        // 权限还没有授予，进行申请
                        ActivityCompat.requestPermissions(SetUserInfoActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                200); // 申请的 requestCode 为 200
                    } else {
                        mPhotoPopupWindow.dismiss();
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        // 判断系统中是否有处理该 Intent 的 Activity
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(intent, REQUEST_IMAGE_GET);
                        } else {
                            Toast.makeText(getApplicationContext(), "未找到图片查看器", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 拍照
                    // 拍照及文件权限检查
                    Log.d("pic", "你选了拍照");
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED
                            || ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        // 权限还没有授予，进行申请
                        ActivityCompat.requestPermissions(SetUserInfoActivity.this,
                                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                300); // 申请的 requestCode 为 300
                    } else {
                        // 权限已经申请，直接拍照
                        mPhotoPopupWindow.dismiss();
                        PictureUtil.startCamera(SetUserInfoActivity.this);
                    }
                }
            });
            View rootView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_setting, null);
            mPhotoPopupWindow.showAtLocation(rootView,
                    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        }
    };


    /**
     * 处理权限回调结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPhotoPopupWindow.dismiss();
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    // 判断系统中是否有处理该 Intent 的 Activity
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intent, REQUEST_IMAGE_GET);
                    } else {
                        Toast.makeText(getApplicationContext(), "未找到图片查看器", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mPhotoPopupWindow.dismiss();
                }
                break;
            case 300:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPhotoPopupWindow.dismiss();
                    PictureUtil.startCamera(SetUserInfoActivity.this);

                } else {
                    mPhotoPopupWindow.dismiss();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 回调成功
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 裁剪图片返回，设置头像
                case REQUEST_SMALL_IMAGE_CUTTING:
                    Log.d("pic", "onActivityResult: REQUEST_SMALL_IMAGE_CUTTING");
//                    if (data != null) {
//                        setPicToView(data);
//                    }

                    PictureUtil.readIconFromFile(getApplicationContext(), imageView);

                    ATask_UpdataIcon aTask_icon=new ATask_UpdataIcon();
                    aTask_icon.execute();
//                    PictureUtil.updateIconToServer(SetUserInfoActivity.this,account);
                    break;

                // 相册选取返回调用，裁剪图片
                case REQUEST_IMAGE_GET:
                    Log.d("pic", "onActivityResult: REQUEST_IMAGE_GET");
                    PictureUtil.cropphoto(SetUserInfoActivity.this, data.getData());
                    break;

                // 拍照
                case REQUEST_IMAGE_CAPTURE:
                    Log.d("pic", "onActivityResult: REQUEST_IMAGE_CAPTURE");
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "user_icon.jpg");

                    PictureUtil.cropphoto(SetUserInfoActivity.this, PictureUtil.getDcimUri(getApplicationContext(), file));
//                    File temp = new File(Environment.getExternalStorageDirectory() + "/" + IMAGE_FILE_NAME);
//                    startSmallPhotoZoom(Uri.fromFile(temp));
                    break;
                case REQUEST_NAME_GET:
                    name = data.getStringExtra("input");
                    T_name.setText(name);

                    break;
                case REQUEST_GENDER_GET:
                    gender = data.getStringExtra("input");
                    T_gender.setText(gender);

                    break;
                case REQUEST_ACCOUNT_GET:
                    account = data.getStringExtra("input");
                    T_account.setText(account);

                    break;
                case REQUEST_SCHOOL_GET:
                    school = data.getStringExtra("input");
                    T_school.setText(school);

                    break;
                case REQUEST_SCHOOLID_GET:
                    schoolid = data.getStringExtra("input");
                    T_schoolid.setText(schoolid);

                    break;
                case REQUEST_MAJOR_GET:
                    major = data.getStringExtra("input");
                    T_major.setText(major);
                    break;

            }
        }
    }



    private class ATask_UpdataUserInfo extends AsyncTask<UpdateUserInfoAPI,UpdateUserInfoAPI,UpdateUserInfoAPI > {

        //后台线程执行时
        @Override
        protected UpdateUserInfoAPI doInBackground(UpdateUserInfoAPI... params) {

            UpdateUserInfoAPI updateUserInfoAPI = new UpdateUserInfoAPI(account, account_type, name, school, schoolid, major, gender);
            return updateUserInfoAPI;
        }

        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
        @Override
        protected void onPostExecute(UpdateUserInfoAPI result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(),result.getResponseData(),Toast.LENGTH_SHORT).show();
            if(result.getResponseData().equals("success")){
                finish();
            }
        }
    }

    private class ATask_UpdataIcon extends AsyncTask<UpdateIconAPI,UpdateIconAPI,UpdateIconAPI > {

        //后台线程执行时
        @Override
        protected UpdateIconAPI doInBackground(UpdateIconAPI... params) {
            String path=getApplicationContext().getExternalCacheDir()+ File.separator+"user_icon"+File.separator+"user_icon.jpg";
            UpdateIconAPI updateIconAPI = new UpdateIconAPI(path,account,account_type);
            return updateIconAPI;
        }

        //后台线程执行结束后的操作，其中参数result为doInBackground返回的结果
        @Override
        protected void onPostExecute(UpdateIconAPI result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(),result.getResponseData(),Toast.LENGTH_SHORT).show();

        }
    }


}