package swevoq.ebread.com.Libraries.FATTSLib;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nicolae on 27/03/2017.
 */

public class AudioRequest extends Request<byte[]> {

    private final Response.Listener<byte[]> mListener;
    private Map<String, String> mParams;
    // creazione di mappa statica per accedere direttamente agli header
    public Map<String, String> responseHeaders;

    public AudioRequest(int post, String mUrl, Response.Listener<byte[]> listener, Response.ErrorListener errorListener, HashMap<String,String> params){
        super(post,mUrl,errorListener);
        //faccio in modo che la richiesta non utilizzi cache.
        setShouldCache(false);
        mListener = listener;
        mParams = params;
    }

    @Override
    protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
        return mParams;
    };

    @Override
    protected void deliverResponse(byte[] response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {

        //Initialise local responseHeaders map with response headers received
        responseHeaders = response.headers;

        //Pass the response data here
        return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
    }
}
