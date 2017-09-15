package com.example.administrator.myappgit.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by baiyu on 2017/4/23.
 * 引导页adapter
 */
public class GuidePagerAdapter extends PagerAdapter {

    private List<ImageView> mImageViews;

    public GuidePagerAdapter(List<ImageView> imageViews) {
        mImageViews = imageViews;
    }

    @Override
    public int getCount() {
        return mImageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mImageViews.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = mImageViews.get(position);
        container.removeView(view);
    }
}
