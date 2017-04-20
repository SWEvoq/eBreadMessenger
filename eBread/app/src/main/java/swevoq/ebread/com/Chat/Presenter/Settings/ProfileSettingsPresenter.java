package swevoq.ebread.com.Chat.Presenter.Settings;

import android.app.DownloadManager;
import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Query;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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

    public UploadTask uploadAvatar(Uri imgLocalPath) {
        return firebase.uploadAvatar(imgLocalPath);
    }

    public StorageReference getAvatar() {
        return firebase.getAvatar();
    }

    public void updateAvatar(String url) {
        firebase.updateAvatar(url);
    }
}
