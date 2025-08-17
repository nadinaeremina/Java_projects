package org.example;

public class User {
    private int id;
    private String nickname;
    private boolean isActive;
    private int bonusBalance;

    public User(int id, String nickname, int bonusBalance) {
        this.id = id;
        this.nickname = nickname;
        this.isActive = true;
        this.bonusBalance = bonusBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getBonusBalance() {
        return bonusBalance;
    }

    public void setBonusBalance(int bonusBalance) {
        this.bonusBalance = bonusBalance;
    }

    @Override
    public String toString() {
        return id + " - " + nickname + " - " + bonusBalance + " - " + isActive;
    }
}
