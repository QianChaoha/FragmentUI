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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class AddPictureGrideView extends GridView {
	private Bitmap bitmap;
	public static List<Bitmap> bitmaps = new ArrayList<Bitmap>();
	private Activity context = (Activity) getContext();

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

	public void init() {
		bitmaps.clear();
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_addpic_unfocused);
		bitmaps.add(bitmap);
		setAdapter(new MyAdapter());
		setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (position == bitmaps.size() - 1) {
					new PopupWindows(context, view);
				}
			}
		});

	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return bitmaps.size();
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
			holder.imageView.setImageBitmap(bitmap);
			return convertView;
		}
	}

	class ViewHolder {
		ImageView imageView;
	}
}
