package com.dream.library.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/15 上午12:08
 * Description: EasyFrame
 */
public class AbGsonUtil {
    private static Gson gson = new Gson();

    /**
     * json转换成Bean
     */
    public static <T> T json2Bean(String json, Class<T> clazz) {
        T t = null;
        try {
            t = gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            AbLog.e(e);
        }
        return t;
    }

    /**
     * Bean转换json
     */
    public static String bean2Json(Object bean) {
        return gson.toJson(bean);
    }
}
