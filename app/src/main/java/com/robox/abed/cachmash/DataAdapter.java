package com.robox.abed.cachmash;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;

import java.util.List;

import CacheMash.*;


public class DataAdapter extends ArrayAdapter<Data> {

    private final String LOG_TAG=getClass().getName();

    Activity activity;
    int resource;
    List<Data> datas;

    public DataAdapter(Activity activity, int resource, List<Data> objects) {
        super(activity, resource, objects);

        this.activity = activity;
        this.resource = resource;
        this.datas = objects;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View row = convertView;
        final DealHolder holder;

        if (row == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            row = inflater.inflate(resource, parent, false);

            holder = new DealHolder();
            holder.image = (ImageView)row.findViewById(R.id.image);
            holder.title = (TextView)row.findViewById(R.id.title);
            holder.description = (TextView)row.findViewById(R.id.description);
            row.setTag(holder);
        }
        else {
            holder = (DealHolder) row.getTag();
        }

        final Data data = datas.get(position);



        RequestHandler rh=new RequestHandler();
        rh.setOnDownloadFinishListener(new RequestHandler.DownloadFinishListener()
        {
            public void onSucess(Object payload)
            {
                byte[] imageBytes=(byte[])payload;
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                Log.d(LOG_TAG, "success3 " + payload);
                holder.image.setImageBitmap(bitmap);
            }
            public void onFail(String errorMessage)
            {
                Log.d(LOG_TAG,"fail3 "+errorMessage);
            }
        });



        CacheMash.load(new ImageRequest(getContext(), data.imageUrl))
                .registerHandler(rh)
                .build();


//        holder.image.setHeightRatio(1.0);
        holder.title.setText(data.title);
        holder.description.setText(data.description);

        return row;
    }

    static class DealHolder {
        ImageView image;
        TextView title;
        TextView description;
    }
}