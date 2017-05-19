package swevoq.ebread.com.Libraries.FirebaseLib;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by Teslaru Nicolae on 20/04/2017.
 */

public class StorageHandler {

    public UploadTask uploadAvatar(StorageReference storage, Uri imgLocalPath) {
        return storage.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).putFile(imgLocalPath);
    }

    public StorageReference getAvatar(StorageReference storage) {
        return storage.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }
}
