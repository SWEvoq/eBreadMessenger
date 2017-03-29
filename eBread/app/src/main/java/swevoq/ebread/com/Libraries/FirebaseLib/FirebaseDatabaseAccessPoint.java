package swevoq.ebread.com.Libraries.FirebaseLib;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import swevoq.ebread.com.Chat.Model.Chat.Chat;

/**
 * Created by Teslaru Nicolae on 23/03/2017.
 */

public class FirebaseDatabaseAccessPoint {
    private FirebaseDatabase database;
    private ChatHandler chatHandler;

    public FirebaseDatabaseAccessPoint(){
       database = FirebaseDatabase.getInstance();
       chatHandler = new ChatHandler();
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

    public Query getPartecipants(String authorId){
        return chatHandler.getPartecipants(database,authorId);
    }
}
