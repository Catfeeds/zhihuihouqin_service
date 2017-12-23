package cn.lc.model.ui.tab3.presenter;

import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.tab3.bean.EvaluateBean;
import cn.lc.model.ui.tab3.bean.IncomeBean;
import cn.lc.model.ui.tab3.bean.IncomeDetailBean;
import cn.lc.model.ui.tab3.view.MyBankView;
import mvp.cn.rx.MvpRxSimplePresenter;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 *  我的评价的Presenter;我的钱包的Presenter;
 */

public class MyBankPresenter extends MvpRxSimplePresenter<MyBankView> {

    public void getData(String name,String mobile) {
        LogUtils.d("PersonNamePresenter发出请求");
        Observable login = RetrofitUtils.getInstance().updatename(name);
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
    public void getEvaluateData(String serviceType,String scoretype,String page) {
        Observable evaluateData = RetrofitUtils.getInstance().getEvaluateData(serviceType,scoretype,page);
        getNetWork(evaluateData, new RetrofitCallBack<EvaluateBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...."+e);
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

    /**
     *  获取收益首页数据
     */
    public void getIncomeData() {
        Observable incomeData = RetrofitUtils.getInstance().getIncomeData();
        getNetWork(incomeData, new RetrofitCallBack<IncomeBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...."+e);
            }

            @Override
            public void onSuccess(IncomeBean incomeBean) {
                getView().getSucc(incomeBean);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     *  获取用户的收益列表
     */
    public void getIncomeList() {
        Observable observable = RetrofitUtils.getInstance().getIncomeList();
        getNetWork(observable, new RetrofitCallBack<IncomeDetailBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...."+e);
            }

            @Override
            public void onSuccess(IncomeDetailBean incomeDetailBean) {
                getView().getSucc(incomeDetailBean);
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
