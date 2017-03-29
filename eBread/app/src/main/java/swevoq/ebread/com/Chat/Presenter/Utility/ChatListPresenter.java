package swevoq.ebread.com.Chat.Presenter.Utility;


import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.View.Utility.ChatListActivity;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

/**
 * Created by Teslaru Nicolae on 21/03/2017.
 */

public class ChatListPresenter {
    private FirebaseAccessPoint firebase;
    public ChatListPresenter(){
        firebase = new FirebaseAccessPoint();
    }

    public Query getChats(){
        return firebase.getChats();
    }

    public FirebaseUser getLoggedUser(){
        return firebase.getLoggedUser();
    }

    public Query getUserData(String id){
        return firebase.getUserData(id);
    }

    public Query getLastMessage(String chatId, String msgId){
        return firebase.getLastMessage(chatId,msgId);
    }

    public Query getPartecipants(String authorId){
        return firebase.getPartecipants(authorId);
    }

}
