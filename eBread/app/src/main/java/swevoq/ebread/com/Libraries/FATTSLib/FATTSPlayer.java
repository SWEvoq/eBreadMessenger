package swevoq.ebread.com.Libraries.FATTSLib;

import android.content.Context;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import swevoq.ebread.com.Chat.Model.Settings.TextSettings;
import swevoq.ebread.com.Chat.Model.Settings.VoiceSettings;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

/**
 * Created by Teslaru Nicolae on 27/04/2017.
 */

public class FATTSPlayer {
    private Context context;
    private PlayerBread player;
    public FATTSPlayer(Context context){
        player = PlayerBread.getInstance();
        this.context = context;
    }

    public void playSyncAudio(File audioFilePath, File syncFilePath, TextView view){
        player.killCurrentPlayer();
        VoiceSettings voiceSettings = new FirebaseAccessPoint().getVoiceSettings(context);
        if(!voiceSettings.isShowHighlight() && !voiceSettings.isPlayVoice())
            return;
        if(!voiceSettings.isShowHighlight() && voiceSettings.isPlayVoice())
            playAudio(audioFilePath);
        if(voiceSettings.isShowHighlight() && !voiceSettings.isPlayVoice())
            playHighlights(audioFilePath,syncFilePath,view);
        if(voiceSettings.isShowHighlight() && voiceSettings.isPlayVoice())
            playAudioWithHighlights(audioFilePath,syncFilePath,view);
    }

    private void playAudioWithHighlights(File audioFilePath, File syncFilePath, TextView view) {
        //playAudio(audioFilePath);
        playHighlights(audioFilePath,syncFilePath,view);
    }

    private void playAudio(File filePath){
        player.setPlayer(MediaPlayer.create(context,Uri.parse(filePath.toURI().toString())));
        player.getPlayer().start();
    }

    private void playHighlights(File audioFilePath,File syncFilePath,TextView view) {
        VoiceSettings voiceSettings = new FirebaseAccessPoint().getVoiceSettings(context);
        if(voiceSettings.isWordHighlight()) {
            if(voiceSettings.isPersistentHighlight())
                playWordHighlights(audioFilePath,syncFilePath, view, voiceSettings.getHighlightDelay(), true);
            else
                playWordHighlights(audioFilePath,syncFilePath, view, voiceSettings.getHighlightDelay(), false);
        }else {
            if(voiceSettings.isPersistentHighlight())
                playLetterHighlights(audioFilePath,syncFilePath, view, voiceSettings.getHighlightDelay(), true);
            else
                playLetterHighlights(audioFilePath,syncFilePath, view, voiceSettings.getHighlightDelay(), false);
        }
    }
    private void playWordHighlights(final File audioFilePath, File syncFilePath, final TextView view , int highlightDelay, final boolean isPersistentHighlight){
        final ArrayList<Token> tokens = readTokens(syncFilePath);
        Handler handler = player.getHandler();
        player.setLastView(view);
        double offset;
        if(highlightDelay<=0){
            offset = 0;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    playAudio(audioFilePath);
                }
            },(long)(highlightDelay*(-1)));
        }else{
            offset = highlightDelay;
            playAudio(audioFilePath);
        }
        for (int i = 0; i<tokens.size() ;i++) {
            final int start = tokens.get(i).getChar_start();
            final int end = tokens.get(i).getChar_end();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(isPersistentHighlight)
                        highlight(view,0,end);
                    else
                        highlight(view,start,end);
                }
            }, (long)(offset));
            offset=tokens.get(i).getEnd()*1000;
            if(highlightDelay>0)
                offset+=highlightDelay;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                highlight(view,0,0);
            }
        }, (long)((offset)+500));
    }
    private void playLetterHighlights(final File audioFilePath,File syncFilePath, final TextView view , int highlightDelay, final boolean isPersistentHighlight){
        final ArrayList<Segment> segments = readSegments(syncFilePath);
        Handler handler = player.getHandler();
        player.setLastView(view);
        double offset;
        if(highlightDelay<=0){
            offset = 0;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    playAudio(audioFilePath);
                }
            },(long)(highlightDelay*(-1)));
        }else{
            offset = highlightDelay;
            playAudio(audioFilePath);
        }
        for (int i = 0; i<segments.size() ;i++) {
            final int position = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(isPersistentHighlight)
                        highlight(view,0,position+1);
                    else
                        highlight(view,position,position+1);
                }
            }, (long)(offset));
            offset=segments.get(i).getEnd()*1000;
            if(highlightDelay>0)
                offset+=highlightDelay;
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
