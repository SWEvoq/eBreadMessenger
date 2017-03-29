package swevoq.ebread.com.Chat.View.Chat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import swevoq.ebread.com.Chat.Model.Chat.Message;
import swevoq.ebread.com.Chat.Model.Chat.TextMessage;
import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.Presenter.Chat.ChatPresenter;
import swevoq.ebread.com.Libraries.FATTSLib.AudioRequest;
import swevoq.ebread.com.R;

public class ChatActivity extends AppCompatActivity implements MessagesListAdapter.SelectionListener, Response.Listener<byte[]>, Response.ErrorListener {
    private MessagesList messagesList;
    private MessagesListAdapter<Message> adapter;
    private MessageInput input;
    private int selectionCount;
    private Menu menu;
    private ChatPresenter presenter;
    AudioRequest request;
    int count;
    private RequestQueue mRequestQueue ;
    Context context;
    private TextView activeBubble;
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        //presenter = new ChatPresenter(); da togliere gli static

        messagesList = (MessagesList) findViewById(R.id.messagesList);
        initMessagesAdapter();

        mRequestQueue = Volley.newRequestQueue(getApplicationContext(), new HurlStack());

        context = this;

        input = (MessageInput) findViewById(R.id.input);
        input.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {
                // Inserimento messaggio nuovo -> Inserire nel contenitore + Chiamate firebase tramite presenter
                adapter.addToStart(new TextMessage(UUID.randomUUID().toString(),input.toString(),new Date(),new User(UUID.randomUUID().toString(),"asd","sdasd","asf","asd","https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg")), true);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.chat_actions_menu, menu);
        onSelectionChanged(0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // menu nella chat
        // Cancellazione è da fare nel container e su firebase richiamandolo dal presenter
        switch (item.getItemId()) {
            case R.id.action_delete:
                adapter.deleteSelectedMessages();
                break;
        }
        return true;
    }

    @Override
    public void onSelectionChanged(int count) {
        this.selectionCount = count;
        menu.findItem(R.id.action_delete).setVisible(count > 0);
    }

    @Override
    public void onBackPressed() {
        if (selectionCount == 0) {
            super.onBackPressed();
        } else {
            adapter.unselectAllItems();
        }
    }

    private void initMessagesAdapter() {
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Picasso.with(ChatActivity.this).load(url).into(imageView);
            }
        };

        adapter = new MessagesListAdapter<>("0", imageLoader);
        adapter.enableSelectionMode(this);

        adapter.addToStart(new TextMessage("asd","Ciao mondo crudele.",new Date(),new User("sadas","asdad","asdasd","asd","asdas","https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg")), false);

        adapter.setLoadMoreListener(new MessagesListAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (totalItemsCount < 50) {
                    loadMessages();
                }
            }
        });

        adapter.setOnMessageClickListener(new MessagesListAdapter.OnMessageClickListener<Message>() {
            @Override
            public void onMessageClick(Message message) {
                verifyStoragePermissions(ChatActivity.this);




                String[] temp =   message.getText().split(" ");
                String req ="";

                for(int i=0;i<temp.length;i++)
                    req+=temp[i]+"+";

                String mUrl="http://fic2fatts.tts.mivoq.it/process?INPUT_TEXT="+req+"&INPUT_TYPE=TEXT&OUTPUT_TYPE=AUDIO&AUDIO=WAVE_FILE&LOCALE=it";
                request = new AudioRequest(Request.Method.GET, mUrl, ChatActivity.this, ChatActivity.this, null);
                mRequestQueue.add(request);
            }
        });


        messagesList.setAdapter(adapter);
    }

    public static void open(Activity activity) {
        Intent intent = new Intent(activity, ChatActivity.class);
        activity.startActivity(intent);
    }

    private void loadMessages() {
        new Handler().postDelayed(new Runnable() { //imitation of internet connection
            @Override
            public void run() {
                ArrayList<Message> messages = presenter.getMessages();
                adapter.addToEnd(messages, true);
            }
        }, 1000);
    }

    @Override
    public void onResponse(byte[] response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String url = "http://fic2fatts.tts.mivoq.it/process?INPUT_TEXT=Ciao+mondo+crudele.&INPUT_TYPE=TEXT&OUTPUT_TYPE=LIPSYNC&LOCALE=it&AUDIO=WAVE_FILE";
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, (String)null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        playSyncAudio(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
        mRequestQueue.add(jsObjRequest);
        try {
            if (response!=null) {
                try{
                    long lenghtOfFile = response.length;
                    Log.d("MyApp","Comincio a fare cose");
                    //covert reponse to input stream
                    InputStream input = new ByteArrayInputStream(response);
                    //File path = Environment.getExternalStorageDirectory();
                    //File file = new File(path, "/result.wav");
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/result.wav");
                    map.put("resume_path", file.toString());
                    BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
                    byte data[] = new byte[2048];

                    long total = 0;

                    while ((count = input.read(data)) != -1) {
                        total += count;
                        output.write(data, 0, count);
                    }

                    output.flush();

                    output.close();
                    input.close();
                    Log.d("MyApp","Finito Di Salvare Il FIle");
                }catch(IOException e){
                    e.printStackTrace();

                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.d("KEY_ERROR", "UNABLE TO DOWNLOAD FILE");
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("KEY_ERROR", "UNABLE TO DOWNLOAD FILE. ERROR:: "+error.getMessage());
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public void playSyncAudio(JSONObject syncInfo){
        // riprodizione file
        Log.d("MyApp","Mi appresto a riprodurre l'audio");
        MediaPlayer player = MediaPlayer.create(context, Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/result.wav"));
        player.start();
        int token = 0;
        try {
            while(player.isPlaying() && token < syncInfo.getJSONArray("tokens").length() ) {
                    Log.d("MyApp", " " + syncInfo.getJSONArray("tokens").getJSONObject(token).getDouble("end") * 1000 + "-" + player.getCurrentPosition());
                    if (syncInfo.getJSONArray("tokens").getJSONObject(token).getDouble("end") * 1000 <= player.getCurrentPosition()) {
                        Log.d("MyAoo","Entro ed Esco con Token="+token);
                        if (token < syncInfo.getJSONArray("tokens").length()) {
                            token+=1;
                            // evidenzio test
                            //Log.d("MyApp", "Evidenzio caratteri da: " + syncInfo.getJSONArray("tokens").getJSONObject(token).getInt("char_start") + " a:" + syncInfo.getJSONArray("tokens").getJSONObject(token).getInt("char_end"));
                        }
                    }
            }
            // Deevidenzio tutto quanto
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
