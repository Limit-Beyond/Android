package com.example.dome2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * CDK查询
 */
public class CdkService extends Service {

    final String FindAll  = "http://182.254.177.94:8080/getPlaces";
    final String FindOne  = "http://182.254.177.94:8080/getPlaces";

    public String findAll(){
        try {
            HttpURLConnection urlConn = super.getConnect(FindAll);//(HttpURLConnection) url.openConnection();
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

    public String findOne(String id){
        try {
            HttpURLConnection urlConn = super.getConnect(FindOne+"?spot_id="+id);//(HttpURLConnection) url.openConnection();
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
