package cn.lc.model.ui.login.presenter;

import cn.lc.model.framework.base.BaseResponse;
import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.login.bean.CaptchaBean;
import cn.lc.model.ui.login.view.RegistStep2View;
import mvp.cn.rx.MvpRxSimplePresenter;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class RegistStep2Presenter extends MvpRxSimplePresenter<RegistStep2View> {
    public void register(String mobile,String password,String servicetypeid ,String captcha) {
        LogUtils.d("registers2is发出请求");
        Observable login = RetrofitUtils.getInstance().register(mobile,password,servicetypeid,captcha);
        getNetWork(login, new RetrofitCallBack<CommonBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...."+e);
            }
            @Override
            public void onSuccess(CommonBean baseResponse) {
                getView().RegisterSucc(baseResponse);
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
