package com.sjkj.myapplication.fragment;

import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sjkj.myapplication.R;
import com.sjkj.myapplication.adapter.rcyAdp.BaseAdapterHelper;
import com.sjkj.myapplication.adapter.rcyAdp.QuickAdapter;
import com.sjkj.myapplication.base.BaseFragment;
import com.sjkj.myapplication.data.Msg;
import com.sjkj.myapplication.view.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class FmFour extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void getMessage(Message msg) {
        switch (msg.what){
            case Msg.SUCCESS:
                mSwipeRefreshLayout.setRefreshing(false);
//                Snackbar.make(getView(), "下拉刷新成功", Snackbar.LENGTH_SHORT).show();

                break;
            case Msg.ERROR:
                mSwipeRefreshLayout.setLoading(false);
//                Snackbar.make(getView(), "上拉加载成功", Snackbar.LENGTH_SHORT).show();

                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fm_four;
    }

    @Override
    protected void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
    }

    /**
     * simple for RecyclerView
     */
    @Override
    protected void initData() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setOnLoadListener(this);
        mSwipeRefreshLayout.setColor(android.R.color.holo_green_dark,
                android.R.color.white,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);

        final List list = new ArrayList();
        for (int i = 0; i < 30; i++) {
            list.add(url);
        }
        final QuickAdapter adapter = new QuickAdapter(getActivity(), R.layout.item_img, list) {
            @Override
            protected void convert(BaseAdapterHelper helper, Object item) {
                ImageLoader.getInstance().displayImage((String) item, helper.getImageView(R.id.img));
            }
        };

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    }

    private static String url = "http://img4.duitang.com/uploads/item/201507/21/20150721125424_5skKB.thumb.224_0.jpeg";

    /**
     * 上拉加载
     */
    @Override
    public void onLoad() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Message msg = handler.obtainMessage();
                    msg.what = Msg.ERROR;
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Message msg = handler.obtainMessage();
                    msg.what = Msg.SUCCESS;
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
