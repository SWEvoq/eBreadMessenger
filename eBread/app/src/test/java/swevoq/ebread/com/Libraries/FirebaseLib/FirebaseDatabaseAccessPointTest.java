package swevoq.ebread.com.Libraries.FirebaseLib;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.internal.zzbmn;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;
import org.junit.runner.RunWith;
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
@PrepareForTest({FirebaseDatabaseAccessPoint.class,ChatHandler.class,AddressBookHandler.class,FirebaseDatabase.class})
public class FirebaseDatabaseAccessPointTest {
    @Test
    public void saveUser() throws Exception {
        ChatHandler chatHandlerMock = mock(ChatHandler.class);
        PowerMockito.whenNew(ChatHandler.class).withNoArguments().thenReturn(chatHandlerMock);

        AddressBookHandler addressBookHandlerMock=mock(AddressBookHandler.class);
        PowerMockito.whenNew(AddressBookHandler.class).withNoArguments().thenReturn(addressBookHandlerMock);

        FirebaseDatabaseAccessPoint dbMocked=mock(FirebaseDatabaseAccessPoint.class);
        PowerMockito.whenNew(FirebaseDatabaseAccessPoint.class).withNoArguments().thenReturn(dbMocked);

        FirebaseDatabaseAccessPoint f=new FirebaseDatabaseAccessPoint();
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
        f.saveUser(user);
    }

    @Test
    public void getChats() throws Exception {
        ChatHandler chatHandlerMock = mock(ChatHandler.class);
        PowerMockito.whenNew(ChatHandler.class).withNoArguments().thenReturn(chatHandlerMock);

        AddressBookHandler addressBookHandlerMock=mock(AddressBookHandler.class);
        PowerMockito.whenNew(AddressBookHandler.class).withNoArguments().thenReturn(addressBookHandlerMock);

        FirebaseDatabase dbMocked=mock(FirebaseDatabase.class);
        PowerMockito.mockStatic(FirebaseDatabase.class);
        PowerMockito.when(FirebaseDatabase.getInstance()).thenReturn(dbMocked);

        FirebaseDatabaseAccessPoint f=new FirebaseDatabaseAccessPoint();
        Query mockedQuery=mock(Query.class);
        when(chatHandlerMock.getChats(dbMocked)).thenReturn(mockedQuery);

        Query actual=f.getChats();
        assertEquals(mockedQuery,actual);
    }

    @Test
    public void getUserData() throws Exception {
        ChatHandler chatHandlerMock = mock(ChatHandler.class);
        PowerMockito.whenNew(ChatHandler.class).withNoArguments().thenReturn(chatHandlerMock);

        AddressBookHandler addressBookHandlerMock=mock(AddressBookHandler.class);
        PowerMockito.whenNew(AddressBookHandler.class).withNoArguments().thenReturn(addressBookHandlerMock);

        FirebaseDatabase dbMocked=mock(FirebaseDatabase.class);
        PowerMockito.mockStatic(FirebaseDatabase.class);
        PowerMockito.when(FirebaseDatabase.getInstance()).thenReturn(dbMocked);

        FirebaseDatabaseAccessPoint f=new FirebaseDatabaseAccessPoint();
        Query mockedQuery=mock(Query.class);
        when(chatHandlerMock.getUserData(dbMocked,"id")).thenReturn(mockedQuery);

        Query actual=f.getUserData("id");
        assertEquals(mockedQuery,actual);
    }

    @Test
    public void getLastMessage() throws Exception {
        ChatHandler chatHandlerMock = mock(ChatHandler.class);
        PowerMockito.whenNew(ChatHandler.class).withNoArguments().thenReturn(chatHandlerMock);

        AddressBookHandler addressBookHandlerMock=mock(AddressBookHandler.class);
        PowerMockito.whenNew(AddressBookHandler.class).withNoArguments().thenReturn(addressBookHandlerMock);

        FirebaseDatabase dbMocked=mock(FirebaseDatabase.class);
        PowerMockito.mockStatic(FirebaseDatabase.class);
        PowerMockito.when(FirebaseDatabase.getInstance()).thenReturn(dbMocked);

        FirebaseDatabaseAccessPoint f=new FirebaseDatabaseAccessPoint();
        Query mockedQuery=mock(Query.class);
        when(chatHandlerMock.getLastMessage(dbMocked,"chatId","msgId")).thenReturn(mockedQuery);

        Query actual=f.getLastMessage("chatId","msgId");
        assertEquals(mockedQuery,actual);
    }

