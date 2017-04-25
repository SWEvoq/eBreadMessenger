package swevoq.ebread.com.Libraries.FirebaseLib;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import swevoq.ebread.com.Chat.Model.Utility.AddressBook;
import swevoq.ebread.com.R;

/**
 * Created by Nicolae on 09/04/2017.
 */

public class AddressBookHandler {

    public void addUserToAddressBook(FirebaseDatabase database, final AddressBook adapter, final Context context, final List<String> friends, final String username) {
        final int notFound = friends.size();
        database.getReference("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot user:dataSnapshot.getChildren()){
                    if(user.child("username").getValue(String.class).equals(username)){
                        if(notFriends(friends,username)) {
                            friends.add(username);
                            adapter.notifyDataSetChanged();
                            updateAddressBook(context, username);
                            Toast.makeText(context, "Hai un nuovo amico!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                if(notFound==friends.size())
                    Toast.makeText(context, "La persona che hai cercato di aggiungere non è un utente eBread!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    private boolean notFriends(List<String> friends,String username){
        boolean result = true;
        for(int i=0;i<friends.size() && result;i++)
            if(friends.get(i).toString().equals(username))
                result = false;
        return result;
    }

    public void updateAddressBook(Context context,String username){
        JSONObject jsonAddressBook = readAddressBook(context);

        if(jsonAddressBook!=null){
            try {
                jsonAddressBook.getJSONObject(FirebaseAuth.getInstance().getCurrentUser().getUid()).put(username," ");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            jsonAddressBook = new JSONObject();
            JSONObject newFriend = new JSONObject();
            try {
                newFriend.put(username, " ");
                jsonAddressBook.put(FirebaseAuth.getInstance().getCurrentUser().getUid(),newFriend);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //DA QUI IN POI SCRIVO SUL FILE.
        try {
            FileOutputStream file = context.openFileOutput("addressbook.txt",context.MODE_PRIVATE);
            file.write(jsonAddressBook.toString().getBytes());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject readAddressBook(Context context){
        JSONObject result = null;
        try {
            FileInputStream file = context.openFileInput("addressbook.txt");
            int size = file.available();
            byte[] buffer = new byte[size];
            file.read(buffer);
            file.close();
            String myJson = new String(buffer, "UTF-8");
            result = new JSONObject(myJson);
        } catch (IOException | JSONException x ) {
            x.printStackTrace();
        }
        return result;
    }

    public ArrayList<String> getFriends(Context context){
        ArrayList<String> result = new ArrayList<>();
        JSONObject jsonAddressBook = readAddressBook(context);
        if(jsonAddressBook!=null){
            try {
                JSONObject jsonUserFriends = jsonAddressBook.getJSONObject(FirebaseAuth.getInstance().getCurrentUser().getUid());
                Iterator<String> iterator = jsonUserFriends.keys();
                while (iterator.hasNext()) {
                    result.add(iterator.next());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        return result;
    }

    public void deleteFriends(AddressBook adapter, Context context, ArrayList<String> ids) {
        JSONObject jsonAddressBook = readAddressBook(context);
        if(jsonAddressBook!=null){
            JSONObject jsonUserFriends = null;
            try {
                jsonUserFriends = jsonAddressBook.getJSONObject(FirebaseAuth.getInstance().getCurrentUser().getUid());
                for(int i=0;i<ids.size();i++){
                    adapter.remove(ids.get(i));
                    jsonUserFriends.remove(ids.get(i));
                }
                adapter.notifyDataSetChanged();
                FileOutputStream file = context.openFileOutput("addressbook.txt",context.MODE_PRIVATE);
                file.write(jsonAddressBook.toString().getBytes());
                file.close();
            } catch (JSONException|IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setContactVoice(Context context,String userClicked, String voiceName) {
        JSONObject jsonAddressBook = readAddressBook(context);
        if(jsonAddressBook!=null){
            try {
                JSONObject jsonUserFriends = jsonAddressBook.getJSONObject(FirebaseAuth.getInstance().getCurrentUser().getUid());
                jsonUserFriends.put(userClicked,voiceName);
                FileOutputStream file = context.openFileOutput("addressbook.txt",context.MODE_PRIVATE);
                Log.d("Myapp",jsonAddressBook.toString());
                file.write(jsonAddressBook.toString().getBytes());
                file.close();
            } catch (JSONException|IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getUserVoice(Context context,String id) {
        JSONObject jsonAddressBook = readAddressBook(context);
        String result = "";
        if(jsonAddressBook!=null){
            try {
                result = jsonAddressBook.getJSONObject(FirebaseAuth.getInstance().getCurrentUser().getUid()).getString(id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(result.equals(""))
            return new FirebaseAccessPoint().getVoiceSettings(context).getVoiceName();
        else
            return result;
    }
}
