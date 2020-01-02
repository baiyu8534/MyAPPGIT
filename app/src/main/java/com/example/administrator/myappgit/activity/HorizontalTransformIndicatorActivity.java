package com.example.administrator.myappgit.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.ShowListFragmentPagerAdapter;
import com.example.administrator.myappgit.fragment.WeatherFragment;
import com.example.administrator.myappgit.ui.HorizontalTransformIndicator;
import com.example.administrator.myappgit.ui.ShowAllDemosRvItemRecoration;
import com.example.administrator.myappgit.ui.tools.RvItemTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalTransformIndicatorActivity extends BaseActivity {
    @BindView(R.id.iv_bg)
    ImageView mIvBg;
    @BindView(R.id.hti_tab)
    HorizontalTransformIndicator mHtiTab;
    @BindView(R.id.vp)
    ViewPager mVp;

    private int selectPosition;

    private List<Fragment> mFragments = new ArrayList<>();
    private ShowListFragmentPagerAdapter mPagerAdapter;


    float preCurrentPosition = -1;
    int first = 0;
    int scrollState;
    private ShowAllDemosRvItemRecoration mShowAllDemosRvItemRecoration;
    private RvItemTransformer mRvItemTransformer;

    private int LorR = 0; //表示向左还是向右划
    private RelativeLayout viewPagerContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_transform_indicator);
        ButterKnife.bind(this);
        selectPosition = getIntent().getIntExtra("position", 0);
        initDate();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVp.setCurrentItem(selectPosition);
//        mVp.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mVp.setCurrentItem(selectPosition);
//
//            }
//        },10);
    }

    private void initDate() {
        mRvItemTransformer = new RvItemTransformer(this);
    }

    private void initView() {

        viewPagerContainer = (RelativeLayout)findViewById(R.id.pager_layout);
        WeatherFragment e = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",1000001);
        e.setArguments(bundle);

        WeatherFragment e1 = new WeatherFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("id",1000002);
        e1.setArguments(bundle1);

        WeatherFragment e2 = new WeatherFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("id",1000003);
        e2.setArguments(bundle2);

        WeatherFragment e3 = new WeatherFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putInt("id",1000004);
        e3.setArguments(bundle3);
        mFragments.add(e);
        mFragments.add(e1);
        mFragments.add(e2);
        mFragments.add(e3);
//        mFragments.add(new ZhiHuFragment());

        mPagerAdapter = new ShowListFragmentPagerAdapter(getSupportFragmentManager(), this, mFragments);
        mVp.setAdapter(mPagerAdapter);
//        mVp.setCurrentItem(3);

        mVp.setOffscreenPageLimit(5);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mVp.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                }
            });
        }
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                System.out.println("positionOffset::::::::::::::" + positionOffset);
//                System.out.println("position::::::::::::::" + position);

//                if (positionOffset!=0) {
//                    if (scrollState == 1) {//手指按下
////						if(preSelectedPage == arg0){//表示往左拉，相应的tab往右走
////							Log.i(TAG, "ux==--> 手指左滑 整体页面--> ");
////						}else {
////							Log.i(TAG, "ux==--> 手指向右 整体页面<--");
////						}
//                    }else if (scrollState==2) {
//                        if(preCurrentPosition == position){//往左拉
//                            Log.i(TAG, "ux==--> 手指左滑 整体页面--> 页面向右");

//                        }else{//表示往右拉
//                            Log.i(TAG, "ux==--> 手指右滑 整体页面-->  页面向左");
//                        }

//                    }
//                }
//                if (first == 0 && positionOffset != 0) {
//                    if (positionOffset < 0.5) {
//                        first = 1;
//                    } else if (positionOffset > 0.5) {
//                        first = -1;
//                    }
//                }
                if (positionOffset != 0 && preCurrentPosition == -1) {
                    preCurrentPosition = positionOffset;
                }
//
//                //手指向右滑动 positionOffset > 0.5  first == -1
//                System.out.println(preCurrentPosition + "::::::::::" + first);
                if (preCurrentPosition < 0.5 && preCurrentPosition != 0) {
                    LorR = 1;
                } else if (preCurrentPosition > 0.5) {
                    LorR = -1;
                }

                for (int i = 0; i < mFragments.size(); i++) {
                    if (i == position) {
                        mRvItemTransformer.transformPage(((WeatherFragment) mFragments.get(i)).mRv, positionOffset, LorR);
                    } else {
                        mRvItemTransformer.transformPage(((WeatherFragment) mFragments.get(i)).mRv, 1 - positionOffset, LorR);
                    }
                }

//                if (first == 1) {
//                    if (preCurrentPosition < 0.5) {
//
//                        for (int i = 0; i < mFragments.size(); i++) {
//                            if (i == position) {
//                                mRvItemTransformer.transformPage(((WeatherFragment) mFragments.get(i)).mRv, 1 - positionOffset);
//                            } else {
//                                mRvItemTransformer.transformPage(((WeatherFragment) mFragments.get(i)).mRv, positionOffset);
//                            }
//                        }
//                    } else if (preCurrentPosition > 0.5) {
//                        for (int i = 0; i < mFragments.size(); i++) {
//                            if (i == position) {
//                                mRvItemTransformer.transformPage(((WeatherFragment) mFragments.get(i)).mRv, positionOffset);
//                            } else {
//                                mRvItemTransformer.transformPage(((WeatherFragment) mFragments.get(i)).mRv, 1 - positionOffset);
//                            }
//                        }
//                    }
//                } else if (first == -1) {
//                    if (preCurrentPosition < 0.5) {
//
//                        for (int i = 0; i < mFragments.size(); i++) {
//                            if (i == position) {
//                                mRvItemTransformer.transformPage(((WeatherFragment) mFragments.get(i)).mRv, positionOffset);
//                            } else {
//                                mRvItemTransformer.transformPage(((WeatherFragment) mFragments.get(i)).mRv, 1 - positionOffset);
//                            }
//                        }
//                    } else if (preCurrentPosition > 0.5) {
//                        for (int i = 0; i < mFragments.size(); i++) {
//                            if (i == position) {
//                                mRvItemTransformer.transformPage(((WeatherFragment) mFragments.get(i)).mRv, 1 - positionOffset);
//                            } else {
//                                mRvItemTransformer.transformPage(((WeatherFragment) mFragments.get(i)).mRv, positionOffset);
//                            }
//                        }
//                    }
//                }
                if (viewPagerContainer != null) {
                    viewPagerContainer.invalidate();
                }

            }

            @Override
            public void onPageSelected(int position) {
//                currentPosition = position;
//                System.out.println("onPageSelected::::::::::::::::::");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                scrollState = state;
//                preCurrentPosition = currentPosition;
//                System.out.println("onPageScrollStateChanged::::::::::::::::::"+state);
                if (state == 0) {
                    preCurrentPosition = -1;
                }

            }
        });

        mHtiTab.initialization(mVp, "tab_cv_", R.layout.item_transform_indicator, selectPosition);
        mHtiTab.setOnHItemClickListener(new HorizontalTransformIndicator.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
            }
        });
    }
}
