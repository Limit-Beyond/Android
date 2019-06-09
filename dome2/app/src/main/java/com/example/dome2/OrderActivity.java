package com.example.dome2;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class OrderActivity extends AppCompatActivity  implements View.OnClickListener{

    ContentResolver contentResolver ;
    /**
     * 这个id应为前一个页面传来的id
     */
    String customer_id = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cdkdetail);

        contentResolver = getApplicationContext().getContentResolver();

        contentResolver.query(Uri.parse("content://666/FindOrders/"+customer_id),null,null,null,null);
        try {
            JSONArray ja = JSONArray.parseArray(Provider.result);
            Log.i("RESULT:",ja.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
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
