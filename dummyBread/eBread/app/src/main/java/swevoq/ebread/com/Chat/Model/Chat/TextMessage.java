package swevoq.ebread.com.Chat.Model.Chat;

import com.stfalcon.chatkit.commons.models.IUser;

import java.util.Date;

import swevoq.ebread.com.Chat.Model.Profile.User;

/**
 * Created by Teslaru Nicolae on 22/03/2017.
 */

public class TextMessage implements Message {
    private String id,text;
    private Date createdAt;
    private User author;

    public TextMessage(){}

    public TextMessage(String id,String text,Date createdAt,User author){
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.author = author;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public IUser getUser() {
        return author;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }
}
