package com.smart.android.cnblogs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smart.android.cnblogs.R;
import com.smart.android.cnblogs.adapter.MainViewPagerAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright © 2015 Phoenix New Media Limited All Rights Reserved.
 * Created by fengjh on 2015/7/1.
 */
public class MainFragment extends Fragment {

    @Bind(R.id.main_tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.main_viewPager)
    ViewPager mViewPager;

    private View mRootView;
    private String[] mTabTitle;
    private ArrayList<Fragment> mFragmentList = null;
    private MainViewPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTabTitle = getActivity().getResources().getStringArray(R.array.main_title);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,mRootView);
        initViewPager();
        initTabTitle();
        adapter = new MainViewPagerAdapter(getActivity().getSupportFragmentManager(), mFragmentList, mTabTitle);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
        return mRootView;
    }

    private void initTabTitle() {
        for (int i=0;i<mTabTitle.length;i++) {
//           mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitle[i]));
            mTabLayout.addTab(mTabLayout.newTab());
        }
    }

    private void initViewPager(){
        if (mFragmentList==null){
            mFragmentList = new ArrayList<Fragment>();
        }
        for (int i=0;i<mTabTitle.length;i++){
            MainViewPagerFragment fragment = new MainViewPagerFragment();
            mFragmentList.add(fragment);
        }
    }
}
