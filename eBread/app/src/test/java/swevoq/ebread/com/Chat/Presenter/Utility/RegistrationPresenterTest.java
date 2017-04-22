package swevoq.ebread.com.Chat.Presenter.Utility;

import android.text.TextUtils;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by marco on 22/04/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({TextUtils.class,RegistrationPresenter.class})
public class RegistrationPresenterTest {

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
        RegistrationPresenter p=new RegistrationPresenter();
        String expected="Inserisci dati di registrazione!",actual;

        actual=p.checkData("","");
        assertEquals(expected,actual);

        expected="Inserisci indirizzo mail";
        actual=p.checkData("","password");
        assertEquals(expected,actual);

        expected="Inserisci password";
        actual=p.checkData("email","");
        assertEquals(expected,actual);

        expected="Password inserita troppo corta. Min 8 caratteri !";
        actual=p.checkData("email","shortpw");

        expected="";
        actual=p.checkData("email","password");
        assertEquals(expected,actual);
    }

    @Mock
    Task<AuthResult> mockedExpected;
    @Test
    public void signUp() throws Exception {
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        when(firebaseMock.signUp("email","password")).thenReturn(mockedExpected); //Quando viene chimato il metodo signUp() di FirebaseAccessPoint viene ritornato un oggetto mocked che indica l'azione di signup
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        RegistrationPresenter r=new RegistrationPresenter();
        Task<AuthResult> actual=r.signUp("email","password");
        assertEquals(mockedExpected,actual);

        mockedExpected=null;
        when(firebaseMock.signUp("email","password")).thenReturn(null); //testo il comportamento nel caso fallisca il signUp e venga restituito un Task vuoto
        actual=r.signUp("email","password");
        assertEquals(mockedExpected,actual);
    }

    @Test
    public void saveUser() throws Exception {
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        RegistrationPresenter r=new RegistrationPresenter();
        r.saveUser();
    }
}