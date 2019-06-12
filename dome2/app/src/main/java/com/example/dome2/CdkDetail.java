package com.example.dome2;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.w3c.dom.Text;


/**
 * 所有跳转的详情页面
 */

public class CdkDetail extends AppCompatActivity {

    ContentResolver contentResolver ;
    TextView textView2=null;
    TextView textView3=null;
    TextView textView4=null;
    ImageView imageView=null;
    Button button1=null;//提交订单

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commondetail);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        textView4=findViewById(R.id.textView4);
        imageView=findViewById(R.id.imageView);
        button1=findViewById(R.id.login1);


        //intent接收上个页面的数据 id
        final Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("data");
        final int  id = data.getInt("id");
        final String type = data.getString("type");
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CdkDetail.this,MainActivity.class);
                startActivity(intent);
                ContentValues contentValues = new ContentValues();
                contentValues.put("Custoemrid",1);//用户id
                /**
                 * 周边，CDK，皮肤，出售，陪玩
                 */
                contentValues.put("type",type);//商品类型,可以不同的Activity向该页面传递不同的productid
                contentValues.put("productid",id);//产品id需从前端传递
                contentValues.put("ticket_date","");//产品id需从前端传递
                contentValues.put("ticket_type","");//产品id需从前端传递
                contentValues.put("startDate","");//产品id需从前端传递
                contentValues.put("endDate","");//产品id需从前端传递
                System.out.println("属性开始");
                System.out.println(type);
                System.out.println(id);
                System.out.println("属性结束");
                contentResolver.insert(Uri.parse("content://666/AddOrder"),contentValues);
                System.out.println("增加数据成功");
                System.out.println("增加数据成功");
                System.out.println("增加数据成功");
            }
        });


        contentResolver = getApplicationContext().getContentResolver();
        if(type!=null)
        switch (type){
            case "CDK":
                contentResolver.query(Uri.parse("content://666/FindCdks/" + 1), null, null, null, null);
                JSONArray ja=JSONArray.parseArray(Provider.result);
                JSONObject jo=ja.getJSONObject(0);
                System.out.println(Provider.result);
                textView2.setText("游戏名称: "+jo.getString("name"));
                textView3.setText("价格: "+jo.getString("price"));
                textView4.setText("游戏简介: "+jo.getString("intro"));
                imageView.setImageResource(R.mipmap.game1+id);
                break;
            case "皮肤":
                contentResolver.query(Uri.parse("content://666/PifuInfo/" + (id+1)), null, null, null, null);
                JSONObject pifujo=JSONObject.parseObject(Provider.result);
                textView2.setText("名称: "+pifujo.getString("name"));
                textView3.setText("价格: "+pifujo.getString("price"));
                textView4.setText("简介: "+pifujo.getString("intro"));
                imageView.setImageResource(R.mipmap.skin1+id%9);
                break;
            case "周边":
                contentResolver.query(Uri.parse("content://666/ZhoubianInfo/" + (id+1)), null, null, null, null);
                Log.i("UFUFUUFF",Provider.result);
                JSONObject zhoubianjo=JSONObject.parseObject(Provider.result);
                textView2.setText("名称: "+zhoubianjo.getString("name"));
                textView3.setText("价格: "+zhoubianjo.getString("price"));
                textView4.setText("简介: "+zhoubianjo.getString("intro"));
                imageView.setImageResource(R.mipmap.toy1+id);

                break;
            case "陪玩":
                contentResolver.query(Uri.parse("content://666/PlayerInfo/" + (id+1)), null, null, null, null);
                JSONObject playerjo=JSONObject.parseObject(Provider.result);
                textView2.setText("名称: "+playerjo.getString("name"));
                textView3.setText("价格: "+playerjo.getString("price"));
                textView4.setText("简介: "+playerjo.getString("intro"));
                imageView.setImageResource(R.mipmap.toy1+id);
                break;

                default:
                    break;

        }


    }
}
