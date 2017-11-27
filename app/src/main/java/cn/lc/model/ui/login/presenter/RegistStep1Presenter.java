package cn.lc.model.ui.login.presenter;

import cn.lc.model.framework.base.BaseResponse;
import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.login.bean.CaptchaBean;
import cn.lc.model.ui.login.bean.LoginBean;
import cn.lc.model.ui.login.view.RegistStep1View;
import mvp.cn.rx.MvpRxSimplePresenter;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class RegistStep1Presenter extends MvpRxSimplePresenter<RegistStep1View> {


    public void getData(String mobie) {
        LogUtils.d("registers1发出请求");
        Observable login = RetrofitUtils.getInstance().capture(mobie);
        getNetWork(login, new RetrofitCallBack<CaptchaBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...."+e);
            }
            @Override
            public void onSuccess(CaptchaBean baseResponse) {
                getView().getSucc(baseResponse);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void checkcapture(String mobie,String serviceid,String capture ,String uid) {
        LogUtils.d("registers1is发出请求");
        Observable login = RetrofitUtils.getInstance().checkcapture(mobie,serviceid,capture,uid);
        getNetWork(login, new RetrofitCallBack<CommonBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...."+e);
            }
            @Override
            public void onSuccess(CommonBean baseResponse) {
                getView().checkSucc(baseResponse);
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
