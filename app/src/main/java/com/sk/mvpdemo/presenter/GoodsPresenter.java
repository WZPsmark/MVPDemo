package com.sk.mvpdemo.presenter;

import android.util.Log;

import com.sk.mvpdemo.mdoel.GoodsModel;
import com.sk.mvpdemo.mdoel.IGoodModel;
import com.sk.mvpdemo.presenter.base.BasePresenter;
import com.sk.mvpdemo.view.IGoodsView;

import java.util.List;

/**
 * Created by smark on 2020/5/7.
 * 邮箱：smarkwzp@163.com
 */
public class GoodsPresenter extends BasePresenter<IGoodsView> {

    IGoodModel mIGoodModel = new GoodsModel();

    public void fetch() {
        if (IView.get() != null && mIGoodModel != null) {
            mIGoodModel.loadGoodsDada(new IGoodModel.OnLoadListener() {
                @Override
                public void onComplete(List<String> data) {
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
