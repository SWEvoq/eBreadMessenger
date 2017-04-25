package swevoq.ebread.com.Chat.View.Chat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ChatActivity.class,Activity.class})
public class ChatActivityTest {

    @Mock
    Window mockedWindow;

    @Mock
    Activity mockedActivity;

    @Mock
    Bundle mockedBundle;

    @Mock
    Menu mockedMenu;

    @Mock
    MenuItem mockedMenuItem;
    @Test
    public void onCreate() throws Exception {
        ChatActivity c=new ChatActivity();
        PowerMockito.whenNew(Activity.class).withAnyArguments().thenReturn(mockedActivity);
        when(mockedActivity.getWindow()).thenReturn(mockedWindow);
        c.onCreate(mockedBundle);
    }

    @Test
    public void onCreateOptionsMenu() throws Exception {
        ChatActivity c=new ChatActivity();
        boolean expected=true;
        boolean actual=c.onCreateOptionsMenu(mockedMenu);
        assertEquals(expected,actual);
    }

    @Test
    public void onOptionsItemSelected() throws Exception {
        ChatActivity c=new ChatActivity();
        boolean expected=true;
        boolean actual=c.onOptionsItemSelected(mockedMenuItem);
        assertEquals(expected,actual);
    }

    @Test
    public void onSelectionChanged() throws Exception {
        ChatActivity c=new ChatActivity();
        c.onSelectionChanged(20);
    }

    @Test
    public void onBackPressed() throws Exception {
        ChatActivity c=new ChatActivity();
        c.onBackPressed();
    }
}