package swevoq.ebread.com.Chat.Model.Profile;

import com.stfalcon.chatkit.commons.models.IUser;

/**
 * Created by Teslaru Nicolae on 23/03/2017.
 */

public class User implements IUser {
    private String id;
    private String username;
    private String name;
    private String surname;
    private String nickname;
    private String avatar;

    public User(){
        id = new String();
        username = new String();
        name = new String();
        surname = new String();
        nickname = new String();
        avatar = new String("http://www.eioba.org/files/user61571/a248626/white.jpg");
    }

    public User(String id, String username, String name, String surname, String nickname, String avatar){
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.avatar = avatar;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        if(nickname.isEmpty())
            return username;
        else return nickname;
    }

    public String getUsername(){
        return username;
    }

    public String getRealName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getNickname(){
        return nickname;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {}

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setRealName(String realName){
        this.name = realName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
