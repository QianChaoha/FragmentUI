package com.example.getpicture;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class PopupWindows extends PopupWindow implements OnClickListener {
	private String name;
	private String path;
	private Activity mContext;

	public PopupWindows(Activity mContext, View parent) {
		this.mContext = mContext;
		View view = View.inflate(mContext, R.layout.item_popupwindow_more, null);
		view.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_from_bottom));
		setWidth(LayoutParams.FILL_PARENT);
		setHeight(LayoutParams.FILL_PARENT);
		setBackgroundDrawable(new BitmapDrawable());
		setFocusable(true);
		setOutsideTouchable(true);
		setContentView(view);
		showAsDropDown(parent, 0, 0);
		update();
		Button but = (Button) view.findViewById(R.id.item_popupwindows_camera);
		but.setOnClickListener(this);
		Button but1 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
		but1.setOnClickListener(this);
		view.findViewById(R.id.item_popupwindows_cancel).setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		Intent intent;
		switch (arg0.getId()) {
		case R.id.item_popupwindows_Photo:
			intent = new Intent(mContext, PicAct.class);
			Bundle bundle = new Bundle();
			bundle.putInt("index", 0);
			intent.putExtras(bundle);
			mContext.startActivity(intent);

			break;
		case R.id.item_popupwindows_camera:// ≈ƒ’’
			takePhotos();
			break;

		case R.id.item_popupwindows_cancel:
			break;
		}
		dismiss();
	}

	private void takePhotos() {

		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File file = new File(Environment.getExternalStorageDirectory() + "/telecom/myimage/");
		if (!file.exists()) {
			file.mkdirs();
		}
		name = String.valueOf(System.currentTimeMillis()) + ".jpg";
		file = new File(file.getAbsolutePath(), name);
		path = file.getPath();

		Uri imageUri = Uri.fromFile(file);
		openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		mContext.startActivityForResult(openCameraIntent, 102);
	}

}
