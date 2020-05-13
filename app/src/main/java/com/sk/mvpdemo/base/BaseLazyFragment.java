package com.sk.mvpdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.sk.mvpdemo.BuildConfig;
import com.sk.mvpdemo.presenter.base.BasePresenter;
import com.sk.mvpdemo.view.base.IBaseView;

import java.util.List;

/**
 * Created by smark on 2020/5/12.
 * 邮箱：smarkwzp@163.com
 * fragment 基类，实现viewPager+fragment \viewPager+fragment+fragment嵌套懒加载
 */
public abstract class BaseLazyFragment<T extends BasePresenter, V extends IBaseView> extends Fragment {
    private final String TAG = fragmentTagName();
    /**
     * 持有表示层
     */
    protected T presenter;
    /**
     * fragment 根view
     */
    protected View rootView = null;
    /**
     * 判断fragment根 view 是否加载完成
     */
    boolean isViewCreated = false;
    /**
     * 记录当前fragment显示状态
     */
    boolean currentVisibleState = false;
    /**
     * 记录fragment是否是第一次显示
     */
    boolean mIsFirstVisible = true;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        log("onAttach");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("onCreate");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(), container, false);
        }
        //便于子类初始化view
        initView(rootView);
        isViewCreated = true;
        log("onCreateView");
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        log("onViewCreated");
        presenter = createPresenter();
        presenter.attachView((V) this);
        init();
        if (!isHidden() && getUserVisibleHint()) {
            dispatchUserVisibleHint(true);
        }
    }

    /**
     * 根据viewpager的源码，
     * 在FragmentPagerAdapter的setPrimaryItem方法中会调用此方法更新fragment的显示和隐藏状态
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        log("setUserVisibleHint：" + isVisibleToUser);
        if (isViewCreated) {//只有当view被创建后才做状态分发
            if (isVisibleToUser && !currentVisibleState) {
                dispatchUserVisibleHint(true);
            } else if (!isVisibleToUser && currentVisibleState) {
                dispatchUserVisibleHint(false);
            }
        }
    }

    /**
     * 当使用FragmentTransaction来控制fragment的hide和show时，此方法会被调用，需要处理
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        log("onHiddenChanged:" + hidden);
        dispatchUserVisibleHint(!hidden);
    }

    @Override
    public void onResume() {
        super.onResume();
        log("onResume");
        //因为viewpager会默认有一个缓存fragment，此判断是避免第一次滑动不可见fragment不会执行分发
        if (!mIsFirstVisible) {
            //此判断是避免用户在当前activity跳转到其他activity后回来，
            // 规避当前activity对所有缓存的fragment进行onResume的调用，只分发可见事件给当前可见的fragment
            if (!isHidden() && !currentVisibleState && getUserVisibleHint()) {
                dispatchUserVisibleHint(true);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        log("onPause");
        if (currentVisibleState && getUserVisibleHint()) {
            dispatchUserVisibleHint(false);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        log("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        log("onDestroyView");
        isViewCreated = false;
        mIsFirstVisible = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        log("onDetach");
    }

    /**
     * 统一处理fragment的用户可见事件分发
     *
     * @param isVisible
     */
    private void dispatchUserVisibleHint(boolean isVisible) {
        log("dispatchUserVisibleHint:" + isVisible);
        //此判断试用于viewpager+fragment嵌套子fragment的时候，
        // 防止默认加载不可见fragment的子fragment的数据
        if (isVisible && isParentInvisible()) {
            return;
        }
        if (currentVisibleState == isVisible) {
            return;
        }
        currentVisibleState = isVisible;
        if (isVisible) {
            if (mIsFirstVisible) {
                mIsFirstVisible = false;
                onFragmentFirstVisible();
            }
            onFragmentResume();
            //在双重ViewPager嵌套的情况下，当嵌套父fragment可见时，
            // 需要分发可见事件给子fragment 如无此场景或需求可忽略
            dispatchChildVisibleState(true);
        } else {
            onFragmentPause();
            dispatchChildVisibleState(false);
        }
    }

    /**
     * 判断父fragment是否可见
     *
     * @return
     */
    private boolean isParentInvisible() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof BaseLazyFragment) {
            BaseLazyFragment lazyFragment = (BaseLazyFragment) parentFragment;
            return lazyFragment.isSupportVisible();
        }
        return false;
    }


    private boolean isSupportVisible() {
        return currentVisibleState;
    }


    /**
     * 分发双重嵌套子fragment的可见事件
     *
     * @param visible
     */
    private void dispatchChildVisibleState(boolean visible) {
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof BaseLazyFragment &&
                        !fragment.isHidden() &&
                        fragment.getUserVisibleHint()) {
                    ((BaseLazyFragment) fragment).dispatchUserVisibleHint(visible);
                }
            }
        }
    }


    /**
     * 初始化需要实现
     */
    protected void init() {
        //注册生命周期监听
        getLifecycle().addObserver(presenter);
    }

    protected void onFragmentFirstVisible() {

    }

    /**
     * 此方法实现懒加载
     */
    protected void onFragmentResume() {
        log("onFragmentResume " + "经过分发后的resume,开始相关操作耗时");
    }

    /**
     * 实现此方法实现中断加载
     */
    protected void onFragmentPause() {
        log("onFragmentPause" + " 经过分发后的Pause,结束相关操作耗时");
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void initView(View rootView);

    protected abstract T createPresenter();

    protected abstract String fragmentTagName();

    /**
     * 打印使用
     *
     * @param logMessage
     */
    private void log(String logMessage) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, logMessage);
        }
    }

}
