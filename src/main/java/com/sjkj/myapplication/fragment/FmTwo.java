package com.sjkj.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sjkj.myapplication.R;
import com.sjkj.myapplication.activity.ActMain;
import com.sjkj.myapplication.adapter.rcyAdp.BaseAdapterHelper;
import com.sjkj.myapplication.adapter.rcyAdp.QuickAdapter;
import com.sjkj.myapplication.data.ServerBaseResult;
import com.sjkj.myapplication.data.ShopNearby;
import com.sjkj.myapplication.http.NetJsonRequest;
import com.sjkj.myapplication.interfaces.NetRequestResult;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FmTwo extends Fragment {
    private String url = "http://akkacn.org:7000/app/user/nearby_v1.1?lat=31.234381&lng=121.473598&shopTypeId=&keyword=&category=0&start=0&limit=0";
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View make_view = inflater.inflate(R.layout.fm_two, null);
        mRecyclerView = (RecyclerView) make_view.findViewById(R.id.recyclerView);
        return make_view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NetJsonRequest request = new NetJsonRequest();
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

        request.NetJsonRequest(Request.Method.GET, url, null, new NetRequestResult() {
            @Override
            public void onResponse(Object object) {
                ServerBaseResult serverBaseResult= (ServerBaseResult) object;
                List<ShopNearby> shopNearbies = serverBaseResult.getData();
                System.out.println("shopNearbies" + shopNearbies.size());
                if (shopNearbies != null && shopNearbies.size() > 0) {
                    List<String> list = new ArrayList<String>();
                    for (ShopNearby shopNearby : shopNearbies) {
                        list.add(shopNearby.getShopLogo());
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
            }
        }, null, ServerBaseResult.class, ((ActMain) getActivity()).getRequestQueue());

    }
}
