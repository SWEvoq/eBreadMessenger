package swevoq.ebread.com.Chat.View.Utility;

import android.os.Bundle;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;


public class RegistrationActivityTest {
    @Mock
    Bundle mockedBundle;

    @Test
    public void onCreate() throws Exception {
        RegistrationActivity r=new RegistrationActivity();
        r.onCreate(mockedBundle);
    }

    @Test
    public void onResume() throws Exception {
        RegistrationActivity r=new RegistrationActivity();
        r.onResume();
    }
}