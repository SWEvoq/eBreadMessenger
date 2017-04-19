package swevoq.ebread.com.Chat.Presenter.Utility;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.Model.Utility.AddressBook;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

/**
 * Created by Nicolae on 09/04/2017.
 */

public class AddressBookPresenter {
    private FirebaseAccessPoint firebase;

    public AddressBookPresenter(){
        firebase = new FirebaseAccessPoint();
    }

    public void addUserToAddressBook(AddressBook adapter,Context context, List<String> friends, String username){
        firebase.addUserToAddressBook(adapter,context,friends,username);
    }

    public List<String> getFriends(Context context) {
        return firebase.getFriends(context);
    }

    public void createChat(Context context,ArrayList<String> ids) {
        firebase.createChat(context,ids);
    }

    public void deleteFriends(AddressBook adapter, Context context, ArrayList<String> ids) {
        firebase.deleteFriends(adapter,context,ids);
    }

    public Query getUsers() {
        return firebase.getUsers();
    }

    public void setContactVoice(Context context,String userClicked, String voiceName) {
        firebase.setContactVoice(context,userClicked,voiceName);
    }
}
