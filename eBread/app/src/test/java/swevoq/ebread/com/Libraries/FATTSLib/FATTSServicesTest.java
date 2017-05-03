package swevoq.ebread.com.Libraries.FATTSLib;

import android.widget.TextView;

import com.android.volley.VolleyError;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import swevoq.ebread.com.Chat.Model.Chat.TextMessage;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FATTSServices.class,FATTSRequest.class})
public class FATTSServicesTest {
    @Test
    public void performAudioRequest() throws Exception {
        TextView textViewMocked=mock(TextView.class);
        FATTSServices f=new FATTSServices(textViewMocked,new TextMessage());
        f.performAudioRequest();
    }

    @Test
    public void onResponse() throws Exception {
        TextView textViewMocked=mock(TextView.class);
        FATTSServices f=new FATTSServices(textViewMocked,new TextMessage());
        f.onResponse(new byte[10]);
    }

    @Test
    public void onErrorResponse() throws Exception {
        TextView textViewMocked=mock(TextView.class);
        FATTSServices f=new FATTSServices(textViewMocked,new TextMessage());
        f.onErrorResponse(new VolleyError());;
    }
}