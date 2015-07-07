package com.smart.android.cnblogs.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.smart.android.cnblogs.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by feng on 2015/7/5.
 */
public class SettingsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolBar;
    @Bind(R.id.ivLogo)
    ImageView mImageLogo;
    @Bind(R.id.text_market_comment)
    TextView mMarketComment;

    @OnClick(R.id.text_market_comment) public void toMarketComment(View view){
//        String mAddress = "market://details?id=" + getPackageName();
        String mAddress = "market://details?id=com.smart.questionbank";
        Intent marketIntent = new Intent("android.intent.action.VIEW");
        marketIntent .setData(Uri.parse(mAddress));
        startActivity(marketIntent );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_setttings);
        ButterKnife.bind(this);
        mImageLogo.setVisibility(View.GONE);
        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
            mToolBar.setNavigationIcon(R.drawable.ic_menu);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle("设置");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            SettingsActivity.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
