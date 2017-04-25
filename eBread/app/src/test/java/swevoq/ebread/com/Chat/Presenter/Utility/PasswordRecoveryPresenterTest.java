package swevoq.ebread.com.Chat.Presenter.Utility;

import android.text.TextUtils;

import com.google.android.gms.tasks.Task;

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
@PrepareForTest({TextUtils.class,PasswordRecoveryPresenter.class})
public class PasswordRecoveryPresenterTest {

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
        PasswordRecoveryPresenter p=new PasswordRecoveryPresenter();
        String expected="Inserisci indirizzo mail", actual;

        actual=p.checkData("");
        assertEquals(expected,actual);

        expected="";
        actual=p.checkData("email");
        assertEquals(expected,actual);
    }

    @Mock
    Task<Void> mockedExpected;
    @Test
    public void passwordRecovery() throws Exception {
        FirebaseAccessPoint firebaseMock = mock(FirebaseAccessPoint.class); // Creo un mock di FirebaseAccessPoint
        when(firebaseMock.passwordRecovery("email")).thenReturn(mockedExpected); //Quando viene chimato il metodo passwordRecovery() di FirebaseAccessPoint viene ritornato un task mocked
        when(firebaseMock.passwordRecovery("wrongEmail")).thenReturn(null); //testo il comportamento nel caso la mail per il recuper password non venga riconosciuta e venga restituito un Task vuoto
        PowerMockito.whenNew(FirebaseAccessPoint.class).withNoArguments().thenReturn(firebaseMock); // Quando viene costruito un oggetto FirebaseAccessPoint mediante il costruttore di default viene ritornato l'oggetto mock

        PasswordRecoveryPresenter p=new PasswordRecoveryPresenter();
        Task<Void> actual=p.passwordRecovery("email");
        assertEquals(mockedExpected,actual);

        mockedExpected=null;
        actual=p.passwordRecovery("wrongEmail");
        assertEquals(mockedExpected,actual);
    }
}