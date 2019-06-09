package com.example.dome2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;

public class Player extends Activity{

    private ImageButton imageButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        PlayerService playerService=new PlayerService();


    }

}
