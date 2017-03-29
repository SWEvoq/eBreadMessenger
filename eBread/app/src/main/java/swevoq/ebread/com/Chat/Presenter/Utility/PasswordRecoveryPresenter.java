package swevoq.ebread.com.Chat.Presenter.Utility;

import android.text.TextUtils;
import com.google.android.gms.tasks.Task;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

/**
 * Created by Teslaru Nicolae on 21/03/2017.
 */

public class PasswordRecoveryPresenter {

    public String checkData(String email){
        if (TextUtils.isEmpty(email)) {
               return "Inserisci indirizzo mail";
        }else {
            return "";
        }
    }

    public Task<Void> passwordRecovery(String email){
        FirebaseAccessPoint db = new FirebaseAccessPoint();
        return db.passwordRecovery(email);
    }
}
