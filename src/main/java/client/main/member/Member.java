package client.main.member;

import java.io.Serializable;

public class Member implements Serializable {
    private String id;
    private String password;
    private String nickName;
    private int winNum;
    private int loseNum;

    public Member(String id, String password, String nickName) {
        this.id = id;
        this.password = password;
        this.nickName = nickName;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }

    public int getWinNum() {
        return winNum;
    }

    public int getLoseNum() {
        return loseNum;
    }
}
