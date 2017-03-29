package swevoq.ebread.com.Libraries.FirebaseLib;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import swevoq.ebread.com.Chat.Model.Chat.Chat;

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

    public Query getPartecipants(String authorId){
        return database.getPartecipants(authorId);
    }
}
