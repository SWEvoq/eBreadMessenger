package swevoq.ebread.com.Libraries.FirebaseLib;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.internal.zzbmn;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.Transaction;

import org.junit.Test;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by marco on 24/04/17.
 */
public class AuthHandlerTest {
    @Mock
    Task<AuthResult> expected;

    @Mock
    Task<Void> Vexpected;

    @Test
    public void signUp() throws Exception {
        FirebaseAuth firebaseMock = mock(FirebaseAuth.class);
        when(firebaseMock.createUserWithEmailAndPassword("email","pass")).thenReturn(expected);

        AuthHandler a=new AuthHandler();
        Task<AuthResult> actual=a.signUp(firebaseMock,"email","pass");
        assertEquals(expected,actual);
    }

    @Test
    public void signIn() throws Exception {
        FirebaseAuth firebaseMock = mock(FirebaseAuth.class);
        when(firebaseMock.signInWithEmailAndPassword("email","pass")).thenReturn(expected);

        AuthHandler a=new AuthHandler();
        Task<AuthResult> actual=a.signIn(firebaseMock,"email","pass");
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
        FirebaseAuth firebaseMock = mock(FirebaseAuth.class);
        when(firebaseMock.getCurrentUser()).thenReturn(expected);

        AuthHandler a=new AuthHandler();
        FirebaseUser actual=a.getLoggedUser(firebaseMock);
        assertEquals(expected,actual);
    }

    @Test
    public void passwordRecovery() throws Exception {
        FirebaseAuth firebaseMock = mock(FirebaseAuth.class);
        when(firebaseMock.sendPasswordResetEmail("email")).thenReturn(Vexpected);

        AuthHandler a=new AuthHandler();
        Task<Void> actual=a.passwordRecovery(firebaseMock,"email");
        assertEquals(expected,actual);
    }
}