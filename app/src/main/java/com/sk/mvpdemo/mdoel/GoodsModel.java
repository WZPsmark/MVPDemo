package com.sk.mvpdemo.mdoel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smark on 2020/5/7.
 * 邮箱：smarkwzp@163.com
 */
public class GoodsModel implements IGoodModel {

    @Override
    public void loadGoodsDada(OnLoadListener loadListener) {
        loadListener.onComplete(getDada());
    }



    private List<String> getDada() {
        List<String> list = new ArrayList<>();
        list.add("小华");
        list.add("小名");
        list.add("alven");
        list.add("小黑虎");
        list.add("大竹炮");
        list.add("时间比较");
        list.add("时候还看i");
        list.add("和登就会是");
        list.add("的机械表");
        list.add("大家看");
        return list;
    }
}
