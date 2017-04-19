package swevoq.ebread.com.Chat.View.Chat;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.stfalcon.chatkit.messages.MessagesListAdapter;

import swevoq.ebread.com.Chat.Model.Chat.Message;

/**
 * Created by Nicolae on 06/04/2017.
 */

public class CustomIncomingMessageViewHolder extends MessagesListAdapter.IncomingMessageViewHolder<Message> {
    public CustomIncomingMessageViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBind(Message message) {
        super.onBind(message);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MyTextView",((TextView)v).getText().toString());
            }
        });

    }
}