package swevoq.ebread.com.Chat.Model.Profile;

import com.stfalcon.chatkit.commons.models.IUser;

/**
 * Created by Teslaru Nicolae on 22/03/2017.
 */

public class User implements IUser {
    private String username, id, name, surname, nickname, avatar;

    public User(){}

    public User(String id,String username,String nickname,String name,String surname,String avatar){
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getRealName(){
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String getName() {
        if(nickname.isEmpty()) {
            return username;
        } else return nickname;
    }

    @Override
    public String getAvatar() {
        if(avatar.isEmpty()){
            return null;
        }else return avatar;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String username) {
        this.username = username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRealName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
