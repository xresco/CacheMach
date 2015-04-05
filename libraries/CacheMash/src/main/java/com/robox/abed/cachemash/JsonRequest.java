package com.robox.abed.cachemash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.json.JSONObject;

/**
 * Created by xresco on 4/4/15.
 */
public class JsonRequest extends Request<JSONObject> {

    private final String LOG_TAG=getClass().getName();
    private int payLoadSize=0;
    private  com.android.volley.toolbox.JsonObjectRequest jsObjRequest;



    public JsonRequest(Context context, String url)
    {
        super(context,url);
    }


    @Override
    public void doRequest() {
       jsObjRequest = new JsonObjectRequest
                (com.android.volley.Request.Method.GET, url,(String) null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        payLoadSize=response.toString().length();
                        payload = response;
                        notifyHandlers(true);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        errorMessage=error.getMessage();
                        notifyHandlers(false);

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsObjRequest);
    }

    @Override
    public void cancelRequest() {
        //stop the request from being called
        jsObjRequest.cancel();
    }


    @Override
    public int getPayloadSize() {
        return payLoadSize;
    }
}
