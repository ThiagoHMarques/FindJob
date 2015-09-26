package com.example.thiago.findjob.activitys;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.adapters.TabsAdapter;
import com.example.thiago.findjob.extras.SlidingTabLayout;

public class Cadastrar extends ActionBarActivity {
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        mToolBar = (Toolbar)findViewById(R.id.tb_main);
        mToolBar.setTitle("Cadastrar");
        //TABS
        mViewPager = (ViewPager)findViewById(R.id.vp_tabs);
        mViewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(),this));

        mSlidingTabLayout = (SlidingTabLayout)findViewById(R.id.stl_tabs);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor( R.color.colorPrimary ));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.accent));
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(mViewPager);
    }
}
