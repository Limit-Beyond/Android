package com.example.dome2;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class MyTermActivity extends AppCompatActivity implements View.OnClickListener {
    int rankBottom;
    Dialog bottomDialog;
    ContentResolver contentResolver ;
    String data,type;
    String cid;
    LinearLayout termList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myterm);
        cid = USERINFO.cid;
        termList = (LinearLayout) findViewById(R.id.mytermlist);
        type="lol";
        contentResolver = getApplicationContext().getContentResolver();

        execute(true);
        findViewById(R.id.gametype).setOnClickListener(this);
//        findViewById(R.id.join).setOnClickListener();

    }

    @Override
    public void onClick(View v) {
        if(type.equals("lol"))
            rankBottom=R.layout.chooserank;
        else
            rankBottom = R.layout.chooserank_dnf;

        switch (v.getId()) {
            case R.id.gametype:
                showBottom(R.layout.choosetype);
                break;
                default:
                    break;
            }
        }
    private void showBottom(int id) {
        bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(id, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();

    }


    public void setType(View view) {
        type= (String) view.getTag();
        execute(false);
    }

    public void doJoin(View v) {
        JSONObject jo  = (JSONObject) v.getTag();
        contentResolver.delete(Uri.parse("content://666/LeaveTerm"),"?id="+jo.getString("id")+"&cid="+cid,null);
        termList.removeView(termList.getChildAt(Integer.parseInt(jo.getString("index"))));
    }

    public void execute(boolean first){
        if(!first)
        termList.removeAllViews();

        data = "?type="+type+"&cid="+cid;
        contentResolver.query(Uri.parse("content://666/MyTerm/1"),null,data,null,null);
        JSONArray ja = JSONArray.parseArray(Provider.result);
        for(int i = 0 ;i<ja.size();i++){
            JSONObject jo  = ja.getJSONObject(i);
            LinearLayout childView = (LinearLayout) LayoutInflater.from(MyTermActivity.this).inflate(R.layout.item, null);
            ImageView imageView = childView.findViewWithTag("image");
            int draw = jo.getString("type").equals("lol")?R.drawable.blue_border:R.drawable.blue_border;
            imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),draw));
            TextView title = childView.findViewWithTag("title");
            TextView intro = childView.findViewWithTag("intro");
            TextView datetime = childView.findViewWithTag("datetime");
            TextView rate = childView.findViewWithTag("rate");
            Button b=childView.findViewById(R.id.join);
            JSONObject temp = new JSONObject();
            temp.put("id",jo.getString("id"));
           temp.put("index",String.valueOf(i));
            b.setTag(temp);
            b.setText("退出");
            rate.setText(jo.getString("person_now")+" / "+jo.getString("person_need"));
            datetime.setText(jo.getString("datetime"));
            intro.setText(jo.getString("intro"));
            title.setText(jo.getString("title"));

            termList.addView(childView,i);

        }
        if(!first)
        bottomDialog.cancel();
    }
}
