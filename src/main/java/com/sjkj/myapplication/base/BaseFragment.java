package com.sjkj.myapplication.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Field;

public abstract class  BaseFragment extends Fragment {
    protected RequestQueue requestQueue;
    private ProgressDialog mProgressDialog;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != mView) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (null != parent) {
                parent.removeView(mView);
            }
        } else {
            mView = inflater.inflate(getLayoutId(), null);
        }
        requestQueue = Volley.newRequestQueue(getActivity());
        mProgressDialog = new ProgressDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
        initView(mView);
        initData();
        return mView;
    }
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
     * 与activity UI交互
     * 或
     * 同一activity中的其他fragment UI交互时，重写此方法
     * simple: Button btn=getActivity().findViewById(R.id.btn);
     * */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 布局ID
     * @return  layoutID
     */
    protected abstract int getLayoutId();
    /**
     * 初始化布局
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * Toast提醒
     * */
    protected void showToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
    /**
     * 显示/隐藏 等待框
     * */
    protected void showProgress(){
        mProgressDialog.show();
    }
    protected void dismissProgress(){
        mProgressDialog.dismiss();
    }

    /***
     * 当解除与activity关联时
     * 解决fragment嵌套fragment出现的问题：no activity
     * */
    @Override
    public void onDetach() {
        super.onDetach();
        try {
            //参数是固定写法
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
