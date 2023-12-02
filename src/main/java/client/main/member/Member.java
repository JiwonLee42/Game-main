package client.main.member;

import java.io.Serializable;

public class Member implements Serializable {
    private String id;
    private String password;
    private String nickName;
    private int winNum;
    private int loseNum;
    private int codeNow;

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

    public int getCodeNow() {
        return codeNow;
    }

    public void setCodeNow(int codeNow) {
        this.codeNow = codeNow;
    }
}
