package swevoq.ebread.com.Chat.Presenter.Chat;

import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import swevoq.ebread.com.Chat.Model.Chat.Message;
import swevoq.ebread.com.Chat.Model.Chat.TextMessage;
import swevoq.ebread.com.Chat.Model.Profile.User;
import swevoq.ebread.com.Chat.Model.Settings.TextSettings;
import swevoq.ebread.com.Libraries.FirebaseLib.FirebaseAccessPoint;

/**
 * Created by Teslaru Nicolae on 23/03/2017.
 */

public class ChatPresenter {
    private FirebaseAccessPoint firebase;

    public ChatPresenter(){
        firebase = new FirebaseAccessPoint();
    }

    public FirebaseUser getLoggedUser(){
        return firebase.getLoggedUser();
    }

    public Query getUserData(String id){
        return firebase.getUserData(id);
    }

    public void newMessage(String chatId,String text,User author){
        firebase.newMessage(chatId,text,author);
    }

    public void deleteSelectedMessages(String chatId,ArrayList<Message> selectedMessages,HashMap<String, ValueEventListener> mListenerMap) {
        firebase.deleteSelectedMessages(chatId,selectedMessages,mListenerMap);
    }

    public Query getMessages(String chatId) {
        return firebase.getMessages(chatId);
    }

    public void setTextViewStyle(TextView text, ViewGroup bubble) {
        TextSettings textSettings = firebase.getTextSettings(text.getContext());
        text.setTextColor(textSettings.getColorByName(textSettings.getTextColor()));
        bubble.setBackgroundColor(textSettings.getColorByName(textSettings.getBubbleColor()));
        text.setTextSize(textSettings.getFontSize());
        text.setTextScaleX(textSettings.getFontSpacing());
        //text.setLetterSpacing(textSettings.getFontSpacing());// API 21 or greater
        if(!textSettings.getTextFont().equals("Roboto")) {
            Typeface font = Typeface.createFromAsset(bubble.getContext().getAssets(), "fonts/" + textSettings.getTextFont() + ".ttf");
            text.setTypeface(font);
        }

    }
}
