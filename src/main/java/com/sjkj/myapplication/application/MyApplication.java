package com.sjkj.myapplication.application;

import android.app.Application;

import com.sjkj.myapplication.util.Tools;

/**
 * Created by QianChao on 2015/8/17.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Tools.initImageLoader(this);
        Tools.initDisplayImageOptions();
    }
}
