package swevoq.ebread.com.Libraries.FirebaseLib;

import android.content.Context;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import swevoq.ebread.com.Chat.Model.Chat.Chat;
import swevoq.ebread.com.Chat.Model.Chat.Message;
import swevoq.ebread.com.Chat.Model.Chat.TextMessage;
import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.Model.Utility.AddressBook;

/**
 * Created by Teslaru Nicolae on 21/03/2017.
 */

public class FirebaseAccessPoint {
    private FirebaseAuthAccessPoint auth;
    private FirebaseDatabaseAccessPoint database;

    public FirebaseAccessPoint(){
        auth = new FirebaseAuthAccessPoint();
        database = new FirebaseDatabaseAccessPoint();
    }

    public Task<AuthResult> signUp(String email, String password){
        return auth.signUp(email,password);
    }

    public Task<AuthResult> signIn(String email, String password){
        return auth.signIn(email,password);
    }

    public FirebaseUser getLoggedUser(){
        return auth.getLoggedUser();
    }

    public Task<Void> passwordRecovery(String email) {
        return auth.passwordRecovery(email);
    }

    public void saveUser(){
        database.saveUser(auth.getLoggedUser());
    }

    public Query getChats(){
        return database.getChats();
    }

    public Query getUserData(String id){
        return database.getUserData(id);
    }

    public Query getLastMessage(String chatId, String msgId){
        return database.getLastMessage(chatId,msgId);
    }

    public void newMessage(String chatId, String text,User author){
        database.newMessage(chatId,text,author);
    }

    public void deleteSelectedMessages(String chatId,ArrayList<Message> selectedMessages,HashMap<String, ValueEventListener> mListenerMap) {
        database.deleteSelectedMessages(chatId,selectedMessages,mListenerMap);
    }

    public Query getMessages(String chatId) {
        return database.getMessages(chatId);
    }

    public void addUserToAddressBook(AddressBook adapter,Context context, List<String> friends, String username) {
        database.addUserToAddressBook(adapter,context,friends,username);
    }

    public List<String> getFriends(Context context) {
        return database.getFriends(context);
    }

    public void createChat(Context context,ArrayList<String> ids) {
        database.createChat(context,ids);
    }

    public void deleteFriends(AddressBook adapter, Context context, ArrayList<String> ids) {
        database.deleteFriends(adapter,context,ids);
    }

    public void deleteChat(Context context, String id) {
        database.deleteChat(context,id);
    }

    public Query getUsers() {
        return database.getUsers();
    }

    public void setContactVoice(Context context,String userClicked, String voiceName) {
        database.setContactVoice(context,userClicked,voiceName);
    }
}
