package com.mtag.app.muaythaiathletesguide;

/**
 * Created by Rachel on 2/2/2018.
 */

public class User {
    public String id;
    public String userName;
    public String email;
    public String password;

    public User(){}

    public User(String id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getId() { return id; }
    public String getUserName() { return userName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password + "]";
    }
}