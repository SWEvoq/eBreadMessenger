package swevoq.ebread.com.Chat.Presenter.Utility;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.internal.zzbmn;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import swevoq.ebread.com.Chat.Presenter.Chat.ChatPresenter;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by marco on 22/04/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ChatListPresenter.class)
public class ChatListPresenterTest {
    @Mock
    Query mockedQuery;

    @Test
    public void getChats() throws Exception {
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        when(firebaseMock.getChats()).thenReturn(mockedQuery); // Quando viene chiamato il metodo getChats() di FirebaseAccessPoint ritorno il mock di Query
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        ChatListPresenter c=new ChatListPresenter();
        Query actual=c.getChats();
        assertEquals(mockedQuery,actual);

        mockedQuery=null;
        when(firebaseMock.getChats()).thenReturn(null); //testo il comportamento nel caso in cui non ci siano chat
        actual=c.getChats();
        assertEquals(mockedQuery,actual);
    }

    @Test
    public void getLoggedUser() throws Exception {
        FirebaseUser expected = new FirebaseUser() {
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
        };

        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        when(firebaseMock.getLoggedUser()).thenReturn(expected); //Quando viene chimato il metodo getLoggedUser() di FirebaseAccessPoint viene ritornato l'utente mocked
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        ChatListPresenter c = new ChatListPresenter();
        FirebaseUser actual = c.getLoggedUser();
        assertEquals(expected, actual);

        expected = null;
        when(firebaseMock.getLoggedUser()).thenReturn(null); //testo il comportamento nel caso non ci siano utenti loggati
        actual = c.getLoggedUser();
        assertEquals(expected, actual);
    }
    @Mock
    Query expectedMockQuery;
    @Test
    public void getUserData() throws Exception {
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        when(firebaseMock.getUserData("id")).thenReturn(expectedMockQuery); // Quando viene chiamato il metodo getUserData() di FirebaseAccessPoint ritorno il mock di Query
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        ChatListPresenter c=new ChatListPresenter();
        Query actual=c.getUserData("id");
        assertEquals(expectedMockQuery,actual);

        when(firebaseMock.getUserData("id")).thenReturn(null); //testo il comportamento di getUserData() qualora non ci sia l'utente corrispondente a id
        expectedMockQuery=null;
        actual=c.getUserData("id");
        assertEquals(expectedMockQuery,actual);
    }

    @Test
    public void getLastMessage() throws Exception {
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        when(firebaseMock.getLastMessage("chatId","msgId")).thenReturn(expectedMockQuery); // Quando viene chiamato il metodo getLastMessage di FirebaseAccessPoint ritorno il mock di Query
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        ChatListPresenter c=new ChatListPresenter();
        Query actual=c.getLastMessage("chatId","msgId");
        assertEquals(expectedMockQuery,actual);

        when(firebaseMock.getLastMessage("chatId","msgId")).thenReturn(null); //testo il comportamento di getLastMessage() qualora non ci sia l'ultimo messaggio
        expectedMockQuery=null;
        actual=c.getLastMessage("chatId","msgId");
        assertEquals(expectedMockQuery,actual);
    }

    @Mock
    Context mockedContext;
    @Test
    public void deleteChat() throws Exception {
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        ChatListPresenter c=new ChatListPresenter();
        c.deleteChat(mockedContext,"id");
    }
}