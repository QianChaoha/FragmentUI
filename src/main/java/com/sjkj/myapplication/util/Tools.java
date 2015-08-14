package com.sjkj.myapplication.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.AbsListView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.sjkj.myapplication.R;

/**
 * Created by Administrator on 2015/8/13 0013.
 */
public class Tools {
    private static ImageLoaderConfiguration config=null;
    public static DisplayImageOptions options=null;
    public static AbsListView.OnScrollListener onScrollListener=null;
    public static void initDisplayImageOptions(){
        if(null!=options){
            return;
        }
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_pic_loading)//加载过程中显示的图片
                .showImageForEmptyUri(R.mipmap.ic_pic_loading)//加载内容为空显示的图片
                .showImageOnFail(R.mipmap.ic_pic_loading)//加载失败显示的图片
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).displayer(new FadeInBitmapDisplayer(388)).build();
    }
    public static void initImageLoader(Context context) {
        if(null!=config){
            return;
        }
        config = new ImageLoaderConfiguration.Builder(context)
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                .threadPriority(Thread.MAX_PRIORITY).denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);


    }

}
