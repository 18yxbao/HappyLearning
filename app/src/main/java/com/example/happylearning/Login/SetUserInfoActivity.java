package com.example.happylearning.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.happylearning.Data.PictureUtil;
import com.example.happylearning.R;
import com.example.happylearning.Setting.PhotoPopupWindow;
import com.example.happylearning.Student.SettingFragment;

import java.io.File;

public class SetUserInfoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imageView;
    private PhotoPopupWindow mPhotoPopupWindow;
    private static final int REQUEST_IMAGE_GET = 0;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_SMALL_IMAGE_CUTTING = 2;
    private static final int REQUEST_BIG_IMAGE_CUTTING = 3;
    private static final String IMAGE_FILE_NAME = "icon.jpg";

    private LinearLayout set_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_info);
        toolbar = findViewById(R.id.class_toolbar);
        toolbar.setTitle("设置个人信息");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView=findViewById(R.id.set_user_info_icon);

        PictureUtil.readIconFromFile(getApplicationContext(),imageView);

        set_icon=(LinearLayout) findViewById(R.id.set_user_info_seticon);

        //设置头像点击事件
        set_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



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
                    PictureUtil.readIconFromFile(getApplicationContext(),imageView);
                    break;

                // 相册选取返回调用，裁剪图片
                case REQUEST_IMAGE_GET:
                    Log.d("pic", "onActivityResult: REQUEST_IMAGE_GET");
                    PictureUtil.cropphoto(SetUserInfoActivity.this,data.getData());
                    break;

                // 拍照
                case REQUEST_IMAGE_CAPTURE:
                    Log.d("pic", "onActivityResult: REQUEST_IMAGE_CAPTURE");
                    File file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"user_icon.jpg");

                    PictureUtil.cropphoto(SetUserInfoActivity.this, PictureUtil.getDcimUri(getApplicationContext(),file));
//                    File temp = new File(Environment.getExternalStorageDirectory() + "/" + IMAGE_FILE_NAME);
//                    startSmallPhotoZoom(Uri.fromFile(temp));
                    break;
            }
        }
    }

}