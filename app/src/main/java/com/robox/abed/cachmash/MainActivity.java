package com.robox.abed.cachmash;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import com.etsy.android.grid.StaggeredGridView;
import com.robox.abed.cachemash.CacheMash;
import com.robox.abed.cachemash.ImageRequest;
import com.robox.abed.cachemash.RequestHandler;

import java.util.ArrayList;
import java.util.Random;



public class MainActivity extends ActionBarActivity {

    final String LOG_TAG=getClass().getName();
    private StaggeredGridView mGridView;
    private DataAdapter mAdapter;
    private final int SAMPLE_DATA_ITEM_COUNT=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = (StaggeredGridView) findViewById(R.id.grid_view);
        mAdapter = new DataAdapter(this, R.layout.tile_item, generateSampleData());
        mGridView.setAdapter(mAdapter);


    }

    public  ArrayList<Data> generateSampleData() {
        String repeat = " repeat";
        final ArrayList<Data> datas = new ArrayList<>();

        Data data = new Data();
        data.imageUrl = "https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-xap1/t31.0-8/1898707_1415814908667155_901254815_o.jpg";
        data.title = "Pinterest Card 1";
        data.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ne.";
        datas.add(data);

        data = new Data();
        data.imageUrl = "https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-xap1/t31.0-8/1898707_1415814908667155_901254815_o.jpg";
        data.title = "Pinterest Card 2";
        data.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi.";
        datas.add(data);


        data = new Data();
        data.imageUrl = "https://fbcdn-sphotos-a-a.akamaihd.net/hphotos-ak-xpf1/t31.0-8/1956692_1531731093742202_2399034899305733720_o.jpg";
        data.title = "Pinterest Card 3";
        data.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.";
        datas.add(data);

        data = new Data();
        data.imageUrl = "https://fbcdn-sphotos-a-a.akamaihd.net/hphotos-ak-xpf1/t31.0-8/1956692_1531731093742202_2399034899305733720_o.jpg";
        data.title = "Pinterest Card 4";
        data.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ne.";
        datas.add(data);


        data = new Data();
        data.imageUrl = "https://fbcdn-sphotos-d-a.akamaihd.net/hphotos-ak-xpa1/t31.0-8/p960x960/10841844_1542807412634570_5908023753903158595_o.jpg";
        data.title = "Pinterest Card 5";
        data.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ant.";
        datas.add(data);


        data = new Data();
        data.imageUrl = "https://scontent-sin.xx.fbcdn.net/hphotos-xap1/t31.0-8/p960x960/1501018_1530979630484015_4668008433368315472_o.jpg";
        data.title = "Pinterest Card 6";
        data.description = "Super awesome description";
        datas.add(data);


        data = new Data();
        data.imageUrl = "https://fbcdn-sphotos-g-a.akamaihd.net/hphotos-ak-xaf1/t31.0-8/1900607_1415842891997690_1405629767_o.jpg";
        data.title = "Pinterest Card";
        data.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ant.";
        datas.add(data);


        data = new Data();
        data.imageUrl = "https://scontent-sin.xx.fbcdn.net/hphotos-xap1/t31.0-8/p960x960/10887387_1530347337213911_2515600953886996644_o.jpg";
        data.title = "Pinterest Card";
        data.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi.";
        datas.add(data);


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
        CacheMash.load(new ImageRequest(getApplicationContext(), url))
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


}
