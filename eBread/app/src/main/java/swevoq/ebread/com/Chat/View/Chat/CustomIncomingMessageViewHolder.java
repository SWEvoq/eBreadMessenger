package swevoq.ebread.com.Chat.View.Chat;

import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.stfalcon.chatkit.messages.MessagesListAdapter;

import swevoq.ebread.com.Chat.Model.Chat.Message;
import swevoq.ebread.com.Chat.Presenter.Chat.ChatPresenter;
import swevoq.ebread.com.Libraries.FATTSLib.FATTSServices;

/**
 * Created by Nicolae on 06/04/2017.
 */

public class CustomIncomingMessageViewHolder extends MessagesListAdapter.IncomingMessageViewHolder<Message> {
    public CustomIncomingMessageViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBind(final Message message) {
        super.onBind(message);
        time.setTextColor(Color.GRAY);

        ChatPresenter presenter = new ChatPresenter();
        presenter.setTextViewStyle(text,bubble);


        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FATTSServices service = new FATTSServices(text,message);
                service.performAudioRequest();
            }
        });

    }
}