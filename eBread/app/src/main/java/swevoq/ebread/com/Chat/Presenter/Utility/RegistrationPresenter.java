package swevoq.ebread.com.Chat.Presenter.Utility;

import android.text.TextUtils;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

/**
 * Created by Teslaru Nicolae on 21/03/2017.
 */

public class RegistrationPresenter {

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
            if (password.length() < 8) {
                return "Password inserita troppo corta. Min 8 caratteri !";
            }
        }
        return "";

    }

    public Task<AuthResult> signUp(String email, String password){
        FirebaseAccessPoint db = new FirebaseAccessPoint();
        return db.signUp(email,password);
    }

    public void saveUser(){
        FirebaseAccessPoint db = new FirebaseAccessPoint();
        db.saveUser();
    }
}
