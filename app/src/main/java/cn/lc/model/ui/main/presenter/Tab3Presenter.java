package cn.lc.model.ui.main.presenter;

import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.view.Tab3View;
import cn.lc.model.ui.tab3.bean.PersonInfoBean;
import mvp.cn.rx.MvpRxSimplePresenter;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class Tab3Presenter extends MvpRxSimplePresenter<Tab3View> {


    public void getData(String serviceType) {
        final Observable personInfo = RetrofitUtils.getInstance().getPersonInfo(serviceType);
        getNetWork(personInfo, new RetrofitCallBack<PersonInfoBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d(e.toString());
            }

            @Override
            public void onSuccess(PersonInfoBean personInfoBean) {
                getView().getSuccess(personInfoBean);
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
