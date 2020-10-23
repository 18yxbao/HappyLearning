package com.example.happylearning.Student;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.happylearning.Data.PictureUtil;
import com.example.happylearning.Login.SetUserInfoActivity;
import com.example.happylearning.R;
import com.example.happylearning.Setting.PhotoPopupWindow;
import java.io.File;

import static android.app.Activity.RESULT_OK;

@SuppressLint("ValidFragment")
public class SettingFragment extends Fragment {
    private static final int REQUEST_IMAGE_GET = 0;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_SMALL_IMAGE_CUTTING = 2;
    private static final int REQUEST_BIG_IMAGE_CUTTING = 3;
    private static final String IMAGE_FILE_NAME = "icon.jpg";
    private PhotoPopupWindow mPhotoPopupWindow;
    private Activity context;

    private LinearLayout mysetting;
    private LinearLayout setting;
    private TextView account_text;
    private TextView name_text;

    private String account;
    private Button logout;
    private ImageView imageView;


    public SettingFragment(String account) {
        this.account=account;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        mysetting=(LinearLayout) view.findViewById(R.id.main_setting_mysetting);
        setting=(LinearLayout) view.findViewById(R.id.main_setting_detailed_setting);
        account_text=(TextView) view.findViewById(R.id.main_setting_account);
        name_text=(TextView) view.findViewById(R.id.main_setting_name);

        account_text.setText(account);
        name_text.setText(account);

        mysetting.setOnClickListener(click);
        setting.setOnClickListener(click);


        //logout=view.findViewById(R.id.main_setting_logout);
        imageView=view.findViewById(R.id.main_setting_icon);

        account_text.setText(account);
        name_text.setText(account);
        PictureUtil.readIconFromFile(context,imageView);

        //设置头像点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhotoPopupWindow = new PhotoPopupWindow(context, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 进入相册选择
                        // 文件权限检查
                        Log.d("pic", "你选了相册");
                        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            // 权限还没有授予，进行申请
                            ActivityCompat.requestPermissions(context,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    200); // 申请的 requestCode 为 200
                        } else {
                            mPhotoPopupWindow.dismiss();
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            // 判断系统中是否有处理该 Intent 的 Activity
                            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                                startActivityForResult(intent, REQUEST_IMAGE_GET);
                            } else {
                                Toast.makeText(context, "未找到图片查看器", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 拍照
                        // 拍照及文件权限检查
                        Log.d("pic", "你选了拍照");
                        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED
                                || ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            // 权限还没有授予，进行申请
                            ActivityCompat.requestPermissions(context,
                                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    300); // 申请的 requestCode 为 300
                        } else {
                            // 权限已经申请，直接拍照
                            mPhotoPopupWindow.dismiss();
                            PictureUtil.startCamera(SettingFragment.this,context);
                        }
                    }
                });
                View rootView = LayoutInflater.from(context).inflate(R.layout.fragment_setting, null);
                mPhotoPopupWindow.showAtLocation(rootView,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

        return view;
    }



    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            Intent intent;
            switch(view.getId()){
                case R.id.main_setting_mysetting:
                    intent=new Intent(view.getContext(), SetUserInfoActivity.class);
                    intent.putExtra("Ser",mPhotoPopupWindow);
                    view.getContext().startActivity(intent);
                    break;
                case R.id.main_setting_detailed_setting:
                    intent=new Intent(view.getContext(), DetailedSettingsActivity.class);
                    view.getContext().startActivity(intent);
                    break;

            }
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
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivityForResult(intent, REQUEST_IMAGE_GET);
                    } else {
                        Toast.makeText(context, "未找到图片查看器", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mPhotoPopupWindow.dismiss();
                }
                break;
            case 300:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPhotoPopupWindow.dismiss();
                    PictureUtil.startCamera(SettingFragment.this,context);
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
                // 小图切割
                case REQUEST_SMALL_IMAGE_CUTTING:
                    Log.d("pic", "onActivityResult: REQUEST_SMALL_IMAGE_CUTTING");
//                    if (data != null) {
//                        setPicToView(data);
//                    }
                    PictureUtil.readIconFromFile(context,imageView);
                    break;

                // 相册选取
                case REQUEST_IMAGE_GET:
                    Log.d("pic", "onActivityResult: REQUEST_IMAGE_GET");
                    PictureUtil.cropphoto(SettingFragment.this,data.getData());
                    break;

                // 拍照
                case REQUEST_IMAGE_CAPTURE:
                    Log.d("pic", "onActivityResult: REQUEST_IMAGE_CAPTURE");
                    File file=new File(Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"user_icon.jpg");
                    PictureUtil.cropphoto(SettingFragment.this, PictureUtil.getDcimUri(context,file));
//                    File temp = new File(Environment.getExternalStorageDirectory() + "/" + IMAGE_FILE_NAME);
//                    startSmallPhotoZoom(Uri.fromFile(temp));
                    break;
            }
        }
    }

}

