package swevoq.ebread.com.Chat.Presenter.Settings;

import android.content.Context;

import swevoq.ebread.com.Chat.Model.Settings.VoiceSettings;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

/**
 * Created by Teslaru Nicolae on 21/04/2017.
 */

public class VoiceSettingsPresenter {
    private FirebaseAccessPoint firebase;

    public VoiceSettingsPresenter(){
        firebase = new FirebaseAccessPoint();
    }

    public VoiceSettings getVoiceSettings(Context context) {
        return firebase.getVoiceSettings(context);
    }

    public void updateVoiceSettings(Context context, VoiceSettings updatedVoiceSettings) {
        firebase.updateVoiceSettings(context,updatedVoiceSettings);
    }
}
