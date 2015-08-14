package com.sjkj.myapplication.activity;

import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.sjkj.myapplication.R;
import com.sjkj.myapplication.adapter.AdpPager;
import com.sjkj.myapplication.base.BaseActivity;
import com.sjkj.myapplication.fragment.FmFour;
import com.sjkj.myapplication.fragment.FmOne;
import com.sjkj.myapplication.fragment.FmThree;
import com.sjkj.myapplication.fragment.FmTwo;

public class ActMain extends BaseActivity implements View.OnClickListener {
    private RadioButton mRb1, mRb2, mRb3, mRb4;
    private ViewPager mViewPager;
    private AdpPager mPagerAdapter;
    private TextView tvTitle;

    @Override
    protected void initData() {
        mPagerAdapter = new AdpPager(this);
        mViewPager
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                        changeTitle(position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });

        mPagerAdapter.addTab(FmOne.class, null);
        mPagerAdapter.addTab(FmTwo.class, null);
        mPagerAdapter.addTab(FmThree.class, null);
        mPagerAdapter.addTab(FmFour.class, null);
        mViewPager.setAdapter(mPagerAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_main;
    }


    @Override
    protected void initView() {
        mViewPager = (ViewPager) this.findViewById(R.id.viewPage);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        mRb1 = (RadioButton) findViewById(R.id.radio0);
        mRb2 = (RadioButton) findViewById(R.id.radio1);
        mRb3 = (RadioButton) findViewById(R.id.radio2);
        mRb4 = (RadioButton) findViewById(R.id.radio3);
        mRb1.setOnClickListener(this);
        mRb2.setOnClickListener(this);
        mRb3.setOnClickListener(this);
        mRb4.setOnClickListener(this);
    }


    /**
     * 主页面更换时更改标题文字
     *
     * @param checkId 对应的底栏按钮id
     */
    private void changeTitle(int checkId) {
        switch (checkId) {
            case 0:
                tvTitle.setText("one");
                mRb1.setChecked(true);
                break;
            case 1:
                tvTitle.setText("two");
                mRb2.setChecked(true);
                break;
            case 2:
                tvTitle.setText("three");
                mRb3.setChecked(true);
                break;
            case 3:
                tvTitle.setText("four");
                mRb4.setChecked(true);
                break;
            default:
                break;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio0:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.radio1:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.radio2:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.radio3:
                mViewPager.setCurrentItem(3);
                break;
            default:
                break;
        }
    }

    @Override
    protected void getMessage(Message msg) {

    }

    @Override
    protected void request() {

    };
}
