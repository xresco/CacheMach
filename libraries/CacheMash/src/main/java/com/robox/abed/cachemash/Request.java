package com.robox.abed.cachemash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by xresco on 4/3/15.
 */
public abstract class Request<T> {
    private final String LOG_TAG=getClass().getName();
    protected T payload;
    protected String errorMessage;
    protected String url;
    protected Context context;
    protected ArrayList<DownloadFinishListener<T>> listeners=new ArrayList<>();


    public Request(Context context,String url)
    {
        this.url=url;
        this.context=context;
    }


    public void registerListener(DownloadFinishListener<T> listener)
    {
        listeners.add(listener);
    }

    public void unregisterListener(DownloadFinishListener<T> listener)
    {
        listeners.remove(listener);
        if(listeners.size()==0)
            cancelRequest();

    }



    public String getUrl() {
        return url;
    }

    private void notifyHandlers(boolean is_from_cache,boolean isSuccessful)
    {
        if(isSuccessful && !is_from_cache)
            CacheManager.getInstance().cach(this.getUrl(),this.getPayload(),this.getPayloadSize());
        if(isSuccessful) {
            for (DownloadFinishListener<T> listener : listeners) {
                listener.onSucess(payload);
            }
        }
        else
        {
            for (DownloadFinishListener<T> listener : listeners) {
                listener.onFail(errorMessage);
            }
        }
    }

    public void notifyHandlers(boolean isSuccessful)
    {
        notifyHandlers(false,isSuccessful);
    }


    public abstract int getPayloadSize();
    public Object getPayload() {
        return payload;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void makeRequest()
    {
        payload=(T)CacheManager.getInstance().get(url);
        if(payload==null) {
            Log.d(LOG_TAG,"not cached! "+url);
            doRequest();
        }
        else {
            Log.d(LOG_TAG,"cached! "+url);
            notifyHandlers(true,true);
        }
    }

    public abstract void doRequest();
    public abstract void cancelRequest();

}
