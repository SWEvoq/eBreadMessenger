package swevoq.ebread.com.Libraries.FATTSLib;

import android.app.Activity;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import swevoq.ebread.com.Chat.Model.Chat.Message;
import swevoq.ebread.com.Chat.Model.Chat.TextMessage;
import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.Model.Settings.VoiceSettings;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

/**
 * Created by Teslaru Nicolae on 24/04/2017.
 */

public class FATTSServices implements  Response.Listener<byte[]>, Response.ErrorListener {
    private RequestQueue requestQueue;
    private TextView textView;
    private Message message;

    public FATTSServices (Activity activity){
        requestQueue = Volley.newRequestQueue(activity.getApplicationContext());
    }

    public FATTSServices (TextView textView, Message message){
        requestQueue = Volley.newRequestQueue(textView.getContext(), new HurlStack());
        this.textView = textView;
        this.message = message;
    }


    public void getVoices(){
        String url="http://fic2fatts.tts.mivoq.it/info/voices/all";
        JsonObjectRequest voicesRequest=new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                saveVoices(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Si è verificato un'errore con la richiesta delle voci
            }
        });
        requestQueue.add(voicesRequest);
    }

    public void getLanguages(){
        String url= "http://fic2fatts.tts.mivoq.it/info/locales/all";
        JsonObjectRequest langRequest=new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                saveLanguages(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Si è verificato un'errore con la richiesta delle lingue
            }
        });
        requestQueue.add(langRequest);
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
        String textToRead ="";
        for(int i=0;i<temp.length;i++)
            try {
                textToRead+= URLEncoder.encode(temp[i],"UTF-8")+"+";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        FirebaseAccessPoint firebase = new FirebaseAccessPoint();
        String voice = firebase.getUserVoice(textView.getContext(),((User)message.getUser()).getUsername());
        return "http://fic2fatts.tts.mivoq.it/say?input[type]=TEXT"+
                "&input[locale]="+voiceSettings.getVoiceLanguage()+
                "&input[content]="+textToRead+
                "&output[type]=AUDIO&output[format]=WAVE_FILE"+
                "&voice[name]="+voice+
                "&utterance[effects]=[{Rate:"+voiceSettings.getVoiceRate()+"}]";
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
        String textToRead ="";
        for(int i=0;i<temp.length;i++)
            try {
                textToRead+= URLEncoder.encode(temp[i],"UTF-8")+"+";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        FirebaseAccessPoint firebase = new FirebaseAccessPoint();
        String voice = firebase.getUserVoice(textView.getContext(),((User)message.getUser()).getUsername());
        return "http://fic2fatts.tts.mivoq.it/say?input[type]=TEXT"+
                "&input[locale]="+voiceSettings.getVoiceLanguage()+
                "&input[content]="+textToRead+
                "&output[type]=LIPSYNC&output[format]=WAVE_FILE"+
                "&voice[name]="+voice+
                "&utterance[effects]=[{Rate:"+voiceSettings.getVoiceRate()+"}]";
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

    private void saveVoices(JSONObject voices){
        try{
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/voices.json");
            FileOutputStream out=new FileOutputStream(file);
            out.write(voices.toString().getBytes());
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void saveLanguages(JSONObject langs){
        try{
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/langs.json");
            FileOutputStream out=new FileOutputStream(file);
            out.write(langs.toString().getBytes());
            out.close();
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
