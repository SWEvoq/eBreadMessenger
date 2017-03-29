package swevoq.ebread.com.Chat.Model.Chat;

import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

/**
 * Created by Teslaru Nicolae on 22/03/2017.
 */

public class Chat implements IDialog {
    private String id, dialogPhoto, dialogName;
    private ArrayList<IUser> users;
    private IMessage lastMessage;
    private int unreadCount;

    public Chat(String id, String dialogPhoto, String dialogName, ArrayList<IUser> users, IMessage lastMessage, int unreadCount) {
        this.id = id;
        this.dialogPhoto = dialogPhoto;
        this.dialogName = dialogName;
        this.users = users;
        this.lastMessage = lastMessage;
        this.unreadCount = unreadCount;
    }

    public Chat(){}

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

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setLastMessage(IMessage message) {
        lastMessage = new TextMessage("","",new Date(),new User()); // default ultimo messaggio vuoto.

    }

    @Override
    public int getUnreadCount() {
        return unreadCount;
    }

    public void setDialogPhoto(String dialogPhoto) {
        this.dialogPhoto = dialogPhoto;
    }

    public void setDialogName(String dialogName) {
        this.dialogName = dialogName;
    }

    public void setUsers(ArrayList<IUser> users) {
        this.users = users;
    }
}
