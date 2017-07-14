package com.example.administrator.myappgit.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myappgit.BuildConfig;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.ShowListFragmentPagerAdapter;
import com.example.administrator.myappgit.fragment.ZhiHuFragment;
import com.example.administrator.myappgit.utils.UnitUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/7/13 0013.
 */

public class ShowListActivity extends BaseActivity {

    @BindView(R.id.icon_image)
    CircleImageView mIconImage;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab_show_list)
    TabLayout mTabShowList;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.iv_nv_menu_icon)
    ImageView mIvNvMenuIcon;

    private List<Fragment> mFragments = new ArrayList<>();
    private ShowListFragmentPagerAdapter mPagerAdapter;
    //首页的点击position
    private int mPosition;

    private int[] itemBG = {
            R.color.mainRvItemBg1,
            R.color.mainRvItemBg2,
            R.color.mainRvItemBg3
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_layout);
        ButterKnife.bind(this);
        mPosition = getIntent().getIntExtra("position", 0);
        initView();
    }

    private void initView() {

        mFragments.add(new ZhiHuFragment());
        mFragments.add(new ZhiHuFragment());
        mFragments.add(new ZhiHuFragment());
        mFragments.add(new ZhiHuFragment());
//        mFragments.add(new ZhiHuFragment());

        mPagerAdapter = new ShowListFragmentPagerAdapter(getSupportFragmentManager(), this, mFragments);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(mPosition);

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
//                getResources().getColor(R.color.colorPrimaryDark),
//                getResources().getColor(R.color.colorPrimary));
        mTabShowList.setTabTextColors(getResources().getColor(R.color.colorWhite), getResources().getColor(R.color.colorWhite));
        mTabShowList.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark));
        mTabShowList.setBackgroundColor(getResources().getColor(itemBG[mPosition % 3]));
        mTabShowList.setupWithViewPager(mViewPager);

        setTabTransitionName();


    }

    /**
     * 关联tabLayout中的textview
     * 用反射获取tabView
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
                        float PXsize = ((TextView) view1).getTextSize();
                        int spSize = UnitUtil.px2sp(ShowListActivity.this, PXsize);
                        if (BuildConfig.DEBUG) Log.d("ShowListActivity", "PXsize:" + PXsize);
                        if (BuildConfig.DEBUG) Log.d("ShowListActivity", "spSize:" + spSize);
                        if (BuildConfig.DEBUG) Log.d("ShowListActivity", "tab_" + i);
                    }
                }
            }
        }
    }
}
