package com.example.sz.exam;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {
    private  static  final boolean NETWORK_NONE = false ;
    private  static  final boolean NETWORK_AVAILABLE = true ;

    public  static  boolean getNetstatus(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo  =  connectivityManager !=null ?connectivityManager.getActiveNetworkInfo():null;
        if(activeNetworkInfo!=null&&activeNetworkInfo.isConnected())
            return NETWORK_AVAILABLE;
        else
            return NETWORK_NONE;
    }
}