    @Test
    public void newMessage() throws Exception {
        ChatHandler chatHandlerMock = mock(ChatHandler.class);
        PowerMockito.whenNew(ChatHandler.class).withNoArguments().thenReturn(chatHandlerMock);

        AddressBookHandler addressBookHandlerMock=mock(AddressBookHandler.class);
        PowerMockito.whenNew(AddressBookHandler.class).withNoArguments().thenReturn(addressBookHandlerMock);

        FirebaseDatabase dbMocked=mock(FirebaseDatabase.class);
        PowerMockito.mockStatic(FirebaseDatabase.class);
        PowerMockito.when(FirebaseDatabase.getInstance()).thenReturn(dbMocked);

        FirebaseDatabaseAccessPoint f=new FirebaseDatabaseAccessPoint();
        f.newMessage("chatId","text",new User());
    }

    @Test
    public void deleteSelectedMessages() throws Exception {
        ChatHandler chatHandlerMock = mock(ChatHandler.class);
        PowerMockito.whenNew(ChatHandler.class).withNoArguments().thenReturn(chatHandlerMock);

        AddressBookHandler addressBookHandlerMock=mock(AddressBookHandler.class);
        PowerMockito.whenNew(AddressBookHandler.class).withNoArguments().thenReturn(addressBookHandlerMock);

        FirebaseDatabase dbMocked=mock(FirebaseDatabase.class);
        PowerMockito.mockStatic(FirebaseDatabase.class);
        PowerMockito.when(FirebaseDatabase.getInstance()).thenReturn(dbMocked);

        FirebaseDatabaseAccessPoint f=new FirebaseDatabaseAccessPoint();
        f.deleteSelectedMessages("chatId", new ArrayList< Message>(), new HashMap<String, ValueEventListener>());
    }

    @Test
    public void getMessages() throws Exception {
        ChatHandler chatHandlerMock = mock(ChatHandler.class);
        PowerMockito.whenNew(ChatHandler.class).withNoArguments().thenReturn(chatHandlerMock);

        AddressBookHandler addressBookHandlerMock=mock(AddressBookHandler.class);
        PowerMockito.whenNew(AddressBookHandler.class).withNoArguments().thenReturn(addressBookHandlerMock);

        FirebaseDatabase dbMocked=mock(FirebaseDatabase.class);
        PowerMockito.mockStatic(FirebaseDatabase.class);
        PowerMockito.when(FirebaseDatabase.getInstance()).thenReturn(dbMocked);

        FirebaseDatabaseAccessPoint f=new FirebaseDatabaseAccessPoint();
        Query mockedQuery=mock(Query.class);
        when(chatHandlerMock.getMessages(dbMocked,"chatId")).thenReturn(mockedQuery);

        Query actual=f.getMessages("chatId");
        assertEquals(mockedQuery,actual);
    }

    @Test
    public void addUserToAddressBook() throws Exception {
        ChatHandler chatHandlerMock = mock(ChatHandler.class);
        PowerMockito.whenNew(ChatHandler.class).withNoArguments().thenReturn(chatHandlerMock);

        AddressBookHandler addressBookHandlerMock=mock(AddressBookHandler.class);
        PowerMockito.whenNew(AddressBookHandler.class).withNoArguments().thenReturn(addressBookHandlerMock);

        FirebaseDatabase dbMocked=mock(FirebaseDatabase.class);
        PowerMockito.mockStatic(FirebaseDatabase.class);
        PowerMockito.when(FirebaseDatabase.getInstance()).thenReturn(dbMocked);

        FirebaseDatabaseAccessPoint f=new FirebaseDatabaseAccessPoint();
        AddressBook addressBookMocked=mock(AddressBook.class);
        Context contextMocked=mock(Context.class);
        f.addUserToAddressBook(addressBookMocked,contextMocked,new ArrayList<String>(),"username");
    }

    @Test
    public void getFriends() throws Exception {
        ChatHandler chatHandlerMock = mock(ChatHandler.class);
        PowerMockito.whenNew(ChatHandler.class).withNoArguments().thenReturn(chatHandlerMock);

        AddressBookHandler addressBookHandlerMock=mock(AddressBookHandler.class);
        PowerMockito.whenNew(AddressBookHandler.class).withNoArguments().thenReturn(addressBookHandlerMock);
        Context contextMocked=mock(Context.class);
        ArrayList<String> expected=new ArrayList<String>();
        PowerMockito.when(addressBookHandlerMock.getFriends(contextMocked)).thenReturn(expected);


        FirebaseDatabase dbMocked=mock(FirebaseDatabase.class);
        PowerMockito.mockStatic(FirebaseDatabase.class);
        PowerMockito.when(FirebaseDatabase.getInstance()).thenReturn(dbMocked);

        FirebaseDatabaseAccessPoint f=new FirebaseDatabaseAccessPoint();
        List<String> actual=f.getFriends(contextMocked);
        assertEquals(expected,actual);
    }

