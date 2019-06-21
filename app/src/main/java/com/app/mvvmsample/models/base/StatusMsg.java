package com.app.mvvmsample.models.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusMsg {
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("msg")
    @Expose
    public String msg;
}
