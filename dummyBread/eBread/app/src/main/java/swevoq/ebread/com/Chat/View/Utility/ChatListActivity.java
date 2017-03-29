package swevoq.ebread.com.Chat.View.Utility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.List;

import swevoq.ebread.com.Chat.Model.Chat.Chat;
import swevoq.ebread.com.Chat.Model.Chat.Message;
import swevoq.ebread.com.Chat.Presenter.Utility.ChatListPresenter;
import swevoq.ebread.com.Chat.View.Chat.ChatActivity;
import swevoq.ebread.com.R;

public class ChatListActivity extends AppCompatActivity {
    private DialogsListAdapter<Chat> dialogsListAdapter;
    private ChatListPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat_list);

        DialogsList dialogsListView = (DialogsList) findViewById(R.id.dialogsList);

        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                //If you using another library - write here your way to load image
                Picasso.with(ChatListActivity.this).load(url).into(imageView);
            }
        };

        dialogsListAdapter = new DialogsListAdapter<>(imageLoader);

        dialogsListAdapter.setItems(getDialogs());

        dialogsListAdapter.setOnDialogClickListener(new DialogsListAdapter.OnDialogClickListener<Chat>() {
            @Override
            public void onDialogClick(Chat dialog) {
                ChatActivity.open(ChatListActivity.this);
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

    private void onNewMessage(String dialogId, Message message) {
        if (!dialogsListAdapter.updateDialogWithMessage(dialogId, message)) {
            //Dialog with this ID doesn't exist, so you can create new Dialog or update all dialogs list
        }
    }

    private void onNewDialog(Chat dialog) {
        dialogsListAdapter.addItem(dialog);
    }

    private List<Chat> getDialogs() {
        return presenter.getChatList(); // dovrebbe essere nel model ChatList
    }
}