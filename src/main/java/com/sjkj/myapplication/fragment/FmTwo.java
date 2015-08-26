package com.sjkj.myapplication.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.android.volley.Request;
import com.sjkj.myapplication.R;
import com.sjkj.myapplication.base.BaseFragment;
import com.sjkj.myapplication.commen.NetImageAdapter;
import com.sjkj.myapplication.data.ServerBaseResult;
import com.sjkj.myapplication.data.ShopNearby;
import com.sjkj.myapplication.interfaces.NetRequestResult;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class FmTwo extends BaseFragment {
    private String url = "http://akkacn.org:7000/app/user/nearby_v1.1?lat=31.234381&lng=121.473598&shopTypeId=&keyword=&category=0&start=0&limit=0";
    private RecyclerView mRecyclerView;


    @Override
    protected int getLayoutId() {
        return R.layout.fm_two;
    }

    @Override
    protected void initView(View view) {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        JSONObject param = new JSONObject();
        try {
            param.put("lat", "31.234381");
            param.put("lng", "121.473598");
            param.put("shopTypeId", "");
            param.put("keyword", "");
            param.put("category", "0");
            param.put("start", "0");
            param.put("limit", "0");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        netJsonRequest.netJsonRequest(Request.Method.GET, url, param, ServerBaseResult.class,
                new NetRequestResult<ServerBaseResult>() {
                    @Override
                    public void onResponse(ServerBaseResult result) {
                        List<ShopNearby> shopNearbies = result.getData();
                        System.out.println("shopNearbies" + shopNearbies.size());
                        if (shopNearbies != null && shopNearbies.size() > 0) {
                            List<String> list = new ArrayList<String>();
                            for (ShopNearby shopNearby : shopNearbies) {
                                list.add(shopNearby.getShopLogo());
                            }
                            mRecyclerView.setAdapter(new NetImageAdapter(list, getActivity(), getDefaultImageLoader()));
                            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                        }
                    }
                }, null);
    }
}
