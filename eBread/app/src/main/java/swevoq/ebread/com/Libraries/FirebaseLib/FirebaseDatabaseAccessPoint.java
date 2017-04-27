package swevoq.ebread.com.Libraries.FirebaseLib;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
import swevoq.ebread.com.Chat.Model.Settings.TextSettings;
import swevoq.ebread.com.Chat.Model.Settings.VoiceSettings;
import swevoq.ebread.com.Chat.Model.Utility.AddressBook;

/**
 * Created by Teslaru Nicolae on 23/03/2017.
 */

public class FirebaseDatabaseAccessPoint {
    private FirebaseDatabase database;
    private ChatHandler chatHandler;
    private AddressBookHandler addressbookHandler;
    private SettingsHandler settingsHandler;

    public FirebaseDatabaseAccessPoint(){
        database = FirebaseDatabase.getInstance();
        chatHandler = new ChatHandler();
        addressbookHandler = new AddressBookHandler();
        settingsHandler = new SettingsHandler();
    }

    public void saveUser(FirebaseUser id){
        chatHandler.saveUser(database,id);
    }

    public Query getChats(){
        return chatHandler.getChats(database);
    }

    public Query getUserData(String id){
        return chatHandler.getUserData(database,id);
    }

    public Query getLastMessage(String chatId, String msgId){
        return chatHandler.getLastMessage(database,chatId,msgId);
    }

    public void newMessage(String chatId, String text,User author){
        chatHandler.newMessage(database,chatId,text,author);
    }

    public void deleteSelectedMessages(String chatId,ArrayList<Message> selectedMessages,HashMap<String, ValueEventListener> mListenerMap) {
        chatHandler.deleteSelectedMessages(database,chatId,selectedMessages,mListenerMap);
    }

    public Query getMessages(String chatId) {
        return chatHandler.getMessages(database,chatId);
    }

    public void addUserToAddressBook(AddressBook adapter,Context context, List<String> friends, String username) {
        addressbookHandler.addUserToAddressBook(database, adapter, context, friends,username);
    }

    public List<String> getFriends(Context context) {
        return addressbookHandler.getFriends(context);
    }

    public void createChat(Context context,ArrayList<String> ids) {
        chatHandler.createChat(database,context,ids);
    }

    public void deleteFriends(AddressBook adapter, Context context, ArrayList<String> ids) {
        addressbookHandler.deleteFriends(adapter,context,ids);
    }

    public void deleteChat(Context context, String id) {
        chatHandler.deleteChat(database,context,id);
    }

    public Query getUsers() {
        return chatHandler.getUsers(database);
    }

    public void setContactVoice(Context context,String userClicked, String voiceName) {
        addressbookHandler.setContactVoice(context,userClicked,voiceName);
    }

    public void updateUser(User dummy) {
        settingsHandler.updateUser(database,dummy);
    }

    public void updateAvatar(String url) {
        chatHandler.updateAvatar(database,url);
    }

    public VoiceSettings getVoiceSettings(Context context) {
        return settingsHandler.getVoiceSettings(context);
    }


    public void updateVoiceSettings(Context context, VoiceSettings updatedVoiceSettings) {
        settingsHandler.updateVoiceSettings(context,updatedVoiceSettings);
    }

    public TextSettings getTextSettings(Context context) {
        return settingsHandler.getTextSettings(context);
    }

    public void updateTextSettings(Context context, TextSettings updatedTextSettings) {
        settingsHandler.updateTextSettings(context,updatedTextSettings);
    }

    public String getUserVoice(Context context,String id) {
        return addressbookHandler.getUserVoice(context,id);
    }

    public void enableFirebaseCache() {
        DatabaseReference scoresRef = FirebaseDatabase.getInstance().getReference();
        scoresRef.keepSynced(true);
    }
}
