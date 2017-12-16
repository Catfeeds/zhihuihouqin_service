package cn.lc.model.ui.tab1.presenter;

import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.tab1.bean.OrderDetailBean;
import cn.lc.model.ui.tab1.view.OrderDetailView;
import mvp.cn.rx.MvpRxSimplePresenter;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class OrderDetailPresenter extends MvpRxSimplePresenter<OrderDetailView> {


    public void getData(String serviceType,String orderid) {
        LogUtils.d("OrderDetailPresenter发出请求");
        Observable orderDetail = RetrofitUtils.getInstance().getOrderDetail(serviceType,orderid);
        getNetWork(orderDetail, new RetrofitCallBack<OrderDetailBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...."+e);
            }
            @Override
            public void onSuccess(OrderDetailBean baseResponse) {
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
