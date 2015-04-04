package com.robox.abed.cachmash;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;
import java.util.Random;

import CacheMash.*;


public class MainActivity extends ActionBarActivity {

    final String LOG_TAG=getClass().getName();
    private StaggeredGridView mGridView;
    private DataAdapter mAdapter;
    private final int SAMPLE_DATA_ITEM_COUNT=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout layout=(RelativeLayout)findViewById(R.id.layout);
//        layout.addView(new PintrestLayout(getApplicationContext(),2));

        mGridView = (StaggeredGridView) findViewById(R.id.grid_view);
        mAdapter = new DataAdapter(this, R.layout.tile_item, generateSampleData());
        mGridView.setAdapter(mAdapter);


        testCacheMash();




    }

    public  ArrayList<Data> generateSampleData() {
        String repeat = " repeat";
        final ArrayList<Data> datas = new ArrayList<Data>();
        for (int i = 0; i < SAMPLE_DATA_ITEM_COUNT; i++) {
            Data data = new Data();
            data.imageUrl = "http://roboxbot.co/wp-content/uploads/2014/12/AMR_2891.jpg";
            data.title = "Pinterest Card";
            data.description = "Super awesome description";
            Random ran = new Random();
            int x = ran.nextInt(i + SAMPLE_DATA_ITEM_COUNT);
            for (int j = 0; j < x; j++)
                data.description += repeat;
            datas.add(data);
        }
        return datas;
    }

    public void testCacheMash()
    {
        RequestHandler rh1=new RequestHandler();
        rh1.setOnDownloadFinishListener(new RequestHandler.DownloadFinishListener()
        {
            public void onSucess(Object payload)
            {
                Log.d(LOG_TAG,"success1 "+payload);
            }
            public void onFail(String errorMessage)
            {
                Log.d(LOG_TAG,"fail1 "+errorMessage);
            }
        });


        RequestHandler rh2=new RequestHandler();
        rh2.setOnDownloadFinishListener(new RequestHandler.DownloadFinishListener()
        {
            public void onSucess(Object payload)
            {
                Log.d(LOG_TAG,"success2 "+payload);
            }
            public void onFail(String errorMessage)
            {
                Log.d(LOG_TAG,"fail2 "+errorMessage);
            }
        });

        RequestHandler rh3=new RequestHandler();
        rh3.setOnDownloadFinishListener(new RequestHandler.DownloadFinishListener()
        {
            public void onSucess(Object payload)
            {
                Log.d(LOG_TAG,"success3 "+payload);
            }
            public void onFail(String errorMessage)
            {
                Log.d(LOG_TAG,"fail3 "+errorMessage);
            }
        });


        String url="https://yt3.ggpht.com/-rgbWQWYxxNw/AAAAAAAAAAI/AAAAAAAAAAA/x_yiQE9Zxq0/s88-c-k-no/photo.jpg";
        String url2="http://i.ytimg.com/vi/EkvRbeL6Ybc/maxresdefault.jpg";
        CacheMash.load(new ImageRequest(getApplicationContext(),url))
                .registerHandler(rh1)
                .registerHandler(rh2)
                .build();

        CacheMash.load(new ImageRequest(getApplicationContext(),url2))
                .registerHandler(rh3)
                .build();
//
//        CacheMash.load(new ImageRequest(getApplicationContext(),url))
//                .registerHandler(rh1)
//                .build();
//
//        CacheMash.load(new ImageRequest(getApplicationContext(),url))
//                .registerHandler(rh1)
//                .build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
