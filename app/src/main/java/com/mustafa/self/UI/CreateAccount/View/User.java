package com.mustafa.self.UI.CreateAccount.View;

public class User {
    private String userName;
    private String userId;

    private static User instance;

    public static User getInstance() {
        if (instance == null)
            instance = new User();
        return instance;

    }

    public User(){}


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
