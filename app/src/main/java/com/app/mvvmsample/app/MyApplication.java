package com.app.mvvmsample.app;

import android.content.Context;
import android.content.res.Configuration;

import androidx.multidex.MultiDexApplication;

/**
 * Created by Akshay Raj on 7/17/2016.
 * Snow Corporation Inc.
 * www.snowcorp.org
 */
public class MyApplication extends MultiDexApplication {
    public static final String TAG = MyApplication.class.getSimpleName();

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }



    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
//        refWatcher = LeakCanary.install(this);
        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

}