package com.example.dome2;

import java.net.HttpURLConnection;

public class TermService extends Service {


    final String FindOne  = "http://182.254.177.94:8080/TermList";
    final String join = "http://182.254.177.94:8080/JoinTerm";
    final String mine = "http://182.254.177.94:8080/MyTerm";
    final String delete = "http://182.254.177.94:8080/LeaveTerm";
    final String insert = "http://182.254.177.94:8080/CreateTerm";
    public String findOne(String data){
        try {
            HttpURLConnection urlConn = super.getConnect(FindOne+data);//(HttpURLConnection) url.openConnection();
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


    public String join(String data) {
        try {
            HttpURLConnection urlConn = super.getConnect(join+data);
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

    @Override
    public String mine(String data) {
        try {
            HttpURLConnection urlConn = super.getConnect(mine+data );
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

    @Override
    public String deleteOne(String id) {
        try {
            HttpURLConnection urlConn = super.getConnect(delete+id );
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

    @Override
    public String insertOne(String data) {
        try {
            HttpURLConnection urlConn = super.getConnect(insert+data );
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
