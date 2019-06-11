package com.example.dome2;

import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.dome2.Service;

import java.net.HttpURLConnection;

/**
 *
 */
public class OrderService extends Service {

    final String FindOne  = "http://182.254.177.94:8080/PersonalOrderInfo";

    final String Insert  = "http://182.254.177.94:8080/AddOrder";

    final String ChgSta  = "http://182.254.177.94:8080/chOrderStatus";

    public String findOne(String id){
        try {
            HttpURLConnection urlConn = super.getConnect(FindOne+"?Custoemrid="+id);
            urlConn.connect();
            if (urlConn.getResponseCode() == 200) {
                return super.toString((urlConn.getInputStream()));
            }
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String insertOne(String data){
        try {
            HttpURLConnection urlConn = super.getConnect(Insert+data);
            urlConn.connect();
            if (urlConn.getResponseCode() == 200) {

            }
            urlConn.disconnect();
            int y,m,d,h,mi,s;
            Calendar cal=Calendar.getInstance();
            y=cal.get(Calendar.YEAR);
            m=cal.get(Calendar.MONTH);
            d=cal.get(Calendar.DATE);
            h=cal.get(Calendar.HOUR_OF_DAY);
            mi=cal.get(Calendar.MINUTE);
            s=cal.get(Calendar.SECOND);
            String time = String.valueOf(y+"-"+m+"-"+d+" "+h+":"+mi+":"+s);

            Thread.sleep(2000);
            HttpURLConnection urlConn2 = super.getConnect(ChgSta+data+"&ticket_date="+time);
            urlConn2.connect();
            if (urlConn2.getResponseCode() == 200) {

            }
            urlConn2.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
