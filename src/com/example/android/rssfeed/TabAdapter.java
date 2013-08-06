package com.example.android.rssfeed;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;

import static android.support.v4.app.Fragment.instantiate;

//import static android.app.Fragment.*;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 8/5/13
 * Time: 1:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class TabAdapter extends FragmentPagerAdapter
        implements ActionBar.TabListener , ViewPager.OnPageChangeListener{
    private final Context mContext;
    private final ActionBar mActionBar;
    private final ViewPager mViewPager;
    private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
    private final String TAG = "21st Polling:";

    static final class TabInfo{
        private final Class<?> clss;
        private final Bundle args;

        TabInfo(Class<?> _class, Bundle _args){
            clss = _class;
            args = _args;
        }
    }

    public TabAdapter(FragmentActivity fa, ViewPager pager) {
        super(fa.getSupportFragmentManager());
//        super(fa.getFragmentManager());
        mContext = fa;
        mActionBar = fa.getActionBar();
        mViewPager = pager;
        Log.d(TAG, "pager is " + mViewPager);
        mViewPager.setAdapter(this);
        mViewPager.setOnPageChangeListener(this);
    }

    public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args){
        TabInfo info = new TabInfo(clss, args);
        tab.setTag(info);
        tab.setTabListener(this);
        mTabs.add(info);
        mActionBar.addTab(tab);
        notifyDataSetChanged();
    }

    @Override
    public void onPageScrollStateChanged(int state) {


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {
        mActionBar.setSelectedNavigationItem(position);
    }

    @Override
    public  void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
        Log.v(TAG, "clicked");
        Object tag = tab.getTag();
        for (int i = 0; i<mTabs.size(); i++){
            if (mTabs.get(i) == tag){
                mViewPager.setCurrentItem(i);
            }
        }

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //Toast.makeText(mContext, "You've deselected a tab", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        TabInfo info = mTabs.get(position);
        return instantiate(mContext, info.clss.getName(), info.args);
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

}