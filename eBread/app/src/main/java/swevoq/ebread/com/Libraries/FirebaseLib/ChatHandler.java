package swevoq.ebread.com.Libraries.FirebaseLib;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import swevoq.ebread.com.Chat.Model.Chat.Chat;
import swevoq.ebread.com.Chat.Model.Profile.User;


/**
 * Created by Teslaru Nicolae on 23/03/2017.
 */

public class ChatHandler {

    public ChatHandler(){}

    public void saveUser(FirebaseDatabase database, FirebaseUser id){
        User utente = new User(id.getUid(),id.getEmail(),"","","","https://s-media-cache-ak0.pinimg.com/236x/5b/00/17/5b0017fa4ba19e7bbed08eac0c6a29db.jpg");
        database.getReference("users").child(id.getUid()).setValue(utente);
    }

    public Query getChats(FirebaseDatabase database){
        return database.getReference("chats");
    }

    public Query getUserData(FirebaseDatabase database, String id){
        return database.getReference("users").child(id);
    }

    public Query getLastMessage(FirebaseDatabase database, String chatId, String msgId){
        return database.getReference("messages").child(chatId).child(msgId);
    }

    public Query getPartecipants(FirebaseDatabase database,String authorId){
        return database.getReference("members").child(authorId);
    }
}
