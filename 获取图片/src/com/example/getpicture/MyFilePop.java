package com.example.getpicture;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MyFilePop {
	private Context context;
	private Bitmap bitmap;
	public static List<Bitmap> bitmaps = new ArrayList<Bitmap>();

	public MyFilePop(Context context) {
		this.context = context;
		bitmaps.clear();
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_addpic_unfocused);
		bitmaps.add(bitmap);
	}

}
