package com.example.happylearning.Data;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.happylearning.BuildConfig;
import com.example.happylearning.Setting.PhotoPopupWindow;
import com.example.happylearning.Student.SettingFragment;

import java.io.File;
import java.io.IOException;

public class PictureUtil {
    private static final int REQUEST_IMAGE_GET = 0;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_SMALL_IMAGE_CUTTING = 2;
    private static final int REQUEST_BIG_IMAGE_CUTTING = 3;

    public static Uri getDcimUri(Context context,File file){
        Uri uri;
//        File file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+
//                File.separator+"user_icon.jpg");
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context,"com.example.happylearning.fileProvider", file);
        } else {
            uri = Uri.fromFile(file);
        }

        return uri;
    }

    //拍照
    public static void startCamera(Activity activity){
        Log.d("evan", "*****************打开相机********************");




        File file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+File.separator+"user_icon.jpg");
        Uri uri=getDcimUri(activity.getApplicationContext(),file);
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//intent隐式调用启动拍照界面
        intent.putExtra("return-data",false);//该属性设置为false表示拍照后不会将数据返回到onResluet方法中（建议设置为false，这样获取的图片会比较清晰）
        ComponentName componentName=intent.resolveActivity(activity.getApplicationContext().getPackageManager());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);//该属性设置的是拍照后图片保存的位置
        //防止app启动意图时崩溃
        if (componentName!=null){
            activity.startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
        }
    }

    //从相册中选取
    public static void startPhoto(Activity activity){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);//intent隐式调用启动相册界面
        intent.setType("image/*");//设置数据类型
        ComponentName componentName = intent.resolveActivity(activity.getApplicationContext().getPackageManager());
        Log.d("pic", "startPhoto: "+componentName);
        if (componentName != null) {//防止启动意图时app崩溃
            activity.startActivityForResult(intent, REQUEST_IMAGE_GET);
        }
    }

    //裁剪选取或拍摄的图片
    public static void cropphoto(Activity activity, Uri uri){
        //设置裁剪图片保存位置
        File bomb=new File(activity.getApplicationContext().getExternalCacheDir(),"bmob");
        Log.d("pic", "cropphoto: "+bomb);
        if (!bomb.exists()){
            bomb.mkdir();
        }
        File file=new File(bomb,"user_icon.jpg");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Intent intent=new Intent("com.android.camera.action.CROP");//intent隐式调用启动拍照界面
        intent.setDataAndType(uri,"image/*");//设置需要裁剪的图片地址
        intent.putExtra("crop", "true");//通过put（key，value）方法设置相关属相
        intent.putExtra("aspectX", 1);//设置图片宽高比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 240);//设置图片宽高
        intent.putExtra("outputY", 240);
        intent.putExtra("return-data", false);//该属性设置为false表示拍照后不会将数据返回到onResluet方法中（建议设置为false，这样获取的图片会比较清晰）
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));//该属性设置的是拍照后图片保存的位置
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//设置输出格式
        intent.putExtra("noFaceDetection", true);//是否取消人脸识别
        /*ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        Log.d("TAG", "cropphoto: "+componentName);
        if (componentName!=null){
            fragment.startActivityForResult(intent,Variable.request_crop);
        }*/
        activity.startActivityForResult(intent,REQUEST_SMALL_IMAGE_CUTTING);
    }

    public static void readIconFromFile(Context context, ImageView imageView){
        BitmapFactory.Options op=new BitmapFactory.Options();
        op.inPreferredConfig=Bitmap.Config.ARGB_4444;
        String path=context.getExternalCacheDir()+
                File.separator+"bmob"+File.separator+"user_icon.jpg";//裁剪好的图片保存位置
        Bitmap icon=BitmapFactory.decodeFile(path,op);//加载本地图片，并获取大小合适的bitmap
        if (icon!=null){
            imageView.setImageBitmap(icon);//将获取的图片设置到imagerview
        }
    }
}
