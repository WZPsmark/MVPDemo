package com.sk.mvpdemo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sk.mvpdemo.presenter.base.BasePresenter;
import com.sk.mvpdemo.view.base.IBaseView;

/**
 * Created by smark on 2020/5/7.
 * 邮箱：smarkwzp@163.com
 */
public abstract class BaseActivity<T extends BasePresenter, V extends IBaseView> extends AppCompatActivity {

    //持有表示层
    protected T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        presenter = createPresenter();
        presenter.attachView((V) this);

        init();
    }

    protected abstract int getContentView();

    protected abstract T createPresenter();

    /**
     * 初始化
     */
    protected void init() {
        //注册生命周期监听
        getLifecycle().addObserver(presenter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
