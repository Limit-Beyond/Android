package com.example.dome2;

import java.net.HttpURLConnection;

/**
 * CDK查询
 */
public class SaleService extends Service {

    /**
     * 历史遗留问题
     */
    final String FindAll  = "http://182.254.177.94:8080/getMeishi?q=undefined+price:undefined";
    final String FindOne  = "http://182.254.177.94:8080/meishi_detail";

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

}
