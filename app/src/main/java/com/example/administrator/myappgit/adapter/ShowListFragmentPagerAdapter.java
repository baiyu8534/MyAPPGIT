package com.example.administrator.myappgit.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.ui.VariationTabLayout;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class ShowListFragmentPagerAdapter extends FragmentPagerAdapter implements VariationTabLayout.IMyOneTabLayoutIconAdapter {

    private Context mContext;
    private List<Fragment> mFragmentList;

    private int[] titleRes = {
            R.string.rv_main_item_1,
            R.string.rv_main_item_2,
//            R.string.rv_main_item_3,
            R.string.rv_main_item_4,
            R.string.rv_main_item_5
    };

    public ShowListFragmentPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragmentList) {
        super(fm);
        mContext = context;
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(titleRes[position]);
    }

    @Override
    public int getTabIcon(int position) {
        switch (position){
            case 0:
                return R.drawable.ic_chrome_reader_mode_black_48dp;
            case 1:
                return R.drawable.ic_description_black_48dp;
            case 2:
                return R.drawable.ic_crop_original_black_48dp;
            case 3:
                return R.drawable.ic_filter_drama_black_48dp;
        }
        return R.drawable.ic_chrome_reader_mode_black_48dp;
    }
}
