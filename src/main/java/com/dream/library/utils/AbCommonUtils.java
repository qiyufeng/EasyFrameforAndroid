package com.dream.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/2 下午9:57
 * Description: EasyFrame
 */
public class AbCommonUtils {

    private static long lastClickTime;

    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 300) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


    /**
     * get format date
     *
     * @param timeMillis timeMillis
     * @return string
     */
    public static String getFormatDate(long timeMillis) {
        return new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA).format(new Date(timeMillis));
    }

    /**
     * decode Unicode string
     *
     * @param s s
     * @return string
     */
    public static String decodeUnicodeStr(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '\\' && chars[i + 1] == 'u') {
                char cc = 0;
                for (int j = 0; j < 4; j++) {
                    char ch = Character.toLowerCase(chars[i + 2 + j]);
                    if ('0' <= ch && ch <= '9' || 'a' <= ch && ch <= 'f') {
                        cc |= (Character.digit(ch, 16) << (3 - j) * 4);
                    } else {
                        cc = 0;
                        break;
                    }
                }
                if (cc > 0) {
                    i += 5;
                    sb.append(cc);
                    continue;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * encode Unicode string
     *
     * @param s s
     * @return string
     */
    public static String encodeUnicodeStr(String s) {
        StringBuilder sb = new StringBuilder(s.length() * 3);
        for (char c : s.toCharArray()) {
            if (c < 256) {
                sb.append(c);
            } else {
                sb.append("\\u");
                sb.append(Character.forDigit((c >>> 12) & 0xf, 16));
                sb.append(Character.forDigit((c >>> 8) & 0xf, 16));
                sb.append(Character.forDigit((c >>> 4) & 0xf, 16));
                sb.append(Character.forDigit((c) & 0xf, 16));
            }
        }
        return sb.toString();
    }

    /**
     * convert time str
     *
     * @param time time
     * @return string
     */
    public static String convertTime(int time) {

        time /= 1000;
        int minute = time / 60;
        int second = time % 60;
        minute %= 60;
        return String.format("%02d:%02d", minute, second);
    }

    /**
     * url is usable
     *
     * @param url url
     * @return boolean
     */
    public static boolean isUrlUsable(String url) {
        if (!AbStringUtils.isUrl(url)) {
            return false;
        }

        URL urlTemp;
        HttpURLConnection conn = null;
        try {
            urlTemp = new URL(url);
            conn = (HttpURLConnection) urlTemp.openConnection();
            conn.setRequestMethod("HEAD");
            int returnCode = conn.getResponseCode();
            if (returnCode == HttpURLConnection.HTTP_OK) {
                return true;
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return false;
    }

    /**
     * get toolbar height
     *
     * @param context context
     * @return int
     */
    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
            new int[]{android.R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    /**
     * 启用外部浏览器
     */
    public static void openBrowser(Context context, String url) {
        try {
            // 启用外部浏览器
            Uri uri = Uri.parse(url);
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(it);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void takePic(Context context) {
        Intent intent;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            ((Activity) context).startActivityForResult(Intent.createChooser(intent, "选择图片"),
                                                        ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD);
        } else {
            intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            ((Activity) context).startActivityForResult(Intent.createChooser(intent, "选择图片"),
                                                        ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD);
        }
    }
}
