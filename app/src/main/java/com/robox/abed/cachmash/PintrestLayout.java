package com.robox.abed.cachmash;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

/**
 * Created by mindvalley on 4/2/15.
 */


public class PintrestLayout extends RelativeLayout {

    View rootView;
    LinearLayout[] tiles;

    public PintrestLayout(Context context, AttributeSet attrs, int tileNum) {
        super(context, attrs);
        tiles=new LinearLayout[tileNum];
        init();
    }

    public PintrestLayout(Context context, int tileNum) {
        this(context, null,tileNum);
        tiles=new LinearLayout[tileNum];
        init();
    }

    private void init()
    {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView=inflater.inflate(R.layout.pintrest_layout,null);
        this.addView(rootView);
        LinearLayout tilesParent=(LinearLayout)rootView.findViewById(R.id.tiles);

        for(int i=0;i<tiles.length;i++)
        {
            Log.d("ss",""+i);
            tiles[i]=new LinearLayout(getContext());
            tiles[i].setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams LParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT,0.5f);
            tiles[i].setLayoutParams(LParams);
            int padding=(int) getContext().getResources().getDimension(R.dimen.padding_between_tiles);
            tiles[i].setPadding(padding,0,padding,0);
            for(int j=0;j<10;j++)
            {
                View item=inflater.inflate(R.layout.tile_item,null);
                item.setClickable(true);
                tiles[i].addView(item);

            }

            tilesParent.addView(tiles[i]);
        }

    }



}
