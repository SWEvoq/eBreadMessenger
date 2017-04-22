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
import swevoq.ebread.com.Chat.Model.Settings.TextSettings;
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

    public void updateVoiceSettings(Context context,VoiceSettings voiceSettings){
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
    public void updateTextSettings(Context context, TextSettings updatedTextSettings) {
        JSONObject usersTextSettings = new JSONObject();
        JSONObject newTextSettings = new JSONObject();
        try {
            newTextSettings.put("textColor",updatedTextSettings.getTextColor());
            newTextSettings.put("bubbleColor", updatedTextSettings.getBubbleColor());
            newTextSettings.put("fontSize",updatedTextSettings.getFontSize());
            newTextSettings.put("fontSpacing",updatedTextSettings.getFontSpacing());
            newTextSettings.put("textFont",updatedTextSettings.getTextFont());
            newTextSettings.put("highlightColor",updatedTextSettings.getHighlightColor());
            usersTextSettings.put(FirebaseAuth.getInstance().getCurrentUser().getUid(),newTextSettings);
        }catch (JSONException e){
            e.printStackTrace();
        }
        try{
            FileOutputStream file = context.openFileOutput("textsettings.txt",context.MODE_PRIVATE);
            file.write(usersTextSettings.toString().getBytes());
            file.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public TextSettings getTextSettings(Context context) {
        TextSettings result = new TextSettings();
        JSONObject usersTextSettings = readTextSettings(context);
        if(usersTextSettings!=null){
            try {
                JSONObject voiceSettings = usersTextSettings.getJSONObject(FirebaseAuth.getInstance().getCurrentUser().getUid());
                result.setTextColor(voiceSettings.getString("textColor"));
                result.setBubbleColor(voiceSettings.getString("bubbleColor"));
                result.setFontSize(voiceSettings.getInt("fontSize"));
                result.setFontSpacing(voiceSettings.getInt("fontSpacing"));
                result.setTextFont(voiceSettings.getString("textFont"));
                result.setHighlightColor(voiceSettings.getString("highlightColor"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private JSONObject readTextSettings(Context context){
        JSONObject result = null;
        try{
            FileInputStream file = context.openFileInput("textsettings.txt");
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
