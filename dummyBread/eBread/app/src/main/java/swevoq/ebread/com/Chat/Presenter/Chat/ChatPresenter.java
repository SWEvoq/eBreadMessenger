package swevoq.ebread.com.Chat.Presenter.Chat;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import swevoq.ebread.com.Chat.Model.Chat.Message;
import swevoq.ebread.com.Chat.Model.Chat.TextMessage;
import swevoq.ebread.com.Chat.Model.Profile.User;

/**
 * Created by Teslaru Nicolae on 23/03/2017.
 */

public class ChatPresenter {
    private ChatPresenter() {
        throw new AssertionError();
    }

    public static ArrayList<Message> getMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message message = new TextMessage(UUID.randomUUID().toString(),"Ciao mondo crudele.",new Date(),new User(UUID.randomUUID().toString(),"asd","sdasd","asf","asd","https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg"));

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -1);

            messages.add(message);
        }
        return messages;
    }
}
