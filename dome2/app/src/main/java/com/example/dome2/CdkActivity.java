package com.example.dome2;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.Random;


public class CdkActivity extends AppCompatActivity  implements View.OnClickListener{

    ContentResolver contentResolver ;
    /**
     * 这个id应为前一个页面传来的id
     */
    String spot_id = "1";
    ArrayList<String>tagNames=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cdkdetail);
        LinearLayout linearLayout;

        tagNames.add("Nametitle");
        tagNames.add("SexTitle");
        tagNames.add("PriceTitle");

        //id传递
        //id传递
        //id传递
        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("data");
        int id = data.getInt("id");
        System.out.println("iiiiiiiiiiiid: "+id);
        //id传递
        //id传递
        //id传递



        contentResolver = getApplicationContext().getContentResolver();

        linearLayout=findViewById(R.id.CdkFatherView);
        contentResolver.query(Uri.parse("content://666/FindCdks/" + spot_id), null, null, null, null);
        System.out.println(Provider.result);

        try {
            JSONArray x=JSONArray.parseArray(Provider.result);
            System.out.println("大小为     "+x.size());
            JSONObject jo = JSONObject.parseObject(JSONArray.parseArray(Provider.result).getString(0));
            System.out.println(jo.toString());
            JSONArray jsonArray=jo.getJSONArray("doodles");
            String intro="";//游戏介绍
            String lo="";//游戏价格
            String cdk="";//游戏cdk

            for(int i=0;i<jsonArray.size();i++){
                LinearLayout childView = (LinearLayout) LayoutInflater.from(CdkActivity.this).inflate(R.layout.item, null);
                linearLayout.addView(childView,i);
                ArrayList<String> textLists=new ArrayList<>();
                JSONObject jsonObject= (JSONObject) jsonArray.get(i);
                cdk=jsonObject.getString("video");//cdk
                intro=jsonObject.getString("intro");//intro
                lo=String.valueOf(new Random().nextInt(200)+50);
                textLists.add(lo);
                textLists.add(cdk);
                textLists.add(intro);
                SetValues(childView,tagNames,textLists);
                JSONObject job = jsonArray.getJSONObject(i);

                String nametitle = job.getString("name");
                String price = job.getString("price");
                String location = job.getString("location");
                System.out.println("属性列表开始");
                System.out.println(nametitle);
                System.out.println(price);
                System.out.println(location);
                System.out.println("属性列表结束");
                textLists.add(nametitle);
                textLists.add(price);
                textLists.add(location);
                SetValues(childView,tagNames,textLists);
            }

            Log.i("RESULT:",jo.toJSONString());
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
