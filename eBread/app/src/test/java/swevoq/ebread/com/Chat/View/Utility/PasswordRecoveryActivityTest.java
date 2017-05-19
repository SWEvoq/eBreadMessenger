package swevoq.ebread.com.Chat.View.Utility;

import android.os.Bundle;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;


public class PasswordRecoveryActivityTest {
    @Mock
    Bundle mockedBundle;

    @Test
    public void onCreate() throws Exception {
        PasswordRecoveryActivity p=new PasswordRecoveryActivity();
        p.onCreate(mockedBundle);
    }
}