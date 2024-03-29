package com.example.dome2;

import java.net.HttpURLConnection;

/**
 *
 */
public class PlayerService extends Service {

    final String FindOne  = "http://182.254.177.94:8080/techan_detail";

    final String FindAll  = "http://182.254.177.94:8080/getTechans";


    public String findOne(String id){
        try {
            HttpURLConnection urlConn = super.getConnect(FindOne+"?id="+id);
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


    public String findAll(){
        try {
            HttpURLConnection urlConn = super.getConnect(FindAll);
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
