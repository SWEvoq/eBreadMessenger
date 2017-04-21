package swevoq.ebread.com.Chat.Presenter.Chat;

import com.google.firebase.auth.FirebaseUser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by marco on 21/04/17.
 */
public class ChatPresenterTest {
    private ChatPresenter c=new ChatPresenter();

    @Test
    public void getLoggedUser() throws Exception {
        FirebaseUser f=c.getLoggedUser();
    }

    @Test
    public void getUserData() throws Exception {

    }

    @Test
    public void newMessage() throws Exception {

    }

    @Test
    public void deleteSelectedMessages() throws Exception {

    }

    @Test
    public void getMessages() throws Exception {

    }

}