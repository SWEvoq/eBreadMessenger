package swevoq.ebread.com.Chat.View.Chat;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;
import java.util.Date;
import java.util.HashMap;

import swevoq.ebread.com.Chat.Model.Chat.Message;
import swevoq.ebread.com.Chat.Model.Chat.TextMessage;
import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.Presenter.Chat.ChatPresenter;
import swevoq.ebread.com.Chat.View.Utility.ChatListActivity;
import swevoq.ebread.com.Libraries.FATTSLib.PlayerBread;
import swevoq.ebread.com.R;

public class ChatActivity extends AppCompatActivity implements MessagesListAdapter.SelectionListener {
    private ChatPresenter presenter;
    private MessagesList messagesList;
    private MessagesListAdapter<Message> adapter;
    private MessageInput input;
    private int selectionCount;
    private Menu menu;
    private String chatId;
    private HashMap<String, ValueEventListener> mListenerMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chatId = getIntent().getExtras().getString("chatId");
        presenter = new ChatPresenter();
        messagesList = (MessagesList)findViewById(R.id.messagesList);
        mListenerMap = new HashMap<>();
        initMessagesAdapter();

        input = (MessageInput) findViewById(R.id.input);
        input.setInputListener(new MessageInput.InputListener(){
            @Override
            public boolean onSubmit(final CharSequence input) {
                final User author = new User();
                presenter.getUserData(presenter.getLoggedUser().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User temp = dataSnapshot.getValue(User.class);
                        author.setId(temp.getId());
                        author.setUsername(temp.getUsername());
                        author.setName(temp.getRealName());
                        author.setSurname(temp.getSurname());
                        author.setNickname(temp.getNickname());
                        author.setAvatar(temp.getAvatar());
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
                presenter.getUserData(presenter.getLoggedUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        presenter.newMessage(chatId,input.toString(),author);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.chat_actions_menu, menu);
        onSelectionChanged(0);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                presenter.deleteSelectedMessages(chatId,adapter.getSelectedMessages(),mListenerMap);
                adapter.deleteSelectedMessages();
                break;
        }
        return true;
    }
    @Override
    public void onSelectionChanged(int count) {
        this.selectionCount = count;
        menu.findItem(R.id.action_delete).setVisible(count > 0);
    }
    @Override
    public void onBackPressed() {
        if (selectionCount == 0) {
            Intent intent = new Intent(ChatActivity.this, ChatListActivity.class);
            ChatActivity.this.startActivity(intent);
            finish();
        } else {
            adapter.unselectAllItems();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        PlayerBread.getInstance().killCurrentPlayer();
    }

    private void initMessagesAdapter(){
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Picasso.with(ChatActivity.this).load(url).into(imageView);
            }
        };
        MessagesListAdapter.HoldersConfig holdersConfig = new MessagesListAdapter.HoldersConfig();
        holdersConfig.setIncomingLayout(R.layout.item_custom_incoming_message);
        holdersConfig.setOutcomingLayout(R.layout.item_custom_outcoming_message);
        holdersConfig.setIncomingHolder(CustomIncomingMessageViewHolder.class);
        holdersConfig.setOutcomingHolder(CustomOutcomingMessageViewHolder.class);
        adapter = new MessagesListAdapter<>(presenter.getLoggedUser().getUid(),holdersConfig,imageLoader);
        adapter.enableSelectionMode(this);

        presenter.getMessages(chatId).orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ValueEventListener listener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final DataSnapshot messageSnapshot = dataSnapshot;
                        final User author = new User();
                        if(dataSnapshot.child("author").getValue(String.class) != null) {
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
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                            presenter.getUserData(dataSnapshot.child("author").getValue(String.class)).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    TextMessage newMessage = new TextMessage(messageSnapshot.getKey(), messageSnapshot.child("message").getValue(String.class), messageSnapshot.child("createdAt").getValue(Date.class), author);
                                    if (newMessage.getCreatedAt() != null && newMessage.getText() != null) {
                                        adapter.addToStart(newMessage, true);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                };
                mListenerMap.put(dataSnapshot.getKey(),listener);
                dataSnapshot.getRef().addValueEventListener(listener);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //dataSnapshot.getRef().removeEventListener(mListenerMap.get(dataSnapshot.getKey()));
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        messagesList.setAdapter(adapter);
    }
}
