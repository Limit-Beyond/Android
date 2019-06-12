package com.example.dome2;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class CreateTermActivity extends AppCompatActivity implements View.OnClickListener,CustomDatePickerDialogFragment.OnSelectedDateListener {

    Button type_btn,min_rank_btn,save,cancel,date;
    EditText  title,hour,minute,second,need;
    Dialog bottomDialog;
    static String frequentString = "lol";
    static String noticeString = "1";
    static String dateString = "0000-00-00 00:00:00";
    static String titleString="";
    static String needString="5";
    String h,m,s;
    ContentResolver contentResolver;
    String mode = "create";
    int count = 0;

    public void setType(View v){

        frequentString= (String) v.getTag();
        type_btn.setText(frequentString);
        bottomDialog.cancel();
    }
    public void setRank(View v){
        String rank = (String) v.getTag();
        noticeString = rank;
        min_rank_btn.setText(noticeString);
        bottomDialog.cancel();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createterm);
        contentResolver = getApplicationContext().getContentResolver();
        Bundle bundle = this.getIntent().getExtras();
        if(bundle==null){}
        else {
            String mode = bundle.getString("modify");
            if (mode != null) {
                this.mode = "modify";
                this.count = Integer.parseInt(bundle.getString("count"));
            } else
                this.mode = "create";
        }
        cancel=(Button)findViewById(R.id.cancel);
        save = (Button)findViewById(R.id.save);

        hour=(EditText)findViewById(R.id.hour) ;
        minute=(EditText)findViewById(R.id.minute) ;
        second=(EditText)findViewById(R.id.second) ;

        need=findViewById(R.id.need);
        title=(EditText)findViewById(R.id.inputTitle);
        type_btn = (Button)findViewById(R.id.frequent);
        min_rank_btn = (Button)findViewById(R.id.notice);
        date = (Button)findViewById(R.id.datepicker);

        type_btn.setOnClickListener(this);min_rank_btn.setOnClickListener(this);date.setOnClickListener(this);cancel.setOnClickListener(this);save.setOnClickListener(this);

        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
        dateString = String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(day);
        date.setText(dateString);
        need.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                needString=need.getText().toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

        });
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                titleString=title.getText().toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

        });
        hour.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                h=hour.getText().toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

        });
        minute.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                m=minute.getText().toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

        });
        second.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence se, int start, int before, int count) {
                s=second.getText().toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

        });

    }

    @Override
    public void onClick(View v) {
        int choot ;
        if(frequentString.equals("lol"))
            choot=R.layout.chooserank;
        else
            choot=R.layout.chooserank_dnf;
        switch (v.getId()) {
            case R.id.datepicker:
                showDatePickDialog();
                break;
            case R.id.frequent:
                showBottom(R.layout.choosetype);
                break;
            case R.id.notice:
                showBottom(choot);
                break;

            case R.id.save:
                try {
                    save();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            default:
                break;
        }
    }



    public void save() throws PackageManager.NameNotFoundException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("cid",USERINFO.cid);
        contentValues.put("title",titleString);
        contentValues.put("datetime",dateString);
        contentValues.put("type",frequentString);
        contentValues.put("min_rank",noticeString);
        contentValues.put("intro","none");
        contentValues.put("need",needString);
        contentValues.put("server","默认服务器");

        contentResolver.insert(Uri.parse("content://666/CreateTerm"),contentValues);
        Intent intent =new Intent(CreateTermActivity.this,MainActivity.class);
        startActivity(intent);
    }

    long day = 24 * 60 * 60 * 1000;

    private void showDatePickDialog() {
        CustomDatePickerDialogFragment fragment = new CustomDatePickerDialogFragment();
        fragment.setOnSelectedDateListener(this);
        Bundle bundle = new Bundle();
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTimeInMillis(System.currentTimeMillis());
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        bundle.putSerializable(CustomDatePickerDialogFragment.CURRENT_DATE, currentDate);


        long start = currentDate.getTimeInMillis() - day *10;
        long end = currentDate.getTimeInMillis()+day*10;
        Calendar startDate = Calendar.getInstance();
        startDate.setTimeInMillis(start);
        Calendar endDate = Calendar.getInstance();
        endDate.setTimeInMillis(end);
        bundle.putSerializable(CustomDatePickerDialogFragment.START_DATE, startDate);
        bundle.putSerializable(CustomDatePickerDialogFragment.END_DATE, endDate);

        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), CustomDatePickerDialogFragment.class.getSimpleName());
    }

    @Override
    public void onSelectedDate(int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(CreateTermActivity.this, year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
        dateString=String.valueOf(year)+"-"+String.valueOf(monthOfYear+1)+"-"+dayOfMonth;
        date.setText(dateString);
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

}
