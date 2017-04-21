package swevoq.ebread.com.Chat.Model.Profile;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by marco on 21/04/17.
 */
public class UserTest {
    private User user=new User("id","username","name","surname","nickname","avatar");

    @Test
    public void getId() throws Exception {
        String expected="id";
        String actual=user.getId();
        assertEquals(expected,actual);
    }

    @Test
    public void getName() throws Exception {
        String expected="nickname";
        String actual=user.getName();
        assertEquals(expected,actual);
        user.setNickname("");
        expected="username";
        actual=user.getName();
        assertEquals(expected,actual);
    }

    @Test
    public void getUsername() throws Exception {
        String expected="username";
        String actual=user.getUsername();
        assertEquals(expected,actual);
    }

    @Test
    public void getRealName() throws Exception {
        String expected="name";
        String actual=user.getRealName();
        assertEquals(expected,actual);
    }

    @Test
    public void getSurname() throws Exception {
        String expected="surname";
        String actual=user.getSurname();
        assertEquals(expected,actual);
    }

    @Test
    public void getNickname() throws Exception {
        String expected="nickname";
        String actual=user.getNickname();
        assertEquals(expected,actual);
    }

    @Test
    public void getAvatar() throws Exception {
        String expected="avatar";
        String actual=user.getAvatar();
        assertEquals(expected,actual);
    }

    @Test
    public void setId() throws Exception {
        String expected="Id";
        user.setId(expected);
        String actual=user.getId();
        assertEquals(expected,actual);
    }

    @Test
    public void setUsername() throws Exception {
        String expected="Username";
        user.setUsername(expected);
        String actual=user.getUsername();
        assertEquals(expected,actual);
    }

    @Test
    public void setSurname() throws Exception {
        String expected="Surname";
        user.setSurname(expected);
        String actual=user.getSurname();
        assertEquals(expected,actual);
    }

    @Test
    public void setNickname() throws Exception {
        String expected="Nickname";
        user.setNickname(expected);
        String actual=user.getNickname();
        assertEquals(expected,actual);
    }

    @Test
    public void setAvatar() throws Exception {
        String expected="Avatar";
        user.setAvatar(expected);
        String actual=user.getAvatar();
        assertEquals(expected,actual);
    }

    @Test
    public void setRealName() throws Exception {
        String expected="Name";
        user.setRealName(expected);
        String actual=user.getRealName();
        assertEquals(expected,actual);
    }

    @Test
    public void toStringTest() throws Exception {
        String expected="User{id='id', username='username', name='name', surname='surname', nickname='nickname', avatar='avatar'}";
        String actual=user.toString();
        assertEquals(expected,actual);
    }

}