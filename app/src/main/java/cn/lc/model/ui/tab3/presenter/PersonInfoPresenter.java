package cn.lc.model.ui.tab3.presenter;

import cn.lc.model.framework.base.BaseResponse;
import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.view.MainView;
import cn.lc.model.ui.tab3.view.PersonInfoView;
import mvp.cn.rx.MvpRxSimplePresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class PersonInfoPresenter extends MvpRxSimplePresenter<PersonInfoView> {


    public void getData() {
//        LogUtil.log("PersonInfo发出请求");
//        Observable login = RetrofitUtils.getInstance().login("", "");
//        getNetWork(login, new RetrofitCallBack<BaseResponse>() {
//
//            @Override
//            public void onPostFail(Throwable e) {
//
//            }
//
//            @Override
//            public void onSuccess(BaseResponse baseResponse) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
