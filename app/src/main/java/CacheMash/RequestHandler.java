package CacheMash;

/**
 * Created by mindvalley on 4/3/15.
 */
public class RequestHandler {
    Request request;
    DownloadFinishListener downloadFinishListener;

    public RequestHandler()
    {
    }

    public RequestHandler(Request request)
    {
        setRequest(request);
    }

    public void setRequest(Request request) {
        this.request=request;
        this.request.registerHandler(this);
    }

    public void notifyHandler(boolean isSuccessful)
    {
        if(isSuccessful) {
            CacheManager.getInstance().cach(request.getUrl(),request.getPayload());
            if(downloadFinishListener!=null)
                downloadFinishListener.onSucess(request.getPayload());
        }
        else
            if(downloadFinishListener!=null)
                downloadFinishListener.onFail(request.getErrorMessage());

    }


    public void setOnDownloadFinishListener(DownloadFinishListener downloadFinishListener) {
        this.downloadFinishListener = downloadFinishListener;
    }

    public interface DownloadFinishListener
    {
        public void onSucess(Object payload);
        public void onFail(String errorMessage);

    }

    public void cancel()
    {
        request.unregisterHandler(this);
    }
}
