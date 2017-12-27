package cn.lc.model.ui.tab2.presenter;

import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.tab2.bean.OrderMessageBean;
import cn.lc.model.ui.tab2.view.MessageDetailView;
import mvp.cn.rx.MvpRxSimplePresenter;
import rx.Observable;

/**
 * Created by Administrator on 2017/12/19.
 */

public class MessageDetailPresenter extends MvpRxSimplePresenter<MessageDetailView> {

    public void getOrderMessage(String serviceTypeId,String page,String limit) {
        Observable orderMessage = RetrofitUtils.getInstance().getOrderMessage(serviceTypeId, page, limit);
        getNetWork(orderMessage, new RetrofitCallBack<OrderMessageBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d(e.toString());
            }

            @Override
            public void onSuccess(OrderMessageBean orderMessageBean) {
                getView().orderMessageSucc(orderMessageBean);
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
