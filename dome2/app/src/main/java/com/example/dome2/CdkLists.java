package com.example.dome2;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class CdkLists extends AppCompatActivity implements View.OnClickListener {


    ContentResolver contentResolver ;
    /**
     * 这个id应为前一个页面传来的id
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toy);


        //id传递
        //id传递
        //id传递
//        Intent intent = getIntent();
//        Bundle data = intent.getBundleExtra("data");
//        int id = data.getInt("id");
//        System.out.println("iiiiiiiiiiiid: "+id);
        //id传递
        //id传递
        //id传递



        contentResolver = getApplicationContext().getContentResolver();


        contentResolver.query(Uri.parse("content://666/CdkInfo"), null, null, null, null);
        System.out.println(Provider.result);

        try {
            JSONObject jo = JSONObject.parseObject(JSONArray.parseArray(Provider.result).getString(0));
            System.out.println(jo.toString());
            Log.i("RESULT:",jo.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onClick(View v) {

    }
}
