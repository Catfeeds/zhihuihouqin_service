package cn.lc.model.ui.tab1.presenter;

import cn.lc.model.framework.base.BaseResponse;
import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.view.MainView;
import cn.lc.model.ui.tab1.view.OrderDetailView;
import cn.lc.model.ui.tab3.view.PersonInfoView;
import mvp.cn.rx.MvpRxSimplePresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class OrderDetailPresenter extends MvpRxSimplePresenter<OrderDetailView> {


    public void getData(String name,String mobile) {
        LogUtils.d("OrderDetailPresenter发出请求");
        Observable login = RetrofitUtils.getInstance().updatename(name,mobile);
        getNetWork(login, new RetrofitCallBack<CommonBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...."+e);
            }
            @Override
            public void onSuccess(CommonBean baseResponse) {
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
