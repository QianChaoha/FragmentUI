package com.sjkj.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sjkj.myapplication.R;
import com.sjkj.myapplication.base.BaseRcyAdapter;
import com.sjkj.myapplication.util.Tools;

import java.util.List;

/**
 * Created by Administrator on 2015/8/13 0013.
 */
public class AdpRcy extends BaseRcyAdapter {
    /**
     * 初始化
     *
     * @param context
     * @param mDatas
     */
    public AdpRcy(Context context, List<String> mDatas) {
        super(context, mDatas);
    }
    @Override
    public int LayoutId() {
        return R.layout.item_img;
    }
    @Override
    protected void setViewData(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder= (ViewHolder) viewHolder;
        ImageLoader.getInstance().displayImage((String) mDatas.get(position),holder.iv, Tools.options);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = getView(viewGroup);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    /**
     * 相应的viewholder类
     * */
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv= (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
