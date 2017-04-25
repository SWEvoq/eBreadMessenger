package swevoq.ebread.com.Chat.View.Settings;

import android.os.Bundle;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

/**
 * Created by marco on 24/04/17.
 */
public class ProfileSettingsActivityTest {
    @Mock
    Bundle mockedBundle;
    @Test
    public void onCreate() throws Exception {
        ProfileSettingsActivity p=new ProfileSettingsActivity();
        p.onCreate(mockedBundle);
    }
}