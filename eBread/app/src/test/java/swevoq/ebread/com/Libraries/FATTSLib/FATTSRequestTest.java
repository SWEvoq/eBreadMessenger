package swevoq.ebread.com.Libraries.FATTSLib;

import android.util.Log;

import com.android.volley.Response;
import com.squareup.picasso.Downloader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FATTSRequest.class,Log.class})
public class FATTSRequestTest {
    @Mock
    Response.Listener<byte[]> mockedListener;

    @Mock
    Response.ErrorListener mockedErrorListener;

    @Test
    public void getParams() throws Exception {
        HashMap<String,String> expected=new HashMap<String,String>();
        FATTSRequest f=new FATTSRequest(1,"url",mockedListener,mockedErrorListener,expected);
        Map<String,String> actual=f.getParams();
        assertEquals(expected,actual);
    }

    @Test
    public void deliverResponse() throws Exception {
        HashMap<String,String> hm=new HashMap<String,String>();
        FATTSRequest f=new FATTSRequest(1,"url",mockedListener,mockedErrorListener,hm);
        f.deliverResponse(new byte[10]);
    }
}