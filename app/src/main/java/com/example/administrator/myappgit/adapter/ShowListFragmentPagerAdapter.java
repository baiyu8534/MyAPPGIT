package com.example.administrator.myappgit.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.myappgit.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class ShowListFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<Fragment> mFragmentList;

    private int[] titleRes = {
            R.string.rv_main_item_1,
            R.string.rv_main_item_2,
            R.string.rv_main_item_3,
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
}
