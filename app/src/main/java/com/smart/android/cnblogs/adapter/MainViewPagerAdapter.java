package com.smart.android.cnblogs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Copyright © 2015 Phoenix New Media Limited All Rights Reserved.
 * Created by fengjh on 2015/7/1.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private String[] mTabTitle;
    private ArrayList<Fragment> mFragmentList = null;

    public MainViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragmentList, String[] mTabTitle) {
        super(fm);
        this.mFragmentList = mFragmentList;
        this.mTabTitle = mTabTitle;
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
        return mTabTitle[position];
    }
}
