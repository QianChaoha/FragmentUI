package com.example.getpicture;

import java.util.ArrayList;

import android.content.Intent;
import android.view.View;

public interface PopMenuUtil {
	View  InitBitmapView(int type);
    void AdapterResh();
    void setTextGone();
    void setLastSelectGone();
    void getFileList(ArrayList<FileEntity> list);
	void onBitmapUtilsMyActivityResult(int requestCode, int resultCode, Intent data);
}
