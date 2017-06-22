package swevoq.ebread.com.Chat.View.Settings;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

import swevoq.ebread.com.Chat.Model.Chat.TextMessage;
import swevoq.ebread.com.Chat.Model.Settings.VoiceSettings;
import swevoq.ebread.com.Chat.Presenter.Settings.VoiceSettingsPresenter;
import swevoq.ebread.com.Chat.View.Utility.ChatListActivity;
import swevoq.ebread.com.Libraries.FATTSLib.FATTSPlayer;
import swevoq.ebread.com.Libraries.FATTSLib.FATTSServices;
import swevoq.ebread.com.R;

public class VoiceSettingsActivity extends AppCompatActivity {
    private VoiceSettingsPresenter presenter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_settings);
        context = this;
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Impostazioni voce di sintesi");
        presenter = new VoiceSettingsPresenter();
        VoiceSettings usersVoiceSettings = presenter.getVoiceSettings(this);
        final FATTSServices service= new FATTSServices(this);

        final Spinner defaultVoiceSpinner = (Spinner)findViewById(R.id.defaultVoiceSpinner);
        String[] voiceList;
        ArrayAdapter<CharSequence> voiceSpinnerAdapter;
        //Prova a leggere le voci disponibili da JSON. In caso di errori, legge una lista predefinita.
        try{
            //Popolo un nuovo file JSON
            service.getVoices(usersVoiceSettings.getVoiceLanguage());
            //Leggo il file JSON
            InputStream input = new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/voices.json");
            int size=input.available();
            byte[] buffer=new byte[size];
            input.read(buffer);
            input.close();
            String voicesJson = new String(buffer, "UTF-8");
            JSONObject voices=new JSONObject(voicesJson);

            JSONArray voiceArray = voices.getJSONArray("voices");
            voiceList= new String[voiceArray.length()];
            for(int i=0; i<voiceArray.length(); i++){
                JSONObject v=voiceArray.getJSONObject(i);
                voiceList[i]=v.getString("id");
            }

            voiceSpinnerAdapter= new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, voiceList);
        } catch(Exception e){
            voiceSpinnerAdapter = android.widget.ArrayAdapter.createFromResource(this, R.array.voices_array, android.R.layout.simple_spinner_item);
        }
        voiceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        defaultVoiceSpinner.setAdapter(voiceSpinnerAdapter);
        defaultVoiceSpinner.setSelection(voiceSpinnerAdapter.getPosition(usersVoiceSettings.getVoiceName()));

        final Spinner languageVoiceSpinner = (Spinner)findViewById(R.id.languageVoiceSpinner);
        String[] langList;
        ArrayAdapter<CharSequence> languageVoiceSpinnerAdapter;
        //Come sopra per le voci, ma stavolta leggo le lingue
        try{
            //Popolo un nuovo file JSON
            service.getLanguages();
            //Leggo il file JSON
            InputStream input = new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/langs.json");
            int size=input.available();
            byte[] buffer=new byte[size];
            input.read(buffer);
            input.close();
            String langsJson = new String(buffer, "UTF-8");
            JSONObject langs=new JSONObject(langsJson);

            JSONArray langArray = langs.getJSONArray("locales");
            langList= new String[langArray.length()];
            for (int i=0; i<langArray.length(); i++) {
                langList[i]=langArray.getString(i);
            }

            languageVoiceSpinnerAdapter= new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, langList);
        } catch(Exception e){
            languageVoiceSpinnerAdapter = android.widget.ArrayAdapter.createFromResource(this, R.array.voices_languages_array, android.R.layout.simple_spinner_item);
        }
        languageVoiceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageVoiceSpinner.setAdapter(languageVoiceSpinnerAdapter);
        languageVoiceSpinner.setSelection(languageVoiceSpinnerAdapter.getPosition(usersVoiceSettings.getVoiceLanguage()));

        languageVoiceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position = languageVoiceSpinner.getSelectedItemPosition();
                String currentLang = languageVoiceSpinner.getItemAtPosition(position).toString();
                service.getVoices(currentLang);
                try {
                    String[] vList;

                    InputStream input = new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/voices.json");
                    int size = input.available();
                    byte[] buffer = new byte[size];
                    input.read(buffer);
                    input.close();
                    String voicesJson = new String(buffer, "UTF-8");
                    JSONObject voices = new JSONObject(voicesJson);

                    JSONArray voiceArray = voices.getJSONArray("voices");
                    vList = new String[voiceArray.length()];
                    for (int i = 0; i < voiceArray.length(); i++) {
                        JSONObject v = voiceArray.getJSONObject(i);
                        vList[i] = v.getString("id");
                    }

                    ArrayAdapter<CharSequence> repopulateAdapter = new ArrayAdapter<CharSequence>(defaultVoiceSpinner.getContext(), android.R.layout.simple_spinner_item, vList);
                    repopulateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    repopulateAdapter.notifyDataSetChanged();
                    defaultVoiceSpinner.setAdapter(repopulateAdapter);
                    defaultVoiceSpinner.setSelection(0);
                }catch(Exception e){
                    ArrayAdapter<CharSequence> repopulateAdapter;
                    repopulateAdapter = android.widget.ArrayAdapter.createFromResource(defaultVoiceSpinner.getContext(), R.array.voices_array, android.R.layout.simple_spinner_item);
                    repopulateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    defaultVoiceSpinner.setAdapter(repopulateAdapter);
                    defaultVoiceSpinner.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        /*defaultVoiceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try{
                    //Metodo brutto fatto velocemente ma dovrebbe funzionare
                    InputStream input = new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/voices.json");
                    int size=input.available();
                    byte[] buffer=new byte[size];
                    input.read(buffer);
                    input.close();
                    String voicesJson = new String(buffer, "UTF-8");
                    JSONObject voices=new JSONObject(voicesJson);
                    JSONArray voiceArray = voices.getJSONArray("voices");

                    input = new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/langs.json");
                    size=input.available();
                    buffer=new byte[size];
                    input.read(buffer);
                    input.close();
                    String langsJson = new String(buffer, "UTF-8");
                    JSONObject langs=new JSONObject(langsJson);
                    JSONArray langArray = langs.getJSONArray("locales");

                    position=defaultVoiceSpinner.getSelectedItemPosition();
                    String currentVoice=defaultVoiceSpinner.getItemAtPosition(position).toString();
                    for(int i=0; i<voiceArray.length(); i++) {
                        JSONObject v = voiceArray.getJSONObject(i);
                        String voice = v.getString("id");
                        if (currentVoice.equals(voice)) {
                            JSONArray a = v.getJSONArray("locales");
                            String ll = a.getString(0);
                            //Toast.makeText(context,ll,Toast.LENGTH_SHORT).show();
                            for (int j = 0; j < langArray.length(); j++) {
                                if (ll.equals(langArray.getString(j))) {
                                    languageVoiceSpinner.setSelection(j);
                                }
                            }
                        }
                    }
                }catch(Exception e){e.printStackTrace();}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });*/

        final Spinner speedVoiceSpinner = (Spinner)findViewById(R.id.speedVoiceSpinner);
        ArrayAdapter<CharSequence> speedVoiceSpinnerAdapter = android.widget.ArrayAdapter.createFromResource(this, R.array.voices_speed_array, android.R.layout.simple_spinner_item);
        speedVoiceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speedVoiceSpinner.setAdapter(speedVoiceSpinnerAdapter);
        if(usersVoiceSettings.getVoiceRate() == 1.5)
            speedVoiceSpinner.setSelection(speedVoiceSpinnerAdapter.getPosition("lento"));
        else if(usersVoiceSettings.getVoiceRate() == 1.0)
            speedVoiceSpinner.setSelection(speedVoiceSpinnerAdapter.getPosition("normale"));
        else if(usersVoiceSettings.getVoiceRate() == 0.7)
            speedVoiceSpinner.setSelection(speedVoiceSpinnerAdapter.getPosition("veloce"));
        else if(usersVoiceSettings.getVoiceRate() == 0.4)
            speedVoiceSpinner.setSelection(speedVoiceSpinnerAdapter.getPosition("super veloce"));


        final Spinner highlightDelayTypeSpinner = (Spinner)findViewById(R.id.highlightDelayTypeSpinner);
        ArrayAdapter<CharSequence> highlightDelayTypeSpinnerAdapter = android.widget.ArrayAdapter.createFromResource(this, R.array.highlight_delay_type_array, android.R.layout.simple_spinner_item);
        highlightDelayTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        highlightDelayTypeSpinner.setAdapter(highlightDelayTypeSpinnerAdapter);
        if(usersVoiceSettings.isForwardHighlight())
            highlightDelayTypeSpinner.setSelection(highlightDelayTypeSpinnerAdapter.getPosition("in avanti"));
        else
            highlightDelayTypeSpinner.setSelection(highlightDelayTypeSpinnerAdapter.getPosition("all\'indietro"));

        final Spinner highlightTypeSpinner = (Spinner)findViewById(R.id.highlightTypeSpinner);
        ArrayAdapter<CharSequence> highlightTypeSpinnerAdapter = android.widget.ArrayAdapter.createFromResource(this, R.array.highlight_type_array, android.R.layout.simple_spinner_item);
        highlightTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        highlightTypeSpinner.setAdapter(highlightTypeSpinnerAdapter);
        if(usersVoiceSettings.isWordHighlight())
            highlightTypeSpinner.setSelection(highlightTypeSpinnerAdapter.getPosition("parola"));
        else
            highlightTypeSpinner.setSelection(highlightTypeSpinnerAdapter.getPosition("lettera"));

        final Switch highlightSwitch = (Switch)findViewById(R.id.highlightSwitch);
        highlightSwitch.setChecked(usersVoiceSettings.isShowHighlight());

        final Switch voiceSwitch = (Switch)findViewById(R.id.voiceSwitch);
        voiceSwitch.setChecked(usersVoiceSettings.isPlayVoice());

        Button previewVoiceSettings = (Button)findViewById(R.id.previewVoiceSettings);
        previewVoiceSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FATTSServices player = new FATTSServices(new TextView(context),new TextMessage());
                VoiceSettings updatedVoiceSettings = new VoiceSettings();
                updatedVoiceSettings.setVoiceName(defaultVoiceSpinner.getSelectedItem().toString());
                updatedVoiceSettings.setVoiceLanguage(languageVoiceSpinner.getSelectedItem().toString());
                if(speedVoiceSpinner.getSelectedItem().toString().equals("lenta"))
                    updatedVoiceSettings.setVoiceRate(1.5);
                else if(speedVoiceSpinner.getSelectedItem().toString().equals("normale"))
                    updatedVoiceSettings.setVoiceRate(1.0);
                else if(speedVoiceSpinner.getSelectedItem().toString().equals("veloce"))
                    updatedVoiceSettings.setVoiceRate(0.7);
                else if(speedVoiceSpinner.getSelectedItem().toString().equals("super veloce"))
                    updatedVoiceSettings.setVoiceRate(0.4);
                if(highlightDelayTypeSpinner.getSelectedItem().toString().equals("in avanti"))
                    updatedVoiceSettings.setForwardHighlight(true);
                else if(highlightDelayTypeSpinner.getSelectedItem().toString().equals("all\'indietro"))
                    updatedVoiceSettings.setForwardHighlight(false);
                if(highlightTypeSpinner.getSelectedItem().toString().equals("parola"))
                    updatedVoiceSettings.setWordHighlight(true);
                else if(highlightTypeSpinner.getSelectedItem().toString().equals("lettera"))
                    updatedVoiceSettings.setWordHighlight(false);
                updatedVoiceSettings.setShowHighlight(highlightSwitch.isChecked());
                updatedVoiceSettings.setPlayVoice(voiceSwitch.isChecked());
                presenter.updateVoiceSettings(context,updatedVoiceSettings);
                player.performTestAudioRequest(updatedVoiceSettings);
            }
        });
        Button updateVoiceSettings = (Button)findViewById(R.id.updateVoiceSettings);
        updateVoiceSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoiceSettings updatedVoiceSettings = new VoiceSettings();
                updatedVoiceSettings.setVoiceName(defaultVoiceSpinner.getSelectedItem().toString());
                updatedVoiceSettings.setVoiceLanguage(languageVoiceSpinner.getSelectedItem().toString());
                if(speedVoiceSpinner.getSelectedItem().toString().equals("lenta"))
                    updatedVoiceSettings.setVoiceRate(1.5);
                else if(speedVoiceSpinner.getSelectedItem().toString().equals("normale"))
                    updatedVoiceSettings.setVoiceRate(1.0);
                else if(speedVoiceSpinner.getSelectedItem().toString().equals("veloce"))
                    updatedVoiceSettings.setVoiceRate(0.7);
                else if(speedVoiceSpinner.getSelectedItem().toString().equals("super veloce"))
                    updatedVoiceSettings.setVoiceRate(0.4);
                if(highlightDelayTypeSpinner.getSelectedItem().toString().equals("in avanti"))
                    updatedVoiceSettings.setForwardHighlight(true);
                else if(highlightDelayTypeSpinner.getSelectedItem().toString().equals("all\'indietro"))
                    updatedVoiceSettings.setForwardHighlight(false);
                if(highlightTypeSpinner.getSelectedItem().toString().equals("parola"))
                    updatedVoiceSettings.setWordHighlight(true);
                else if(highlightTypeSpinner.getSelectedItem().toString().equals("lettera"))
                    updatedVoiceSettings.setWordHighlight(false);
                updatedVoiceSettings.setShowHighlight(highlightSwitch.isChecked());
                updatedVoiceSettings.setPlayVoice(voiceSwitch.isChecked());
                presenter.updateVoiceSettings(context,updatedVoiceSettings);
                Toast.makeText(context,"Le impostazioni sono state aggiornate!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(VoiceSettingsActivity.this, ChatListActivity.class);
        VoiceSettingsActivity.this.startActivity(intent);
        finish();
    }
}
