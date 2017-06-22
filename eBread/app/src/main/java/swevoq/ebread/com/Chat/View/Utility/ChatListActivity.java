package swevoq.ebread.com.Chat.View.Utility;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.commons.models.IUser;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;
import java.util.ArrayList;
import java.util.Date;
import swevoq.ebread.com.Chat.Model.Chat.Chat;
import swevoq.ebread.com.Chat.Model.Chat.TextMessage;
import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.Presenter.Utility.ChatListPresenter;
import swevoq.ebread.com.Chat.View.Chat.ChatActivity;
import swevoq.ebread.com.Chat.View.Settings.ProfileSettingsActivity;
import swevoq.ebread.com.Chat.View.Settings.TextSettingsActivity;
import swevoq.ebread.com.Chat.View.Settings.VoiceSettingsActivity;
import swevoq.ebread.com.R;

public class ChatListActivity extends AppCompatActivity {
    private DialogsListAdapter<Chat> dialogsListAdapter;
    private ChatListPresenter presenter;
    private ArrayList<Chat> chats;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        context = this;
        presenter = new ChatListPresenter();
        presenter.enableFirebaseCache();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.newchat_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatListActivity.this, AddressBookActivity.class);
                intent.putExtra("compState",true);
                ChatListActivity.this.startActivity(intent);
                finish();
            }
        });
        final DialogsList dialogsListView = (DialogsList) findViewById(R.id.dialogsList);
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Picasso.with(ChatListActivity.this).load(url).into(imageView);
            }
        };
        dialogsListAdapter = new DialogsListAdapter<>(imageLoader);
        chats = new ArrayList<>();
        presenter.getChats().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chats.clear();
                dialogsListAdapter.notifyDataSetChanged();
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
                        presenter.getLastMessage(chat.getKey(),chat.child("lastMessage").getValue(String.class)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChildren()) {
                                    lastMessage.setId(dataSnapshot.getKey());
                                    lastMessage.setText(dataSnapshot.child("message").getValue(String.class));
                                    lastMessage.setCreatedAt(new Date()); // mettere data effettiva che si trova in firebase.
                                    final User author = new User();
                                    presenter.getUserData(dataSnapshot.child("author").getValue(String.class)).addListenerForSingleValueEvent(new ValueEventListener() {
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
                intent.putExtra("chatId",dialog.getId());
                ChatListActivity.this.startActivity(intent);
                finish();
            }
        });
        dialogsListAdapter.setOnDialogLongClickListener(new DialogsListAdapter.OnDialogLongClickListener<Chat>() {

            // Qui ci dovrebbe andare la parte per cancellare le chat.
            @Override
            public void onDialogLongClick(final Chat chatDialog) {
                LayoutInflater layoutInflater = LayoutInflater.from(ChatListActivity.this);
                View promptView = layoutInflater.inflate(R.layout.deletechat_input_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder((ChatListActivity.this));
                alertDialogBuilder.setView(promptView);
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("Conferma", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id) {
                                presenter.deleteChat(context,chatDialog.getId());
                            }
                        })
                        .setNegativeButton("Annulla",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create an alert dialog
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();

            }
        });

        dialogsListView.setAdapter(dialogsListAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chat_list_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_addressbook:
                // apro rubrica
                Intent addressbookIntent = new Intent(ChatListActivity.this, AddressBookActivity.class);
                addressbookIntent.putExtra("compState",false);
                ChatListActivity.this.startActivity(addressbookIntent);
                finish();
                return true;
            case R.id.action_personal_settings:
                //apro impostazioni personali
                Intent personalsettingsIntent = new Intent(ChatListActivity.this, ProfileSettingsActivity.class);
                ChatListActivity.this.startActivity(personalsettingsIntent);
                finish();
                return true;
            case R.id.action_voice_settings:
                Intent voicesettingsIntent = new Intent(ChatListActivity.this, VoiceSettingsActivity.class);
                ChatListActivity.this.startActivity(voicesettingsIntent);
                finish();
                return true;
            case R.id.action_text_settings:
                Intent textsettingsIntent = new Intent(ChatListActivity.this, TextSettingsActivity.class);
                ChatListActivity.this.startActivity(textsettingsIntent);
                finish();
                return true;
            case R.id.action_logout:
                Intent authIntent = new Intent(ChatListActivity.this, AuthActivity.class);
                ChatListActivity.this.startActivity(authIntent);
                FirebaseAuth.getInstance().signOut();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
