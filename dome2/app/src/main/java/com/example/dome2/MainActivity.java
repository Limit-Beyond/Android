
package com.example.dome2;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


import com.zhenzi.sms.ZhenziSmsClient;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity   {
    final String apiUrl = "https://sms_developer.zhenzikj.com";
    final String appSecret = "7870cce1-cd97-4bc7-b91a-33bcef783d03";
    final String appId = "101771";

    private BottomNavigationView bottomNavigationView;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private Fragment5 fragment5;
    private Fragment[] fragments;

    private int count=0;
    NetBroadcastReceiver  netBroadcastReceiver=null;
    private int lastfragment;//用于记录上个选择的Fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        Timer timer = new Timer(true);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Notification();
            }
        };
        timer.schedule(timerTask, 0,30000);


Thread t = new Thread(new Runnable() {
    @Override
    public void run() {
        ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
        try {

            String result = client.send("15868871857", "恰饭恰饭");
            Log.i("resultsssssssssss",result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
});
t.start();


    }



public void  Notification( ){
    NotificationUtils notificationUtils = new NotificationUtils(this);
    count++;
    Bitmap abcd =  BitmapFactory.decodeResource(getResources(), R.drawable.game+count%4);
    notificationUtils.sendNotification("Battlefield Three", "广阔开放的环境，到密闭的室内枪战，无所不能。以前所未有的高画质毁灭效果、新玩法、更佳的持久性，以及包括ACW-R卡宾枪、M417狙 击步枪、L86LSW机枪和MP5K战术冲锋手枪等更多武器，《战地风云3：正面交锋》带给玩家越加精彩丰富的体验。",abcd);
}

    //初始化fragment和fragment数组
    private void initFragment()
    {

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();
        fragments = new Fragment[]{fragment1,fragment2,fragment3,fragment4,fragment5};
        lastfragment=0;
        getSupportFragmentManager().beginTransaction().replace(R.id.mainview,fragment1).show(fragment1).commit();
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bnv);

        bottomNavigationView.setOnNavigationItemSelectedListener(changeFragment);
    }
    //判断选择的菜单
    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.id1:
                {
                    if(lastfragment!=0)
                    {
                        switchFragment(lastfragment,0);
                        lastfragment=0;

                    }

                    return true;
                }
                case R.id.id2:
                {
                    
                    if(lastfragment!=1)
                    {
                        switchFragment(lastfragment,1);
                        lastfragment=1;

                    }
                    return true;

                }
                case R.id.id3:
                {
                    if(lastfragment!=2)
                    {
                        switchFragment(lastfragment,2);
                        lastfragment=2;

                    }



                    return true;
                }
                case R.id.id4:
                {
                    if(lastfragment!=3)
                    {
                        switchFragment(lastfragment,3);
                        lastfragment=3;

                    }

                    return true;
                }
                case R.id.id5:
                {
//                    if(lastfragment!=4)
//                    {
//                        switchFragment(lastfragment,4);
//                        lastfragment=4;
//
//                    }
                    Intent intent =new Intent(MainActivity.this,TermActivity.class);
                    startActivity(intent);


                    return true;
                }


            }
            return false;
        }
    };



    //切换Fragment
    private void switchFragment(int lastfragment,int index)
    {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if(fragments[index].isAdded()==false)
        {
            transaction.add(R.id.mainview,fragments[index]);


        }
        transaction.show(fragments[index]).commitAllowingStateLoss();


    }
}
