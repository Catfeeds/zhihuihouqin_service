package cn.lc.model.ui.tab1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.ui.tab1.bean.OrderDetailBean;
import cn.lc.model.ui.tab1.bean.OrderWaterDetailBean;
import cn.lc.model.ui.tab1.bean.StationeryDetailBean;
import cn.lc.model.ui.tab1.fragment.MaintainDetailFragment;
import cn.lc.model.ui.tab1.fragment.OrderWaterDetailFragment;
import cn.lc.model.ui.tab1.fragment.StationeryDetailFragment;
import cn.lc.model.ui.tab1.presenter.OrderDetailPresenter;
import cn.lc.model.ui.tab1.view.OrderDetailView;

/**
 * Created by Administrator on 2017/11/20.
 */

public class OrderDetailActivity extends MvpSimpleActivity<OrderDetailView,OrderDetailPresenter>implements OrderDetailView {
    @BindView(R.id.iv_back)
    ImageView iv_back;


    String serviceType;
    String orderid;
    int type;
    /*String phoneNum;       // 电话号码;*/

    @Override
    public void setContentLayout() {
        setContentView(R.layout.order_detail);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        serviceType = bundle.getString("serviceType");
        orderid = bundle.getString("orderid");
        type = bundle.getInt("type");

        if (SharedPrefHelper.getInstance().getServicetype() == 1) {                 // 维修.
            MaintainDetailFragment fragment = new MaintainDetailFragment();
            fragment.setOrderId(serviceType,orderid,type);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layout_orderDetail_content,fragment)
                    .commit();
        } else if (SharedPrefHelper.getInstance().getServicetype() == 8) {          //办公用品
            StationeryDetailFragment stationeryDetailFragment = new StationeryDetailFragment();
            stationeryDetailFragment.setOrderId(serviceType,orderid,type);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layout_orderDetail_content,stationeryDetailFragment)
                    .commit();

        } else if (SharedPrefHelper.getInstance().getServicetype() == 18) {         // 订水
            OrderWaterDetailFragment detailFragment = new OrderWaterDetailFragment();
            detailFragment.setOrderId(serviceType,orderid,type);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layout_orderDetail_content,detailFragment)
                    .commit();

        } else if (SharedPrefHelper.getInstance().getServicetype() == 7) {          // 会议.

        }
    }
    @OnClick({R.id.iv_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
    @Override
    public void getSucc(OrderDetailBean bean) {}

    @Override
    public void getSucc(StationeryDetailBean bean) {}

    @Override
    public void getSucc(OrderWaterDetailBean bean) {}

    @Override
    public OrderDetailPresenter createPresenter() {
        return new OrderDetailPresenter();
    }
}
