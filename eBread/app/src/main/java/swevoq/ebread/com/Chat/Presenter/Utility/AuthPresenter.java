package swevoq.ebread.com.Chat.Presenter.Utility;

import android.text.TextUtils;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

/**
 * Created by Teslaru Nicolae on 21/03/2017.
 */

public class AuthPresenter {

    public String checkData(String email, String password){
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password) ) {
            return "Inserisci dati di registrazione!";
        }else {
            if (TextUtils.isEmpty(email)) {
                return "Inserisci indirizzo mail";
            }
            if (TextUtils.isEmpty(password)) {
                return "Inserisci password";
            }
        }
        return "";

    }

    public FirebaseUser getLoggedUser(){
        FirebaseAccessPoint db = new FirebaseAccessPoint();
        return db.getLoggedUser();
    }

    public Task<AuthResult> signIn(String email, String password){
        FirebaseAccessPoint db = new FirebaseAccessPoint();
        return db.signIn(email,password);
    }

}
