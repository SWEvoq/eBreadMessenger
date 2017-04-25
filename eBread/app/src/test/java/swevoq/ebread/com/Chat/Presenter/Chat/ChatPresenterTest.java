package swevoq.ebread.com.Chat.Presenter.Chat;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.internal.zzbmn;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import swevoq.ebread.com.Chat.Model.Chat.Message;
import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

import static org.junit.Assert.*;

/**
 * Created by marco on 21/04/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(ChatPresenter.class)
public class ChatPresenterTest {
    @Mock
    Query expectedMockQuery;

    @Test
    public void getLoggedUser() throws Exception {
        FirebaseUser expected=new FirebaseUser() {
            @NonNull
            @Override
            public String getUid() {
                return null;
            }

            @NonNull
            @Override
            public String getProviderId() {
                return null;
            }

            @Override
            public boolean isAnonymous() {
                return false;
            }

            @Nullable
            @Override
            public List<String> getProviders() {
                return null;
            }

            @NonNull
            @Override
            public List<? extends UserInfo> getProviderData() {
                return null;
            }

            @NonNull
            @Override
            public FirebaseUser zzU(@NonNull List<? extends UserInfo> list) {
                return null;
            }

            @Override
            public FirebaseUser zzaY(boolean b) {
                return null;
            }

            @NonNull
            @Override
            public FirebaseApp zzVE() {
                return null;
            }

            @Nullable
            @Override
            public String getDisplayName() {
                return null;
            }

            @Nullable
            @Override
            public Uri getPhotoUrl() {
                return null;
            }

            @Nullable
            @Override
            public String getEmail() {
                return null;
            }

            @NonNull
            @Override
            public zzbmn zzVF() {
                return null;
            }

            @Override
            public void zza(@NonNull zzbmn zzbmn) {

            }

            @NonNull
            @Override
            public String zzVG() {
                return null;
            }

            @NonNull
            @Override
            public String zzVH() {
                return null;
            }

            @Override
            public boolean isEmailVerified() {
                return false;
            }
        }; //Creo un mock di FirebaseUser

        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        when(firebaseMock.getLoggedUser()).thenReturn(expected); // Quando viene chiamato il metodo getLoggedUser() di FirebaseAccessPoint ritorno il mock di FirebaseUser
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        ChatPresenter c=new ChatPresenter();
        FirebaseUser actual=c.getLoggedUser();
        assertEquals(expected,actual);

        when(firebaseMock.getLoggedUser()).thenReturn(null); //testo il comportamento di getLoggedUser() qualora non ci siano utenti loggati
        expected=null;
        actual=c.getLoggedUser();
        assertEquals(expected,actual);
    }

    @Test
    public void getUserData() throws Exception {
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        when(firebaseMock.getUserData("id")).thenReturn(expectedMockQuery); // Quando viene chiamato il metodo getUserData() di FirebaseAccessPoint ritorno il mock di Query
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        ChatPresenter c=new ChatPresenter();
        Query actual=c.getUserData("id");
        assertEquals(expectedMockQuery,actual);

        when(firebaseMock.getUserData("id")).thenReturn(null); //testo il comportamento di getUserData() qualora non ci sia l'utente corrispondente a id
        expectedMockQuery=null;
        actual=c.getUserData("id");
        assertEquals(expectedMockQuery,actual);
    }

    @Test
    public void newMessage() throws Exception {
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        ChatPresenter c=new ChatPresenter();
        c.newMessage("string","text",new User());
    }

    @Test
    public void deleteSelectedMessages() throws Exception {
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        ChatPresenter c=new ChatPresenter();
        c.deleteSelectedMessages("id",new ArrayList<Message>(), new HashMap<String, ValueEventListener>());
    }

    @Test
    public void getMessages() throws Exception {
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        when(firebaseMock.getMessages("id")).thenReturn(expectedMockQuery); // Quando viene chiamato il metodo getMessages() di FirebaseAccessPoint ritorno il mock di Query
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        ChatPresenter c=new ChatPresenter();
        Query actual=c.getMessages("id");
        assertEquals(expectedMockQuery,actual);

        when(firebaseMock.getUserData("id")).thenReturn(null); //testo il comportamento di getMessages() qualora non ci siano messaggi corrispondenti alla chat id
        expectedMockQuery=null;
        actual=c.getMessages("id");
        assertEquals(expectedMockQuery,actual);
    }
}