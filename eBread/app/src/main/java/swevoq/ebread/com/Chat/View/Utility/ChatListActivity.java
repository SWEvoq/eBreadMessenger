package swevoq.ebread.com.Chat.View.Utility;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Debug;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.commons.models.IUser;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import swevoq.ebread.com.Chat.Model.Chat.Chat;
import swevoq.ebread.com.Chat.Model.Chat.Message;
import swevoq.ebread.com.Chat.Model.Chat.TextMessage;
import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.Presenter.Utility.ChatListPresenter;
import swevoq.ebread.com.Chat.View.Chat.ChatActivity;
import swevoq.ebread.com.R;

public class ChatListActivity extends AppCompatActivity {

    private DialogsListAdapter<Chat> dialogsListAdapter;
    private ChatListPresenter presenter;
    private ArrayList<Chat> chats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        presenter = new ChatListPresenter();
        DialogsList dialogsListView = (DialogsList) findViewById(R.id.dialogsList);
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                //If you using another library - write here your way to load image
                Picasso.with(ChatListActivity.this).load(url).into(imageView);
            }
        };
        dialogsListAdapter = new DialogsListAdapter<>(imageLoader);
        chats = new ArrayList<Chat>();
        presenter.getChats().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chats.clear();
                //Inside Chats
                for(DataSnapshot chat: dataSnapshot.getChildren()){
                    if(chat.child("members").hasChild(presenter.getLoggedUser().getUid())){
                        final ArrayList<IUser> members = new ArrayList<IUser>();
                        for(DataSnapshot member:chat.child("members").getChildren()){
                            // Reading chat members;
                            presenter.getUserData(member.getKey()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    members.add(dataSnapshot.getValue(User.class));
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {}
                            });
                        }
                        final TextMessage lastMessage = new TextMessage();
                        presenter.getLastMessage(chat.getKey(),chat.child("lastMessage").getValue(String.class)).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                lastMessage.setId(dataSnapshot.getKey());
                                lastMessage.setText(dataSnapshot.child("message").getValue(String.class));
                                lastMessage.setCreatedAt(new Date());
                                final User author = new User();
                                presenter.getUserData(dataSnapshot.child("author").getValue(String.class)).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        User temp = dataSnapshot.getValue(User.class);
                                        author.setId(temp.getId());
                                        author.setUsername(temp.getUsername());
                                        author.setName(temp.getRealName());
                                        author.setSurname(temp.getSurname());
                                        author.setNickname(temp.getNickname());
                                        author.setAvatar(temp.getAvatar());
                                        dialogsListAdapter.notifyDataSetChanged();
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });
                                lastMessage.setAuthor(author);
                                dialogsListAdapter.notifyDataSetChanged();
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                        chats.add(new Chat(chat.getKey(),chat.child("dialogPhoto").getValue(String.class),chat.child("dialogName").getValue(String.class),members,lastMessage,chat.child("unreadCount").getValue(Integer.class)));
                        dialogsListAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        dialogsListAdapter.setItems(chats);
        dialogsListAdapter.setOnDialogClickListener(new DialogsListAdapter.OnDialogClickListener<Chat>() {
            @Override
            public void onDialogClick(Chat dialog) {
                Intent intent = new Intent(ChatListActivity.this, ChatActivity.class);
                ChatListActivity.this.startActivity(intent);
            }
        });
        dialogsListAdapter.setOnDialogLongClickListener(new DialogsListAdapter.OnDialogLongClickListener<Chat>() {
            @Override
            public void onDialogLongClick(Chat dialog) {
                Toast.makeText(ChatListActivity.this, dialog.getDialogName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        dialogsListView.setAdapter(dialogsListAdapter);
    }

}
