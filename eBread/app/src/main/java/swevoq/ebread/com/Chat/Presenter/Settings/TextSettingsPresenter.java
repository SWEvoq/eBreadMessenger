package swevoq.ebread.com.Chat.Presenter.Settings;

import android.content.Context;
import android.graphics.Color;

import java.util.HashMap;

import swevoq.ebread.com.Chat.Model.Settings.TextSettings;
import swevoq.ebread.com.Chat.View.Settings.TextSettingsActivity;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

/**
 * Created by Teslaru Nicolae on 21/04/2017.
 */

public class TextSettingsPresenter {
    private FirebaseAccessPoint firebase;

    public TextSettingsPresenter(){
        firebase = new FirebaseAccessPoint();
    }

    public HashMap<String,Integer> getAvaiableColors() {
        HashMap<String,Integer> result = new HashMap<>();
        result.put("Blue Grey", Color.parseColor("#78909c"));
        result.put("Grey", Color.parseColor("#bdbdbd"));
        result.put("Deep Orange", Color.parseColor("#ff7043"));
        result.put("Yellow", Color.parseColor("#ffee58"));
        result.put("Light Green", Color.parseColor("#9ccc65"));
        result.put("Light Blue", Color.parseColor("#81d4fa"));
        result.put("Red", Color.parseColor("#ef9a9a"));
        result.put("Black", Color.BLACK);
        result.put("White", Color.WHITE);
        return result;
    }
    public HashMap<String,Integer> getAvaiableTextSizing() {
        HashMap<String,Integer> result = new HashMap<>();
        result.put("Piccolo",14);
        result.put("Medio",15);
        result.put("Normale",16);
        result.put("Grande",17);
        result.put("Molto Grande",18);
        return result;
    }
    public HashMap<String,Integer> getAvaiableTextSpacing() {
        HashMap<String,Integer> result = new HashMap<>();
        result.put("Piccolo",0);
        result.put("Medio",1);
        result.put("Normale",2);
        result.put("Grande",3);
        result.put("Molto Grande",4);
        return result;
    }

    public TextSettings getTextSettings(Context context) {
        return firebase.getTextSettings(context);
    }

    public void updateTextSettings(Context context, TextSettings updatedTextSettings) {
        firebase.updateTextSettings(context,updatedTextSettings);
    }
}
