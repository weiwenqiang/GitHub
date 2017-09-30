package com.chen.mvp.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by long on 17-4-1.
 */

public class MyDialog {

    private static ProgressDialog progressDialog;

    /**
     * 显示进入对话框
     */
    public static void showProgressDialog(Context context,String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(msg);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    public static void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}
