package com.chen.test.Utils;

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
    public static void showProgressDialog(Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("正在加载...");
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
