package com.smart.android.cnblogs.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.smart.android.cnblogs.R;
import com.smart.android.cnblogs.adapter.TopicRecyclerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by feng on 2015/7/5.
 */
public class TopicActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolBar;
    @Bind(R.id.ivLogo)
    ImageView mImageLogo;
    @Bind(R.id.topic_recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_topic);
        ButterKnife.bind(this);
        mImageLogo.setVisibility(View.GONE);
        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
            mToolBar.setNavigationIcon(R.drawable.ic_menu);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle("专题");
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        String[] title = getResources().getStringArray(R.array.main_title);
        final String[] topic = new String[title.length - 2];
        for (int i = 0; i < title.length - 2; i++) {
            topic[i] = title[i + 2];
        }
        TopicRecyclerAdapter adapter = new TopicRecyclerAdapter(this,topic);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new TopicRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(TopicActivity.this,"click: "+position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TopicActivity.this, TopicDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                bundle.putStringArray("topic",topic);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(TopicActivity.this,"long click: "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            TopicActivity.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
