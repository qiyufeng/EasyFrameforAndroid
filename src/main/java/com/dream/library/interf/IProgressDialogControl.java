package com.dream.library.interf;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/27 下午3:45
 * Description: EasyFrame
 */
public interface IProgressDialogControl {

    KProgressHUD showProgressDialog();

    KProgressHUD showNonCancelableProgressDialog();

    KProgressHUD showProgressDialog(int resId);

    KProgressHUD showNonCancelableProgressDialog(int resId);

    KProgressHUD showProgressDialog(String text);

    KProgressHUD showNonCancelableProgressDialog(String text);

    KProgressHUD showProgressDialog(String text, boolean isCancelable);

    void hideProgressDialog();
}
