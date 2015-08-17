package com.sjkj.myapplication.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.sjkj.myapplication.R;
import com.sjkj.myapplication.interfaces.DialogInterface;

/**
 * Created by QianChao on 2015/8/17.
 * 通用的ProgressDialog
 */
public class CommenDialog {
    private AlertDialog.Builder mDialog;
    private TextView tv_title, tv_content, tv_ok, tv_cancle;
    private DialogInterface mDialogInterface;
    private AlertDialog mAlertDialog;

    public CommenDialog(Context context, String title, String content, final DialogInterface mDialogInterface) {
        this.mDialogInterface = mDialogInterface;
        mDialog = new AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.progress_dialog_layout, null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_content = (TextView) view.findViewById(R.id.tv_content);
        tv_ok = (TextView) view.findViewById(R.id.tv_ok);
        tv_cancle = (TextView) view.findViewById(R.id.tv_cancle);

        tv_title.setText(title);
        tv_content.setText(content);

        //确定按钮点击事件的回调
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
                mDialogInterface.ok_click(v);
            }
        });
        //取消按钮点击事件的回调
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
                mDialogInterface.cancle_click(v);
            }
        });
        mDialog.setView(view);
        mAlertDialog = mDialog.create();
        mAlertDialog.show();
    }

}
