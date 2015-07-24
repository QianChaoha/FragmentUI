package com.example.getpicture.utils;

import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public abstract class MyImgAdapterBaseAbs extends BaseAdapter{
	private static DisplayImageOptions options;
	private int nRes = -1;
	protected final DisplayImageOptions getDisplayImageOptions(final int nRes){
		if (options == null||this.nRes!=nRes) {
			this.nRes = nRes;
			options = new DisplayImageOptions.Builder()
			//������
			.showStubImage(nRes)
			//����Ϊ��ʱ
			.showImageForEmptyUri(nRes)
			//����ʧ��ʱ
			.showImageOnFail(nRes)
			//�ڴ滺��
			.cacheInMemory(false)
			//Ӳ���л���
			.cacheOnDisc(true).build();
		}
		return options;
	}
	
	public MyImgAdapterBaseAbs() {
		super();
	}
	
	protected final void getAsyncBitMap(final ImageView imgView, final String picUrl)
	{
		this.getAsyncBitMap(imgView, picUrl,com.example.getpicture.R.drawable.ic_launcher);//,R.drawable.default_img);
	}
	
	protected final void getAsyncBitMap(final ImageView imgView, final String picUrl,
			final int nRes) {
		ImageLoader.getInstance().displayImage(picUrl, imgView, getDisplayImageOptions(nRes));
	}
}
