package cn.lc.model.ui.main.presenter;

import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.view.Tab1View;
import cn.lc.model.ui.tab1.bean.StationeryBean;
import mvp.cn.rx.MvpRxSimplePresenter;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class Tab1Presenter extends MvpRxSimplePresenter<Tab1View> {

    public void getOrder(String serviceType, String page, String limit, String orderstatus) {
        LogUtils.d("tab1_1发出请求");
        Observable login = RetrofitUtils.getInstance().getOrder(serviceType , page , limit , orderstatus);
        getNetWork(login, new RetrofitCallBack<StationeryBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...." + e);
            }

            @Override
            public void onSuccess(StationeryBean baseResponse) {
                getView().getSucc(baseResponse);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
