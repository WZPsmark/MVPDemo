package com.sk.mvpdemo.presenter.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.sk.mvpdemo.view.base.IBaseView;

import java.lang.ref.WeakReference;

/**
 * Created by smark on 2020/5/7.
 * 邮箱：smarkwzp@163.com
 */
public class BasePresenter<T extends IBaseView> implements LifecycleObserver {

    WeakReference<T> IView;

    /**
     * 绑定View
     * @param view
     */
    public void attachView(T view) {
        IView = new WeakReference<>(view);
    }


    /**
     * 解绑view
     * @param
     */
    public void detachView() {
        if (IView != null) {
            IView.clear();
            IView = null;
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(){

    }

}
