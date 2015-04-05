package com.robox.abed.cachmash;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.etsy.android.grid.StaggeredGridView;
import com.robox.abed.cachemash.CacheMash;
import com.robox.abed.cachemash.DownloadFinishListener;
import com.robox.abed.cachemash.ImageRequest;
import com.robox.abed.cachemash.JsonRequest;

import org.json.JSONObject;

import java.util.ArrayList;



public class MainActivity extends ActionBarActivity {

    final String LOG_TAG=getClass().getName();
    private StaggeredGridView mGridView;
    private DataAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = (StaggeredGridView) findViewById(R.id.grid_view);
        mAdapter = new DataAdapter(this, R.layout.tile_item, generateSampleData());
        mGridView.setAdapter(mAdapter);

        final JsonRequest jReq=new JsonRequest(getApplicationContext(),"http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=demo");
        jReq.registerListener(new DownloadFinishListener<JSONObject>() {
            @Override
            public void onSucess(JSONObject payload) {
                Log.d(LOG_TAG,"Success Json "+payload);
              
            }

            @Override
            public void onFail(String errorMessage) {
                Log.d(LOG_TAG,"failed Json "+errorMessage);
            }
        });

        CacheMash.load(jReq)
                .build();





    }

    public  ArrayList<Data> generateSampleData() {
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
        data.imageUrl = "https://scontent-sin.xx.fbcdn.net/hphotos-xap1/t31.0-8/10861023_1531025870479391_7445632189531582896_o.png";
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
        data.title = "Pinterest Card 7";
        data.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ant.";
        datas.add(data);


        data = new Data();
        data.imageUrl = "https://scontent-sin.xx.fbcdn.net/hphotos-xap1/t31.0-8/p960x960/10887387_1530347337213911_2515600953886996644_o.jpg";
        data.title = "Pinterest Card 8";
        data.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi.";
        datas.add(data);


        return datas;
    }



}
