package com.dream.library.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

import com.dream.library.utils.AbListViewAndGridViewUtils;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/8 下午3:57
 * Description: 解决 ScrollView、ListView 嵌套 GridView 的问题，及GridView点击空白地方事件扩展。
 * {@link AbListViewAndGridViewUtils}
 * <p>
 * ScrollView、ListView 嵌套 GridView 显示不全解决方法
 * 布局写法:
 * <ScrollView
 * android:id="@+id/scrollView"
 * android:layout_width="match_parent"
 * android:layout_height="0dp"
 * android:layout_weight="1"
 * android:fillViewport="true">
 * <com.dream.library.widgets.XGridView
 * android:id="@+id/listView"
 * android:layout_width="fill_parent"
 * android:layout_height="wrap_content"
 * android:divider="@color/window_background"
 * android:dividerHeight="1dp" />
 * </ScrollView>
 * 代码写法:
 * ScrollView scroll = (ScrollView) view.findViewById(R.id.scrollView);
 * GridView list = (GridView) view.findViewById(R.id.gridView);
 * AbListViewAndGridViewUtils.setListViewHeightBasedOnChildren(list);
 * scroll.smoothScrollTo(0, 0);
 */
public class XGridView extends GridView {
    public XGridView(Context context) {
        super(context);
    }

    public XGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public XGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    //*******************************************************************************************

    /**
     * GridView点击空白地方事件扩展
     */
    private OnTouchInvalidPositionListener mOnTouchInvalidPositionListener;

    public interface OnTouchInvalidPositionListener {
        /**
         * motionEvent 可使用 MotionEvent.ACTION_DOWN 或者 MotionEvent.ACTION_UP等来按需要进行判断
         *
         * @return 是否要终止事件的路由
         */
        boolean onTouchInvalidPosition();
    }

    /**
     * 点击空白区域时的响应和处理接口
     */
    public void setOnTouchInvalidPositionListener(OnTouchInvalidPositionListener listener) {
        mOnTouchInvalidPositionListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (mOnTouchInvalidPositionListener == null) {
            return super.onTouchEvent(event);
        }

        if (!isEnabled()) {
            // A disabled view that is clickable still consumes the touch
            // events, it just doesn't respond to them.
            return isClickable() || isLongClickable();
        }

        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            final int motionPosition = pointToPosition((int) event.getX(), (int) event.getY());

            if (motionPosition == INVALID_POSITION) {
                super.onTouchEvent(event);
                return mOnTouchInvalidPositionListener.onTouchInvalidPosition();
            }
        }

        return super.onTouchEvent(event);
    }
}
