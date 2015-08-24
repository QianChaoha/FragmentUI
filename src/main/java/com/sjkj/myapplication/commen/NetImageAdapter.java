package com.sjkj.myapplication.commen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.sjkj.myapplication.R;

import java.util.List;

/**
 * Created by QianChao on 2015/8/24.
 */
public class NetImageAdapter extends RecyclerView.Adapter {
    private List<String> imageList;
    private Context context;
    private ImageLoader imageLoader;

    public NetImageAdapter(List<String> imageList, Context context, ImageLoader imageLoader) {
        this.context = context;
        this.imageList = imageList;
        this.imageLoader = imageLoader;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_img, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.itemView.setImageUrl(imageList.get(position), imageLoader);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        public NetworkImageView itemView;

        public MyHolder(View view) {
            super(view);
            NetworkImageView itemView= (NetworkImageView) view.findViewById(R.id.img);
            this.itemView = itemView;
        }
    }
}
