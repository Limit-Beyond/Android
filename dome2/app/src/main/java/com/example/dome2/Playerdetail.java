package com.example.dome2;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

//这个页面和commondetail功能一样
//私聊的跳转已加
public class Playerdetail extends AppCompatActivity {


    ContentResolver contentResolver ;
    TextView textView2=null;
    TextView textView3=null;
    TextView textView4=null;
    ImageView imageView=null;
    Button button1=null;//私聊
    Button button2=null;//提交订单
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playerdetail);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        textView4=findViewById(R.id.textView4);
        imageView=findViewById(R.id.imageView);
        Button slbtn=(Button)findViewById(R.id.slbtn);
        button2=(Button)findViewById(R.id.login1);
        //intent接收上个页面的数据 id
        final Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("data");
        int id = data.getInt("id");
        String type = data.getString("type");
        contentResolver = getApplicationContext().getContentResolver();
        linearLayout=findViewById(R.id.fatherView);
        contentResolver.query(Uri.parse("content://666/PlayerInfo/"+(id+1)),null,null,null,null);

        JSONObject playerjo=JSONObject.parseObject(Provider.result);
        textView2.setText("名称: "+playerjo.getString("name"));
        textView3.setText("价格: "+playerjo.getString("price"));
        textView4.setText("简介: "+playerjo.getString("intro"));
        imageView.setImageResource(R.mipmap.character3+id%7);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Playerdetail.this,MainActivity.class);
                startActivity(intent1);
            }
        });


        slbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Playerdetail.this,Wenziliaotian.class);
                startActivity(intent);
                             }
        });
    }
}