package swevoq.ebread.com.Libraries.FirebaseLib;

import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import swevoq.ebread.com.Chat.Model.Utility.AddressBook;

import static org.junit.Assert.*;


public class AddressBookHandlerTest {
    @Mock
    FirebaseDatabase firebaseDatabaseMocked;
    @Mock
    Context mockedContext;

    @Test
    public void addUserToAddressBook() throws Exception {
        AddressBookHandler a=new AddressBookHandler();
        a.addUserToAddressBook(firebaseDatabaseMocked,new AddressBook(mockedContext,1,new ArrayList<String>()),mockedContext,new ArrayList<String>(),"string");
    }

    @Test
    public void updateAddressBook() throws Exception {
        AddressBookHandler a=new AddressBookHandler();
        //a.updateAddressBook(mockedContext,"username");
    }

    @Test
    public void getFriends() throws Exception {
        AddressBookHandler a=new AddressBookHandler();
        List<String> list=a.getFriends(mockedContext);
    }

    @Test
    public void deleteFriends() throws Exception {
        AddressBookHandler a=new AddressBookHandler();
        a.deleteFriends(new AddressBook(mockedContext,1,new ArrayList<String>()),mockedContext,new ArrayList<String>());
    }

    @Test
    public void setContactVoice() throws Exception {
        AddressBookHandler a=new AddressBookHandler();
        a.setContactVoice(mockedContext,"string","it");
    }
}