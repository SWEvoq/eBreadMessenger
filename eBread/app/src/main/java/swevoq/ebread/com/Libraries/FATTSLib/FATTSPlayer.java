package swevoq.ebread.com.Libraries.FATTSLib;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Handler;

import swevoq.ebread.com.Chat.Model.Settings.TextSettings;
import swevoq.ebread.com.Chat.Model.Settings.VoiceSettings;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

/**
 * Created by Teslaru Nicolae on 27/04/2017.
 */

public class FATTSPlayer {
    private MediaPlayer player;
    private Context context;
    public FATTSPlayer(Context context){
        this.context = context;
        player = PlayerBread.getInstance().getPlayer();
    }

    public void playSyncAudio(File audioFilePath, File syncFilePath, TextView view){
        VoiceSettings voiceSettings = new FirebaseAccessPoint().getVoiceSettings(context);
        if(!voiceSettings.isShowHighlight() && !voiceSettings.isPlayVoice())
            return;
        if(!voiceSettings.isShowHighlight() && voiceSettings.isPlayVoice())
            playAudio(audioFilePath);
        if(voiceSettings.isShowHighlight() && !voiceSettings.isPlayVoice())
            playHighlights(syncFilePath,view);
        if(voiceSettings.isShowHighlight() && voiceSettings.isPlayVoice())
            playAudioWithHighlights(audioFilePath,syncFilePath,view);
    }

    private void playAudioWithHighlights(File audioFilePath, File syncFilePath, TextView view) {
        playAudio(audioFilePath);
        playHighlights(syncFilePath,view);
    }

    private void playAudio(File filePath){
        player.reset();
        player = MediaPlayer.create(context,Uri.parse(filePath.toURI().toString()));
        player.start();
    }

    private void playHighlights(File syncFilePath,TextView view) {
        VoiceSettings voiceSettings = new FirebaseAccessPoint().getVoiceSettings(context);
        if(voiceSettings.isWordHighlight()) {
            if(voiceSettings.isForwardHighlight())
                playWordHighlights(syncFilePath, view, true);
            else
                playWordHighlights(syncFilePath, view, false);
        }else {
            if(voiceSettings.isForwardHighlight())
                playLetterHighlights(syncFilePath, view, true);
            else
                playLetterHighlights(syncFilePath, view, false);
        }
    }
    private void playWordHighlights(File syncFilePath, final TextView view , boolean isForwardHiglight){
        final ArrayList<Token> tokens = readTokens(syncFilePath);
        android.os.Handler handler = new android.os.Handler();
        double offset = 0 ;
        for (int i = 0; i<tokens.size() ;i++) {
            if(isForwardHiglight)
                offset=tokens.get(i).getEnd()*1000;
            final int start = tokens.get(i).getChar_start();
            final int end = tokens.get(i).getChar_end();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    highlight(view,start,end);
                }
            }, (long)(offset));
            if(!isForwardHiglight)
                offset=tokens.get(i).getEnd()*1000;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                highlight(view,0,0);
            }
        }, (long)((offset)+500));
    }
    private void playLetterHighlights(File syncFilePath, final TextView view , boolean isForwardHiglight){
        final ArrayList<Segment> segments = readSegments(syncFilePath);
        android.os.Handler handler = new android.os.Handler();
        double offset = 0 ;
        for (int i = 0; i<segments.size() ;i++) {
            if(isForwardHiglight)
                offset=segments.get(i).getEnd()*1000;
            final int position = i;
            Log.d("MyApp","Termine lettera:"+offset);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    highlight(view,position,position+1);
                }
            }, (long)(offset));
            if(!isForwardHiglight)
                offset=segments.get(i).getEnd()*1000;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                highlight(view,0,0);
            }
        }, (long)((offset)+500));
    }
    private void highlight(TextView view,int start,int end){
        if(!view.getText().toString().isEmpty()) {
            Spannable stringToSpan = new SpannableString(view.getText().toString().replace("\\s",""));
            if(end<=stringToSpan.length()) {
                TextSettings textSettings = new FirebaseAccessPoint().getTextSettings(context);
                stringToSpan.setSpan(new BackgroundColorSpan(textSettings.getColorByName(textSettings.getHighlightColor())), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                view.setText(stringToSpan);
            }
        }
    }

    private ArrayList<Token> readTokens(File syncFilePath) {
        ArrayList<Token> resultTokens = new ArrayList<>();
        try {
            JSONArray tokens = readSyncData(syncFilePath).getJSONArray("tokens");
            for(int i=0;i<tokens.length();i++){
                resultTokens.add(new Token(
                        tokens.getJSONObject(i).getDouble("start"),
                        tokens.getJSONObject(i).getDouble("end"),
                        tokens.getJSONObject(i).getInt("char_start"),
                        tokens.getJSONObject(i).getInt("char_end")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultTokens;
    }

    private ArrayList<Segment> readSegments(File syncFilePath) {
        // IN THE REAL WORLD
        ArrayList<Segment> resultSegments = new ArrayList<>();
        try{
            JSONArray tokens = readSyncData(syncFilePath).getJSONArray("tokens");
            double currentMs = 0;
            for (int i=0;i<tokens.length();i++){
                int letters = tokens.getJSONObject(i).getInt("char_end")-tokens.getJSONObject(i).getInt("char_start")+1;
                double offset = (tokens.getJSONObject(i).getDouble("end")-tokens.getJSONObject(i).getDouble("start"))/letters;
                Log.d("MyApp","Token: "+i+" - "+" Letters: "+ letters+ " Offset: "+offset);
                for(int j=0;j<letters;j++){
                    resultSegments.add(new Segment(currentMs,currentMs + offset));
                    currentMs+=offset;
                }
            }
        }catch(JSONException e){
            e.printStackTrace();
        }

        return  resultSegments;
        /* IN A PERFECT WORLD THINGS WOULD BE LIKE THIS
        ArrayList<Segment> resultSegments = new ArrayList<>();
        try {
            JSONArray segments = readSyncData(syncFilePath).getJSONArray("segments");
            for(int i=0;i<segments.length();i++){
                resultSegments.add(new Segment(
                        segments.getJSONObject(i).getDouble("start"),
                        segments.getJSONObject(i).getDouble("end")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultSegments;
        */
    }

    private JSONObject readSyncData(File syncFilePath) {
        JSONObject result = null;
        try{
            FileInputStream file = new FileInputStream(syncFilePath);
            int size = file.available();
            byte[] buffer = new byte[size];
            file.read(buffer);
            file.close();
            String myJson = new String(buffer,"UTF-8");
            result = new JSONObject(myJson);
        }catch(IOException |JSONException x){
            x.printStackTrace();
        }
        return result;
    }

}
