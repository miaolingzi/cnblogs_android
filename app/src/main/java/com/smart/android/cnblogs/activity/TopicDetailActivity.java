package com.smart.android.cnblogs.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.smart.android.cnblogs.R;
import com.smart.android.cnblogs.adapter.MainViewPagerAdapter;
import com.smart.android.cnblogs.fragment.MainViewPagerFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by feng on 2015/7/5.
 */
public class TopicDetailActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolBar;
    @Bind(R.id.ivLogo)
    ImageView mImageLogo;
    @Bind(R.id.topic_tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.topic_viewPager)
    ViewPager mViewPager;
    private String mTopic;
    private String[] mTopicTitle;
    private ArrayList<Fragment> mFragmentList = null;
    private MainViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            int position = bundle.getInt("position");
            String[] topics = bundle.getStringArray("topic");
            mTopic = topics[position];
            initTopicTitle(position);
        }
        ButterKnife.bind(this);
        mImageLogo.setVisibility(View.GONE);
        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
            mToolBar.setNavigationIcon(R.drawable.ic_menu);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(mTopic);
        }
        initTabLayout();
        initViewPager();
        adapter = new MainViewPagerAdapter(getSupportFragmentManager(), mFragmentList, mTopicTitle);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }

    private void initTopicTitle(int position) {
        switch (position) {
            case 0:
                mTopicTitle = getResources().getStringArray(R.array.topic_net);
                break;
            case 1:
                mTopicTitle = getResources().getStringArray(R.array.topic_programme_language);
                break;
            case 2:
                mTopicTitle = getResources().getStringArray(R.array.topic_software_design);
                break;
            case 3:
                mTopicTitle = getResources().getStringArray(R.array.topic_web);
                break;
            case 4:
                mTopicTitle = getResources().getStringArray(R.array.topic_mobile_dev);
                break;
            case 5:
                mTopicTitle = getResources().getStringArray(R.array.topic_software_engineer);
                break;
            case 6:
                mTopicTitle = getResources().getStringArray(R.array.topic_database_technique);
                break;
        }
    }

    private void initTabLayout() {
        for (int i = 0; i < mTopicTitle.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab());
        }
    }

    private void initViewPager() {
        if (mFragmentList == null) {
            mFragmentList = new ArrayList<Fragment>();
        }
        for (int i = 0; i < mTopicTitle.length; i++) {
            MainViewPagerFragment fragment = new MainViewPagerFragment();
            mFragmentList.add(fragment);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            TopicDetailActivity.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
