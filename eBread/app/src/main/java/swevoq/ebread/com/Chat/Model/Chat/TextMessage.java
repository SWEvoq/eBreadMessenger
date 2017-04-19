package swevoq.ebread.com.Chat.Model.Chat;

import com.stfalcon.chatkit.commons.models.IUser;

import java.util.Date;

import swevoq.ebread.com.Chat.Model.Profile.User;

/**
 * Created by Teslaru Nicolae on 23/03/2017.
 */

public class TextMessage implements Message {
    private String id;
    private String text;

    private Date createdAt;
    private User author;

    public TextMessage() {
        id = new String();
        text = new String();
        createdAt = new Date();
        author = new User();
    }

    public TextMessage(String id, String text, Date createdAt, User author) {
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

    public void setId(String id){
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", author=" + author +
                '}';
    }
}
