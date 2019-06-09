package com.example.dome2;

import android.content.ContentResolver;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Random;


public class PlayerActivity extends AppCompatActivity  implements View.OnClickListener{

    ContentResolver contentResolver ;
    LinearLayout linearLayout;
    ArrayList<String>tagNames=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);
        tagNames.add("Nametitle");
        tagNames.add("SexTitle");
        tagNames.add("PriceTitle");
        contentResolver = getApplicationContext().getContentResolver();
        linearLayout=findViewById(R.id.fatherView);
        contentResolver.query(Uri.parse("content://666/FindPlayers/1"),null,null,null,null);
        try {
            JSONArray ja = JSONArray.parseArray(Provider.result);
            if(ja.size()>0){
                for(int i=0;i<ja.size();i++){
                    LinearLayout childView = (LinearLayout) LayoutInflater.from(PlayerActivity.this).inflate(R.layout.item, null);
                    linearLayout.addView(childView,i);
                    ArrayList<String> textLists=new ArrayList<>();
                    JSONObject job = ja.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象

                    //暂定服务器
                    ImageView imageView=childView.findViewWithTag("imgeTitle");
                    imageView.setImageResource(R.mipmap.character3+i%6);
                    String nametitle = job.getString("name");
                    String price = job.getString("price");
                    String location = job.getString("location");
                    textLists.add(nametitle);
                    textLists.add(price);
                    textLists.add(location);
                    SetValues(childView,tagNames,textLists);

                }
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
