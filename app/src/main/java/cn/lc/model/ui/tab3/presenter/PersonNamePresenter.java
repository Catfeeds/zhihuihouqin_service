package cn.lc.model.ui.tab3.presenter;

import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.tab3.view.PersonNameView;
import mvp.cn.rx.MvpRxSimplePresenter;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class PersonNamePresenter extends MvpRxSimplePresenter<PersonNameView> {

    public void UpdateName(String name) {
        LogUtils.d("PersonNamePresenter发出请求");
        Observable login = RetrofitUtils.getInstance().updatename(name);
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

    public void UpdatePhone(String phone) {
        LogUtils.d("PersonNamePresenter发出请求");
        Observable login = RetrofitUtils.getInstance().updatePhone(phone);
        getNetWork(login, new RetrofitCallBack<CommonBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...."+e);
            }
            @Override
            public void onSuccess(CommonBean baseResponse) {
                getView().updateSucc(baseResponse);
                getView().showToast("修改成功");
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
