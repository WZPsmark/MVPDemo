package com.sk.mvpdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.sk.mvpdemo.MainActivity;
import com.sk.mvpdemo.R;
import com.sk.mvpdemo.base.BaseLazyFragment;
import com.sk.mvpdemo.presenter.GoodsPresenter;
import com.sk.mvpdemo.view.IGoodsView;

import java.util.List;

/**
 * Created by smark on 2020/5/12.
 * 邮箱：smarkwzp@163.com
 */
public class TestFragment3 extends BaseLazyFragment<GoodsPresenter, IGoodsView> implements IGoodsView {
    TextView tv_text;
    Button btn;
    Bundle bundle ;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_test;
    }

    public static Fragment newIntance(String name) {
        TestFragment3 fragment = new TestFragment3();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView(View rootView) {
        tv_text = rootView.findViewById(R.id.tv_text);
        btn = rootView.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
    }

    @Override
    protected GoodsPresenter createPresenter() {
        return new GoodsPresenter();
    }

    @Override
    protected String fragmentTagName() {
        return TestFragment3.class.getName();
    }

    @Override
    protected void init() {
        super.init();
       bundle = getArguments();
        tv_text.setText((String) bundle.get("name"));
    }


    @Override
    protected void onFragmentResume() {
        super.onFragmentResume();
    }

    @Override
    protected void onFragmentPause() {
        super.onFragmentPause();
    }

    @Override
    public void loadGoodData(List<String> data) {

    }

    @Override
    public void showErrorMessage(String msg) {

    }
}
