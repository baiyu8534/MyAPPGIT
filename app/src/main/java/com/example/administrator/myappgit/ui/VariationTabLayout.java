package com.example.administrator.myappgit.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myappgit.R;

/**
 * 文件名：VariationTabLayout
 * 描述：icon和text相互变幻的TabLayout
 * 作者：白煜
 * 时间：2017/10/20 0020
 * 版权：
 */

public class VariationTabLayout extends TabLayout {

    private RelativeLayout mRelativeLayout;
    private OnTabSelectedListener mOnTabSelectedListener;

    public interface IMyOneTabLayoutIconAdapter {
        int getTabIcon(int position);
    }

    public VariationTabLayout(Context context) {
        super(context);
        init(context);
    }

    public VariationTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VariationTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        super.setupWithViewPager(viewPager);
        if (!(viewPager.getAdapter() instanceof IMyOneTabLayoutIconAdapter)) {
            throw new IllegalArgumentException("viewPager not implements MyOneTabLayout.IMyOneTabLayoutIconAdapter");
        }
        IMyOneTabLayoutIconAdapter adapter = (IMyOneTabLayoutIconAdapter) viewPager.getAdapter();
        for (int i = 0; i < getTabCount(); i++) {
            getTabAt(i).setCustomView(R.layout.tab_layout);
            ImageView imageView = (ImageView) getTabAt(i).getCustomView().findViewById(R.id.iv);
            TextView textView = (TextView) getTabAt(i).getCustomView().findViewById(R.id.tv);
            textView.setText(viewPager.getAdapter().getPageTitle(i));
            imageView.setImageResource(adapter.getTabIcon(i));
        }
        for (int i = 0; i < getTabCount(); i++) {
            if (i == getSelectedTabPosition()) {
                View iv = getTabAt(i).getCustomView().findViewById(R.id.iv);
                iv.setAlpha(0);
                iv.setScaleX(0.4f);
                iv.setScaleY(0.4f);
            } else {
                View tv = getTabAt(i).getCustomView().findViewById(R.id.tv);
                tv.setAlpha(0);
                tv.setScaleX(0.4f);
                tv.setScaleY(0.4f);
            }
        }
    }



    private void init(Context context) {
//        initCustomView(context);
        initListener();
        addOnTabSelectedListener(mOnTabSelectedListener);
    }



    private void initListener() {
        mOnTabSelectedListener = new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                if(tab != null && tab.getCustomView()!=null) {
                    View imageView = tab.getCustomView().findViewById(R.id.iv);
                    View textView = tab.getCustomView().findViewById(R.id.tv);
                    imageView.animate()
                            .alpha(0)
                            .scaleX(0.4f)
                            .scaleY(0.4f)
                            .setDuration(300).start();
                    textView.animate()
                            .alpha(1)
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(300).start();
                }
            }

            @Override
            public void onTabUnselected(Tab tab) {
                if(tab != null && tab.getCustomView()!=null) {
                    View imageView = tab.getCustomView().findViewById(R.id.iv);
                    View textView = tab.getCustomView().findViewById(R.id.tv);
                    textView.animate()
                            .alpha(0)
                            .scaleX(0.4f)
                            .scaleY(0.4f)
                            .setDuration(300).start();
                    imageView.animate()
                            .alpha(1)
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(300).start();
                }
            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        };
    }
}
