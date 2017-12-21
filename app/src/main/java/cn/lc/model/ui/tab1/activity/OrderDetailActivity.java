package cn.lc.model.ui.tab1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.ui.tab1.bean.OrderDetailBean;
import cn.lc.model.ui.tab1.bean.OrderWaterDetailBean;
import cn.lc.model.ui.tab1.bean.StationeryDetailBean;
import cn.lc.model.ui.tab1.constant.Tab1Constants;
import cn.lc.model.ui.tab1.fragment.MaintainDetailFragment;
import cn.lc.model.ui.tab1.fragment.MeettingRoomDetailFragment;
import cn.lc.model.ui.tab1.fragment.OrderWaterDetailFragment;
import cn.lc.model.ui.tab1.fragment.StationeryDetailFragment;
import cn.lc.model.ui.tab1.presenter.OrderDetailPresenter;
import cn.lc.model.ui.tab1.view.OrderDetailView;
import cn.lc.model.ui.tab3.fragment.SelectReasonFragment;

/**
 * Created by Administrator on 2017/11/20.
 */

public class OrderDetailActivity extends MvpSimpleActivity<OrderDetailView,OrderDetailPresenter>implements OrderDetailView {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;

    String serviceType;
    String orderid;
    int type;
    /*String phoneNum;       // 电话号码;*/
    public static boolean isReason = false;         // 是否是原因选择.

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

        if (isReason) {
            if (SharedPrefHelper.getInstance().getServicetype() == 1) {                 // 维修.
                SelectReasonFragment selectReasonFragment = new SelectReasonFragment();
                selectReasonFragment.setOrderId(serviceType,orderid);
                if (type == Tab1Constants.MAINTAIN_UNSERVICE) {             // 拒绝接单
                    tv_title.setText("拒绝订单");
                    selectReasonFragment.setType(SelectReasonFragment.REFUSE_TYPE);
                } else if (type == Tab1Constants.MAINTAIN_SERVICING) {      // 取消接单.
                    tv_title.setText("取消订单");
                    selectReasonFragment.setType(SelectReasonFragment.CANCEL_TYPE);
                }
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.layout_orderDetail_content,selectReasonFragment)
                        .commit();
            } else if (SharedPrefHelper.getInstance().getServicetype() == 8) {          //办公用品
                SelectReasonFragment selectReasonFragment = new SelectReasonFragment();
                selectReasonFragment.setOrderId(serviceType,orderid);
                if (type == Tab1Constants.WORK_UNRECEIVE_ORDER) {             // 拒绝接单
                    tv_title.setText("拒绝订单");
                    selectReasonFragment.setType(SelectReasonFragment.REFUSE_TYPE);
                } else if (type == Tab1Constants.WORK_RECEIVED_ORDER) {      // 取消接单.
                    tv_title.setText("取消订单");
                    selectReasonFragment.setType(SelectReasonFragment.CANCEL_TYPE);
                }
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.layout_orderDetail_content,selectReasonFragment)
                        .commit();

            }

        } else {

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
                MeettingRoomDetailFragment meettingRoomDetailFragment = new MeettingRoomDetailFragment();
                meettingRoomDetailFragment.setOrderId(serviceType,orderid,type);

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.layout_orderDetail_content,meettingRoomDetailFragment)
                        .commit();
            }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        isReason = false;
    }
}
