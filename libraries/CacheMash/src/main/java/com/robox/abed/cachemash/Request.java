package com.robox.abed.cachemash;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by mindvalley on 4/3/15.
 */
public abstract class Request {
    private final String LOG_TAG=getClass().getName();
    private ArrayList<RequestHandler> requestHandlers=new ArrayList<>();
    protected Object payload;
    protected String errorMessage;
    protected String url;
    protected Context context;

    



    public Request(Context context,String url)
    {
        this.url=url;
        this.context=context;
    }



    public void registerHandler(RequestHandler requestHandler)
    {
        requestHandlers.add(requestHandler);
    }

    public void unregisterHandler(RequestHandler requestHandler)
    {
        requestHandlers.remove(requestHandler);
        if(requestHandlers.size()==0)
            cancelRequest();

    }



    public String getUrl() {
        return url;
    }

    public void notifyHandlers(boolean isSuccessful)
    {
        for(RequestHandler handler:requestHandlers)
            handler.notifyHandler(isSuccessful);
    }


    public Object getPayload() {
        return payload;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void makeRequest()
    {
        payload=CacheManager.getInstance().get(url);
        if(payload==null) {
            Log.d(LOG_TAG,"not cached!");
            doRequest();
        }
        else {
            Log.d(LOG_TAG,"cached! "+payload);
            notifyHandlers(true);
        }
    }



    public abstract void doRequest();
    public abstract void cancelRequest();
}
