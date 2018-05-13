package com.mtag.app.muaythaiathletesguide;

public class User {
    public String id;
    public String userName;
    public String email;
    public String password;
    public String rank;

    public User(){}

    public User(String id, String userName, String email, String password, String rank) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.rank = rank;
    }

    public String getId() { return id; }
    public String getUserName() { return userName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRank() { return rank; }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password + ", rank=" + rank + "]";
    }
}