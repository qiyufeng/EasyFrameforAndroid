package com.dream.library.utils;

import android.view.View;

/**
 * Created by qiyunfeng on 2017/2/13.
 */
public abstract class OnClickEvent implements View.OnClickListener {

    private static long lastTime;

    public abstract void singleClick(View v);
    private long delay = 500;

    public OnClickEvent(long delay) {
        this.delay = delay;
    }

    public OnClickEvent() {
        super();
    }

    @Override
    public void onClick(View v) {
        if (onMoreClick(v)) {
            return;
        }
        singleClick(v);
    }

    public boolean onMoreClick(View v) {
        boolean flag = false;
        long time = System.currentTimeMillis() - lastTime;
        if (time < delay) {
            flag = true;
        }
        lastTime = System.currentTimeMillis();
        return flag;
    }
}
