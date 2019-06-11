package com.example.dome2;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        final Intent intent = getIntent();
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
            JSONArray ja=JSONArray.parseArray(Provider.result);
            for(int i=0;i<ja.size();i++){
                JSONObject jo=ja.getJSONObject(i);

                //测试
                System.out.println(jo.getString("name"));
                System.out.println(jo.getString("cdk"));
                System.out.println(jo.getString("name"));//price
                //测试

//                System.out.println(jo.getString("price"));//暂时没有price
//                LinearLayout childView = (LinearLayout) LayoutInflater.from(CdkActivity.this).inflate(R.layout.commonitem, null);
                final ConstraintLayout childView = (ConstraintLayout) LayoutInflater.from(CdkActivity.this).inflate(R.layout.commonitem, null);
                childView.setId(i);
                childView.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Intent intent1=new Intent(CdkActivity.this,CdkDetail.class);
                        Bundle data = new Bundle();
                        data.putInt("id",childView.getId());
                        data.putString("type","cdk");
                        intent1.putExtra("data",data);
                        startActivity( intent1);
                    }
                });
                linearLayout.addView(childView,i);
                ArrayList<String> textLists=new ArrayList<>();
                textLists.add(jo.getString("name"));
                textLists.add("价格:"+jo.getString("price").substring(0,jo.getString("price").indexOf("."))+"  ");
                textLists.add(String.valueOf(new Random().nextInt(10))+"件在售");
                ImageView imageView=childView.findViewWithTag("imgeTitle");
                imageView.setImageResource(R.mipmap.game1+i%7);
                SetValues(childView,tagNames,textLists);
            }
    }catch (Exception e){
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
