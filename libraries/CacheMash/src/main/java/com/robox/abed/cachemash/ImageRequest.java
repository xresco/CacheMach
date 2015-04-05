package com.robox.abed.cachemash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by xresco on 4/4/15.
 */
public class ImageRequest extends Request<Bitmap> {

    private final String LOG_TAG=getClass().getName();

    public ImageRequest(Context context,String url)
    {
        super(context,url);
    }
    int payloadSize=0;

    @Override
    public void doRequest() {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .toBytes()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(new SimpleTarget<byte[]>(300, 300) {
                    @Override
                    public void onResourceReady(byte[] resource, GlideAnimation<? super byte[]> glideAnimation) {
                        payloadSize=resource.length;
                        Bitmap bitmap = BitmapFactory.decodeByteArray(resource, 0, resource.length);
                        payload = bitmap;
                        notifyHandlers(true);
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        errorMessage = e.getMessage();
                        notifyHandlers(false);
                    }
                });

    }

    @Override
    public void cancelRequest() {
        //stop the request from being called
    }

    @Override
    public int getPayloadSize() {
        return payloadSize;
    }
}
