package com.robox.abed.cachemash;

/**
* Created by xresco on 4/5/15.
*/
public abstract class DownloadFinishListener <T> {

    public  abstract void  onSucess(T payload);
    public abstract void onFail(String errorMessage);


}
