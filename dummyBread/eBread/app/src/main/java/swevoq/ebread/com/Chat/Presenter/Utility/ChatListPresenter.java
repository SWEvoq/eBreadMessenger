package swevoq.ebread.com.Chat.Presenter.Utility;

import android.support.annotation.NonNull;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import swevoq.ebread.com.Chat.Model.Chat.Chat;
import swevoq.ebread.com.Chat.Model.Chat.TextMessage;
import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.Presenter.Chat.FixturesData;

/**
 * Created by Teslaru Nicolae on 23/03/2017.
 */

public class ChatListPresenter extends FixturesData {
    private ChatListPresenter() {
        throw new AssertionError();
    }

    public static ArrayList<Chat> getChatList() {
        ArrayList<Chat> chats = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            chats.add(getDialog(i));
        }
        return chats;
    }
/*
    private static IMessage getMessage(final Date date) {
        return new IMessage() {
            @Override
            public String getId() {
                return Long.toString(UUID.randomUUID().getLeastSignificantBits());
            }

            @Override
            public String getText() {
                return messages.get(rnd.nextInt(messages.size()));
            }

            @Override
            public IUser getUser() {
                return ChatListPresenter.getUser();
            }

            @Override
            public Date getCreatedAt() {
                return date;
            }
        };
    }
*/
    private static Chat getDialog(int i) {
        ArrayList<IUser> users = getUsers();
        return new Chat(String.valueOf(UUID.randomUUID().getLeastSignificantBits()),
                users.size() > 1 ? groupChatImages.get(users.size() - 2) : avatars.get(rnd.nextInt(4)),
                users.size() > 1 ? groupChatTitles.get(users.size() - 2) : users.get(0).getName(),
                users,
                new TextMessage("asd","sdasd",new Date(),new User("sadas","asdad","asdasd","asd","asdas","asd")),
                0);
    }

    private static ArrayList<IUser> getUsers() {
        ArrayList<IUser> users = new ArrayList<>();
        int usersCount = 1 + rnd.nextInt(4);
        for (int i = 0; i < usersCount; i++) {
            users.add(getUser());
        }
        return users;
    }

    @NonNull
    private static IUser getUser() {
        return new User(String.valueOf(UUID.randomUUID().getLeastSignificantBits()),"email",names.get(rnd.nextInt(names.size())),"Tesla","Tesla",avatars.get(rnd.nextInt(4)));
    }
}
