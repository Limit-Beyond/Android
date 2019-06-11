package com.example.dome2;

import java.net.HttpURLConnection;

/**
 * CDK查询
 */
public class PifuService extends Service {

    final String FindOne  = "http://182.254.177.94:8080/leader_detail";

    final String FindAll  = "http://182.254.177.94:8080/getLeaders";


    public String findOne(String id){
        try {
            System.out.println("id思蜀        "  +id );
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
            System.out.println("id思蜀      手段对付  " );
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
