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
        uriMatcher.addURI("666","ZhoubianInfo", TYPES.ZHOUBIANINFO.ordinal());
        uriMatcher.addURI("666","FindPifus/#", TYPES.FINDPIFUS.ordinal());
        uriMatcher.addURI("666","PifuInfo", TYPES.PIFUINFO.ordinal());
        uriMatcher.addURI("666","FindPlayers/#", TYPES.FINDPLAYERS.ordinal());
        uriMatcher.addURI("666","PlayerInfo", TYPES.PLAYERINFO.ordinal());
        return false;
    }


    public void doSomeThing(Class c ,TYPE.TypeEnum t ,long id) {
        if(!c.isInstance(service)) {
            try {
                service = (Service)c.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        if(id!=-1){
            ThreadA a = new ThreadA(service,t,String.valueOf(id));
            Thread thread = new Thread(a);
            thread.start();
            while(!a.isCompleted()){}
            Provider.result = a.getResult();
        }else{
            ThreadA a = new ThreadA(service,t);
            Thread thread = new Thread(a);
            thread.start();
            while(!a.isCompleted()){}
            Provider.result = a.getResult();
        }


    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
     
        int code=uriMatcher.match(uri);
        TYPES t[]  =  TYPES.values();

        TYPES tt = t[code];
        switch (tt) {
            case CDKINFO:
                doSomeThing(CdkService.class,TYPE.TypeEnum.FINDALL,-1);
                return null;
            case ORDERINFO:
                doSomeThing(OrderService.class,TYPE.TypeEnum.FINDALL,-1);
                return null;
            case PIFUINFO:
                doSomeThing(PifuService.class,TYPE.TypeEnum.FINDALL,-1);
                return null;
            case ZHOUBIANINFO:
                doSomeThing(ZhoubianService.class,TYPE.TypeEnum.FINDALL,-1);
                return null;
            case SALEINFO:
                doSomeThing(SaleService.class,TYPE.TypeEnum.FINDALL,-1);
                return null;
            case PLAYERINFO:
                doSomeThing(PlayerService.class,TYPE.TypeEnum.FINDALL,-1);
                return null;
            default:
                break;
        }


        long id= ContentUris.parseId(uri);


        switch (tt) {
            case FINDCDKS:
                doSomeThing(CdkService.class,TYPE.TypeEnum.FINDONE,id);
                return null;

            case FINDORDERS:
                doSomeThing(OrderService.class,TYPE.TypeEnum.FINDONE,id);
                break;
            case FINDPIFUS:
                doSomeThing(PifuService.class,TYPE.TypeEnum.FINDALL,id);
                break;
            case FINDZHOUBIANS:
                doSomeThing(ZhoubianService.class,TYPE.TypeEnum.FINDALL,id);
                break;

            case FINDSALES:
                doSomeThing(SaleService.class,TYPE.TypeEnum.FINDALL,id);
                break;

            case FINDPLAYERS:
                doSomeThing(PlayerService.class,TYPE.TypeEnum.FINDALL,id);
                break;
            default:
                break;

        }



//        switch (tt) {
//            case FINDCDKS:
//                doSomeThing(CdkService.class,TYPE.TypeEnum.FINDONE,id);
//                return null;
//            case CDKINFO:
//                //更改位置
//                doSomeThing(CdkService.class,TYPE.TypeEnum.FINDALL,-1);
//                break;
//            case FINDORDERS:
//                doSomeThing(OrderService.class,TYPE.TypeEnum.FINDONE,id);
//                break;
//            case ORDERINFO:
//                break;
//            case FINDPIFUS:
//                doSomeThing(PifuService.class,TYPE.TypeEnum.FINDALL,id);
//                break;
//            case PIFUINFO:
//                break;
//            case FINDZHOUBIANS:
//                doSomeThing(ZhoubianService.class,TYPE.TypeEnum.FINDALL,id);
//                break;
//            case ZHOUBIANINFO:
//                break;
//            case FINDSALES:
//                doSomeThing(SaleService.class,TYPE.TypeEnum.FINDALL,id);
//                break;
//            case SALEINFO:
//                break;
//            case FINDPLAYERS:
//                doSomeThing(PlayerService.class,TYPE.TypeEnum.FINDALL,id);
//                break;
//            case PLAYERINFO:
//                break;
//        }
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
        switch (code) {

        }
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        long id= ContentUris.parseId(uri);
    
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        long id= ContentUris.parseId(uri);
        return 0;
    }
}
