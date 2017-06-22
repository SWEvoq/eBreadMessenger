package swevoq.ebread.com.Libraries.FATTSLib;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.widget.TextView;

import swevoq.ebread.com.Chat.Model.Settings.TextSettings;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

/**
 * Created by Teslaru Nicolae on 27/04/2017.
 */

public class PlayerBread {
    private static PlayerBread mInstance = null;

    private MediaPlayer player;
    private Handler handler;
    private TextView lastView;

    private PlayerBread(){
        player = new MediaPlayer();
        handler = new Handler();
        lastView = null;
    }

    public static PlayerBread getInstance(){
        if(mInstance == null)
                mInstance = new PlayerBread();
        return mInstance;
    }

    public MediaPlayer getPlayer(){
            return player;
        }

    public void setPlayer(MediaPlayer mediaPlayer) {
        player = mediaPlayer;
    }

    public void killCurrentPlayer() {
        if(player!=null){
            try {
                player.stop();
            }catch(IllegalStateException e){
                e.printStackTrace();
            }
            player.release();
            handler.removeCallbacksAndMessages(null);
            if (lastView != null) {
                if (!lastView.getText().toString().isEmpty()) {
                    Spannable stringToSpan = new SpannableString(lastView.getText().toString().replace("\\s", ""));
                    stringToSpan.setSpan(new BackgroundColorSpan(Color.WHITE), 0, 0, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lastView.setText(stringToSpan);
                }
            }
        }
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public TextView getLastView() {
        return lastView;
    }

    public void setLastView(TextView lastView) {
        this.lastView = lastView;
    }
}
