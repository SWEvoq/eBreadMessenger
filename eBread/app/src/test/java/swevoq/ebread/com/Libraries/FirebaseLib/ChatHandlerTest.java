package swevoq.ebread.com.Libraries.FirebaseLib;

import android.content.Context;
import android.net.Uri;
import swevoq.ebread.com.Chat.Model.Chat.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.internal.zzbmn;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

import swevoq.ebread.com.Chat.Model.Profile.User;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ChatHandler.class, FirebaseDatabase.class})
public class ChatHandlerTest {
    @Mock
    DatabaseReference mockedReference;

    @Test
    public void saveUser() throws Exception {
        FirebaseUser user=new FirebaseUser() {
            @NonNull
            @Override
            public String getUid() {
                return "string";
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
        FirebaseDatabase firebaseMock = mock(FirebaseDatabase.class);
        when(firebaseMock.getReference("users")).thenReturn(mockedReference);

        ChatHandler c=new ChatHandler();
        c.saveUser(firebaseMock,user);
    }

    @Test
    public void getChats() throws Exception {
        FirebaseDatabase firebaseMock = mock(FirebaseDatabase.class);
        when(firebaseMock.getReference("chats")).thenReturn(mockedReference);

        ChatHandler c=new ChatHandler();
        Query actual=c.getChats(firebaseMock);
        assertEquals(mockedReference,actual);
    }

    @Test
    public void getUserData() throws Exception {
        FirebaseDatabase firebaseMock = mock(FirebaseDatabase.class);
        when(firebaseMock.getReference("users")).thenReturn(mockedReference);

        ChatHandler c=new ChatHandler();
        Query actual=c.getChats(firebaseMock);
        assertEquals(mockedReference,actual);
    }

    @Test
    public void getLastMessage() throws Exception {
        FirebaseDatabase firebaseMock = mock(FirebaseDatabase.class);
        when(firebaseMock.getReference("messages")).thenReturn(mockedReference);

        ChatHandler c=new ChatHandler();
        Query actual=c.getLastMessage(firebaseMock,"string","string");
        assertEquals(mockedReference,actual);
    }

    @Test
    public void newMessage() throws Exception {
        FirebaseDatabase firebaseMock = mock(FirebaseDatabase.class);
        ChatHandler c=new ChatHandler();
        c.newMessage(firebaseMock,"string","string",new User());
    }

    @Test
    public void setLastMessage() throws Exception {
        FirebaseDatabase firebaseMock = mock(FirebaseDatabase.class);
        ChatHandler c=new ChatHandler();
        //c.setLastMessage(firebaseMock,"string","string");
    }

    @Test
    public void deleteSelectedMessages() throws Exception {
        FirebaseDatabase firebaseMock = mock(FirebaseDatabase.class);
        ChatHandler c=new ChatHandler();
        c.deleteSelectedMessages(firebaseMock,"string",new ArrayList<Message>(), new HashMap<String, ValueEventListener>());
    }

    @Test
    public void getMessages() throws Exception {
        FirebaseDatabase firebaseMock = mock(FirebaseDatabase.class);
        ChatHandler c=new ChatHandler();
        c.getMessages(firebaseMock,"string");
    }

    @Test
    public void createChat() throws Exception {
        FirebaseDatabase firebaseMock = mock(FirebaseDatabase.class);
        Context contextMocked=mock(Context.class);

        ChatHandler c=new ChatHandler();
        c.createChat(firebaseMock,contextMocked,new ArrayList<String>());
    }

    @Test
    public void deleteChat() throws Exception {
        FirebaseDatabase firebaseMock = mock(FirebaseDatabase.class);
        Context contextMocked=mock(Context.class);

        ChatHandler c=new ChatHandler();
        c.deleteChat(firebaseMock,contextMocked,"string");
    }

    @Test
    public void getUsers() throws Exception {
        FirebaseDatabase firebaseMock = mock(FirebaseDatabase.class);
        when(firebaseMock.getReference("users")).thenReturn(mockedReference);

        ChatHandler c=new ChatHandler();
        Query actual=c.getUsers(firebaseMock);
        assertEquals(mockedReference,actual);
    }
}