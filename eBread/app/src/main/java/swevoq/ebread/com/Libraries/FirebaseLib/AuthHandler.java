package swevoq.ebread.com.Libraries.FirebaseLib;

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Teslaru Nicolae on 21/03/2017.
 */

public class AuthHandler {

    public Task<AuthResult> signUp(FirebaseAuth auth,String email,String password){
         return auth.createUserWithEmailAndPassword(email, password);
    }

    public Task<AuthResult> signIn(FirebaseAuth auth,String email,String password){
        return auth.signInWithEmailAndPassword(email, password);
    }

    public FirebaseUser getLoggedUser(FirebaseAuth auth){
        return auth.getCurrentUser();
    }

    public Task<Void> passwordRecovery(FirebaseAuth auth,String email) {
        return auth.sendPasswordResetEmail(email);
    }
}
