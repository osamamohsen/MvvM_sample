package com.app.mvvmsample.models.login;

import com.app.mvvmsample.models.base.StatusMsg;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends StatusMsg {

    @SerializedName("data")
    @Expose
    public User data;
}
