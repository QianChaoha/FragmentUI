package com.sjkj.myapplication.fragment.childFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sjkj.myapplication.R;


public class FmOneOne extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View collection_view = null;
        collection_view = View.inflate(getActivity(), R.layout.fm_one_one, null);


        collection_view = View.inflate(getActivity(), R.layout.empty, null);
        return collection_view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
