package swevoq.ebread.com.Chat.Model.Chat;

import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.ArrayList;
import java.util.List;

import swevoq.ebread.com.Chat.Model.Profile.User;

/**
 * Created by Teslaru Nicolae on 23/03/2017.
 */

public class Chat implements IDialog {
    private String id;
    private String dialogPhoto;
    private String dialogName;
    private ArrayList<IUser> users;
    private IMessage lastMessage;
    private int unreadCount;

    public Chat() {
        id = new String();
        dialogPhoto = new String();
        dialogName = new String();
        users = new ArrayList<>();
        lastMessage = new TextMessage();
        unreadCount = 0;
    }

    public Chat(String id, String dialogPhoto, String dialogName, ArrayList<IUser> users, IMessage lastMessage, int unreadCount) {
        this.id = id;
        this.dialogPhoto = dialogPhoto;
        this.dialogName = dialogName;
        this.users = users;
        this.lastMessage = lastMessage;
        this.unreadCount = unreadCount;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDialogPhoto() {
        return dialogPhoto;
    }

    @Override
    public String getDialogName() {
        return dialogName;
    }

    @Override
    public List<IUser> getUsers() {
        return users;
    }

    @Override
    public IMessage getLastMessage() {
        return lastMessage;
    }

    @Override
    public void setLastMessage(IMessage message) {
        lastMessage = message;
    }

    @Override
    public int getUnreadCount() {
        return unreadCount;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id='" + id + '\'' +
                ", dialogPhoto='" + dialogPhoto + '\'' +
                ", dialogName='" + dialogName + '\'' +
                ", users=" + users +
                ", lastMessage=" + lastMessage +
                ", unreadCount=" + unreadCount +
                '}';
    }

}
