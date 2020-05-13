package com.sk.mvpdemo.adapter;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by smark on 2020/5/12.
 * 邮箱：smarkwzp@163.com
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "MyFragmentPagerAdapter";

    private List<Fragment> fragmentList;
    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> flist) {
        super(fm);
        fragmentList = flist;
    }
    @Override
    public Fragment getItem(int i) {
        Log.d(TAG, "getItem: " + i);
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

}
