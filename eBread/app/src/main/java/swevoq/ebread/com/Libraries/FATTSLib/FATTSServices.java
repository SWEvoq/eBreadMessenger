package swevoq.ebread.com.Libraries.FATTSLib;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import swevoq.ebread.com.Chat.Model.Chat.Message;
import swevoq.ebread.com.Chat.Model.Chat.TextMessage;
import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.Model.Settings.TextSettings;
import swevoq.ebread.com.Chat.Model.Settings.VoiceSettings;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

/**
 * Created by Teslaru Nicolae on 24/04/2017.
 */

public class FATTSServices implements  Response.Listener<byte[]>, Response.ErrorListener {
    private RequestQueue requestQueue;
    private TextView textView;
    private Message message;

    public FATTSServices (Context context){
        requestQueue = Volley.newRequestQueue(context,new HurlStack());
        textView = null;
        message = null;
    }
    public FATTSServices (TextView textView, Message message){
        requestQueue = Volley.newRequestQueue(textView.getContext(), new HurlStack());
        this.textView = textView;
        this.message = message;
    }

    public void performTestAudioRequest(VoiceSettings voiceSettings){
        ((TextMessage)message).setId("test");
        ((TextMessage)message).setText("Benvenuto nel mondo della sintesi vocale.");
        String url = buildAudioRequestUrl(voiceSettings);
        FATTSRequest request = new FATTSRequest(Request.Method.GET,url,FATTSServices.this,FATTSServices.this,null);
        requestQueue.add(request);
    }

    public void performAudioRequest(){
        String url = buildAudioRequestUrl(new FirebaseAccessPoint().getVoiceSettings(textView.getContext()));
        FATTSRequest request = new FATTSRequest(Request.Method.GET,url,FATTSServices.this,FATTSServices.this,null);
        requestQueue.add(request);
    }

    private String buildAudioRequestUrl(VoiceSettings voiceSettings) {
        String[] temp =   message.getText().split(" ");
        temp[temp.length-1]+=adjustStringEnding(temp[temp.length-1]);
        String textToRead ="";
        for(int i=0;i<temp.length;i++)
            try {
                textToRead+= URLEncoder.encode(temp[i],"UTF-8");
                if(!(i==temp.length-1))
                    textToRead+="+";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.d("MyApp",textToRead);
        FirebaseAccessPoint firebase = new FirebaseAccessPoint();
        String voice = firebase.getUserVoice(textView.getContext(),((User)message.getUser()).getUsername());
        String language = firebase.getUserVoiceLang(textView.getContext(),((User)message.getUser()).getUsername());
        return "http://fic2fatts.tts.mivoq.it/say?input[type]=TEXT"+
                "&input[locale]="+language+
                "&input[content]="+textToRead+
                "&output[type]=AUDIO&output[format]=WAVE_FILE"+
                "&voice[name]="+voice+
                "&utterance[effects]=[{Rate:"+voiceSettings.getVoiceRate()+"}]";
    }

    public void getVoicesByLanguage(final ArrayAdapter<String> adapter, String language){
        String url = "http://fic2fatts.tts.mivoq.it/info/voices/locale/"+language;
        JsonObjectRequest voicesRequest = new JsonObjectRequest(Request.Method.GET,url,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    adapter.clear();
                    JSONArray voices = response.getJSONArray("voices");
                    adapter.clear();
                    for(int i=0;i<voices.length();i++){
                        adapter.add(voices.getJSONObject(i).getString("id"));
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                // Si è verificato un'errore con la richiesta dei tokenù
            }
        });
        requestQueue.add(voicesRequest);
    }
    public void getLanguages(final ArrayAdapter<String> adapter){
        String url = "http://fic2fatts.tts.mivoq.it/info/locales/all";
        JsonObjectRequest languagesRequest = new JsonObjectRequest(Request.Method.GET,url,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    adapter.clear();
                    JSONArray languages = response.getJSONArray("locales");
                    for(int i=0;i<languages.length();i++){
                        adapter.add(languages.getString(i));
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                // Si è verificato un'errore con la richiesta dei token
            }
        });
        requestQueue.add(languagesRequest);
    }

    private void performSyncRequest(){
        String url = buildSyncRequestUrl(new FirebaseAccessPoint().getVoiceSettings(textView.getContext()));
        JsonObjectRequest tokensRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                saveSyncDataToDevice(response);
                // Ho ricevuto i token, posso procedere con la riproduzione del audio.
                File audioFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + message.getId()+".wav");
                File syncDataFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + message.getId() + ".txt");
                if(audioFile.exists() && syncDataFile.exists()) {
                    FATTSPlayer player = new FATTSPlayer(textView.getContext());
                    player.playSyncAudio(audioFile,syncDataFile,textView);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Si è verificato un'errore con la richiesta dei token
            }
        });
        requestQueue.add(tokensRequest);
    }

    private String buildSyncRequestUrl(VoiceSettings voiceSettings) {
        String[] temp =   message.getText().split(" ");
        temp[temp.length-1]+=adjustStringEnding(temp[temp.length-1]);
        String textToRead ="";
        for(int i=0;i<temp.length;i++)
            try {
                textToRead+= URLEncoder.encode(temp[i],"UTF-8");
                if(!(i==temp.length-1))
                    textToRead+="+";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        FirebaseAccessPoint firebase = new FirebaseAccessPoint();
        String voice = firebase.getUserVoice(textView.getContext(),((User)message.getUser()).getUsername());
        String language = firebase.getUserVoiceLang(textView.getContext(),((User)message.getUser()).getUsername());
        return "http://fic2fatts.tts.mivoq.it/say?input[type]=TEXT"+
                "&input[locale]="+language+
                "&input[content]="+textToRead+
                "&output[type]=LIPSYNC&output[format]=WAVE_FILE"+
                "&voice[name]="+voice+
                "&utterance[effects]=[{Rate:"+voiceSettings.getVoiceRate()+"}]";
    }

    private String adjustStringEnding(String text){
        String lastChar = text.charAt(text.length()-1)+"";
        if( !(lastChar.equals("?")) && !(lastChar.equals("!")) && !(lastChar.equals(".")))
            return ".";
        else
            return "";
    }
    @Override
    public void onResponse(byte[] response) {
        if(response!=null) {
            saveAudioToDevice(response);
        }
        performSyncRequest();
    }

    private void saveAudioToDevice(byte[] response){
        try {
            InputStream input = new ByteArrayInputStream(response);
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + message.getId()+".wav");
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
            byte data[] = new byte[input.available()];
            int count = 0;
            while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void saveSyncDataToDevice(JSONObject syncData){
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + message.getId() + ".txt");
            FileOutputStream output = new FileOutputStream(file);
            output.write(syncData.toString().getBytes());
            output.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        File audioFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + message.getId()+".wav");
        File syncDataFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + message.getId() + ".txt");
        if(audioFile.exists() && syncDataFile.exists()){
            // Chiamo media player con file locali
            FATTSPlayer player = new FATTSPlayer(textView.getContext());
            player.playSyncAudio(audioFile,syncDataFile,textView);
        }else{
            Toast.makeText(textView.getContext(),"Audio non presente in memoria & Server Mivoq non raggiungibili",Toast.LENGTH_SHORT).show();
        }
    }
}
