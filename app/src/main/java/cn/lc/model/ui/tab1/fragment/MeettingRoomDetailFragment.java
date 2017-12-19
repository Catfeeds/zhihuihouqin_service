package cn.lc.model.ui.tab1.fragment;

import android.os.Bundle;
import android.view.View;

import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.ui.tab1.bean.OrderDetailBean;
import cn.lc.model.ui.tab1.bean.OrderWaterDetailBean;
import cn.lc.model.ui.tab1.bean.StationeryDetailBean;
import cn.lc.model.ui.tab1.presenter.OrderDetailPresenter;
import cn.lc.model.ui.tab1.view.OrderDetailView;

/**
 * Created by Administrator on 2017/12/19.
 */

public class MeettingRoomDetailFragment extends MvpSimpleFragment<OrderDetailView, OrderDetailPresenter> implements OrderDetailView {
    private String serviceType;
    private String orderid;
    private int type;

    @Override
    public OrderDetailPresenter createPresenter() {
        return new OrderDetailPresenter();
    }

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_meetting_order_detail);
    }

    @Override
    public void initView(View v) {

    }

    public void setOrderId(String serviceType,String orderid,int type) {
        this.serviceType = serviceType;
        this.orderid = orderid;
        this.type = type;
    }

    @Override
    public void getSucc(OrderDetailBean bean) {}
    @Override
    public void getSucc(StationeryDetailBean bean) {}
    @Override
    public void getSucc(OrderWaterDetailBean bean) {}
}
