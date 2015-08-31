package com.sjkj.myapplication.data;

import com.sjkj.myapplication.http.parser.BackResult;

import java.util.List;

public  class ServerBaseResult extends BackResult {

  private List<ShopNearby> data;

    public List<ShopNearby> getData() {
        return data;
    }

    public void setData(List<ShopNearby> data) {
        this.data = data;
    }
}
