package com.example.happylearning.Student.Class;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.happylearning.API.AddPostListAPI;
import com.example.happylearning.Data.AccountUtil;
import com.example.happylearning.Data.TimeUtil;
import com.example.happylearning.R;

/**
 * 发微博
 */
public class PublicPostActivity extends AppCompatActivity {
    private TextView content;
    private ImageView picture1;
    private ImageView picture2;
    private ImageView picture3;
    private Button sendButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_post);
        getSupportActionBar().setTitle("发送讨论消息");
        content=findViewById(R.id.public_post_content);
        picture1=findViewById(R.id.public_post_picture1);
        picture2=findViewById(R.id.public_post_picture2);
        picture3=findViewById(R.id.public_post_picture3);
        sendButton=findViewById(R.id.public_post_send);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contentText=content.getText().toString();
                String class_number=getIntent().getStringExtra("class_number");
                Context context=PublicPostActivity.this;
                TalkAtask talkAtask=new TalkAtask(class_number,AccountUtil.getAccount(context),AccountUtil.getName(context),
                        contentText,"0","0",null, TimeUtil.getTime(),"0");
                talkAtask.execute();
            }
        });
    }

    private class TalkAtask extends AsyncTask<AddPostListAPI,AddPostListAPI,AddPostListAPI> {
        private String class_number;
        private String user_number;
        private String user_name;
        private String post_content;
        private String content_type;
        private String image_number;
        private String[] image_src;
        private String post_date;
        private String reply_id;

        public TalkAtask(String class_number,String user_number, String user_name,String post_content,String content_type,
                         String image_number, String[] image_src, String post_date, String reply_id) {
            this.class_number=class_number;
            this.user_number=user_number;
            this.user_name=user_name;
            this.post_content=post_content;
            this.content_type=content_type;
            this.image_number=image_number;
            this.image_src=image_src;
            this.post_date=post_date;
            this.reply_id=reply_id;
        }

        @Override
        protected AddPostListAPI doInBackground(AddPostListAPI... seeNoticeListAPIS) {

            return new AddPostListAPI(class_number, user_number,user_name,post_content,content_type,
                    image_number,image_src,post_date,reply_id);
        }

        @Override
        protected void onPostExecute(AddPostListAPI result) {
            super.onPostExecute(result);
            String login_result = result.getResponseData();
            Toast.makeText(getApplicationContext(),login_result,Toast.LENGTH_SHORT).show();
        }
    }
}
