package com.dream.library.widgets;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.library.R;
import com.dream.library.base.BaseAppCompatActivity;
import com.dream.library.eventbus.EventCenter;
import com.dream.library.utils.AbLog;
import com.dream.library.utils.netstatus.AbNetUtils;

import java.lang.ref.WeakReference;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Description TODO
 * Author SuSong
 * Date 15/8/8 下午11:05
 */
public class PhotoViewActivity extends BaseAppCompatActivity {

    public static final String PHOTO_VIEW_LIST = "PHOTO_VIEW_LIST";
    public static final String PHOTO_VIEW_CURRENT_ITEM = "PHOTO_VIEW_CURRENT_ITEM";
    TextView mTvTitle;
    ViewPager mViewPager;

    private List<String> mList;
    private int mItem;


    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_photo_view;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        if (mList == null || mList.size() == 0) {
            AbLog.e("传入的集合为空！");
            return;
        }

        if (mItem < 0 || mItem >= mList.size()) {
            AbLog.e("传入的PHOTO_VIEW_CURRENT_ITEM有误！");
            return;
        }
        mViewPager.setAdapter(new SamplePagerAdapter(mContext, mList));
        int i = mItem + 1;
        mTvTitle.setText(i + "/" + mList.size());
        mViewPager.setCurrentItem(mItem);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int i = position + 1;
                mTvTitle.setText(i + "/" + mList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mList = extras.getStringArrayList(PHOTO_VIEW_LIST);
        mItem = extras.getInt(PHOTO_VIEW_CURRENT_ITEM);
    }

    public class SamplePagerAdapter extends PagerAdapter {

        private List<String> mList;
        private Context mContext;

        public SamplePagerAdapter(Context context, List<String> list) {
            this.mContext = context;
            this.mList = list;
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // ReferenceQueue<PhotoView> referenceQueue = new ReferenceQueue<>();
            WeakReference<PhotoView> weakReference = new WeakReference<PhotoView>(new PhotoView(container.getContext()));
            //PhotoView photoView = new PhotoView(container.getContext());
            Glide.with(mContext).load(mList.get(position)).placeholder(R.drawable.moren).thumbnail(0.5f).into(weakReference.get());
            container.addView(weakReference.get(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            weakReference.get().setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    System.gc();
                    PhotoViewActivity.this.finish();
                }

                @Override
                public void onOutsidePhotoTap() {

                }
            });
            return weakReference.get();
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void onNetworkConnected(AbNetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return true;
    }

    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }
}
