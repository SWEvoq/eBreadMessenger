package swevoq.ebread.com.Chat.View.Utility;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;


public class ChatListActivityTest {
    @Mock
    Bundle mockedBundle;

    @Mock
    MenuItem mockedMenuItem;

    @Mock
    Menu mockedMenu;

    @Test
    public void onCreate() throws Exception {
        ChatListActivity t=new ChatListActivity();
        t.onCreate(mockedBundle);
    }

    @Test
    public void onCreateOptionsMenu() throws Exception {
        ChatListActivity t=new ChatListActivity();
        t.onCreateOptionsMenu(mockedMenu);
    }

    @Test
    public void onOptionsItemSelected() throws Exception {
        ChatListActivity t=new ChatListActivity();
        t.onOptionsItemSelected(mockedMenuItem);
    }
}