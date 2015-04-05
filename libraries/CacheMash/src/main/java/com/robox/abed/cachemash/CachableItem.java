package com.robox.abed.cachemash;

import java.io.Serializable;

/**
 * Created by xresco on 4/3/15.
 */
public class CachableItem implements Serializable {
    private int priorityNumber=0;
    private Object payload;
    private String url;
    private int payloadSize; // the size of the payload in bytes


    public CachableItem(String url, Object payload,int size)
    {
        this.url=url;
        this.payload=payload;
        this.payloadSize=size;
        priorityNumber=0;
    }


    public String getUrl() {
        return url;
    }

    public Object getPayload() {
        priorityNumber++;
        return payload;
    }

    public int getPayloadSize() {
        return payloadSize;
    }

    public Integer getPriorityNumber() {
        return priorityNumber;
    }


}

