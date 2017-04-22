package swevoq.ebread.com.Chat.View.Settings;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import swevoq.ebread.com.Chat.Model.Settings.VoiceSettings;
import swevoq.ebread.com.Chat.Presenter.Settings.VoiceSettingsPresenter;
import swevoq.ebread.com.Chat.View.Utility.ChatListActivity;
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

        final Spinner defaultVoiceSpinner = (Spinner)findViewById(R.id.defaultVoiceSpinner);
        ArrayAdapter<CharSequence> voiceSpinnerAdapter = android.widget.ArrayAdapter.createFromResource(this, R.array.voices_array, android.R.layout.simple_spinner_item);
        voiceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        defaultVoiceSpinner.setAdapter(voiceSpinnerAdapter);
        defaultVoiceSpinner.setSelection(voiceSpinnerAdapter.getPosition(usersVoiceSettings.getVoiceName()));

        final Spinner languageVoiceSpinner = (Spinner)findViewById(R.id.languageVoiceSpinner);
        ArrayAdapter<CharSequence> languageVoiceSpinnerAdapter = android.widget.ArrayAdapter.createFromResource(this, R.array.voices_languages_array, android.R.layout.simple_spinner_item);
        languageVoiceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageVoiceSpinner.setAdapter(languageVoiceSpinnerAdapter);
        languageVoiceSpinner.setSelection(languageVoiceSpinnerAdapter.getPosition(usersVoiceSettings.getVoiceLanguage()));

        final Spinner speedVoiceSpinner = (Spinner)findViewById(R.id.speedVoiceSpinner);
        ArrayAdapter<CharSequence> speedVoiceSpinnerAdapter = android.widget.ArrayAdapter.createFromResource(this, R.array.voices_speed_array, android.R.layout.simple_spinner_item);
        speedVoiceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speedVoiceSpinner.setAdapter(speedVoiceSpinnerAdapter);
        if(usersVoiceSettings.getVoiceRate() == 0.5)
            speedVoiceSpinner.setSelection(speedVoiceSpinnerAdapter.getPosition("lento"));
        else if(usersVoiceSettings.getVoiceRate() == 1.0)
            speedVoiceSpinner.setSelection(speedVoiceSpinnerAdapter.getPosition("normale"));
        else if(usersVoiceSettings.getVoiceRate() == 1.5)
            speedVoiceSpinner.setSelection(speedVoiceSpinnerAdapter.getPosition("veloce"));
        else if(usersVoiceSettings.getVoiceRate() == 2.0)
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
                // Riproduzione audio di esempio
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
                    updatedVoiceSettings.setVoiceRate(0.5);
                else if(speedVoiceSpinner.getSelectedItem().toString().equals("normale"))
                    updatedVoiceSettings.setVoiceRate(1.0);
                else if(speedVoiceSpinner.getSelectedItem().toString().equals("veloce"))
                    updatedVoiceSettings.setVoiceRate(1.5);
                else if(speedVoiceSpinner.getSelectedItem().toString().equals("super veloce"))
                    updatedVoiceSettings.setVoiceRate(2.0);
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
