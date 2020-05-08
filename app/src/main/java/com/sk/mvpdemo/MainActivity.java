package com.sk.mvpdemo;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sk.mvpdemo.adapter.RVAdapter;
import com.sk.mvpdemo.base.BaseActivity;
import com.sk.mvpdemo.presenter.GoodsPresenter;
import com.sk.mvpdemo.view.IGoodsView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<GoodsPresenter,IGoodsView> implements IGoodsView {

    RecyclerView mRecyclerView;
    RVAdapter mAdapter;
    private List<String> datas;


    @Override
    protected void init() {
        super.init();
        mRecyclerView = findViewById(R.id.recycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        datas = new ArrayList<>();
        mAdapter = new RVAdapter(datas, this);
        mRecyclerView.setAdapter(mAdapter);
        presenter.fetch();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected GoodsPresenter createPresenter() {
        return new GoodsPresenter();
    }

    @Override
    public void loadGoodData(List<String> data) {
        datas.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String msg) {

    }
}
