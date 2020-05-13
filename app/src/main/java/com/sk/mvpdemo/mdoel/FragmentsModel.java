package com.sk.mvpdemo.mdoel;

import androidx.fragment.app.Fragment;

import com.sk.mvpdemo.fragment.TestFragment1;
import com.sk.mvpdemo.fragment.TestFragment2;
import com.sk.mvpdemo.fragment.TestFragment3;
import com.sk.mvpdemo.fragment.TestFragment4;
import com.sk.mvpdemo.fragment.TestFragment5;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smark on 2020/5/7.
 * 邮箱：smarkwzp@163.com
 */
public class FragmentsModel implements IFragmentModel {

    @Override
    public void loadGoodsDada(OnLoadListener loadListener) {
        loadListener.onComplete(getDada());
    }


    private List<Fragment> getDada() {
        List<Fragment> list = new ArrayList<>();
        list.add(TestFragment1.newIntance("fragment1"));
        list.add(TestFragment2.newIntance("fragment2"));
        list.add(TestFragment3.newIntance("fragment3"));
        list.add(TestFragment4.newIntance("fragment4"));
        list.add(TestFragment5.newIntance("fragment5"));

        return list;
    }
}
