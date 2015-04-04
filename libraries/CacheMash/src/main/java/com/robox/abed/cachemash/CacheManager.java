package com.robox.abed.cachemash;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by mindvalley on 4/3/15.
 */
public class CacheManager {

    private final String LOG_TAG=getClass().getName();
    private HashMap<String,CachableItem> items;
    private int maxSize=5*1024*1024; //Size in bytes
    private static CacheManager instance;

    private CacheManager()
    {
        items=new HashMap<>();
    }

    public static CacheManager getInstance()
    {
        if(instance==null)
            instance=new CacheManager();
        return instance;
    }

    public void setMaxSize(int maxSize) {
        maxSize = maxSize;
    }


    public int getCachSize()
    {


        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(items);
            oos.close();
            Log.d(LOG_TAG,"CashSize: "+baos.size()+", num items: "+items.size());
            return baos.size();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Log.d(LOG_TAG,"CashSize: 0");
        return 0;
    }

    public void cach(String URL,Object payload)
    {
        Log.d(LOG_TAG,"Cash: "+URL+" "+payload);
        items.put(URL,new CachableItem(URL,payload));
        if(getCachSize()>=maxSize)
        {
            ArrayList<CachableItem> list = new ArrayList(items.values());
            Collections.sort(list, new CachableItemComparator());
            while (getCachSize()>=maxSize)
            {

                items.remove(list.get(0).getUrl());
                list.remove(0);
                if(items.size()==0)
                    break;
            }
        }
    }

    public Object get(String url)
    {
        Log.d(LOG_TAG,"get: "+url);
        CachableItem item= items.get(url);
        if(item!=null)
            return item.getPayload();
        else
            return null;
    }

    public class CachableItemComparator implements Comparator<CachableItem>  {

        public int compare(CachableItem object1, CachableItem object2) {
            return object1.getPriorityNumber().compareTo(object2.getPriorityNumber());
        }
    }

}
