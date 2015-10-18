package com.example.thiago.findjob.activitys;


import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.adapters.TabsAdapter;
import com.example.thiago.findjob.extras.SlidingTabLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
