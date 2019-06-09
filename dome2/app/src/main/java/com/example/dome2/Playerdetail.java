package com.example.dome2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
//这个页面和commondetail功能一样
//私聊的跳转已加
public class Playerdetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playerdetail);
        Button slbtn=(Button)findViewById(R.id.slbtn);
        slbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Playerdetail.this,Wenziliaotian.class);
                startActivity(intent);
                             }
        });
    }
}