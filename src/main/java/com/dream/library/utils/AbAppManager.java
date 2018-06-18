package com.dream.library.utils;

import android.app.Activity;

import com.dream.library.base.BaseLibApplication;
import com.dream.library.utils.netstatus.NetStateReceiver;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/2 下午9:57
 * Description: EasyFrame
 */
@SuppressWarnings("unused")
public class AbAppManager {

    private static final String TAG = AbAppManager.class.getSimpleName();

    private static AbAppManager mAbAppManager = null;
    public static Stack<WeakReference<Activity>> mActivityStack = new Stack<>();

    private AbAppManager() {

    }

    /**
     * 单一实例
     */
    public static AbAppManager getAbAppManager() {
        if (mAbAppManager == null) {
            synchronized (AbAppManager.class) {
                if (mAbAppManager == null) {
                    mAbAppManager = new AbAppManager();
                }
            }
        }
        return mAbAppManager;
    }

    public int size() {
        return mActivityStack.size();
    }

    /**
     * 添加Activity到堆栈
     */
    public synchronized void addActivity(Activity activity) {
        if (activity != null) {
            WeakReference<Activity> weakReference = new WeakReference<Activity>(activity);
            mActivityStack.add(weakReference);
        }
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        return mActivityStack.lastElement().get();
    }

    /**
     * 获取指定的Activity
     */
    public Activity getActivity(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        for (WeakReference<Activity> weakReference : mActivityStack) {
            Activity activity = weakReference.get();
            if (activity != null) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        }
        return null;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishCurrentActivity() {
        finishActivity(mActivityStack.lastElement().get());
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity... activities) {
        if (activities == null || activities.length == 0) {
            return;
        }
        for (Activity a : activities) {
            //1、finish
            if (!a.isFinishing()) {
                a.finish();
            }
            //2、从栈中移除
            for (WeakReference<Activity> weakReference : mActivityStack) {
                Activity activity = weakReference.get();
                if (activity != null) {
                    if (a.equals(activity)) {
                        mActivityStack.remove(weakReference);
                        break;
                    }
                }
            }
        }
    }

    /**
     * 结束指定类名的Activity，可能为多个
     */
    public void finishActivity(Class<?>... cls) {
        if (cls == null || cls.length == 0) {
            return;
        }
        Stack<WeakReference<Activity>> activityStackTemp = new Stack<>();
        for (Class<?> c : cls) {
            for (WeakReference<Activity> weakReference : mActivityStack) {
                Activity activity = weakReference.get();
                if (activity != null) {
                    if (activity.getClass().getName().equals(c.getName())) {
                        activityStackTemp.add(weakReference);
                    }
                }
            }
        }
        mActivityStack.removeAll(activityStackTemp);
        for (WeakReference<Activity> weakReference : activityStackTemp) {
            Activity activity = weakReference.get();
            if (activity != null) {
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
        activityStackTemp.clear();
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (WeakReference<Activity> weakReference : mActivityStack) {
            Activity activity = weakReference.get();
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
        mActivityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
            NetStateReceiver.unRegisterNetworkStateReceiver(BaseLibApplication.getInstance());
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
