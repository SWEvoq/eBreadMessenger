package swevoq.ebread.com.Libraries.FirebaseLib;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Teslaru Nicolae on 21/03/2017.
 */

public class FirebaseAuthAccessPoint {
    private FirebaseAuth auth;
    private AuthHandler handler;

    public FirebaseAuthAccessPoint(){
        auth = FirebaseAuth.getInstance();
        handler = new AuthHandler();
    }

    public Task<AuthResult> signUp(String email, String password){
        return handler.signUp(auth,email,password);
    }

    public Task<AuthResult> signIn(String email, String password){
        return handler.signIn(auth,email,password);
    }

    public FirebaseUser getLoggedUser(){
        return handler.getLoggedUser(auth);
    }

    public Task<Void> passwordRecovery(String email) {
        return handler.passwordRecovery(auth,email);
    }
}
