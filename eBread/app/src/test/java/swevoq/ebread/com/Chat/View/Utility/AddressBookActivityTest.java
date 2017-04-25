package swevoq.ebread.com.Chat.View.Utility;

import android.os.Bundle;

import org.junit.Test;
import org.mockito.Mock;

import swevoq.ebread.com.Chat.Model.Utility.AddressBook;

import static org.junit.Assert.*;


public class AddressBookActivityTest {
    @Mock
    Bundle mockedBundle;
    @Test
    public void onCreate() throws Exception {
        AddressBookActivity a=new AddressBookActivity();
        a.onCreate(mockedBundle);
    }

    @Test
    public void onBackPressed() throws Exception {
        AddressBookActivity a=new AddressBookActivity();
        a.onBackPressed();
    }

    @Test
    public void onStop() throws Exception {
        AddressBookActivity a=new AddressBookActivity();
        a.onStop();
    }
}