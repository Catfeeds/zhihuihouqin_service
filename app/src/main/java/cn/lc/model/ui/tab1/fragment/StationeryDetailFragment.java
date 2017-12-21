package cn.lc.model.ui.tab1.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.ui.tab1.activity.OrderDetailActivity;
import cn.lc.model.ui.tab1.bean.MeetingDetailBean;
import cn.lc.model.ui.tab1.bean.OrderDetailBean;
import cn.lc.model.ui.tab1.bean.OrderWaterDetailBean;
import cn.lc.model.ui.tab1.bean.StationeryDetailBean;
import cn.lc.model.ui.tab1.constant.Tab1Constants;
import cn.lc.model.ui.tab1.presenter.OrderDetailPresenter;
import cn.lc.model.ui.tab1.view.OrderDetailView;

import static cn.lc.model.R.id.iv_goodsIcon;

/**
 * Created by Administrator on 2017/12/18.
 */

public class StationeryDetailFragment extends MvpSimpleFragment<OrderDetailView, OrderDetailPresenter> implements OrderDetailView {
    private static final String TAG = "StationeryDetailFragmen";

    @BindView(R.id.tx_personname)
    TextView txPersonname;
    @BindView(R.id.tx_personphone)
    TextView txPersonphone;
    @BindView(R.id.iv_call)
    ImageView ivCall;
    @BindView(R.id.tx_personaddress)
    TextView txPersonaddress;
    @BindView(iv_goodsIcon)
    SimpleDraweeView ivGoodsIcon;
    @BindView(R.id.tv_goodsName)
    TextView tvGoodsName;
    @BindView(R.id.tv_goodsCount)
    TextView tvGoodsCount;
    @BindView(R.id.tv_goodsMoney)
    TextView tvGoodsMoney;
    @BindView(R.id.tx_ordertime)
    TextView txOrdertime;
    @BindView(R.id.tx_totime)
    TextView txTotime;
    @BindView(R.id.tx_content)
    TextView txContent;
    @BindView(R.id.tx_beizhu)
    TextView txBeizhu;
    @BindView(R.id.tx_ordernum)
    TextView txOrdernum;
    @BindView(R.id.tv_stationery_functionTop)
    TextView tv_funtionTop;
    @BindView(R.id.tv_stationery_functionBottom)
    TextView tv_funtionBottom;

    private String serviceType;
    private String orderid;
    private int type;

    @Override
    public void getSucc(OrderDetailBean bean) {}

    @Override
    public void getSucc(StationeryDetailBean bean) {
        txPersonname.setText(bean.getUsername());
        txPersonphone.setText(bean.getMobile());
        txPersonaddress.setText(bean.getAddress());
        ivGoodsIcon.setImageURI(bean.getImg());
        tvGoodsName.setText(bean.getProductname());
        tvGoodsCount.setText("x" + bean.getCount());
        tvGoodsMoney.setText("¥" + bean.getTotalprice());
        txOrdertime.setText(bean.getCreatetime());
        txTotime.setText(bean.getExpertarrivaltme());
        txBeizhu.setText(bean.getRemark());
        txOrdernum.setText(bean.getCode());
    }

    @Override
    public void getSucc(OrderWaterDetailBean bean) {

    }

    @Override
    public void getSucc(MeetingDetailBean bean) {

    }

    @Override
    public OrderDetailPresenter createPresenter() {
        return new OrderDetailPresenter();
    }

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_stationery_order_detail);
    }

    @Override
    public void initView(View v) {
        getPresenter().getStationeryDetialData(serviceType,orderid);
        switch (type) {
            case Tab1Constants.WORK_UNRECEIVE_ORDER:    // 待接单
                tv_funtionTop.setVisibility(View.VISIBLE);
                tv_funtionBottom.setVisibility(View.VISIBLE);
                tv_funtionTop.setText("立即服务");
                tv_funtionBottom.setText("拒绝接单");
                break;
            case Tab1Constants.WORK_RECEIVED_ORDER:     // 已接单
                tv_funtionTop.setVisibility(View.VISIBLE);
                tv_funtionBottom.setVisibility(View.VISIBLE);
                tv_funtionTop.setText("立即配送");
                tv_funtionBottom.setText("取消订单");
                break;
            case Tab1Constants.WORK_DELIVERY:           // 配送中
                tv_funtionTop.setVisibility(View.VISIBLE);
                tv_funtionBottom.setVisibility(View.GONE);
                tv_funtionTop.setText("已送达");
                break;
            case Tab1Constants.WORK_FINISH:             // 已完成
                tv_funtionTop.setVisibility(View.GONE);
                tv_funtionBottom.setVisibility(View.GONE);
                break;
            case Tab1Constants.WORK_CANCEL:             // 已取消.
                tv_funtionTop.setVisibility(View.VISIBLE);
                tv_funtionBottom.setVisibility(View.GONE);
                tv_funtionTop.setText("删除订单");
                break;
        }
    }

    @OnClick({R.id.tv_stationery_functionTop,R.id.tv_stationery_functionBottom})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_stationery_functionTop:
                switch (type) {
                    case Tab1Constants.WORK_UNRECEIVE_ORDER:    // 待接单
                        getPresenter().goService(serviceType,orderid);
                        break;
                    case Tab1Constants.WORK_RECEIVED_ORDER:     // 已接单
                        getPresenter().peiSongOrder(serviceType,orderid);
                        break;
                    case Tab1Constants.WORK_DELIVERY:           // 配送中
                        getPresenter().finishService(serviceType,orderid);
                        break;
                    case Tab1Constants.WORK_CANCEL:             // 已取消.
                        getPresenter().deleteOrder(serviceType,orderid);
                        break;
                }
                break;
            case R.id.tv_stationery_functionBottom:
                switch (type) {
                    case Tab1Constants.WORK_UNRECEIVE_ORDER:    // 拒接接单
                        OrderDetailActivity.isReason = true;
                        Bundle bundle = new Bundle();
                        bundle.putString("serviceType", serviceType);
                        bundle.putString("orderid",orderid);
                        bundle.putInt("type",type);
                        UIManager.turnToAct(getActivity(), OrderDetailActivity.class,bundle);
                        break;
                    case Tab1Constants.WORK_RECEIVED_ORDER:     // 取消接单
                        OrderDetailActivity.isReason = true;
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("serviceType", serviceType);
                        bundle1.putString("orderid",orderid);
                        bundle1.putInt("type",type);
                        UIManager.turnToAct(getActivity(), OrderDetailActivity.class,bundle1);
                        break;
                }


                break;
        }
    }


    public void setOrderId(String serviceType,String orderid,int type) {
        this.serviceType = serviceType;
        this.orderid = orderid;
        this.type = type;
    }
}
