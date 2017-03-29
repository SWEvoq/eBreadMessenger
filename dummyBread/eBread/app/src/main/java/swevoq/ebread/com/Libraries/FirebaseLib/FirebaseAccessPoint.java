package swevoq.ebread.com.Libraries.FirebaseLib;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Teslaru Nicolae on 21/03/2017.
 */

public class FirebaseAccessPoint {
    private FirebaseAuthAccessPoint auth;

    public FirebaseAccessPoint(){
        auth = new FirebaseAuthAccessPoint();
    }

    public Task<AuthResult> signUp(String email, String password){
        return auth.signUp(email,password);
    }

    public Task<AuthResult> signIn(String email, String password){
        return auth.signIn(email,password);
    }

    public FirebaseUser getLoggedUser(){
        return auth.getLoggedUser();
    }

    public Task<Void> passwordRecovery(String email) {
        return auth.passwordRecovery(email);
    }
}
