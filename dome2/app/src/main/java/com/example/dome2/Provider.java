package com.example.dome2;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

public class Provider extends ContentProvider {

    static Service service = null;
    private UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static String result;

    @Override
    public boolean onCreate() {
        Log.i("000000", "创建PROVIDER  ON Create");
        uriMatcher.addURI("666","FindCdks/#", TYPES.FINDCDKS.ordinal());
        uriMatcher.addURI("666","CdkInfo", TYPES.CDKINFO.ordinal());
        uriMatcher.addURI("666","FindOrders/#", TYPES.FINDORDERS.ordinal());
        uriMatcher.addURI("666","OrderInfo", TYPES.ORDERINFO.ordinal());
        uriMatcher.addURI("666","FindSales/#", TYPES.FINDSALES.ordinal());
        uriMatcher.addURI("666","SaleInfo", TYPES.SALEINFO.ordinal());
        uriMatcher.addURI("666","FindZhoubians/#", TYPES.FINDZHOUBIANS.ordinal());
        uriMatcher.addURI("666","ZhoubianInfo/#", TYPES.ZHOUBIANINFO.ordinal());
        uriMatcher.addURI("666","FindPifus/#", TYPES.FINDPIFUS.ordinal());
        uriMatcher.addURI("666","PifuInfo/#", TYPES.PIFUINFO.ordinal());
        uriMatcher.addURI("666","FindPlayers/#", TYPES.FINDPLAYERS.ordinal());
        uriMatcher.addURI("666","PlayerInfo/#", TYPES.PLAYERINFO.ordinal());

        uriMatcher.addURI("666","AddOrder", TYPES.ADDORDER.ordinal());
        uriMatcher.addURI("666","AddSale", TYPES.ADDSALE.ordinal());
        uriMatcher.addURI("666","DeleteSale", TYPES.DELETESALE.ordinal());
        uriMatcher.addURI("666","TermList/#", TYPES.FINDTERMS.ordinal());
        uriMatcher.addURI("666","MyTerm/#", TYPES.FINDMYTERM.ordinal());
        uriMatcher.addURI("666","JoinTerm", TYPES.JOINTERM.ordinal());
        uriMatcher.addURI("666","MyTerm", TYPES.FINDMYTERM.ordinal());
        uriMatcher.addURI("666","LeaveTerm", TYPES.LEAVETERM.ordinal());
        uriMatcher.addURI("666","CreateTerm", TYPES.CREATETERM.ordinal());
        return false;
    }


    public void doSomeThing(Class c ,TYPE.TypeEnum t ,Object id) {
        if(!c.isInstance(service)) {
            try {
                service = (Service)c.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        ThreadA a = new ThreadA(service,t,String.valueOf(id));
        Thread thread = new Thread(a);
        thread.start();
        while(!a.isCompleted()){}
        Provider.result = a.getResult();
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {

        Object id ;
        int code=uriMatcher.match(uri);
        try {
            id = ContentUris.parseId(uri);
        }
        catch (Exception e){
            id = 1;
        }

        TYPES t[]  =  TYPES.values();
        TYPES tt = t[code];

        switch (tt) {
            case FINDCDKS:
                doSomeThing(CdkService.class,TYPE.TypeEnum.FINDONE,id);
                return null;
            case CDKINFO:
                break;
            case FINDORDERS:
                doSomeThing(OrderService.class,TYPE.TypeEnum.FINDONE,id);
                break;
            case ORDERINFO:
                break;
            case FINDPIFUS:
                doSomeThing(PifuService.class,TYPE.TypeEnum.FINDALL,id);
                break;
            case PIFUINFO:
                doSomeThing(PifuService.class,TYPE.TypeEnum.FINDONE,id);
                break;
            case FINDZHOUBIANS:
                doSomeThing(ZhoubianService.class,TYPE.TypeEnum.FINDALL,id);
                break;
            case ZHOUBIANINFO:
                doSomeThing(ZhoubianService.class,TYPE.TypeEnum.FINDONE,id);
                break;
            case FINDSALES:
                doSomeThing(SaleService.class,TYPE.TypeEnum.FINDALL,id);
                break;
            case SALEINFO:
                break;
            case FINDPLAYERS:
                doSomeThing(PlayerService.class,TYPE.TypeEnum.FINDALL,id);
                break;
            case PLAYERINFO:
                doSomeThing(PlayerService.class,TYPE.TypeEnum.FINDONE,id);
                break;
            case FINDTERMS:
                doSomeThing(TermService.class,TYPE.TypeEnum.FINDONE,s);
                break;
            case FINDMYTERM:
                doSomeThing(TermService.class,TYPE.TypeEnum.MINE,s);
                break;

        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.i("tt","getType");
        return null;
    }



    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int code=uriMatcher.match(uri);
        Object id ;
        try {
            id = ContentUris.parseId(uri);
        }
        catch (Exception e){
            id = 1;
        }

        TYPES t[]  =  TYPES.values();
        TYPES tt = t[code];

        String data;
        switch (tt) {
            case ADDORDER:
                data = "?Custoemrid=" + contentValues.get("Custoemrid") ;
                for(String key : contentValues.keySet()){
                    if(key.equals("Custoemrid"))
                        continue;
                    data+="&"+key+"="+contentValues.get(key);
                }
                doSomeThing(OrderService.class,TYPE.TypeEnum.INSERTONE,data);
                break;
            case ADDSALE:
                data = "?Custoemrid=" + contentValues.get("Custoemrid") ;
                for(String key : contentValues.keySet()){
                    if(key.equals("Custoemrid"))
                        continue;
                    data+="&"+key+"="+contentValues.get(key);
                }
                doSomeThing(OrderService.class,TYPE.TypeEnum.INSERTONE,data);
                break;
            case JOINTERM:
                data = "?cid=" + contentValues.get("cid") ;
                for(String key : contentValues.keySet()){
                    if(key.equals("cid"))
                        continue;
                    data+="&"+key+"="+contentValues.get(key);
                }
                doSomeThing(TermService.class,TYPE.TypeEnum.JOIN,data);
                break;
            case CREATETERM:
                data = "?cid=" + contentValues.get("cid") ;
                for(String key : contentValues.keySet()){
                    if(key.equals("cid"))
                        continue;
                    data+="&"+key+"="+contentValues.get(key);
                }
                Log.i("IDODODDOODD",data);
                doSomeThing(TermService.class,TYPE.TypeEnum.INSERTONE,data);
                break;


        }
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        int code=uriMatcher.match(uri);
        Object id ;
        try {
            id = ContentUris.parseId(uri);
        }
        catch (Exception e){
            id = 1;
        }

        TYPES t[]  =  TYPES.values();
        TYPES tt = t[code];

        switch (tt) {
            case DELETESALE:
                doSomeThing(OrderService.class,TYPE.TypeEnum.DELETEONE,id);
                break;
            case LEAVETERM:
                doSomeThing(TermService.class,TYPE.TypeEnum.DELETEONE,s);
                break;

        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        long id= ContentUris.parseId(uri);
        return 0;
    }
}
