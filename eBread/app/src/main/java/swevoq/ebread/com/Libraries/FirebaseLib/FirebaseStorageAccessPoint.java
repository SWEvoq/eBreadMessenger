package swevoq.ebread.com.Libraries.FirebaseLib;

import android.net.Uri;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by Teslaru Nicolae on 20/04/2017.
 */

public class FirebaseStorageAccessPoint {
    private StorageHandler handler;
    private StorageReference storage;

    public FirebaseStorageAccessPoint() {
        handler = new StorageHandler();
        storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://ebread-c16e6.appspot.com");
    }

    public UploadTask uploadAvatar(Uri imgLocalPath) {
        return handler.uploadAvatar(storage,imgLocalPath);
    }

    public StorageReference getAvatar() {
        return handler.getAvatar(storage);
    }
}
