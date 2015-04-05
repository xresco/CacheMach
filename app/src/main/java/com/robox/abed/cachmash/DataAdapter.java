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
import android.widget.ProgressBar;
import android.widget.TextView;
import com.robox.abed.cachemash.CacheMash;
import com.robox.abed.cachemash.DownloadFinishListener;
import com.robox.abed.cachemash.ImageRequest;

import java.util.List;




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
            holder.progressBar=(ProgressBar)row.findViewById(R.id.progress_bar);
            row.setTag(holder);
        }
        else {
            holder = (DealHolder) row.getTag();
        }

        final Data data = datas.get(position);


        ImageRequest request=new ImageRequest(getContext(), data.imageUrl);
        request.registerListener(new DownloadFinishListener<Bitmap>() {
            @Override
            public void onSucess(Bitmap payload) {
                holder.image.setImageBitmap(payload);
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFail(String errorMessage) {
                Log.d(LOG_TAG,"fail "+errorMessage);
                holder.progressBar.setVisibility(View.GONE);

            }
        });

        holder.progressBar.setVisibility(View.VISIBLE);
        CacheMash.load(request)
                .build();

        holder.title.setText(data.title);
        holder.description.setText(data.description);

        return row;
    }

    static class DealHolder {
        ImageView image;
        TextView title;
        TextView description;
        ProgressBar progressBar;
    }
}