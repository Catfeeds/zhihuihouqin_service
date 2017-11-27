package cn.lc.model.ui.login.presenter;

import cn.lc.model.framework.base.BaseResponse;
import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.login.bean.LoginBean;
import cn.lc.model.ui.login.view.LoginView;
import mvp.cn.rx.MvpRxSimplePresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class LoginPresenter extends MvpRxSimplePresenter<LoginView> {

    public void getData(String mobie,String psw,String servicetype) {
        LogUtils.d("LoginPresenter发出请求");
        Observable login = RetrofitUtils.getInstance().login(mobie,psw,servicetype);
        getNetWork(login, new RetrofitCallBack<LoginBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...."+e);
            }
            @Override
            public void onSuccess(LoginBean baseResponse) {
                getView().loginSucc(baseResponse);
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
