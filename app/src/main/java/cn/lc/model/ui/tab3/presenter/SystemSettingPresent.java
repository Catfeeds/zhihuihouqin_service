package cn.lc.model.ui.tab3.presenter;

import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.tab3.view.SystemSettingView;
import mvp.cn.rx.MvpRxSimplePresenter;
import rx.Observable;

/**
 * Created by Administrator on 2017/12/20.
 */

public class SystemSettingPresent extends MvpRxSimplePresenter<SystemSettingView> {
    /**
     *  修改密码
     */
    public void changePW(String pw) {
        Observable observable = RetrofitUtils.getInstance().changePW(pw);
        getNetWork(observable, new RetrofitCallBack<CommonBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d(e.toString());
            }

            @Override
            public void onSuccess(CommonBean commonBean) {
                getView().getSucc(commonBean);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
