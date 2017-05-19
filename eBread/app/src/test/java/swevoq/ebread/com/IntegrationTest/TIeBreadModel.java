package swevoq.ebread.com.IntegrationTest;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;

import swevoq.ebread.com.Chat.Model.Chat.Chat;
import swevoq.ebread.com.Chat.Model.Chat.Message;
import swevoq.ebread.com.Chat.Model.Chat.TextMessage;
import swevoq.ebread.com.Chat.Model.Profile.User;

/**
 * Created by simone on 07/05/17.
 */

public class TIeBreadModel extends TestCase{
    public void testCreation(){
        User u1= new User();
        User u2= new User();
        Message m1= new TextMessage("id1","test1",new Date(),u1);
        Message m2= new TextMessage("id2","test2",new Date(),u2);
        ArrayList<IUser> array= new ArrayList<IUser>();
        array.add(u1);
        array.add(u2);
        Chat c1= new Chat("id1", "dialogPhoto", "dialogName", array, new IMessage() {
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
        }, 1);
        assertEquals(array,c1.getUsers());

    }
}
