package com.dream.library.utils;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        16/7/27 上午12:48
 * Description: 日志类
 */
@SuppressWarnings("unused")
public final class AbLog {

    private static final String DEFAULT_TAG = "XLog";

    private static boolean isLogEnable = false;

    public static void disableLog() {
        isLogEnable = false;
        init();
    }

    public static void enableLog() {
        isLogEnable = true;
        init();
    }

    public static boolean isLogEnable() {
        return isLogEnable;
    }

    static {
        init();
    }

    private AbLog() {
    }

    /**
     * Debug
     *
     * @param msg msg
     */
    public static void d(String msg, Object... args) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            android.util.Log.d(DEFAULT_TAG, rebuildMsg(stackTraceElement, createMsg(msg, args)));
        }
    }

    /**
     * Debug
     *
     * @param tag tag
     * @param msg msg
     */
    public static void d(String tag, String msg, Object... args) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            android.util.Log.d(tag, rebuildMsg(stackTraceElement, createMsg(msg, args)));
        }
    }

    /**
     * Information
     *
     * @param msg msg
     */
    public static void i(String msg, Object... args) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            android.util.Log.i(DEFAULT_TAG, rebuildMsg(stackTraceElement, createMsg(msg, args)));
        }
    }

    /**
     * Information
     *
     * @param tag tag
     * @param msg msg
     */
    public static void i(String tag, String msg, Object... args) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            android.util.Log.i(tag, rebuildMsg(stackTraceElement, createMsg(msg, args)));
        }
    }

    /**
     * Verbose
     *
     * @param msg msg
     */
    public static void v(String msg, Object... args) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            android.util.Log.v(DEFAULT_TAG, rebuildMsg(stackTraceElement, createMsg(msg, args)));
        }
    }

    /**
     * Verbose
     *
     * @param tag tag
     * @param msg msg
     */
    public static void v(String tag, String msg, Object... args) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            android.util.Log.v(tag, rebuildMsg(stackTraceElement, createMsg(msg, args)));
        }
    }

    /**
     * Warning
     *
     * @param msg msg
     */
    public static void w(String msg, Object... args) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            android.util.Log.w(DEFAULT_TAG, rebuildMsg(stackTraceElement, createMsg(msg, args)));
        }
    }

    /**
     * Warning
     *
     * @param tag tag
     * @param msg msg
     */
    public static void w(String tag, String msg, Object... args) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            android.util.Log.w(tag, rebuildMsg(stackTraceElement, createMsg(msg, args)));
        }
    }

    /**
     * Error
     *
     * @param msg msg
     */
    public static void e(String msg, Object... args) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            android.util.Log.e(DEFAULT_TAG, rebuildMsg(stackTraceElement, createMsg(msg, args)));
        }
    }

    /**
     * Error
     *
     * @param tag tag
     * @param msg msg
     */
    public static void e(String tag, String msg, Object... args) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            android.util.Log.e(tag, rebuildMsg(stackTraceElement, createMsg(msg, args)));
        }
    }

    /**
     * Error
     *
     * @param throwable Throwable
     */
    public static void e(Throwable throwable) {
        if (isLogEnable) {
            StringWriter writer = new StringWriter();
            throwable.printStackTrace(new PrintWriter(writer));
            String            errorMsg          = writer.toString();
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            android.util.Log.e(DEFAULT_TAG, rebuildMsg(stackTraceElement, errorMsg));
        }
    }

    /**
     * Error
     *
     * @param tag       tag
     * @param throwable Throwable
     */
    public static void e(String tag, Throwable throwable) {
        if (isLogEnable) {
            StringWriter writer = new StringWriter();
            throwable.printStackTrace(new PrintWriter(writer));
            String            errorMsg          = writer.toString();
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            android.util.Log.e(tag, rebuildMsg(stackTraceElement, errorMsg));
        }
    }

    private static String createMsg(String msg, Object... args) {
        return args == null || args.length == 0 ? msg : String.format(msg, args);
    }

    /**
     * Rebuild Log Msg
     *
     * @param msg msg
     * @return String
     */
    private static String rebuildMsg(StackTraceElement stackTraceElement, String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("Thread:")
          .append(Thread.currentThread().getName())
          .append(" ")
          .append(getSimpleClassName(stackTraceElement.getClassName()))
          .append(".")
          .append(stackTraceElement.getMethodName())
          .append(" (")
          .append(stackTraceElement.getFileName())
          .append(":")
          .append(stackTraceElement.getLineNumber())
          .append(") ")
          .append(msg);
        return sb.toString();
    }

    /**
     * Get SimpleClass Name
     *
     * @param name name
     * @return String
     */
    private static String getSimpleClassName(String name) {
        int lastIndex = name.lastIndexOf(".");
        return name.substring(lastIndex + 1);
    }

    //===================   增强版Log    ===================

    private static void init() {
        if (isLogEnable) {
            Logger
                    .init(DEFAULT_TAG)              // default PRETTYLOGGER or use just init()
                    .methodCount(1)                 // default 2
                    .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                    //.hideThreadInfo()               // default shown
                    //.logAdapter(new AndroidLogAdapter()) //default AndroidLogAdapter
                    .methodOffset(1);               // default 0
        } else {
            Logger
                    .init(DEFAULT_TAG)              // default PRETTYLOGGER or use just init()
                    .methodCount(1)                 // default 2
                    .logLevel(LogLevel.NONE)        // default LogLevel.FULL
                    //.hideThreadInfo()               // default shown
                    //.logAdapter(new AndroidLogAdapter()) //default AndroidLogAdapter
                    .methodOffset(1);               // default 0
        }
    }

    public static void log(int priority, String tag, String message, Throwable throwable) {
        if (isLogEnable) {
            Logger.log(priority, tag, message, throwable);
        }
    }

    public static void dl(String msg, Object... args) {
        if (isLogEnable) {
            Logger.d(msg, args);
        }
    }

    public static void dl(String tag, String msg, Object... args) {
        if (isLogEnable) {
            Logger.t(tag).d(msg, args);
        }
    }

    public static void dl(Object object) {
        if (isLogEnable) {
            Logger.d(object);
        }
    }

    public static void dl(String tag, Object object) {
        if (isLogEnable) {
            Logger.t(tag).d(object);
        }
    }

    public static void el(String msg, Object... args) {
        if (isLogEnable) {
            Logger.e(msg, args);
        }
    }

    public static void el(String tag, String msg, Object... args) {
        if (isLogEnable) {
            Logger.t(tag).e(msg, args);
        }
    }

    public static void el(Throwable throwable) {
        if (isLogEnable) {
            StringWriter writer = new StringWriter();
            throwable.printStackTrace(new PrintWriter(writer));
            String errorMsg = writer.toString();
            Logger.e(errorMsg);
        }
    }

    public static void el(String tag, Throwable throwable) {
        if (isLogEnable) {
            StringWriter writer = new StringWriter();
            throwable.printStackTrace(new PrintWriter(writer));
            String errorMsg = writer.toString();
            Logger.t(tag).e(errorMsg);
        }
    }

    public static void el(Throwable throwable, String msg, Object... args) {
        if (isLogEnable) {
            Logger.e(throwable, msg, args);
        }
    }

    public static void el(String tag, Throwable throwable, String msg, Object... args) {
        if (isLogEnable) {
            Logger.t(tag).e(throwable, msg, args);
        }
    }

    public static void il(String msg, Object... args) {
        if (isLogEnable) {
            Logger.i(msg, args);
        }
    }

    public static void il(String tag, String msg, Object... args) {
        if (isLogEnable) {
            Logger.t(tag).i(msg, args);
        }
    }

    public static void vl(String msg, Object... args) {
        if (isLogEnable) {
            Logger.v(msg, args);
        }
    }

    public static void vl(String tag, String msg, Object... args) {
        if (isLogEnable) {
            Logger.t(tag).v(msg, args);
        }
    }

    public static void wl(String msg, Object... args) {
        if (isLogEnable) {
            Logger.w(msg, args);
        }
    }

    public static void wl(String tag, String msg, Object... args) {
        if (isLogEnable) {
            Logger.t(tag).w(msg, args);
        }
    }

    public static void wtf(String msg, Object... args) {
        if (isLogEnable) {
            Logger.wtf(msg, args);
        }
    }

    public static void wtf(String tag, String msg, Object... args) {
        if (isLogEnable) {
            Logger.t(tag).wtf(msg, args);
        }
    }

    public static void json(String json) {
        if (isLogEnable) {
            Logger.json(json);
        }
    }

    public static void json(String tag, String json) {
        if (isLogEnable) {
            Logger.t(tag).json(json);
        }
    }

    public static void xml(String xml) {
        if (isLogEnable) {
            Logger.xml(xml);
        }
    }

    public static void xml(String tag, String xml) {
        if (isLogEnable) {
            Logger.t(tag).xml(xml);
        }
    }
}
