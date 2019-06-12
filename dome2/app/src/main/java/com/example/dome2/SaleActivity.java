package com.example.dome2;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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
import java.util.Random;


public class SaleActivity extends AppCompatActivity  implements View.OnClickListener{

    ContentResolver contentResolver ;
    ArrayList<String>tagNames=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment3);
        LinearLayout linearLayout;
        contentResolver = getApplicationContext().getContentResolver();
        tagNames.add("NameTitle");
        tagNames.add("PriceTitle");
        tagNames.add("SaleStatus");


        linearLayout=findViewById(R.id.SaleFatherView);
        contentResolver.query(Uri.parse("content://666/FindSales/1"),null,null,null,null);
        try {
            JSONArray ja = JSONArray.parseArray(Provider.result);
            for(int i=0;i<ja.size();i++){
                JSONObject jo=ja.getJSONObject(i);
                //测试
                System.out.println(jo.getString("name"));
                System.out.println(jo.getString("price"));
                System.out.println(jo.getString("intro"));
                System.out.println(jo.getString("isSaled"));
                System.out.println(jo.getString("id"));
                //测试


                final LinearLayout childView = (LinearLayout) LayoutInflater.from(SaleActivity.this).inflate(R.layout.gamesale, null);
                linearLayout.addView(childView,i);
                ArrayList<String> textLists=new ArrayList<>();
                textLists.add(jo.getString("name"));
                textLists.add("价格:"+jo.getString("price"));
                textLists.add("销售情况:"+jo.getString("isSaled"));
                ImageView imageView=childView.findViewWithTag("imgeTitle");
                imageView.setImageResource(R.mipmap.toy1+i%7);
                SetValues(childView,tagNames,textLists);
            }
            Log.i("RESULT:",ja.toJSONString());
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
