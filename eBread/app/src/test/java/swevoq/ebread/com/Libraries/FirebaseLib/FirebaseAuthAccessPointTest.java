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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
@RunWith(PowerMockRunner.class)
@PrepareForTest({FirebaseAuth.class,AuthHandler.class})
public class FirebaseAuthAccessPointTest {
    @Mock
    Task<AuthResult> taskMocked;

    @Mock
    Task<Void> taskVoidMocked;

    @Test
    public void signUp() throws Exception {
        FirebaseAuth authMock = mock(FirebaseAuth.class);
        PowerMockito.mockStatic(FirebaseAuth.class);
        PowerMockito.when(FirebaseAuth.getInstance()).thenReturn(authMock);

        AuthHandler authHandlerMock = mock(AuthHandler.class);
        PowerMockito.whenNew(AuthHandler.class).withNoArguments().thenReturn(authHandlerMock);
        FirebaseAccessPoint f=new FirebaseAccessPoint();
        Task<AuthResult> actual=f.signUp("mail","password");
        assertEquals(taskMocked,actual);
    }

    @Test
    public void signIn() throws Exception {
        FirebaseAuth authMock = mock(FirebaseAuth.class);
        PowerMockito.mockStatic(FirebaseAuth.class);
        PowerMockito.when(FirebaseAuth.getInstance()).thenReturn(authMock);

        AuthHandler authHandlerMock = mock(AuthHandler.class);
        PowerMockito.whenNew(AuthHandler.class).withNoArguments().thenReturn(authHandlerMock);
        FirebaseAccessPoint f=new FirebaseAccessPoint();
        Task<AuthResult> actual=f.signIn("mail","password");
        assertEquals(taskMocked,actual);
    }

    @Test
    public void getLoggedUser() throws Exception {
        FirebaseUser user=new FirebaseUser() {
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
        FirebaseAuth authMock = mock(FirebaseAuth.class);
        PowerMockito.mockStatic(FirebaseAuth.class);
        PowerMockito.when(FirebaseAuth.getInstance()).thenReturn(authMock);

        AuthHandler authHandlerMock = mock(AuthHandler.class);
        PowerMockito.whenNew(AuthHandler.class).withNoArguments().thenReturn(authHandlerMock);
        FirebaseAccessPoint f=new FirebaseAccessPoint();
        FirebaseUser actual=f.getLoggedUser();
        assertEquals(user,actual);
    }

    @Test
    public void passwordRecovery() throws Exception {
        FirebaseAuth authMock = mock(FirebaseAuth.class);
        PowerMockito.mockStatic(FirebaseAuth.class);
        PowerMockito.when(FirebaseAuth.getInstance()).thenReturn(authMock);

        AuthHandler authHandlerMock = mock(AuthHandler.class);
        PowerMockito.whenNew(AuthHandler.class).withNoArguments().thenReturn(authHandlerMock);
        FirebaseAccessPoint f=new FirebaseAccessPoint();
        Task<Void> actual=f.passwordRecovery("mail");
        assertEquals(taskVoidMocked,actual);
    }
}