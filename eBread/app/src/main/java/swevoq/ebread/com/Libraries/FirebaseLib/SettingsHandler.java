package swevoq.ebread.com.Libraries.FirebaseLib;

import com.google.firebase.database.FirebaseDatabase;

import swevoq.ebread.com.Chat.Model.Profile.User;

/**
 * Created by Nicolae on 20/04/2017.
 */

public class SettingsHandler {

    public void updateUser(FirebaseDatabase database,User user){
        database.getReference("users").child(user.getId()).setValue(user);
    }
}
