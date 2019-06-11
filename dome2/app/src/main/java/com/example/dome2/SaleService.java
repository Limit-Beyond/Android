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

    /**
     * 6.11日新增
     */
    final String Insert = "http://182.254.177.94:8080/AddSale";
    final String Delete = "http://182.254.177.94:8080/DeleteSale";
    public String insertOne(String data) {
        try {
            HttpURLConnection urlConn = super.getConnect(Insert+data);
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
    public String deleteOne(String id) {
        try {
            HttpURLConnection urlConn = super.getConnect(Delete+"?id="+id);
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

    /**
     * 6.11日新增
     */


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
