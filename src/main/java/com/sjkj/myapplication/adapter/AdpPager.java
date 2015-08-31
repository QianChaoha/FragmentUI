package com.sjkj.myapplication.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AdpPager extends FragmentPagerAdapter {

	private final Context mContext;
    private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();


    static final class TabInfo {
        private final Class<?> mClss;
        private final Bundle mArgs;
        TabInfo(Class<?> aClass, Bundle args) {
            mClss = aClass;
            mArgs = args;
        }
    }

    public AdpPager(FragmentActivity activity) {
        super(activity.getSupportFragmentManager());
        mContext = activity;
    }
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }
    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public Fragment getItem(int position) {
        TabInfo info = mTabs.get(position);
        return Fragment.instantiate(mContext, info.mClss.getName(),
                info.mArgs);
    }

    public void addTab(Class<?> clss, Bundle args) {
        TabInfo info = new TabInfo(clss, args);
        mTabs.add(info);
        notifyDataSetChanged();
    }
}
