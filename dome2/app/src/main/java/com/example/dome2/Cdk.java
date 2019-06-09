package com.example.dome2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;



public class Cdk extends Activity{

    private String data[] = {"动作游戏","养成游戏","教育游戏","益智游戏","卡牌游戏","射击游戏"};//假数据
    private ListView listview;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cdk);
        ListView listView = (ListView) findViewById(R.id.listview);//在视图中找到ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);//新建并配置ArrayAapeter
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            Intent intent = new Intent(Cdk.this,CdkActivity.class);
            Bundle data = new Bundle();
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:

                        Toast.makeText(Cdk.this,"你点击了"+i+"按钮",Toast.LENGTH_SHORT).show();

                        data.putInt("id",i);
                        intent.putExtra("data",data);
                        startActivity( intent);

                        break;//当我们点击某一项就能吐司我们点了哪一项

                    case 1:
                        data.putInt("id",i);
                        intent.putExtra("data",data);
                        startActivity( intent);
                        Toast.makeText(Cdk.this,"你点击了"+i+"按钮",Toast.LENGTH_SHORT).show();
                        break;

                    case 2:
                        data.putInt("id",i);
                        intent.putExtra("data",data);
                        startActivity( intent);
                        Toast.makeText(Cdk.this,"你点击了"+i+"按钮",Toast.LENGTH_SHORT).show();
                        break;

                    case 3:
                        data.putInt("id",i);
                        intent.putExtra("data",data);
                        startActivity( intent);
                        Toast.makeText(Cdk.this,"你点击了"+i+"按钮",Toast.LENGTH_SHORT).show();
                        break;

                    case 4:
                        data.putInt("id",i);
                        intent.putExtra("data",data);
                        startActivity( intent);
                        Toast.makeText(Cdk.this,"你点击了"+i+"按钮",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });



    }

}
