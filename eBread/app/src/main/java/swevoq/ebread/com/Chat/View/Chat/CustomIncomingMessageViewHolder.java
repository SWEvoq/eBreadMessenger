package swevoq.ebread.com.Chat.View.Chat;

import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.stfalcon.chatkit.messages.MessagesListAdapter;

import swevoq.ebread.com.Chat.Model.Chat.Message;
import swevoq.ebread.com.Chat.Presenter.Chat.ChatPresenter;

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
        time.setTextColor(Color.GRAY);

        ChatPresenter presenter = new ChatPresenter();
        presenter.setTextViewStyle(text,bubble);


        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Qui si dovranno fare le chiamate al player : ricordare view.getContext()
            }
        });

    }
}