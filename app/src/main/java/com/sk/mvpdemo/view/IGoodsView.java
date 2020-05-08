package com.sk.mvpdemo.view;

import com.sk.mvpdemo.view.base.IBaseView;

import java.util.List;

/**
 * Created by smark on 2020/5/7.
 * 邮箱：smarkwzp@163.com
 */
public interface IGoodsView extends IBaseView {

    void loadGoodData(List<String> data);
}
