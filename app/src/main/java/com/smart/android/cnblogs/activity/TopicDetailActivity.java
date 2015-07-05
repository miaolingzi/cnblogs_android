package com.smart.android.cnblogs.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.smart.android.cnblogs.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by feng on 2015/7/5.
 */
public class TopicDetailActivity extends AppCompatActivity{

    @Bind(R.id.toolbar)
    Toolbar mToolBar;
    @Bind(R.id.ivLogo)
    ImageView mImageLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);
        ButterKnife.bind(this);
        mImageLogo.setVisibility(View.GONE);
        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
            mToolBar.setNavigationIcon(R.drawable.ic_menu);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle("专题详细");
        }
    }
}
