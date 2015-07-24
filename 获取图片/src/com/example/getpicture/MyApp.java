package com.example.getpicture;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Application;

public class MyApp extends Application {
@Override
public void onCreate() {
	super.onCreate();
	ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
}
}
