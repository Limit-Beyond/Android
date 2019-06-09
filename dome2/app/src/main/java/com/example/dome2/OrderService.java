package com.example.dome2;

import java.net.HttpURLConnection;

/**
 * CDK查询
 */
public class OrderService extends Service {

    final String FindOne  = "http://182.254.177.94:8080/PersonalOrderInfo";




    public String findOne(String id){
        try {
            HttpURLConnection urlConn = super.getConnect(FindOne+"?Custoemrid="+id);//(HttpURLConnection) url.openConnection();
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

}
