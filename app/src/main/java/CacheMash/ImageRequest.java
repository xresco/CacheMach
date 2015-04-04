package CacheMash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by mindvalley on 4/4/15.
 */
public class ImageRequest extends Request {

    private final String LOG_TAG=getClass().getName();
    public ImageRequest(Context context,String url)
    {
        super(context,url);
    }
    @Override
    public void doRequest() {

        Glide.with(context)
                .load(url)
                .asBitmap()
                .toBytes()
                .into(new SimpleTarget<byte[]>(300,300) {
                    @Override
                    public void onResourceReady(byte[] resource, GlideAnimation<? super byte[]> glideAnimation) {
                        payload=resource;
                        Log.d(LOG_TAG," success:: "+payload);
                        notifyHandlers(true);
                    }
                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        errorMessage=e.getMessage();
                        Log.d(LOG_TAG," fail:: "+errorMessage);
                        notifyHandlers(false);
                    }
                });

    }

    @Override
    public void cancelRequest() {

    }
}
