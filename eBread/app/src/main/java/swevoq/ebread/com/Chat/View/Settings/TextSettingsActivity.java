package swevoq.ebread.com.Chat.View.Settings;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import swevoq.ebread.com.Chat.Model.Settings.TextSettings;
import swevoq.ebread.com.Chat.Presenter.Settings.TextSettingsPresenter;
import swevoq.ebread.com.Chat.View.Utility.ChatListActivity;
import swevoq.ebread.com.R;

public class TextSettingsActivity extends AppCompatActivity {
    private TextSettingsPresenter presenter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_settings);
        presenter = new TextSettingsPresenter();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Impostazioni visualizzazione");
        context = this;

        final HashMap<String,Integer> availableColors = presenter.getAvaiableColors();
        final HashMap<String,Integer> availableTextSizing = presenter.getAvaiableTextSizing();
        final HashMap<String,Integer> availableTextSpacing = presenter.getAvaiableTextSpacing();
        TextSettings usersTextSettings = presenter.getTextSettings(this);

        final Spinner textColorSpinner = (Spinner)findViewById(R.id.textColorSpinner);
        ArrayAdapter<CharSequence> textColorSpinnerAdapter = android.widget.ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item);
        textColorSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textColorSpinner.setAdapter(textColorSpinnerAdapter);
        textColorSpinner.setSelection(textColorSpinnerAdapter.getPosition(usersTextSettings.getTextColor()));
        textColorSpinner.setBackgroundColor(availableColors.get(textColorSpinner.getSelectedItem().toString()));
        textColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textColorSpinner.setBackgroundColor(availableColors.get(textColorSpinner.getSelectedItem().toString()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        final Spinner bubbleColorSpinner = (Spinner)findViewById(R.id.bubbleColorSpinner);
        ArrayAdapter<CharSequence> bubbleColorSpinnerAdapter = android.widget.ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item);
        bubbleColorSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bubbleColorSpinner.setAdapter(bubbleColorSpinnerAdapter);
        bubbleColorSpinner.setSelection(bubbleColorSpinnerAdapter.getPosition(usersTextSettings.getBubbleColor()));
        bubbleColorSpinner.setBackgroundColor(availableColors.get(bubbleColorSpinner.getSelectedItem().toString()));
        bubbleColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bubbleColorSpinner.setBackgroundColor(availableColors.get(bubbleColorSpinner.getSelectedItem().toString()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        final Spinner highlightColorSpinner = (Spinner)findViewById(R.id.highlightColorSpinner);
        ArrayAdapter<CharSequence> highlightColorSpinnerAdapter = android.widget.ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item);
        highlightColorSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        highlightColorSpinner.setAdapter(highlightColorSpinnerAdapter);
        highlightColorSpinner.setSelection(highlightColorSpinnerAdapter.getPosition(usersTextSettings.getHighlightColor()));
        highlightColorSpinner.setBackgroundColor(availableColors.get(highlightColorSpinner.getSelectedItem().toString()));
        highlightColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                highlightColorSpinner.setBackgroundColor(availableColors.get(highlightColorSpinner.getSelectedItem().toString()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        final Spinner textFontSpinner = (Spinner)findViewById(R.id.textFontSpinner);
        ArrayAdapter<CharSequence> textFontSpinnerAdapter = android.widget.ArrayAdapter.createFromResource(this, R.array.fonts_array, android.R.layout.simple_spinner_item);
        textFontSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textFontSpinner.setAdapter(textFontSpinnerAdapter);
        textFontSpinner.setSelection(textFontSpinnerAdapter.getPosition(usersTextSettings.getTextFont()));

        final Spinner fontSizeSpinner = (Spinner)findViewById(R.id.fontSizeSpinner);
        ArrayAdapter<CharSequence> fontSizeSpinnerAdapter = android.widget.ArrayAdapter.createFromResource(this, R.array.sizes_array, android.R.layout.simple_spinner_item);
        fontSizeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontSizeSpinner.setAdapter(fontSizeSpinnerAdapter);
        if(usersTextSettings.getFontSize()==14)
            fontSizeSpinner.setSelection(fontSizeSpinnerAdapter.getPosition("Piccolo"));
        else if(usersTextSettings.getFontSize()==16)
            fontSizeSpinner.setSelection(fontSizeSpinnerAdapter.getPosition("Medio"));
        else if(usersTextSettings.getFontSize()==18)
            fontSizeSpinner.setSelection(fontSizeSpinnerAdapter.getPosition("Normale"));
        else if(usersTextSettings.getFontSize()==20)
            fontSizeSpinner.setSelection(fontSizeSpinnerAdapter.getPosition("Grande"));
        if(usersTextSettings.getFontSize()==22)
            fontSizeSpinner.setSelection(fontSizeSpinnerAdapter.getPosition("Molto Grande"));

        final Spinner fontSpacingSpinner = (Spinner)findViewById(R.id.fontSpacingSpinner);
        ArrayAdapter<CharSequence> fontSpacingSpinnerAdapter = android.widget.ArrayAdapter.createFromResource(this, R.array.sizes_array, android.R.layout.simple_spinner_item);
        fontSpacingSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontSpacingSpinner.setAdapter(fontSpacingSpinnerAdapter);
        if(usersTextSettings.getFontSpacing()==0)
            fontSpacingSpinner.setSelection(fontSpacingSpinnerAdapter.getPosition("Piccolo"));
        else if(usersTextSettings.getFontSpacing()==1)
            fontSpacingSpinner.setSelection(fontSpacingSpinnerAdapter.getPosition("Medio"));
        else if(usersTextSettings.getFontSpacing()==2)
            fontSpacingSpinner.setSelection(fontSpacingSpinnerAdapter.getPosition("Normale"));
        if(usersTextSettings.getFontSpacing()==3)
            fontSpacingSpinner.setSelection(fontSpacingSpinnerAdapter.getPosition("Grande"));
        if(usersTextSettings.getFontSpacing()==4)
            fontSpacingSpinner.setSelection(fontSpacingSpinnerAdapter.getPosition("Molto Grande"));

        Button updateTextSettings = (Button)findViewById(R.id.updateTextSettings);
        updateTextSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextSettings updatedTextSettings = new TextSettings();
                updatedTextSettings.setTextColor(textColorSpinner.getSelectedItem().toString());
                updatedTextSettings.setBubbleColor(bubbleColorSpinner.getSelectedItem().toString());
                updatedTextSettings.setFontSize(availableTextSizing.get(fontSizeSpinner.getSelectedItem().toString()));
                updatedTextSettings.setFontSpacing(availableTextSpacing.get(fontSpacingSpinner.getSelectedItem().toString()));
                updatedTextSettings.setTextFont(textFontSpinner.getSelectedItem().toString());
                updatedTextSettings.setHighlightColor(highlightColorSpinner.getSelectedItem().toString());
                presenter.updateTextSettings(context,updatedTextSettings);
                Toast.makeText(context,"Le impostazioni sono state aggiornate!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(TextSettingsActivity.this, ChatListActivity.class);
        TextSettingsActivity.this.startActivity(intent);
        finish();
    }
}
