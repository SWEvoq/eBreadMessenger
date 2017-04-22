package swevoq.ebread.com.Chat.View.Settings;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.net.URI;

import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.Presenter.Settings.ProfileSettingsPresenter;
import swevoq.ebread.com.Chat.View.Utility.AddressBookActivity;
import swevoq.ebread.com.Chat.View.Utility.ChatListActivity;
import swevoq.ebread.com.R;

public class ProfileSettingsActivity extends AppCompatActivity {
    private ProfileSettingsPresenter presenter;
    private int PICK_IMAGE_REQUEST = 111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Impostazioni personali");
        final ImageView avatar = (ImageView) findViewById(R.id.personalProfileImg);
        Button updateAvatarButton = (Button) findViewById(R.id.updateProfilePicture);
        Button removeAvatarButton = (Button) findViewById(R.id.deleteProfilePicture);
        Button updatePersonalSettings = (Button) findViewById(R.id.updateProfileSettings);
        final EditText insertNickname = (EditText) findViewById(R.id.inputNickname);
        final EditText insertName = (EditText) findViewById(R.id.inputName);
        final EditText insertSurname = (EditText) findViewById(R.id.inputSurname);
        presenter = new ProfileSettingsPresenter();
        final User dummy = new User();
        final Context context = this;
        presenter.getUserData(presenter.getLoggedUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User userData = dataSnapshot.getValue(User.class);
                Picasso.with(context).load(userData.getAvatar()).resize(400,400).into(avatar);
                dummy.setId(userData.getId());
                dummy.setAvatar(userData.getAvatar());
                dummy.setUsername(userData.getUsername());
                insertNickname.setText(userData.getNickname());
                insertName.setText(userData.getRealName());
                insertSurname.setText(userData.getSurname());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        updateAvatarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imgPicker = new Intent();
                imgPicker.setType("image/*");
                imgPicker.setAction(Intent.ACTION_PICK);
                startActivityForResult(imgPicker.createChooser(imgPicker,"Seleziona Immagine Profilo"),PICK_IMAGE_REQUEST);
            }
        });
        removeAvatarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateAvatar("https://cdn2.iconfinder.com/data/icons/rcons-user/32/male-circle-128.png");
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
                Toast.makeText(context,"Le impostazioni sono state aggiornate!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ProfileSettingsActivity.this, ChatListActivity.class);
        ProfileSettingsActivity.this.startActivity(intent);
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri imgLocalPath = null;
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
            imgLocalPath = data.getData();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Caricamento immagine profilo in corso...");

        if(imgLocalPath!=null){
            progressDialog.show();
            presenter.uploadAvatar(imgLocalPath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(ProfileSettingsActivity.this, "Caricamento completato.", Toast.LENGTH_SHORT).show();
                    presenter.getAvatar().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            presenter.updateAvatar(uri.toString());
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(ProfileSettingsActivity.this, "Caricamento fallito.", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
