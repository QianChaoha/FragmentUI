package com.sjkj.myapplication.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2015/8/13 0013.
 */
public abstract class BaseRcyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<?> mDatas;
    protected  Context mContext;
    protected LayoutInflater mInflater;
    private onItemClickListener mOnItemClickListener;
    /**
     * 初始化
     */
    public BaseRcyAdapter(Context context,List<?> mDatas) {
        this.mContext=context;
        this.mDatas=mDatas;
        mInflater = LayoutInflater.from(mContext);

    }
    public interface onItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    /**
     * 添加item点击监听
     */
    public void setOnItemClickListener(BaseRcyAdapter.onItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * 指定布局ItemLayout
     */
    public abstract int LayoutId();

    /**
     * 删除对应的item
     */
    public void removedItem(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
    /**
     * 绑定viewHolder中的数据
     */
    protected abstract void setViewData(RecyclerView.ViewHolder viewHolder, int position);

    /**
     * 得到数据总量
     */
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public View getView(ViewGroup viewGroup){
       return  mInflater.inflate(LayoutId(), viewGroup, false);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int i) {
        setViewData(viewHolder, i);
        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(viewHolder.itemView, viewHolder.getAdapterPosition());
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(viewHolder.itemView, viewHolder.getAdapterPosition());
                    return true;
                }
            });
        }
    }

}
