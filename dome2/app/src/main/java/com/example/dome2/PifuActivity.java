package com.example.dome2;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;


public class PifuActivity extends AppCompatActivity  implements View.OnClickListener{

    ContentResolver contentResolver ;
    ArrayList<String>tagNames=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skin);
        LinearLayout linearLayout=findViewById(R.id.SkinFatherView);

        contentResolver = getApplicationContext().getContentResolver();

        contentResolver.query(Uri.parse("content://666/FindPifus/1"),null,null,null,null);

        tagNames.add("Nametitle");
        tagNames.add("SexTitle");
        tagNames.add("PriceTitle");


        //    皮肤_leader name（名称） spot_id（对应游戏类别），price，intro

        try {
            JSONArray ja = JSONArray.parseArray(Provider.result);
           for(int i=0;i<ja.size();i++){
               JSONObject jo=ja.getJSONObject(i);
               final  LinearLayout childView = (LinearLayout) LayoutInflater.from(PifuActivity.this).inflate(R.layout.item, null);


               childView.setId(i);
               childView.setOnClickListener(new View.OnClickListener(){

                   @Override
                   public void onClick(View v) {
                       Intent intent1=new Intent(PifuActivity.this,CdkDetail.class);
                       Bundle data = new Bundle();
                       data.putInt("id",childView.getId());
                       data.putString("type","pifu");
                       intent1.putExtra("data",data);
                       startActivity( intent1);
                   }
               });


               childView.setId(i);
               linearLayout.addView(childView,i);
               ArrayList<String> textLists=new ArrayList<>();
               textLists.add(jo.getString("name"));
               textLists.add(jo.getString("intro"));
               textLists.add(jo.getString("price"));
               ImageView imageView=childView.findViewWithTag("imgeTitle");
               imageView.setImageResource(R.mipmap.skin1+i%9);
               SetValues(childView,tagNames,textLists);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void SetValues(LinearLayout linearLayout, ArrayList <String>tagNames,ArrayList <String>ViewsText){

        for(int i=0;i<tagNames.size();i++){
            TextView textView= linearLayout.findViewWithTag(tagNames.get(i));
            textView.setText(ViewsText.get(i));
        }

    }



    @Override
    public void onClick(View view) {
        int vid = view.getId();
        switch (vid){


        }


    }


    /**
     * 花里胡哨的动画
     */
    private void initAnimation(TextView textView, int position) {
        switch (position) {
            case 1:
                TranslateAnimation mLeftAnimation1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                        0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                mLeftAnimation1.setDuration(1000);
                textView.startAnimation(mLeftAnimation1);
                textView.animate().alpha(1);
                break;
            case 2:
                TranslateAnimation mLeftAnimation2 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                        0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                mLeftAnimation2.setDuration(1000);
                textView.startAnimation(mLeftAnimation2);
                textView.animate().alpha(1);
                break;

        }

    }

}
