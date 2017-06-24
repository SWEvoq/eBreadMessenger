package swevoq.ebread.com.Chat.View.Utility;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.Model.Utility.AddressBook;
import swevoq.ebread.com.Chat.Presenter.Utility.AddressBookPresenter;
import swevoq.ebread.com.Libraries.FATTSLib.FATTSServices;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;
import swevoq.ebread.com.R;

public class AddressBookActivity extends AppCompatActivity {
    private ListView listView;
    private AddressBook adapter;
    private AddressBookPresenter presenter;
    private Context context;
    private boolean abState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);
        listView = (ListView)findViewById(R.id.listview);
        presenter = new AddressBookPresenter();
        context = this;
        abState = getIntent().getExtras().getBoolean("compState");
        ActionBar actionBar = getSupportActionBar();
        if(abState)
            actionBar.setTitle("Seleziona contatti");
        else
            actionBar.setTitle("Rubrica");
        //Lettura rubrica
        final List<String> list = presenter.getFriends(context);
        adapter = new AddressBook(this,R.layout.custom_textview,list);
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.newuser_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(AddressBookActivity.this);
                View promptView = layoutInflater.inflate(R.layout.adduser_input_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddressBookActivity.this);
                alertDialogBuilder.setView(promptView);

                final EditText editText = (EditText) promptView.findViewById(R.id.inpuText);
                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("Aggiungi", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id) {
                                presenter.addUserToAddressBook(adapter,context ,list,editText.getText().toString().toLowerCase());
                            }
                        })
                        .setNegativeButton("Annulla",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create an alert dialog
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /* Questo è quello che succede quanto ho il single click
                aka apro la scheda del contatto solo se sono nella modalità "consultazione rubrica". Nella modalità "composizione" non succede nulla.
                 */
                if(!abState) {
                    final String userClicked = parent.getAdapter().getItem(position).toString();
                    presenter.getUsers().addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(final DataSnapshot user: dataSnapshot.getChildren()) {
                                if(user.child("username").getValue(String.class).equals(userClicked)) {
                                    final User userData = user.getValue(User.class);
                                    LayoutInflater layoutInflater = LayoutInflater.from(AddressBookActivity.this);
                                    View promptView = layoutInflater.inflate(R.layout.viewuser_dialog, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddressBookActivity.this);
                                    alertDialogBuilder.setView(promptView);

                                    final TextView profileInfo = (TextView) promptView.findViewById(R.id.profileInfo);
                                    String details = "Nominativo: "+userData.getRealName() + " " + userData.getSurname() +
                                                     "\n" + "Username: " + userData.getUsername() +
                                                     "\n" + "@" + userData.getNickname() +
                                                     "\n" + "Voce: " + presenter.getUserVoice(context,userData.getUsername()) +
                                                     " Lingua: " + presenter.getUserVoiceLang(context,userData.getUsername());
                                    profileInfo.setText(details);
                                    final ImageView profileImg = (ImageView) promptView.findViewById(R.id.profileImg);
                                    Picasso.with(context).load(userData.getAvatar()).resize(400,400).into(profileImg);

                                    final FATTSServices fattsServices = new FATTSServices(context);

                                    final Spinner voiceSpinner = (Spinner) promptView.findViewById(R.id.voiceSpinner);
                                    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item);
                                    fattsServices.getVoicesByLanguage(arrayAdapter,presenter.getUserVoiceLang(context,userData.getUsername()));
                                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    voiceSpinner.setAdapter(arrayAdapter);

                                    voiceSpinner.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
                                        @Override
                                        public void onChildViewAdded(View parent, View child) {
                                            voiceSpinner.setSelection(arrayAdapter.getPosition(presenter.getUserVoice(context,userData.getUsername())));
                                        }

                                        @Override
                                        public void onChildViewRemoved(View parent, View child) {}
                                    });

                                    final Spinner languageVoiceSpinner = (Spinner) promptView.findViewById(R.id.voiceLangSpinner);
                                    final ArrayAdapter<String> languageVoiceSpinnerAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item);
                                    fattsServices.getLanguages(languageVoiceSpinnerAdapter);
                                    languageVoiceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    languageVoiceSpinner.setAdapter(languageVoiceSpinnerAdapter);

                                    languageVoiceSpinner.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
                                        @Override
                                        public void onChildViewAdded(View parent, View child) {
                                            languageVoiceSpinner.setSelection(languageVoiceSpinnerAdapter.getPosition(presenter.getUserVoiceLang(context,userData.getUsername())));
                                        }
                                        @Override
                                        public void onChildViewRemoved(View parent, View child) {}
                                    });

                                    languageVoiceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            fattsServices.getVoicesByLanguage(arrayAdapter,languageVoiceSpinner.getSelectedItem().toString());
                                        }
                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {
                                        }
                                    });

                                    alertDialogBuilder.setNegativeButton("Blocca", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ArrayList<String> toRemove = new ArrayList<String>();
                                            toRemove.add(userClicked);
                                            presenter.deleteFriends(adapter,context,toRemove);
                                            dialog.cancel();
                                        }
                                    });

                                    alertDialogBuilder.setPositiveButton("Aggiorna", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            presenter.setContactVoice(context,userClicked,voiceSpinner.getSelectedItem().toString(),languageVoiceSpinner.getSelectedItem().toString());
                                            dialog.cancel();
                                        }
                                    });
                                    // create an alert dialog
                                    AlertDialog alert = alertDialogBuilder.create();
                                    alert.show();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else{
                    ArrayList<String> ids = new ArrayList<String>();
                    ids.add(((TextView)view).getText().toString());
                    presenter.createChat(context,ids);
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
                // Capture ListView item click
                listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener(){

                    @Override
                    public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                        if(abState) {
                            mode.setTitle("Crea chat");
                            mode.getMenuInflater().inflate(R.menu.address_book_create_menu, menu);
                        }else{
                            mode.setTitle("Elimina contatti");
                            mode.getMenuInflater().inflate(R.menu.address_book_delete_menu, menu);
                        }
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                        if (item.getItemId() == R.id.action_create_chat){
                            // QUI CREO LA NUOVA CHAT
                            ArrayList<String> ids = new ArrayList<>();
                            SparseBooleanArray selected = adapter.getSelectedItemsIds();
                            int size = selected.size();
                            for(byte i=0;i<size;i++){
                                if(selected.valueAt(i)){
                                    ids.add(adapter.getItem(selected.keyAt(i)));
                                }
                            }
                            presenter.createChat(context,ids);
                            // Close CAB (Contextual Action Bar)
                            mode.finish();
                            return true;
                        }
                        if(item.getItemId() == R.id.action_delete_friends){
                            // QUI CANCELLO GLI UTENTI SELEZIONATI
                            ArrayList<String> ids = new ArrayList<>();
                            SparseBooleanArray selected = adapter.getSelectedItemsIds();
                            int size = selected.size();
                            for(byte i=0;i<size;i++){
                                if(selected.valueAt(i)){
                                    ids.add(adapter.getItem(selected.keyAt(i)));
                                }
                            }
                            presenter.deleteFriends(adapter,context,ids);
                            // Close CAB (Contextual Action Bar)
                            mode.finish();
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public void onDestroyActionMode(android.view.ActionMode mode) {}

                    @Override
                    public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
                        adapter.toggleSelection(position);
                    }
                });
                return false;
            }
        });
    }
    @Override
    public void onBackPressed() {
        if ( listView.getCheckedItemCount() == 0) {
            Intent intent = new Intent(AddressBookActivity.this, ChatListActivity.class);
            AddressBookActivity.this.startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
