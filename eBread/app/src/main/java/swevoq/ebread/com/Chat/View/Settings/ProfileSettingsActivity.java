package swevoq.ebread.com.Chat.View.Settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.Presenter.Settings.ProfileSettingsPresenter;
import swevoq.ebread.com.Chat.View.Utility.AddressBookActivity;
import swevoq.ebread.com.Chat.View.Utility.ChatListActivity;
import swevoq.ebread.com.R;

public class ProfileSettingsActivity extends AppCompatActivity {
    private ImageView avatar;
    private Button updateAvatarButton;
    private Button removeAvatarButton;
    private Button updatePersonalSettings;
    private EditText insertNickname;
    private EditText insertName;
    private EditText insertSurname;
    private ProfileSettingsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        avatar = (ImageView) findViewById(R.id.personalProfileImg);
        updateAvatarButton = (Button) findViewById(R.id.updateProfilePicture);
        removeAvatarButton = (Button) findViewById(R.id.deleteProfilePicture);
        updatePersonalSettings = (Button) findViewById(R.id.updateProfileSettings);
        insertNickname = (EditText) findViewById(R.id.inputNickname);
        insertName = (EditText) findViewById(R.id.inputName);
        insertSurname = (EditText) findViewById(R.id.inputSurname);
        presenter = new ProfileSettingsPresenter();
        final User dummy = new User();
        presenter.getUserData(presenter.getLoggedUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User result = dataSnapshot.getValue(User.class);
                dummy.setId(result.getId());
                dummy.setAvatar(result.getAvatar());
                dummy.setUsername(result.getUsername());
                insertNickname.setText(result.getNickname());
                insertName.setText(result.getRealName());
                insertSurname.setText(result.getSurname());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        updateAvatarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // QUI AGGIORNO L?AVATAR -> AGGIORNARE FIREBASE + IMAGEVIEW
            }
        });
        removeAvatarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // QUI RIMETTO A DEFAULT LìAVATAR -> AGGIORNARE FIREBASE + IMAGEVIEW
            }
        });
        updatePersonalSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // QUI SALVO TUTTI I DATI IN FIREBASE.
                dummy.setRealName(insertName.getText().toString());
                dummy.setSurname(insertSurname.getText().toString());
                dummy.setNickname(insertNickname.getText().toString());
                presenter.updateUser(dummy);
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ProfileSettingsActivity.this, ChatListActivity.class);
        ProfileSettingsActivity.this.startActivity(intent);
        finish();
    }
}
