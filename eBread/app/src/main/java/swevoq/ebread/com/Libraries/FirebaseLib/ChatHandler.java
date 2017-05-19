package swevoq.ebread.com.Libraries.FirebaseLib;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import swevoq.ebread.com.Chat.Model.Chat.Chat;
import swevoq.ebread.com.Chat.Model.Chat.Message;
import swevoq.ebread.com.Chat.Model.Chat.TextMessage;
import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.View.Chat.ChatActivity;
import swevoq.ebread.com.Chat.View.Utility.AddressBookActivity;
import swevoq.ebread.com.Chat.View.Utility.ChatListActivity;


/**
 * Created by Teslaru Nicolae on 23/03/2017.
 */

public class ChatHandler {

    public void saveUser(FirebaseDatabase database, FirebaseUser id){
        User utente = new User(id.getUid(),id.getEmail(),"","","","https://cdn2.iconfinder.com/data/icons/rcons-user/32/male-circle-128.png");
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

    public void newMessage(FirebaseDatabase database, String chatId, String text, User author){
        DatabaseReference ref = database.getReference("messages").child(chatId).push();
        Date createdAt = new Date();
        ref.child("author").setValue(author.getId());
        ref.child("createdAt").setValue(createdAt);
        ref.child("message").setValue(text);
        setLastMessage(database,chatId,ref.getKey());
    }

    private void setLastMessage(FirebaseDatabase database, String chatId, String messageId){
        database.getReference("chats").child(chatId).child("lastMessage").setValue(messageId);
    }

    public void deleteSelectedMessages(FirebaseDatabase database,String chatId,ArrayList<Message> selectedMessages,HashMap<String, ValueEventListener> mListenerMap) {
        for(int i=0;i<selectedMessages.size();i++){
                database.getReference("messages").child(chatId).child(selectedMessages.get(i).getId()).removeEventListener(mListenerMap.get(selectedMessages.get(i).getId()));
                database.getReference("messages").child(chatId).child(selectedMessages.get(i).getId()).removeValue();
        }
    }

    public Query getMessages(FirebaseDatabase database, String chatId) {
        return database.getReference("messages").child(chatId);
    }

    public void createChat(FirebaseDatabase database, final Context context, final ArrayList<String> ids) {
        final DatabaseReference ref = database.getReference("chats").push();
        String dialogName=ids.get(0);
        String dialogPhoto="https://cdn4.iconfinder.com/data/icons/internet-and-social-networking/32/i22_internet-512.png";
        for(int i=1;i<ids.size();i++){
            dialogName+=","+ids.get(i);
        }
        if(ids.size()==1)
            dialogPhoto="https://cdn2.iconfinder.com/data/icons/rcons-user/32/male-circle-128.png";
        ref.child("dialogName").setValue(dialogName);
        ref.child("dialogPhoto").setValue(dialogPhoto);
        ref.child("lastMessage").setValue(" ");
        ref.child("unreadCount").setValue(0);
        ref.child("members").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(true);
        database.getReference("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot member : dataSnapshot.getChildren()){
                    for(int i=0;i<ids.size();i++) {
                        if (member.child("username").getValue(String.class).equals(ids.get(i)))
                            ref.child("members").child(member.getKey()).setValue(true);
                    }
                }
                // Vai alla Chat Activity appena creata.
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("chatId",ref.getKey());
                context.startActivity(intent);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void deleteChat(FirebaseDatabase database, final Context context,String id) {
        database.getReference().child("messages").child(id).removeValue();
        database.getReference().child("chats").child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, "La chat è stata eliminata!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Query getUsers(FirebaseDatabase database) {
        return database.getReference("users");
    }

    public void updateAvatar(FirebaseDatabase database, String url) {
        database.getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("avatar").setValue(url);
    }
}
