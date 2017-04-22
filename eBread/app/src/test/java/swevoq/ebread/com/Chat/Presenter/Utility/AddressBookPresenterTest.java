package swevoq.ebread.com.Chat.Presenter.Utility;

import android.content.Context;

import com.google.firebase.database.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import swevoq.ebread.com.Chat.Model.Utility.AddressBook;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by marco on 22/04/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(AddressBookPresenter.class)
public class AddressBookPresenterTest {
    @Mock
    Context mockedContext;

    @Mock
    AddressBook mockedAddressBook;

    @Mock
    Query mockedQuery;

    @Test
    public void addUserToAddressBook() throws Exception {
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        AddressBookPresenter a=new AddressBookPresenter();
        a.addUserToAddressBook(mockedAddressBook,mockedContext,new ArrayList<String>(),"username");
    }

    @Test
    public void getFriends() throws Exception {
        List<String> expected=new ArrayList<String>();
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        when(firebaseMock.getFriends(mockedContext)).thenReturn(expected); // Quando viene chiamato il metodo getFriends() di FirebaseAccessPoint ritorno il mock della lista di amici
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        AddressBookPresenter a=new AddressBookPresenter();
        List<String> actual=a.getFriends(mockedContext);
        assertEquals(expected,actual);

        expected=null;
        when(firebaseMock.getFriends(mockedContext)).thenReturn(null); //Testo il comportamento nel caso la lista ritornata non contenga amici
        actual=a.getFriends(mockedContext);
        assertEquals(expected,actual);
    }

    @Test
    public void createChat() throws Exception {
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        AddressBookPresenter a=new AddressBookPresenter();
        a.createChat(mockedContext,new ArrayList<String>());
    }

    @Test
    public void deleteFriends() throws Exception {
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        AddressBookPresenter a=new AddressBookPresenter();
        a.deleteFriends(mockedAddressBook,mockedContext,new ArrayList<String>());
    }

    @Test
    public void getUsers() throws Exception {
        Query expected=mockedQuery;
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        when(firebaseMock.getUsers()).thenReturn(expected); // Quando viene chiamato il metodo getUsers() di FirebaseAccessPoint ritorno il mock della lista degli utenti
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        AddressBookPresenter a=new AddressBookPresenter();
        Query actual=a.getUsers();
        assertEquals(expected,actual);
    }

    @Test
    public void setContactVoice() throws Exception {
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        AddressBookPresenter a=new AddressBookPresenter();
        a.setContactVoice(mockedContext,"string","voiceName");
    }
}