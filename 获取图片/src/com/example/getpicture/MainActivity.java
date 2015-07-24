package com.example.getpicture;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity {
	GridView gv_add_file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		gv_add_file = (GridView) findViewById(R.id.gv_add_file);
//		MyFilePop filePop = new MyFilePop(this);
	}
}
