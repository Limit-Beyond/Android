package com.example.dome2;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import org.w3c.dom.Text;


public class TermActivity  extends AppCompatActivity implements View.OnClickListener {
    int rankBottom;
    Dialog bottomDialog;
    ContentResolver contentResolver ;
    String data,type,min_rank;
    LinearLayout termList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term);
        termList = (LinearLayout) findViewById(R.id.termlist);
        type="lol";min_rank="";
        data = "?type="+type+"&min_rank="+min_rank;
        contentResolver = getApplicationContext().getContentResolver();
        contentResolver.query(Uri.parse("content://666/TermList/1"),null,data,null,null);
        Log.i("pppppppppp",Provider.result);
        JSONArray ja = JSONArray.parseArray(Provider.result);
        for(int i = 0 ;i<ja.size();i++){
            JSONObject jo  = ja.getJSONObject(i);
            LinearLayout childView = (LinearLayout) LayoutInflater.from(TermActivity.this).inflate(R.layout.term_item, null);
            ImageView imageView = childView.findViewWithTag("image");
            int draw = jo.getString("type").equals("lol")?R.drawable.blue_border:R.drawable.blue_border;
            imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),draw));
            TextView title = childView.findViewWithTag("title");
            TextView intro = childView.findViewWithTag("intro");
            TextView datetime = childView.findViewWithTag("datetime");
            TextView rate = childView.findViewWithTag("rate");
            Button b=childView.findViewById(R.id.join);
            b.setTag(i+1);
            rate.setText(jo.getString("person_now")+" / "+jo.getString("person_need"));
            datetime.setText(jo.getString("datetime"));
            intro.setText(jo.getString("intro"));
            title.setText(jo.getString("title"));



            termList.addView(childView,i);
        }

        findViewById(R.id.minrank).setOnClickListener(this);
        findViewById(R.id.gametype).setOnClickListener(this);
        findViewById(R.id.createTerm).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(type.equals("lol"))
            rankBottom=R.layout.chooserank;
        else
            rankBottom = R.layout.chooserank_dnf;
        switch (v.getId()) {
            case R.id.minrank:
                showBottom(rankBottom);

                break;
            case R.id.gametype:
                showBottom(R.layout.choosetype);

                break;
            case R.id.createTerm:
                    Intent intent =new Intent(TermActivity.this,CreateTermActivity.class);
                startActivity(intent);


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

    public void setRank(View view) {


        String rank = (String) view.getTag();
        min_rank = rank;
        execute();
    }

    public void setType(View view) {
        type= (String) view.getTag();
        execute();
    }

    public void doJoin(View v) {
        String id = String .valueOf(v.getTag());
        String cid = "1";
        contentResolver = getApplicationContext().getContentResolver();

        ContentValues contentValues = new ContentValues();
        contentValues.put("cid",cid);
        contentValues.put("id",id);
        contentResolver.insert(Uri.parse("content://666/JoinTerm"),contentValues);
    }

    public void execute(){
        termList.removeAllViews();
        data = "?type="+type+"&min_rank="+min_rank;
        contentResolver.query(Uri.parse("content://666/TermList/1"),null,data,null,null);
        JSONArray ja = JSONArray.parseArray(Provider.result);
        for(int i = 0 ;i<ja.size();i++){
            JSONObject jo  = ja.getJSONObject(i);
            LinearLayout childView = (LinearLayout) LayoutInflater.from(TermActivity.this).inflate(R.layout.term_item, null);
            ImageView imageView = childView.findViewWithTag("image");
            int draw = jo.getString("type").equals("lol")?R.drawable.blue_border:R.drawable.blue_border;
            imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),draw));
            TextView title = childView.findViewWithTag("title");
            TextView intro = childView.findViewWithTag("intro");
            TextView datetime = childView.findViewWithTag("datetime");
            TextView rate = childView.findViewWithTag("rate");
            Button b=childView.findViewById(R.id.join);
            b.setTag(jo.getString("id"));
            rate.setText(jo.getString("person_now")+" / "+jo.getString("person_need"));
            datetime.setText(jo.getString("datetime"));
            intro.setText(jo.getString("intro"));
            title.setText(jo.getString("title"));

            termList.addView(childView,i);

        }
        bottomDialog.cancel();
    }

    public void createTerm(View view) {
    }
}
