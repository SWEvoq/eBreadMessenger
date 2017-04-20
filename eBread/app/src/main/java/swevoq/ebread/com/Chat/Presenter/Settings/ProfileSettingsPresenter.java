package swevoq.ebread.com.Chat.Presenter.Settings;

import android.app.DownloadManager;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Query;

import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

/**
 * Created by Nicolae on 20/04/2017.
 */

public class ProfileSettingsPresenter {
    private FirebaseAccessPoint firebase;

    public ProfileSettingsPresenter() {
        firebase = new FirebaseAccessPoint();
    }

    public Query getUserData(String id){
        return firebase.getUserData(id);
    }

    public FirebaseUser getLoggedUser(){
        return firebase.getLoggedUser();
    }

    public void updateUser(User dummy) {
        firebase.updateUser(dummy);
    }
}
