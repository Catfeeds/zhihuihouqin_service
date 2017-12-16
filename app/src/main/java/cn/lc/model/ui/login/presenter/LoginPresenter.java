package cn.lc.model.ui.login.presenter;

import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.login.bean.LoginBean;
import cn.lc.model.ui.login.view.LoginView;
import mvp.cn.rx.MvpRxSimplePresenter;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class LoginPresenter extends MvpRxSimplePresenter<LoginView> {

    public void getData(String mobie,String psw,String servicetype) {
        LogUtils.d("LoginPresenter发出请求");
        getView().showProgressDialog();
        Observable login = RetrofitUtils.getInstance().login(mobie,psw,servicetype);
        getNetWork(login, new RetrofitCallBack<LoginBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...."+e);
                getView().dismissProgressDialog();
            }
            @Override
            public void onSuccess(LoginBean mResponse) {
                if (mResponse == null)
                    return;
               if (mResponse.errCode == 0) {
                   getView().loginSucc(mResponse);
                } else {
                    getView().showToast(mResponse.msg);
                }
            }

            @Override
            public void onComplete() {
                getView().dismissProgressDialog();
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
