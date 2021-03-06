package com.dream.library.widgets.empty;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.library.R;
import com.dream.library.utils.AbStringUtils;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/3 上午12:13
 * Description: EasyFrame
 */
public class EmptyViewHelperController {

    private IEmptyViewHelper helper;

    public EmptyViewHelperController(View view) {
        this(new EmptyViewHelper(view));
    }

    public EmptyViewHelperController(IEmptyViewHelper helper) {
        super();
        this.helper = helper;
    }

    public void showNetworkError(View.OnClickListener onClickListener) {
        View     layout   = helper.inflate(R.layout.il_empty);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        textView.setText(helper.getContext().getResources().getString(R.string.common_no_network_msg));

        ImageView imageView = (ImageView) layout.findViewById(R.id.message_icon);
        imageView.setImageResource(R.drawable.ic_empty_icon_network);

        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }

    public void showError(String errorMsg, View.OnClickListener onClickListener) {
        View     layout   = helper.inflate(R.layout.il_empty);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        if (!AbStringUtils.isEmpty(errorMsg)) {
            textView.setText(errorMsg);
        } else {
            textView.setText(helper.getContext().getResources().getString(R.string.common_error_msg));
        }

        ImageView imageView = (ImageView) layout.findViewById(R.id.message_icon);
        imageView.setImageResource(R.drawable.ic_empty_icon_error);

        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }

    public void showCartEmpty(String emptyMsg, View.OnClickListener onClickListener) {
        View     layout   = helper.inflate(R.layout.il_cart_empty);
        TextView textView = (TextView) layout.findViewById(R.id.tv_null);
        Button   bt_null  = (Button) layout.findViewById(R.id.bt_null);
        if (!AbStringUtils.isEmpty(emptyMsg)) {
            textView.setText(emptyMsg);
        } else {
            textView.setText("购物车空空如也~");
        }


        if (null != onClickListener) {
            bt_null.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }


    public void showEmpty(String emptyMsg, View.OnClickListener onClickListener) {
        View     layout   = helper.inflate(R.layout.il_empty);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        if (!AbStringUtils.isEmpty(emptyMsg)) {
            textView.setText(emptyMsg);
        } else {
            textView.setText(helper.getContext().getResources().getString(R.string.common_empty_msg));
        }

        ImageView imageView = (ImageView) layout.findViewById(R.id.message_icon);
        imageView.setImageResource(R.drawable.ic_empty_icon_empty);

        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }


    public void showLoading(String msg) {
        View layout = helper.inflate(R.layout.il_empty_loading);
        if (!AbStringUtils.isEmpty(msg)) {
            TextView textView = (TextView) layout.findViewById(R.id.loading_msg);
            textView.setText(msg);
        }
        helper.showLayout(layout);
    }

    public void restore() {
        helper.restoreView();
    }
}
