package swevoq.ebread.com.Libraries.FATTSLib;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Teslaru Nicolae on 24/04/2017.
 */

public class FATTSRequest extends Request<byte[]> {

    private final Response.Listener<byte[]> listener;
    private Map<String,String> params;

    public FATTSRequest(int post, String url, Response.Listener<byte[]> listener, Response.ErrorListener errorListener, HashMap<String,String> params){
        super(post,url,errorListener);
        setShouldCache(false);
        this.listener = listener;
        this.params = params;
    }
    @Override
    protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
        return params;
    };

    @Override
    protected void deliverResponse(byte[] response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
    }
}
