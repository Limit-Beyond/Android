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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

//这个页面和commondetail功能一样
//私聊的跳转已加
public class Playerdetail extends AppCompatActivity {

    ContentResolver contentResolver;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playerdetail);
        Button slbtn=(Button)findViewById(R.id.slbtn);
        //intent接收上个页面的数据 id
        final Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("data");
        int id = data.getInt("id");
        String type = data.getString("type");
        contentResolver = getApplicationContext().getContentResolver();
        linearLayout=findViewById(R.id.fatherView);
        contentResolver.query(Uri.parse("content://666/PlayerInfo"),null,null,null,null);

        JSONArray ja = JSONArray.parseArray(Provider.result);
        JSONObject jo=ja.getJSONObject(id);




        slbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Playerdetail.this,Wenziliaotian.class);
                startActivity(intent);
                             }
        });
    }
}