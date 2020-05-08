package com.sk.mvpdemo.mdoel;

import java.util.List;

/**
 * Created by smark on 2020/5/7.
 * 邮箱：smarkwzp@163.com
 */
public interface IGoodModel {
    void loadGoodsDada(OnLoadListener loadListener);

    interface OnLoadListener {

        void onComplete(List<String> data);

        void onError(String mag);

    }
}
