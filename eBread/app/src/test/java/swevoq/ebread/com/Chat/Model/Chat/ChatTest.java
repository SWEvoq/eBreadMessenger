package swevoq.ebread.com.Chat.Model.Chat;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by marco on 21/04/17.
 */
public class ChatTest {
    private ArrayList<IUser> users=new ArrayList<IUser>();
    private IMessage message=new IMessage() {
        @Override
        public String getId() {
            return null;
        }

        @Override
        public String getText() {
            return null;
        }

        @Override
        public IUser getUser() {
            return null;
        }

        @Override
        public Date getCreatedAt() {
            return null;
        }
    };
    private Chat c=new Chat("stringa","foto","nome",users,message,2);

    @Test
    public void getId() throws Exception {
        String expected="stringa";
        String actual=c.getId();
        assertEquals(expected,actual);
    }

    @Test
    public void getDialogPhoto() throws Exception {
        String expected="foto";
        String actual=c.getDialogPhoto();
        assertEquals(expected,actual);
    }

    @Test
    public void getDialogName() throws Exception {
        String expected="nome";
        String actual=c.getDialogName();
        assertEquals(expected,actual);
    }

    @Test
    public void getUsers() throws Exception {
        ArrayList<IUser> expected=users;
        List<IUser> actual=c.getUsers();
        assertEquals(expected,actual);
    }

    @Test
    public void getLastMessage() throws Exception {
        IMessage expected=message;
        IMessage actual=c.getLastMessage();
        assertEquals(expected,actual);
    }

    @Test
    public void setLastMessage() throws Exception {
        IMessage expected=new IMessage() {
            @Override
            public String getId() {
                return null;
            }

            @Override
            public String getText() {
                return null;
            }

            @Override
            public IUser getUser() {
                return null;
            }

            @Override
            public Date getCreatedAt() {
                return null;
            }
        };
        c.setLastMessage(expected);
        IMessage actual=c.getLastMessage();
        assertEquals(expected,actual);
    }

    @Test
    public void getUnreadCount() throws Exception {
        int expected=2;
        int actual=c.getUnreadCount();
        assertEquals(expected,actual);
    }

    @Test
    public void toStringTest() throws Exception {
        String expected="Chat{id='stringa', dialogPhoto='foto', dialogName='nome', users=[], lastMessage=swevoq.ebread.com.Chat.Model.Chat.ChatTest$1@45fe3ee3, unreadCount=2}";
        String actual=c.toString();
        assertEquals(expected,actual);
    }
}