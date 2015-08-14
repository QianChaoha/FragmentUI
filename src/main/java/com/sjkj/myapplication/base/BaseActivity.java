package com.sjkj.myapplication.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.sjkj.myapplication.data.StaticData;
import com.sjkj.myapplication.util.SharePreference;
import com.sjkj.myapplication.util.Tools;


public abstract class BaseActivity extends FragmentActivity implements OnClickListener {
    private ProgressDialog mProgressDialog;
    private Context mContext;
    protected Bundle savedInstanceState;
    //volley请求队列
    protected RequestQueue requestQueue;
    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getMessage(msg);
        }
    };


    /***
     * Handler回调方法
     * */
    protected abstract void getMessage(Message msg);
    /**
     * 设置用户是否可以操作当前的页面
     * true为可以  false为不可以
     * 可用于某些重要数据加载时，限制用户操作，强制等待数据加载完成
     */
    protected boolean isTouchEvent = true;

    /**
     * 得到上下文
     */
    public Context getContext() {
        return mContext;
    }


    /**
     * 配置文件操作
     */
    protected SharePreference spUtil;

    /**
     * 布局ID
     * @return  layoutID
     */
    protected abstract int getLayoutId();

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tools.initImageLoader(this);
        Tools.initDisplayImageOptions();
        this.savedInstanceState = savedInstanceState;
        spUtil = new SharePreference(this, StaticData.SHAREPREFERENCENAME);
        mContext = this.getApplicationContext();
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            setContentView(layoutId);
        }
        mProgressDialog = new ProgressDialog(getContext(), AlertDialog.THEME_HOLO_LIGHT);
        /**
         * volley网络框架的使用
         * 请求队列加进mRequestQueue
         * */
        requestQueue = Volley.newRequestQueue(this);
        initView();
        initData();
    }

    public void onClick(View arg0) {
        // TODO Auto-generated method stub
    }
    /**
     * Toast提醒
     * */
    protected void showToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }



    /**
     * 网络请求统一放置该方法内
     */
    protected abstract void request();

    /**
     * 显示/隐藏 等待框
     * */
    protected void showProgress(){
        mProgressDialog.show();
    }
    protected void dismissProgress(){
        mProgressDialog.dismiss();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isTouchEvent=true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        //暂停这个队列的请求
        requestQueue.stop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestQueue.start();
    }

    @Override
    public void finish() {
        super.finish();
        //取消这个队列里的所有请求
        requestQueue.cancelAll(this);
        mProgressDialog.dismiss();
    }
    /**
     * 拦截触摸事件
     * */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isTouchEvent) {
            return super.dispatchTouchEvent(ev);
        } else {
            return false;
        }
    }

}
