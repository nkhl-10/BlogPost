package com.example.postblog.Method;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResult {

    @SerializedName("Post")
    @Expose
    private List<User> User;

    public List<User> getPost() {
        return User;
    }

    public void setPost(List<User> user) {
        this.User = user;
    }

}
