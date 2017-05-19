package swevoq.ebread.com.Chat.Model.Chat;

import com.stfalcon.chatkit.commons.models.IUser;

import org.junit.Test;

import java.util.Date;

import swevoq.ebread.com.Chat.Model.Profile.User;

import static org.junit.Assert.*;

/**
 * Created by marco on 21/04/17.
 */
public class TextMessageTest {
    private Date date=new Date(2000);
    private User user=new User();
    private TextMessage t=new TextMessage("id","text",date,user);

    @Test
    public void getId() throws Exception {
        String expected="id";
        String actual=t.getId();
        assertEquals(expected,actual);
    }

    @Test
    public void getText() throws Exception {
        String expected="text";
        String actual=t.getText();
        assertEquals(expected,actual);
    }

    @Test
    public void getUser() throws Exception {
        User expected=user;
        IUser actual=t.getUser();
        assertEquals(expected,actual);
    }

    @Test
    public void getCreatedAt() throws Exception {
        Date expected=date;
        Date actual=t.getCreatedAt();
        assertEquals(expected,actual);
    }

    @Test
    public void setId() throws Exception {
        String expected="exp";
        t.setId(expected);
        String actual=t.getId();
        assertEquals(expected,actual);
    }

    @Test
    public void setText() throws Exception {
        String expected="random";
        t.setText(expected);
        String actual=t.getText();
        assertEquals(expected,actual);
    }

    @Test
    public void setCreatedAt() throws Exception {
        Date expected=new Date();
        t.setCreatedAt(expected);
        Date actual=t.getCreatedAt();
        assertEquals(expected,actual);
    }

    @Test
    public void setAuthor() throws Exception {
        User expected=new User();
        t.setAuthor(expected);
        IUser actual=t.getUser();
        assertEquals(expected,actual);
    }

    @Test
    public void toStringTest() throws Exception {
        String expected="TextMessage{id='id', text='text', createdAt=Thu Jan 01 01:00:02 CET 1970, author=User{id='', username='', name='', surname='', nickname='', avatar='http://www.eioba.org/files/user61571/a248626/white.jpg'}}";
        String actual=t.toString();
        assertEquals(expected,actual);
    }
}