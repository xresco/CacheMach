package com.robox.abed.cachemash;

/**
 * Created by xresco on 4/3/15.
 */
public class CacheMash {

    private Request request;


    public static CacheMash load( Request request)
    {
        CacheMash cacheMashInstance= new CacheMash();
        cacheMashInstance.request=request;
        return cacheMashInstance;
    }

    //for future use, here i'm gonna use dependency enjection design pattern to tell the Library where to cache objects (Disk, Memory, File ...)
    //Still need some brainstorming
//    public CacheMash cacheStrategy()
//    {
//        return this;
//    }


    public void build()
    {
        request.makeRequest();
    }


}


