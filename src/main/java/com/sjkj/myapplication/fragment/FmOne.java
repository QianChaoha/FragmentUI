package com.sjkj.myapplication.fragment;

import android.graphics.Color;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sjkj.myapplication.R;
import com.sjkj.myapplication.base.BaseFragment;
import com.sjkj.myapplication.fragment.childFragment.FmOneOne;
import com.sjkj.myapplication.fragment.childFragment.FmOneTwo;

public class FmOne extends BaseFragment {
    private ViewPager vp;
    private TextView tv_collection, tv_make;
    private ImageView line;
    private int currentIndex;


    @Override
    protected int getLayoutId() {
        return R.layout.fm_one;
    }

    @Override
    protected void initView(View view) {
        vp = (ViewPager) view.findViewById(R.id.vp);
        tv_collection = (TextView) view.findViewById(R.id.my_collection_tv);
        tv_make = (TextView) view.findViewById(R.id.my_make_tv);
        line = (ImageView) view.findViewById(R.id.line);
        tv_collection.setTextColor(Color.rgb(248, 82, 0));
        tv_make.setTextColor(Color.rgb(99, 99, 99));
    }

    @Override
    protected void initData() {
        initTabLineWidth();
        setListener();
        initViewPager();
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                //resetTextView();
                switch (arg0) {
                    case 0:
                        tv_collection.setTextColor(Color.rgb(248, 82, 0));
                        tv_make.setTextColor(Color.rgb(99, 99, 99));
                        break;
                    case 1:
                        tv_make.setTextColor(Color.rgb(248, 82, 0));
                        tv_collection.setTextColor(Color.rgb(99, 99, 99));
                        break;
                }
                currentIndex = arg0;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) line
                        .getLayoutParams();
                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景： 记3个页面, 从左到右分别为0,1,2 0->1; 1->2; 2->1;
                 * 1->0
                 */
                int margin = screenWidth / 2;
                if (currentIndex == 0 && arg0 == 0)// 0->1
                {
                    lp.leftMargin = (int) (arg1 * (screenWidth / 2)
                            + currentIndex * (screenWidth / 2) + 50);
                } else if (currentIndex == 1 && arg0 == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - arg1) * (screenWidth / 2) + currentIndex
                            * (screenWidth / 2 + 50));
                } else if (currentIndex == 1 && arg0 == 1) // 1->2
                {
                    lp.leftMargin = (int) (arg1 * (screenWidth * 1.0 / 2) + currentIndex
                            * (screenWidth / 2 + 50));
                }
                line.setLayoutParams(lp);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    private void initViewPager() {
        vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int arg0) {
                Fragment fr = null;
                if (arg0 == 0) {
                    fr = new FmOneOne();
                } else if (arg0 == 1) {
                    fr = new FmOneTwo();
                }
                return fr;
            }
        });
    }

    private int screenWidth;

    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getActivity().getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) line
                .getLayoutParams();
        lp.width = screenWidth / 2 - 100;
        line.setLayoutParams(lp);
    }

    private void setListener() {
        tv_collection.setOnClickListener(new Myclick(0));
        tv_make.setOnClickListener(new Myclick(1));
    }

    class Myclick implements OnClickListener {
        int index;

        public Myclick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            //选项卡被点击，设置响应页面
            vp.setCurrentItem(index);
        }
    }


}
