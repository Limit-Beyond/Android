package com.example.dome2;

import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;


public class OrderActivity extends AppCompatActivity  implements View.OnClickListener{

    ContentResolver contentResolver ;
    /**
     * 这个id应为前一个页面传来的id
     */

    ArrayList<String> tagNames=new ArrayList<>();
    String customer_id = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        LinearLayout linearLayout;
        tagNames.add("Nametitle");
        tagNames.add("SexTitle");
        tagNames.add("PriceTitle");

        contentResolver = getApplicationContext().getContentResolver();
        linearLayout=findViewById(R.id.OrderFatherView);

        contentResolver.query(Uri.parse("content://666/FindOrders/"+1),null,null,null,null);
        try {
            JSONArray ja = JSONArray.parseArray(Provider.result);
            for(int i=0;i<ja.size();i++){
                ConstraintLayout childView = (ConstraintLayout) LayoutInflater.from(OrderActivity.this).inflate(R.layout.commonitem, null);
                JSONObject jo=ja.getJSONObject(i);
                ArrayList<String> textLists=new ArrayList<>();
                textLists.add(jo.getString("pkid"));
                textLists.add(jo.getString("orderstatus"));
                textLists.add(jo.getString("intime"));
                textLists.add(jo.getString("status"));
                SetValues(childView,tagNames,textLists);
                linearLayout.addView(childView,i);
            }
            Log.i("RESULT:",ja.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void SetValues(ConstraintLayout linearLayout, ArrayList <String>tagNames,ArrayList <String>ViewsText){
        for(int i=0;i<tagNames.size();i++){
            TextView textView= linearLayout.findViewWithTag(tagNames.get(i));
            System.out.println("开始");
            System.out.println(textView.getText().toString());
            System.out.println("结束");
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
