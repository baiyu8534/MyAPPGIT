package com.example.administrator.myappgit.activity;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myappgit.BuildConfig;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.ShowListFragmentPagerAdapter;
import com.example.administrator.myappgit.fragment.WeatherFragment;
import com.example.administrator.myappgit.fragment.ZhiHuFragment;
import com.example.administrator.myappgit.utils.UIUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.administrator.myappgit.utils.UIUtil.colorResId2ColorInt;
import static com.example.administrator.myappgit.utils.UIUtil.getTextWidth;
import static com.example.administrator.myappgit.utils.UIUtil.setWindowStatusBarColor;

/**
 * Created by Administrator on 2017/7/13 0013.
 */

public class ShowListActivity extends BaseActivity {

    @BindView(R.id.icon_image)
    CircleImageView mIconImage;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.tab_show_list)
    TabLayout mTabShowList;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.iv_nv_menu_icon)
    ImageView mIvNvMenuIcon;
    @BindView(R.id.tb)
    Toolbar mTb;
    @BindView(R.id.abl)
    AppBarLayout mAbl;

    private List<Fragment> mFragments = new ArrayList<>();
    private ShowListFragmentPagerAdapter mPagerAdapter;
    //首页的点击position
    private int mPosition;

    //记录viewpager的位置
    private float prePosition = 0;

    private int proVerticalOffset = 0;


    private int[] itemBG = {
            R.color.mainRvItemBg1,
            R.color.mainRvItemBg2,
            R.color.mainRvItemBg3
    };

    private TextView mTabTextView1;
    private TextView mTabTextView2;
    private TextView mTabTextView3;
    private TextView mTabTextView4;

    private ArgbEvaluator mArgbEvaluator;
    private ZhiHuFragment mZhiHuFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_layout);
        ButterKnife.bind(this);
        mPosition = getIntent().getIntExtra("position", 0);
        initView();
        initListener();
    }

    private void initListener() {
        //tabLayout设置了不能滑动，监听这个没用
        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTabShowList.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (BuildConfig.DEBUG) Log.d("ShowListActivity", "scrollX:" + scrollX);
                    if (BuildConfig.DEBUG) Log.d("ShowListActivity", "scrollY:" + scrollY);
                    if (BuildConfig.DEBUG) Log.d("ShowListActivity", "oldScrollX:" + oldScrollX);
                    if (BuildConfig.DEBUG) Log.d("ShowListActivity", "oldScrollY:" + oldScrollY);
                }
            });
        }*/

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // TODO: 2017/9/12 0012 待优化，看下网上的demo是怎么实现的，动态改变tabLayout和toolBar的颜色
                //动态改变tabLayout和toolBar的颜色，和每个tabView中的Text的大小

                if (position + positionOffset > 0 && position + positionOffset < 1 && position + positionOffset -
                        prePosition > 0) {
                    mTabTextView1.setScaleX(1.2f - 0.2f * positionOffset);
                    mTabTextView1.setScaleY(1.2f - 0.2f * positionOffset);

                    mTabTextView2.setScaleX(1f + 0.2f * positionOffset);
                    mTabTextView2.setScaleY(1f + 0.2f * positionOffset);

                    //mTabShowList.setBackgroundColor(colorResId2ColorInt(ShowListActivity.this, itemBG[position % 3]))
                    mAbl.setBackgroundColor(colorResId2ColorInt(ShowListActivity.this, itemBG[position % 3]))
                    ; //先设置第0页时还没有滑动时tablayout的颜色
                    mTb.setBackgroundColor(colorResId2ColorInt(ShowListActivity.this, itemBG[position % 3]));
                    //先设置第0页时还没有滑动时toolbar的颜色
                    int evaluate = (Integer) mArgbEvaluator.evaluate(positionOffset, colorResId2ColorInt
                            (ShowListActivity.this, itemBG[position % 3]), colorResId2ColorInt(ShowListActivity.this,
                            itemBG[(position + 1) % 3]));
                    //mTabShowList.setBackgroundColor(evaluate);//设置背景颜色为算出的两种颜色之间的过渡色
                    mAbl.setBackgroundColor(evaluate);//设置背景颜色为算出的两种颜色之间的过渡色
                    mTb.setBackgroundColor(evaluate);

                    setWindowStatusBarColor(ShowListActivity.this, evaluate);

                } else if (position + positionOffset > 1 && position + positionOffset < 2 && position +
                        positionOffset - prePosition > 0) {
                    mTabTextView2.setScaleX(1.2f - 0.2f * positionOffset);
                    mTabTextView2.setScaleY(1.2f - 0.2f * positionOffset);

                    mTabTextView3.setScaleX(1f + 0.2f * positionOffset);
                    mTabTextView3.setScaleY(1f + 0.2f * positionOffset);

                    //mTabShowList.setBackgroundColor(colorResId2ColorInt(ShowListActivity.this, itemBG[position % 3]));
                    mAbl.setBackgroundColor(colorResId2ColorInt(ShowListActivity.this, itemBG[position % 3]));
                    mTb.setBackgroundColor(colorResId2ColorInt(ShowListActivity.this, itemBG[position % 3]));
                    int evaluate = (Integer) mArgbEvaluator.evaluate(positionOffset, colorResId2ColorInt
                            (ShowListActivity.this, itemBG[position % 3]), colorResId2ColorInt(ShowListActivity.this,
                            itemBG[(position + 1) % 3]));

                    //mTabShowList.setBackgroundColor(evaluate);//设置背景颜色为算出的两种颜色之间的过渡色
                    mAbl.setBackgroundColor(evaluate);//设置背景颜色为算出的两种颜色之间的过渡色
                    mTb.setBackgroundColor(evaluate);

                    setWindowStatusBarColor(ShowListActivity.this, evaluate);

                } else if (position + positionOffset > 2 && position + positionOffset < 3 && position +
                        positionOffset - prePosition > 0) {
                    mTabTextView3.setScaleX(1.2f - 0.2f * positionOffset);
                    mTabTextView3.setScaleY(1.2f - 0.2f * positionOffset);

                    mTabTextView4.setScaleX(1f + 0.2f * positionOffset);
                    mTabTextView4.setScaleY(1f + 0.2f * positionOffset);

                    //mTabShowList.setBackgroundColor(colorResId2ColorInt(ShowListActivity.this, itemBG[position % 3]));
                    mAbl.setBackgroundColor(colorResId2ColorInt(ShowListActivity.this, itemBG[position % 3]));
                    mTb.setBackgroundColor(colorResId2ColorInt(ShowListActivity.this, itemBG[position % 3]));
                    int evaluate = (Integer) mArgbEvaluator.evaluate(positionOffset, colorResId2ColorInt
                            (ShowListActivity.this, itemBG[position % 3]), colorResId2ColorInt(ShowListActivity.this,
                            itemBG[(position + 1) % 3]));
                    //mTabShowList.setBackgroundColor(evaluate);//设置背景颜色为算出的两种颜色之间的过渡色
                    mAbl.setBackgroundColor(evaluate);//设置背景颜色为算出的两种颜色之间的过渡色
                    mTb.setBackgroundColor(evaluate);

                    setWindowStatusBarColor(ShowListActivity.this, evaluate);

                } else if (position + positionOffset > 2 && position + positionOffset < 3 && position +
                        positionOffset - prePosition < 0) {
                    mTabTextView4.setScaleX(1.2f - 0.2f * positionOffset);
                    mTabTextView4.setScaleY(1.2f - 0.2f * positionOffset);

                    mTabTextView3.setScaleX(1f + 0.2f * positionOffset);
                    mTabTextView3.setScaleY(1f + 0.2f * positionOffset);
                } else if (position + positionOffset > 1 && position + positionOffset < 2 && position +
                        positionOffset - prePosition < 0) {
                    mTabTextView3.setScaleX(1.2f - 0.2f * positionOffset);
                    mTabTextView3.setScaleY(1.2f - 0.2f * positionOffset);

                    mTabTextView2.setScaleX(1f + 0.2f * positionOffset);
                    mTabTextView2.setScaleY(1f + 0.2f * positionOffset);
                } else if (position + positionOffset > 0 && position + positionOffset < 1 && position +
                        positionOffset - prePosition < 0) {
                    mTabTextView2.setScaleX(1.2f - 0.2f * positionOffset);
                    mTabTextView2.setScaleY(1.2f - 0.2f * positionOffset);

                    mTabTextView1.setScaleX(1f + 0.2f * positionOffset);
                    mTabTextView1.setScaleY(1f + 0.2f * positionOffset);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        /**
         * 监听toolBar的竖直方向的移动
         */
        mAbl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //控制toolbar上的控件动画
                /**
                 * ObjectAnimator animator1 = ObjectAnimator.ofFloat(mIconImage, "translationX", verticalOffset * 5);
                 * 改了一下，但是还不是很完美。。
                 */
                if (proVerticalOffset - verticalOffset > 0) {
                    //向上隐藏
                    if (verticalOffset < -30) {
                        if (BuildConfig.DEBUG) Log.d("ShowListActivity", "向上隐藏");
                        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mIconImage, "translationX", -UIUtil.getActionSize
                                (ShowListActivity.this) * 10);
                        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mTvTitle, "translationY", -UIUtil.getActionSize
                                (ShowListActivity.this) * 10);
                        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mIvNvMenuIcon, "translationX", UIUtil.getActionSize
                                (ShowListActivity.this) * 10);
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.play(animator1).with(animator2).with(animator3);
                        animatorSet.setDuration(200);
                        animatorSet.start();
                    }
                } else if (proVerticalOffset - verticalOffset < 0) {
                    //向下显示
                    if (BuildConfig.DEBUG) Log.d("ShowListActivity", "向下显示");
                    ObjectAnimator animator1 = ObjectAnimator.ofFloat(mIconImage, "translationX", verticalOffset * 10);
                    animator1.start();
                    ObjectAnimator animator2 = ObjectAnimator.ofFloat(mTvTitle, "translationY", verticalOffset * 10);
                    animator2.start();
                    ObjectAnimator animator3 = ObjectAnimator.ofFloat(mIvNvMenuIcon, "translationX", -verticalOffset * 10);
                    animator3.start();
                }

                proVerticalOffset = verticalOffset;
            }
        });

        mIvNvMenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

    }

    private void initView() {
        mZhiHuFragment = new ZhiHuFragment();
        mFragments.add(mZhiHuFragment);
        mFragments.add(new WeatherFragment());
        mFragments.add(new WeatherFragment());
        mFragments.add(new WeatherFragment());
//        mFragments.add(new ZhiHuFragment());

        mPagerAdapter = new ShowListFragmentPagerAdapter(getSupportFragmentManager(), this, mFragments);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(mPosition);
        mViewPager.setOffscreenPageLimit(3);

        //        下面就来解释一下TabGravity和TabMode，
//        TabGravity:放置Tab的Gravity,有GRAVITY_CENTER 和 GRAVITY_FILL两种效果。
//        顾名思义，一个是居中，另一个是尽可能的填充（注意，GRAVITY_FILL需要和MODE_FIXED一起使用才有效果）
//        TabMode:布局中Tab的行为模式（behavior mode），有两种值：MODE_FIXED 和 MODE_SCROLLABLE。
//        MODE_FIXED:固定tabs，并同时显示所有的tabs。
//        MODE_SCROLLABLE：可滚动tabs，显示一部分tabs，在这个模式下能包含长标签和大量的tabs，最好用于用户不需要直接比较tabs。
        mTabShowList.setTabMode(TabLayout.MODE_FIXED);
        mTabShowList.setTabGravity(TabLayout.GRAVITY_CENTER);


        //设置字体颜色，int不是resID
//        mTabShowList.setTabTextColors(
//                colorResId2ColorInt(R.color.colorPrimaryDark),
//                colorResId2ColorInt(R.color.colorPrimary));
        mTabShowList.setTabTextColors(colorResId2ColorInt(ShowListActivity.this, R.color.colorWhite),
                colorResId2ColorInt(ShowListActivity.this, R.color.colorWhite));
        mTabShowList.setSelectedTabIndicatorColor(colorResId2ColorInt(ShowListActivity.this, R.color.colorPrimaryDark));
//        mTabShowList.setBackgroundColor(colorResId2ColorInt(ShowListActivity.this, itemBG[mPosition % 3]));
        mTabShowList.setupWithViewPager(mViewPager);

        mAbl.setBackgroundColor(colorResId2ColorInt(ShowListActivity.this, itemBG[mPosition % 3]));

        setTabTransitionName();
        setOtherTransitionName();

        //设置toolbar和状态栏的颜色
        mTb.setBackgroundColor(colorResId2ColorInt(ShowListActivity.this, itemBG[mPosition % 3]));
        setWindowStatusBarColor(this, colorResId2ColorInt(this, itemBG[mPosition % 3]));


        //初始化tabview的textview动画
        getTextVIewInTabView(mPosition)
                .animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(500).start();

//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(getTextVIewInTabView(mPosition), "scaleY", 1f, 1.2f);
//        ObjectAnimator animator2 = ObjectAnimator.ofFloat(getTextVIewInTabView(mPosition), "scaleX", 1f, 1.2f);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.play(animator1).with(animator2);
//        animatorSet.setDuration(500);
//        animatorSet.start();

        mTabTextView1 = getTextVIewInTabView(0);
        mTabTextView2 = getTextVIewInTabView(1);
        mTabTextView3 = getTextVIewInTabView(2);
        mTabTextView4 = getTextVIewInTabView(3);

        int textWidth1 = getTextWidth(mTabTextView1);
        int textWidth2 = getTextWidth(mTabTextView2);
        int textWidth3 = getTextWidth(mTabTextView3);
        int textWidth4 = getTextWidth(mTabTextView4);

        //因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
        mTabTextView1.setWidth((int) (textWidth1 * 1.2f));
        mTabTextView2.setWidth((int) (textWidth2 * 1.2f));
        mTabTextView3.setWidth((int) (textWidth3 * 1.2f));
        mTabTextView4.setWidth((int) (textWidth4 * 1.2f));

        mArgbEvaluator = new ArgbEvaluator();

    }

    private void setOtherTransitionName() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mIconImage.setTransitionName("mIconImage");
            mTvTitle.setTransitionName("mTvTitle");
            mIvNvMenuIcon.setTransitionName("mIvNvMenuIcon");
        }
    }

    /**
     * 关联tabLayout中的textview
     * 用反射获取tabView
     * 给每个textView设置TransitionName
     */
    private void setTabTransitionName() {
        LinearLayout tabView = null;
        for (int i = 0; i < mFragments.size(); i++) {

            TabLayout.Tab tab = mTabShowList.getTabAt(i);
            Field view = null;
            try {
                view = TabLayout.Tab.class.getDeclaredField("mView");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            view.setAccessible(true);
            try {
                tabView = (LinearLayout) view.get(tab);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            for (int j = 0; j < tabView.getChildCount(); j++) {
                View view1 = tabView.getChildAt(j);
                if (view1 instanceof TextView) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view1.setTransitionName("tab_" + i);
//                        float PXsize = ((TextView) view1).getTextSize();
//                        int spSize = UnitUtil.px2sp(ShowListActivity.this, PXsize);
//                        if (BuildConfig.DEBUG) Log.d("ShowListActivity", "PXsize:" + PXsize);
//                        if (BuildConfig.DEBUG) Log.d("ShowListActivity", "spSize:" + spSize);
//                        if (BuildConfig.DEBUG) Log.d("ShowListActivity", "tab_" + i);
                    }
                }
            }
        }
    }

    /**
     * 获取对应tabVIew中的textview
     *
     * @param position
     * @return
     */
    private TextView getTextVIewInTabView(int position) {
        LinearLayout tabView = null;
        TabLayout.Tab tab = mTabShowList.getTabAt(position);
        Field view = null;
        try {
            view = TabLayout.Tab.class.getDeclaredField("mView");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        view.setAccessible(true);
        try {
            tabView = (LinearLayout) view.get(tab);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < tabView.getChildCount(); i++) {
            View view1 = tabView.getChildAt(i);
            if (view1 instanceof TextView) {
                return (TextView) view1;
            }
        }
        return null;

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                return true;
            }
            int selectedTabPosition = mTabShowList.getSelectedTabPosition();
            switch (selectedTabPosition) {
                case 0:
                    mTabTextView1.setScaleX(1f);
                    mTabTextView1.setScaleY(1f);
                    break;
                case 1:
                    mTabTextView2.setScaleX(1f);
                    mTabTextView2.setScaleY(1f);
                    break;
                case 2:
                    mTabTextView3.setScaleX(1f);
                    mTabTextView3.setScaleY(1f);
                    break;
                case 3:
                    mTabTextView4.setScaleX(1f);
                    mTabTextView4.setScaleY(1f);
                    break;
            }
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void noNetworkConnFail() {
        UIUtil.snackNewWorkErrorMessage(mViewPager, getString(R.string.error_message_network_connections_break));
    }

    @Override
    protected void noNetworkConnSuccess() {
        mZhiHuFragment.setLoadMoreRefreshState();
    }
}
