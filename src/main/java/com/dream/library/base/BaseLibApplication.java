package com.dream.library.base;

import android.app.Application;

import com.dream.library.utils.netstatus.NetStateReceiver;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/28 下午3:55
 * Description: EasyFrame
 */
public abstract class BaseLibApplication extends Application {

    private static BaseLibApplication instance;

    /**
     * 获得当前app运行的AppContext
     *
     * @return BaseLibApplication
     */
    public static BaseLibApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    private void init() {
        // 错误信息捕获
//        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler(this));
        NetStateReceiver.registerNetworkStateReceiver(this);
    }

}
