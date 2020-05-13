package com.sk.mvpdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sk.mvpdemo.adapter.MyFragmentPagerAdapter;
import com.sk.mvpdemo.base.BaseActivity;
import com.sk.mvpdemo.fragment.TestFragment1;
import com.sk.mvpdemo.fragment.TestFragment2;
import com.sk.mvpdemo.fragment.TestFragment3;
import com.sk.mvpdemo.fragment.TestFragment4;
import com.sk.mvpdemo.fragment.TestFragment5;
import com.sk.mvpdemo.presenter.FragmentsPresenter;
import com.sk.mvpdemo.presenter.GoodsPresenter;
import com.sk.mvpdemo.view.IFragmentView;
import com.sk.mvpdemo.view.IGoodsView;

import java.util.ArrayList;
import java.util.List;

public class TestFragmentActivity extends BaseActivity<FragmentsPresenter, IFragmentView> implements IFragmentView{

    ViewPager mViewPager;
    private BottomNavigationView bottomNavigationView;
    List<Fragment> fragmentList;
    MyFragmentPagerAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_test_fragment;
    }

    @Override
    protected void init() {
        super.init();
        mViewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        fragmentList = new ArrayList<>();

        presenter.fetch();

    }

    @Override
    protected FragmentsPresenter createPresenter() {
        return new FragmentsPresenter();
    }

    ViewPager.OnPageChangeListener viewpagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            int itemId = R.id.fragment_1;
            switch (i) {
                case 0:
                    itemId = R.id.fragment_1;
                    break;
                case 1:
                    itemId = R.id.fragment_2;
                    break;
                case 2:
                    itemId = R.id.fragment_3;
                    break;
                case 3:
                    itemId = R.id.fragment_4;
                    break;
                case 4:
                    itemId = R.id.fragment_5;
                    break;
            }
            bottomNavigationView.setSelectedItemId(itemId);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.fragment_1:
                    mViewPager.setCurrentItem(0, true);
                    return true;
                case R.id.fragment_2:
                    mViewPager.setCurrentItem(1, true);
                    return true;
                case R.id.fragment_3:
                    mViewPager.setCurrentItem(2, true);
                    return true;
                case R.id.fragment_4:
                    mViewPager.setCurrentItem(3, true);
                    return true;
                case R.id.fragment_5:
                    mViewPager.setCurrentItem(4, true);
                    return true;
            }
            return false;
        }

    };

    @Override
    public void loadGoodData(List<Fragment> data) {
        fragmentList.clear();
        fragmentList.addAll(data);
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(viewpagerChangeListener);
    }

    @Override
    public void showErrorMessage(String msg) {

    }
}
