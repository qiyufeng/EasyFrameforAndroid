package com.dream.library.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ListView;

import com.dream.library.utils.AbListViewAndGridViewUtils;


/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/8 下午3:57
 * Description: 解决 ScrollView、GridView 嵌套 ListView 的问题.
 * http://stackoverflow.com/questions/18367522/android-list-view-inside-a-scroll-view
 * {@link AbListViewAndGridViewUtils}
 * <p>
 * ScrollView、GridView 嵌套 GridView 显示不全解决方法
 * 布局写法:
 * <ScrollView
 * android:id="@+id/scrollView"
 * android:layout_width="match_parent"
 * android:layout_height="0dp"
 * android:layout_weight="1"
 * android:fillViewport="true">
 * <com.dream.library.widgets.XListView
 * android:id="@+id/listView"
 * android:layout_width="fill_parent"
 * android:layout_height="wrap_content"
 * android:divider="@color/window_background"
 * android:dividerHeight="1dp" />
 * </ScrollView>
 * 代码写法:
 * ScrollView scroll = (ScrollView) view.findViewById(R.id.scrollView);
 * ListView list = (ListView) view.findViewById(R.id.listView);
 * AbListViewAndGridViewUtils.setListViewHeightBasedOnChildren(list);
 * scroll.smoothScrollTo(0, 0);
 */
@SuppressWarnings("unused")
public class XListView extends ListView {
    public XListView(Context context) {
        super(context);
    }

    public XListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public XListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
