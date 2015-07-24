package com.example.getpicture;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.getpicture.utils.MyImgAdapterBaseAbs;

public class AddPictureGrideView extends GridView {
	private Bitmap bitmap;
	public static List<Object> bitmaps = new ArrayList<Object>();
	private Activity context = (Activity) getContext();
	private boolean hasPic = false;
	private MyAdapter myAdapter;

	public AddPictureGrideView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public AddPictureGrideView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public AddPictureGrideView(Context context) {
		super(context);
		init();
	}

	public void refresh() {
		if (ImageGridAct.selectPics.size() > 0) {
			hasPic=true;
			myAdapter.notifyDataSetChanged();
//			Collection<FileItem> c = ImageGridAct.selectPics.values();
//			Iterator<FileItem> it = c.iterator();
//			Iterator iter = ImageGridAct.selectPics.entrySet().iterator();
//			while (iter.hasNext()) {
//				Map.Entry entry = (Map.Entry) iter.next();
////				selectPics.add(((FileItem) entry.getValue()).getFileLocalPath());
//			}
		}
	}

	public void init() {
		bitmaps.clear();
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_addpic_unfocused);
		bitmaps.add(bitmap);
		myAdapter = new MyAdapter();
		setAdapter(myAdapter);
		setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (position == myAdapter.getCount()-1) {
					new PopupWindows(context, view);
				}
			}
		});
	}

	class MyAdapter extends MyImgAdapterBaseAbs {

		@Override
		public int getCount() {
			if (hasPic) {
				return bitmaps.size()+ImageGridAct.selectPics.size();
			}else {
				return bitmaps.size();
			}
		
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = View.inflate(context, R.layout.picture_item, null);
				holder.imageView = (ImageView) convertView.findViewById(R.id.iv);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			if (hasPic) {
				if (position<ImageGridAct.selectPics.size()) {
					this.getAsyncBitMap(holder.imageView, ImageGridAct.selectPics.get(position));
				}else {
					holder.imageView.setImageBitmap(bitmap);
				}
			}else {
				holder.imageView.setImageBitmap(bitmap);
			}
		
			return convertView;
		}
	}

	class ViewHolder {
		ImageView imageView;
	}
}
