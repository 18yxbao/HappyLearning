package com.example.happylearning.Student.Class;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happylearning.API.PostAPI.AddPostListAPI;
import com.example.happylearning.API.PostAPI.GetPostListAPI;
import com.example.happylearning.Adapter.TiebaRecyclerViewAdapter;
import com.example.happylearning.Bean.PostBean;
import com.example.happylearning.Data.AccountUtil;
import com.example.happylearning.Data.Filedata;
import com.example.happylearning.Data.TimeUtil;
import com.example.happylearning.R;

import java.util.ArrayList;
import java.util.List;

public class CommentDetailActivity extends AppCompatActivity {
    private String replyID;
    private String classID;

    private RecyclerView recyclerView;
    private TiebaRecyclerViewAdapter adapter;
    private List<PostBean> postBeanList= new ArrayList<PostBean>();

    private EditText editText;
    private Button sendComment;

    private ImageView icon;
    private TextView userT;
    private TextView timeT;
    private TextView content;
    private ImageView picture1;
    private ImageView picture2;
    private ImageView picture3;
    private TextView star;
    private TextView comment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_comment);
        Intent intent=getIntent();

        icon=findViewById(R.id.detailed_comment_icon);
        userT =findViewById(R.id.detailed_comment_username);
        timeT = findViewById(R.id.detailed_comment_time);
        content=findViewById(R.id.detailed_comment_content);
        picture1=findViewById(R.id.detailed_comment_picture1);
        picture2=findViewById(R.id.detailed_comment_picture2);
        picture3=findViewById(R.id.detailed_comment_picture3);
        star=findViewById(R.id.detailed_comment_star);
        comment=findViewById(R.id.detailed_comment_comment);

        replyID=intent.getStringExtra("replyID");
        classID=intent.getStringExtra("classID");
        icon.setImageBitmap(Filedata.bytesToBitmap(intent.getByteArrayExtra("icon")));
        userT.setText(intent.getStringExtra("userT"));
        timeT.setText(intent.getStringExtra("timeT"));
        content.setText(intent.getStringExtra("content"));
        star.setText(intent.getStringExtra("star")+"点赞");
        comment.setText(intent.getStringExtra("comment")+"评论");

        recyclerView = findViewById(R.id.detailed_comment_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(CommentDetailActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new TiebaRecyclerViewAdapter(postBeanList,classID,AccountUtil.getAccount(CommentDetailActivity.this),CommentDetailActivity.this);
        recyclerView.setAdapter(adapter);

        editText=findViewById(R.id.detailed_comment_edittext);
        sendComment=findViewById(R.id.detailed_comment_send);
        sendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contentText=editText.getText().toString();
                SendCommentTask talkAtask=new SendCommentTask(classID,
                        AccountUtil.getAccount(CommentDetailActivity.this),AccountUtil.getAccount_type(CommentDetailActivity.this),
                        AccountUtil.getName(CommentDetailActivity.this), contentText,"0","0",null,
                        TimeUtil.getTime(),replyID);
                talkAtask.execute();
            }
        });
        GetPostListAtask atask=new GetPostListAtask(classID,
                AccountUtil.getAccount(CommentDetailActivity.this),
                AccountUtil.getAccount_type(CommentDetailActivity.this),replyID);
        atask.execute();
    }

    private class SendCommentTask extends AsyncTask<AddPostListAPI,AddPostListAPI,AddPostListAPI> {
        private String class_number;
        private String user_number;
        private String user_type;
        private String user_name;
        private String post_content;
        private String content_type;
        private String image_number;
        private String[] image_src;
        private String post_date;
        private String reply_id;

        public SendCommentTask(String class_number,String user_number, String user_type, String user_name,String post_content,
                         String content_type, String image_number, String[] image_src, String post_date, String reply_id) {
            this.class_number=class_number;
            this.user_number=user_number;
            this.user_type=user_type;
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

            return new AddPostListAPI(class_number, user_number,user_type,user_name,post_content,content_type,
                    image_number,image_src,post_date,reply_id);
        }

        @Override
        protected void onPostExecute(AddPostListAPI result) {
            super.onPostExecute(result);
            String login_result = result.getResponseData();
            Toast.makeText(getApplicationContext(),login_result,Toast.LENGTH_SHORT).show();
            if(login_result.equals("success")) editText.setText("");
        }
    }

    private class GetPostListAtask extends AsyncTask<GetPostListAPI,GetPostListAPI,GetPostListAPI>{
        String classID;
        String userNum;
        String user_type;
        String reply_id;
        public GetPostListAtask(String classID,String userNum,String user_type,String reply_id) {
            this.classID=classID;
            this.userNum=userNum;
            this.user_type=user_type;
            this.reply_id=reply_id;
        }

        @Override
        protected GetPostListAPI doInBackground(GetPostListAPI... seeNoticeListAPIS) {

            return new GetPostListAPI(classID,userNum,user_type,reply_id);
        }

        @Override
        protected void onPostExecute(GetPostListAPI result) {
            super.onPostExecute(result);
            String seeNoticeListAPI_result = result.getResponseData();
            if(seeNoticeListAPI_result.equals("fail")){
                Toast.makeText(CommentDetailActivity.this, "还没有人回复这条消息，快去发一个吧！", Toast.LENGTH_SHORT).show();
                return;
            }
            if(seeNoticeListAPI_result.equals("")){
                Toast.makeText(CommentDetailActivity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                postBeanList.clear();
                String[] spiltResult=seeNoticeListAPI_result.split("\n");

                for(int i=0;i<spiltResult.length;i++){
                    Log.d("123456789", "onPostExecute: "+spiltResult[i]);
                    String[] temp=spiltResult[i].split(",@.#");
                    PostBean postBean=new PostBean();
                    if(temp[0].equals("like")){
                        postBean.setStar("1");
                    }else if(temp[0].equals("dislike")){
                        postBean.setStar("0");
                    }else{
                        Toast.makeText(CommentDetailActivity.this,"解析出错",Toast.LENGTH_SHORT).show();
                    }
                    int j=1;
                    postBean.setUserType(temp[j++]);
                    String iconStr=temp[j++];
                    postBean.setIcon(Filedata.stringtoBitmap(iconStr));

                    postBean.setId(temp[j++]);
                    postBean.setUserid(temp[j++]);
                    postBean.setUsername(temp[j++]);
                    postBean.setContent(temp[j++]);
                    postBean.setPictureNum(temp[j++]);
                    postBean.setCommentNum(temp[j++]);
                    postBean.setTime(temp[j++]);
                    postBean.setStarNum(temp[j++]);

                    postBeanList.add(postBean);

                }
                Log.d("12345678", "onPostExecute: "+postBeanList.toString());
                adapter.notifyDataSetChanged();
            }
        }
    }

}
