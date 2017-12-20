package cn.lc.model.ui.tab3.presenter;

import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.tab3.bean.EvaluateBean;
import cn.lc.model.ui.tab3.view.MyBankView;
import mvp.cn.rx.MvpRxSimplePresenter;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 *  我的评价的Presenter;
 */

public class MyBankPresenter extends MvpRxSimplePresenter<MyBankView> {

    public void getData(String name,String mobile) {
        LogUtils.d("PersonNamePresenter发出请求");
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

    /**
     *  获取我的评价的数据
     */
    public void getEvaluateData() {
        Observable evaluateData = RetrofitUtils.getInstance().getEvaluateData();
        getNetWork(evaluateData, new RetrofitCallBack<EvaluateBean>() {
            @Override
            public void onPostFail(Throwable e) {

            }

            @Override
            public void onSuccess(EvaluateBean evaluateBean) {
                getView().getSucc(evaluateBean);
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
