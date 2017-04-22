package swevoq.ebread.com.Chat.Presenter.Utility;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.android.gms.internal.zzbmn;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by marco on 22/04/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({TextUtils.class,AuthPresenter.class})
public class AuthPresenterTest {

    @Before //Mock comportamento metodo TextUtils.isEmpty()
    public void setup() {
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.when(TextUtils.isEmpty(any(CharSequence.class))).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                CharSequence a = (CharSequence) invocation.getArguments()[0];
                return !(a != null && a.length() > 0);
            }
        });
    }

    @Test
    public void checkData() throws Exception {
        AuthPresenter a=new AuthPresenter();
        String expected="Inserisci dati di registrazione!", actual;
        actual=a.checkData("","");
        assertEquals(expected,actual);


        expected="Inserisci indirizzo mail";
        actual=a.checkData("","password");
        assertEquals(expected,actual);

        expected="Inserisci password";
        actual=a.checkData("email","");
        assertEquals(expected,actual);

        expected="";
        actual=a.checkData("email","password");
        assertEquals(expected,actual);
    }


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
        };

        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        when(firebaseMock.getLoggedUser()).thenReturn(expected); //Quando viene chimato il metodo getLoggedUser() di FirebaseAccessPoint viene ritornato l'utente mocked
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        AuthPresenter a=new AuthPresenter();
        FirebaseUser actual=a.getLoggedUser();
        assertEquals(expected,actual);

        expected=null;
        when(firebaseMock.getLoggedUser()).thenReturn(null); //testo il comportamento nel caso non ci siano utenti loggati
        actual=a.getLoggedUser();
        assertEquals(expected,actual);
    }

    @Mock
    Task<AuthResult> mockedExpected;
    @Test
    public void signIn() throws Exception {

        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        when(firebaseMock.signIn("email","password")).thenReturn(mockedExpected); //Quando viene chimato il metodo signIn() di FirebaseAccessPoint viene ritornato un oggetto mocked che indica l'azione di login
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        AuthPresenter a=new AuthPresenter();
        Task<AuthResult> actual=a.signIn("email","password");
        assertEquals(mockedExpected,actual);

        mockedExpected=null;
        when(firebaseMock.signIn("email","password")).thenReturn(null); //testo il comportamento nel caso fallisca il login e venga restituito un Task vuoto
        actual=a.signIn("email","password");
        assertEquals(mockedExpected,actual);
    }
}