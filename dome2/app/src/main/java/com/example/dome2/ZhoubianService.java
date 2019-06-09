package com.example.dome2;

import java.net.HttpURLConnection;

/**
 * CDK查询
 */
public class ZhoubianService extends Service {

    final String FindOne  = "http://182.254.177.94:8080/QueryByCarId";

    final String FindAll  = "http://182.254.177.94:8080/SpotBusInfo";


    public String findOne(String id){
        try {
            HttpURLConnection urlConn = super.getConnect(FindOne+"?taxiid="+id);
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
            HttpURLConnection urlConn = super.getConnect(FindAll+"?spotid=undefined");
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
