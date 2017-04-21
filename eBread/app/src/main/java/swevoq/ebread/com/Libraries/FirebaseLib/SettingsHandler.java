package swevoq.ebread.com.Libraries.FirebaseLib;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.Model.Settings.VoiceSettings;

/**
 * Created by Nicolae on 20/04/2017.
 */

public class SettingsHandler {

    public void updateUser(FirebaseDatabase database,User user){
        database.getReference("users").child(user.getId()).setValue(user);
    }

    public VoiceSettings getVoiceSettings(Context context) {
        VoiceSettings result = new VoiceSettings();
        JSONObject usersVoiceSettings = readVoiceSettings(context);
        if(usersVoiceSettings!=null){
            try {
                JSONObject voiceSettings = usersVoiceSettings.getJSONObject(FirebaseAuth.getInstance().getCurrentUser().getUid());
                result.setVoiceName(voiceSettings.getString("voiceName"));
                result.setVoiceLanguage(voiceSettings.getString("voiceLanguage"));
                result.setVoiceRate(voiceSettings.getDouble("voiceRate"));
                result.setForwardHighlight(voiceSettings.getBoolean("forwardHighlight"));
                result.setWordHighlight(voiceSettings.getBoolean("wordHighlight"));
                result.setShowHighlight(voiceSettings.getBoolean("showHighlight"));
                result.setPlayVoice(voiceSettings.getBoolean("playVoice"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void updateUserVoiceSettings(Context context,VoiceSettings voiceSettings){
        JSONObject usersVoiceSettings = new JSONObject();
        JSONObject newVoiceSettings = new JSONObject();
        try {
            newVoiceSettings.put("voiceName", voiceSettings.getVoiceName());
            newVoiceSettings.put("voiceLanguage", voiceSettings.getVoiceLanguage());
            newVoiceSettings.put("voiceRate",voiceSettings.getVoiceRate());
            newVoiceSettings.put("forwardHighlight",voiceSettings.isForwardHighlight());
            newVoiceSettings.put("wordHighlight",voiceSettings.isWordHighlight());
            newVoiceSettings.put("showHighlight",voiceSettings.isShowHighlight());
            newVoiceSettings.put("playVoice",voiceSettings.isPlayVoice());
            usersVoiceSettings.put(FirebaseAuth.getInstance().getCurrentUser().getUid(),newVoiceSettings);
        }catch (JSONException e){
            e.printStackTrace();
        }
        try{
            Log.d("MyApp","Sal,kbkjbhkvo:"+usersVoiceSettings.toString());
            FileOutputStream file = context.openFileOutput("voicesettings.txt",context.MODE_PRIVATE);
            file.write(usersVoiceSettings.toString().getBytes());
            file.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    private JSONObject readVoiceSettings(Context context){
        JSONObject result = null;
        try{
            FileInputStream file = context.openFileInput("voicesettings.txt");
            int size = file.available();
            byte[] buffer = new byte[size];
            file.read(buffer);
            file.close();
            String myJson = new String(buffer,"UTF-8");
            result = new JSONObject(myJson);
        }catch(IOException|JSONException x){
            x.printStackTrace();
        }
        return result;
    }
}
