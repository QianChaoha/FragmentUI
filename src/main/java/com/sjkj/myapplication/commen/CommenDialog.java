package com.sjkj.myapplication.commen;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.sjkj.myapplication.R;
import com.sjkj.myapplication.interfaces.DialogInterface;
import com.sjkj.myapplication.view.MyDialog;

/**
 * Created by QianChao on 2015/8/17.
 * 通用的Dialog
 */
public class CommenDialog {
    private MyDialog mDialog;
    private TextView tv_title, tv_content, tv_ok, tv_cancle;
    private DialogInterface mDialogInterface;

    /**
     *
     * @param context
     * @param title 标题
     * @param content 内容
     * @param mDialogInterface dialog点击事件 回调接口
     */
    public CommenDialog(Context context, String title, String content, final DialogInterface mDialogInterface) {
        this.mDialogInterface = mDialogInterface;
        View view = View.inflate(context, R.layout.progress_dialog_layout, null);
        mDialog = new MyDialog(context,view,R.style.dialog);

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
                mDialog.dismiss();
                mDialogInterface.ok_click(v);
            }
        });
        //取消按钮点击事件的回调
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                mDialogInterface.cancle_click(v);
            }
        });
        mDialog.show();
    }

}
