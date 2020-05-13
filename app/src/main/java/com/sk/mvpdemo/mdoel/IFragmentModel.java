package com.sk.mvpdemo.mdoel;

import androidx.fragment.app.Fragment;

import java.util.List;

/**
 * Created by smark on 2020/5/7.
 * 邮箱：smarkwzp@163.com
 */
public interface IFragmentModel {
    void loadGoodsDada(OnLoadListener loadListener);

    interface OnLoadListener {

        void onComplete(List<Fragment> data);

        void onError(String mag);

    }
}
