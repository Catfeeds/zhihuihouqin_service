package cn.lc.model.ui.tab3.presenter;

import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.login.bean.LoginBean;
import cn.lc.model.ui.tab3.view.PersonInfoView;
import cn.lc.model.ui.tab3.view.PersonNameView;
import mvp.cn.rx.MvpRxSimplePresenter;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class PersonNamePresenter extends MvpRxSimplePresenter<PersonNameView> {

    public void UpdateName(String name,String mobile) {
        LogUtils.d("PersonNamePresenter发出请求");
        Observable login = RetrofitUtils.getInstance().updatename(name,mobile);
        getNetWork(login, new RetrofitCallBack<CommonBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...."+e);
            }
            @Override
            public void onSuccess(CommonBean baseResponse) {
                getView().updateSucc(baseResponse);
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
