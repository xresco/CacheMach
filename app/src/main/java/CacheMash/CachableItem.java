package CacheMash;

import java.io.Serializable;

/**
 * Created by mindvalley on 4/3/15.
 */
public class CachableItem implements Serializable {
    private int priorityNumber=0;
    private Object payload;
    private String url;


    public CachableItem(String url, Object payload)
    {
        this.url=url;
        this.payload=payload;
        priorityNumber=0;
    }

//    public int getSize()
//    {
//        return payload.;
//    }

    public String getUrl() {
        return url;
    }

    public Object getPayload() {
        priorityNumber++;
        return payload;
    }

    public Integer getPriorityNumber() {
        return priorityNumber;
    }


}

