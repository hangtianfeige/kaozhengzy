package com.example.administrator.kaozhengzy;

/**
 * 作者：刘帅 on 2016/2/26 12:21
 * 邮箱：857279611@qq.com
 */

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.widget.Toast;


/**
 * 提示信息的管理
 */
public class PromptManager {

    public static ProgressDialog dialog;

    public PromptManager(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle(R.string.app_name);
        dialog.setMessage("请等候，数据加载中……");
    }

    public  void showProgress(Context context) {
        dialog.show();
    }

    public  void closeProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 当判断当前手机没有网络时使用
     *
     * @param context
     */
    public static void showNoNetWork(final Context context) {
        AlertDialog.Builder builder = new Builder(context);
        builder.setMessage("没有网络");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.app_name);
        builder.setPositiveButton("设置", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setClassName("com.android.settings", "com.android.settings" +
                        ".WirelessSettings");
                context.startActivity(intent);
            }
        });

        builder.setNegativeButton("知道了", null);
        builder.show();
    }

    public static void showExitApplication(Context context) {
        AlertDialog.Builder builder = new Builder(context);
        builder.setCancelable(false);
        builder.setIcon(R.mipmap.ic_launcher).setTitle(R.string.app_name).setMessage("是否退出程序")
                .setPositiveButton("确定", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                }).setNegativeButton("取消", null).show();
    }

    /**
     * 显示错误提示框
     *
     * @param context
     * @param msg
     */
    public static void showErrorDialog(Context context, String msg) {
        new AlertDialog.Builder(context)//
                .setIcon(R.mipmap.ic_launcher)//
                .setTitle(R.string.app_name)//
                .setMessage(msg)//
                .setNegativeButton("取消", null)//
                .show();
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showToast(Context context, int msgResId) {
        Toast.makeText(context, msgResId, Toast.LENGTH_LONG).show();
    }


    private static final boolean isShow = true;

    /**
     * 测试用 在正式投入市场：删
     *
     * @param context
     * @param msg
     */
    public static void showToastTest(Context context, String msg) {
        if (isShow) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        }
    }

}
