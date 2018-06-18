package com.dream.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import com.dream.library.widgets.PhotoViewActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * 界面帮助类
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @version 创建时间：2014年10月10日 下午3:33:36
 */
public class AbUIHelper {

    public static void showPicInPhotoView(Context context, List<String> list, int currentItem) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putStringArrayListExtra(PhotoViewActivity.PHOTO_VIEW_LIST, (ArrayList<String>) list);
        intent.putExtra(PhotoViewActivity.PHOTO_VIEW_CURRENT_ITEM, currentItem);
        context.startActivity(intent);
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
