package com.sk.mvpdemo.presenter;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.sk.mvpdemo.mdoel.FragmentsModel;
import com.sk.mvpdemo.mdoel.IFragmentModel;
import com.sk.mvpdemo.mdoel.IGoodModel;
import com.sk.mvpdemo.presenter.base.BasePresenter;
import com.sk.mvpdemo.view.IFragmentView;

import java.util.List;

/**
 * Created by smark on 2020/5/7.
 * 邮箱：smarkwzp@163.com
 */
public class FragmentsPresenter extends BasePresenter<IFragmentView> {

    IFragmentModel mIFragmentModel = new FragmentsModel();

    public void fetch() {
        if (IView.get() != null && mIFragmentModel != null) {
            mIFragmentModel.loadGoodsDada(new IFragmentModel.OnLoadListener() {
                @Override
                public void onComplete(List<Fragment> data) {
                    IView.get().loadGoodData(data);
                }

                @Override
                public void onError(String mag) {
                    IView.get().showErrorMessage(mag);
                }
            });
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("GoodsPresenter","onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("GoodsPresenter","onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("GoodsPresenter","onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("GoodsPresenter","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("GoodsPresenter","onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("GoodsPresenter","onDestroy");
    }


}
