package com.robox.abed.cachemash;

/**
 * Created by mindvalley on 4/3/15.
 */
public class CacheMash {

    private Request request;


    public static CacheMash load( Request request)
    {
        CacheMash cacheMashInstance= new CacheMash();
        cacheMashInstance.request=request;
        return cacheMashInstance;
    }

//    public CacheMash cacheStrategy()
//    {
//        return this;
//    }

    public CacheMash registerHandler(RequestHandler requestHandler)
    {
        requestHandler.setRequest(request);
        return this;
    }

    public void build()
    {
        request.makeRequest();
    }


}


