package com.example.dome2;

import android.content.Intent;
import android.os.IBinder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Service  {

    public String findOne(String id){
        return null;
    }
    public String deleteOne(String id){
        return null;
    }
    public String findAll(){
        return null;
    }
    public String deleteAll(){
        return null;
    }
    public String insertOne(String data){
        return null;
    }
    public HttpURLConnection getConnect(String uri){
        try {
        URL url = new URL(uri);
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        urlConn.setConnectTimeout(5 * 1000);
        urlConn.setReadTimeout(5 * 1000);
        urlConn.setDoOutput(true);
        urlConn.setDoInput(true);
        urlConn.setUseCaches(false);
        urlConn.setRequestMethod("GET");
        urlConn.setInstanceFollowRedirects(true);
        urlConn.setRequestProperty("Content-Type", "application/json");
        return urlConn;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String toString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }





}
