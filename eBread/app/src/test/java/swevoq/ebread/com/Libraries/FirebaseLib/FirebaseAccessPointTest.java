package swevoq.ebread.com.Libraries.FirebaseLib;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.internal.zzbmn;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import swevoq.ebread.com.Chat.Model.Chat.Message;
import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.Model.Utility.AddressBook;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FirebaseAccessPoint.class,FirebaseAuthAccessPoint.class})
public class FirebaseAccessPointTest {
    @Mock
    Task<AuthResult> tMocked;

    @Mock
    Task<Void> tvMocked;

    @Test
    public void signUp() throws Exception {
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        when(firebaseMock.signUp("email","password")).thenReturn(tMocked);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        Task<AuthResult> actual=f.signUp("email","password");
        assertEquals(tMocked,actual);
    }

    @Test
    public void signIn() throws Exception {
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        when(firebaseMock.signIn("email","password")).thenReturn(tMocked);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        Task<AuthResult> actual=f.signIn("email","password");
        assertEquals(tMocked,actual);
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
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        when(firebaseMock.getLoggedUser()).thenReturn(user);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        FirebaseUser actual=f.getLoggedUser();
        assertEquals(user,actual);
    }

    @Test
    public void passwordRecovery() throws Exception {
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        when(firebaseMock.passwordRecovery("email")).thenReturn(tvMocked);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        Task<Void> actual=f.passwordRecovery("email");
        assertEquals(tvMocked,actual);
    }

    @Test
    public void saveUser() throws Exception {
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
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);

        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        f.saveUser();
    }

    @Test
    public void getChats() throws Exception {
        Query qMocked=mock(Query.class);
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        when(dbMocked.getChats()).thenReturn(qMocked);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        Query actual=f.getChats();
        assertEquals(qMocked,actual);
    }

    @Test
    public void getUserData() throws Exception {
        Query qMocked=mock(Query.class);
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        when(dbMocked.getUserData("id")).thenReturn(qMocked);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        Query actual=f.getUserData("id");
        assertEquals(qMocked,actual);
    }

    @Test
    public void getLastMessage() throws Exception {
        Query qMocked=mock(Query.class);
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        when(dbMocked.getLastMessage("chatid","msgid")).thenReturn(qMocked);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        Query actual=f.getLastMessage("chatid","msgid");
        assertEquals(qMocked,actual);
    }

    @Test
    public void newMessage() throws Exception {
        User user=new User();
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        f.newMessage("chatid","text",user);
    }

    @Test
    public void deleteSelectedMessages() throws Exception {
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        f.deleteSelectedMessages("chatid",new ArrayList<Message>(),new HashMap<String, ValueEventListener>());
    }

    @Test
    public void getMessages() throws Exception {
        Query qMocked=mock(Query.class);
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        when(dbMocked.getMessages("chatid")).thenReturn(qMocked);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        Query actual=f.getMessages("chatid");
        assertEquals(qMocked,actual);
    }

    @Test
    public void addUserToAddressBook() throws Exception {
        AddressBook ab=mock(AddressBook.class);
        Context context=mock(Context.class);
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        f.addUserToAddressBook(ab,context,new ArrayList<String>(),"string");
    }

    @Test
    public void getFriends() throws Exception {
        List<String> expected=new ArrayList<String>();
        Context c=mock(Context.class);
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        when(dbMocked.getFriends(c)).thenReturn(expected);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        List<String> actual=f.getFriends(c);
        assertEquals(expected,actual);
    }

    @Test
    public void createChat() throws Exception {
        Context context=mock(Context.class);
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        f.createChat(context,new ArrayList<String>());
    }

    @Test
    public void deleteFriends() throws Exception {
        Context context=mock(Context.class);
        AddressBook a=mock(AddressBook.class);
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        f.deleteFriends(a,context,new ArrayList<String>());
    }

    @Test
    public void deleteChat() throws Exception {
        Context context=mock(Context.class);
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        f.deleteChat(context,"id");
    }

    @Test
    public void getUsers() throws Exception {
        Query qMocked=mock(Query.class);
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        when(dbMocked.getUsers()).thenReturn(qMocked);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        Query actual=f.getUsers();
        assertEquals(qMocked,actual);
    }

    @Test
    public void setContactVoice() throws Exception {
        Context context=mock(Context.class);
        FirebaseAuthAccessPoint firebaseMock = mock(FirebaseAuthAccessPoint.class);
        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);
        PowerMockito.whenNew(FirebaseAuthAccessPoint.class).withNoArguments().thenReturn(firebaseMock);

        FirebaseAccessPoint f=new FirebaseAccessPoint();
        f.setContactVoice(context,"id","voiceName");
    }
}