    @Test
    public void createChat() throws Exception {
        ChatHandler chatHandlerMock = mock(ChatHandler.class);
        PowerMockito.whenNew(ChatHandler.class).withNoArguments().thenReturn(chatHandlerMock);

        AddressBookHandler addressBookHandlerMock=mock(AddressBookHandler.class);
        PowerMockito.whenNew(AddressBookHandler.class).withNoArguments().thenReturn(addressBookHandlerMock);

        FirebaseDatabase dbMocked=mock(FirebaseDatabase.class);
        PowerMockito.mockStatic(FirebaseDatabase.class);
        PowerMockito.when(FirebaseDatabase.getInstance()).thenReturn(dbMocked);

        Context contextMocked=mock(Context.class);
        FirebaseDatabaseAccessPoint f=new FirebaseDatabaseAccessPoint();
        f.createChat(contextMocked,new ArrayList<String>());
    }

    @Test
    public void deleteFriends() throws Exception {
        ChatHandler chatHandlerMock = mock(ChatHandler.class);
        PowerMockito.whenNew(ChatHandler.class).withNoArguments().thenReturn(chatHandlerMock);

        AddressBookHandler addressBookHandlerMock=mock(AddressBookHandler.class);
        PowerMockito.whenNew(AddressBookHandler.class).withNoArguments().thenReturn(addressBookHandlerMock);

        FirebaseDatabase dbMocked=mock(FirebaseDatabase.class);
        PowerMockito.mockStatic(FirebaseDatabase.class);
        PowerMockito.when(FirebaseDatabase.getInstance()).thenReturn(dbMocked);

        Context contextMocked=mock(Context.class);
        AddressBook addressBookMocked=mock(AddressBook.class);
        FirebaseDatabaseAccessPoint f=new FirebaseDatabaseAccessPoint();
        f.deleteFriends(addressBookMocked,contextMocked,new ArrayList<String>());
    }

    @Test
    public void deleteChat() throws Exception {
        ChatHandler chatHandlerMock = mock(ChatHandler.class);
        PowerMockito.whenNew(ChatHandler.class).withNoArguments().thenReturn(chatHandlerMock);

        AddressBookHandler addressBookHandlerMock=mock(AddressBookHandler.class);
        PowerMockito.whenNew(AddressBookHandler.class).withNoArguments().thenReturn(addressBookHandlerMock);

        FirebaseDatabase dbMocked=mock(FirebaseDatabase.class);
        PowerMockito.mockStatic(FirebaseDatabase.class);
        PowerMockito.when(FirebaseDatabase.getInstance()).thenReturn(dbMocked);

        Context contextMocked=mock(Context.class);
        FirebaseDatabaseAccessPoint f=new FirebaseDatabaseAccessPoint();
        f.deleteChat(contextMocked,"id");
    }

    @Test
    public void getUsers() throws Exception {
        ChatHandler chatHandlerMock = mock(ChatHandler.class);
        PowerMockito.whenNew(ChatHandler.class).withNoArguments().thenReturn(chatHandlerMock);

        AddressBookHandler addressBookHandlerMock=mock(AddressBookHandler.class);
        PowerMockito.whenNew(AddressBookHandler.class).withNoArguments().thenReturn(addressBookHandlerMock);

        FirebaseDatabase dbMocked=mock(FirebaseDatabase.class);
        PowerMockito.mockStatic(FirebaseDatabase.class);
        PowerMockito.when(FirebaseDatabase.getInstance()).thenReturn(dbMocked);

        FirebaseDatabaseAccessPoint f=new FirebaseDatabaseAccessPoint();
        Query mockedQuery=mock(Query.class);
        when(chatHandlerMock.getUsers(dbMocked)).thenReturn(mockedQuery);

        Query actual=f.getUsers();
        assertEquals(mockedQuery,actual);
    }

    @Test
    public void setContactVoice() throws Exception {
        ChatHandler chatHandlerMock = mock(ChatHandler.class);
        PowerMockito.whenNew(ChatHandler.class).withNoArguments().thenReturn(chatHandlerMock);

        AddressBookHandler addressBookHandlerMock=mock(AddressBookHandler.class);
        PowerMockito.whenNew(AddressBookHandler.class).withNoArguments().thenReturn(addressBookHandlerMock);

        FirebaseDatabase dbMocked=mock(FirebaseDatabase.class);
        PowerMockito.mockStatic(FirebaseDatabase.class);
        PowerMockito.when(FirebaseDatabase.getInstance()).thenReturn(dbMocked);

        Context contextMocked=mock(Context.class);
        FirebaseDatabaseAccessPoint f=new FirebaseDatabaseAccessPoint();
        f.setContactVoice(contextMocked,"userClicked","voiceName");
    }
}