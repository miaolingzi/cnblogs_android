package com.smart.android.cnblogs.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.smart.android.cnblogs.R;
import com.smart.android.cnblogs.adapter.MainViewPagerAdapter;
import com.smart.android.cnblogs.fragment.MainFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolBar;
    @Bind(R.id.main_nav_view)
    NavigationView mNavigationView;
    @Bind(R.id.main_drawerLayout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.main_linear_container)
    LinearLayout mLinearContainer;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private MainFragment fragment;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
            mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close);
            mActionBarDrawerToggle.syncState();
            mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
            setupDrawerContent(mNavigationView);
        }
        fragment = new MainFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment);
        transaction.replace(R.id.main_linear_container, fragment, "mainFragment");
        transaction.commit();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        if (!menuItem.isChecked()) {
                            transaction.remove(fragment);
                            transaction.replace(R.id.main_linear_container, fragment, "mainFragment");
                        }
                        break;
                    case R.id.nav_topic:
                        openActivity(TopicActivity.class, null);
                        break;
                    case R.id.nav_collection:
                        openActivity(CollectionActivity.class, null);
                        break;
                    case R.id.nav_settings:
                        openActivity(SettingsActivity.class, null);
                        break;
                }
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawers();
            } else {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openActivity(final Class<?> cls, final Bundle bundle) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, cls);
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                startActivity(intent);
            }
        }, 300);
    }
}
