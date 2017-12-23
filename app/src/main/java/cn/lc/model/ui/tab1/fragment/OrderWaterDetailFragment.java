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
import cn.lc.model.ui.tab1.bean.MeetingDetailBean;
import cn.lc.model.ui.tab1.bean.OrderDetailBean;
import cn.lc.model.ui.tab1.bean.OrderWaterDetailBean;
import cn.lc.model.ui.tab1.bean.StationeryDetailBean;
import cn.lc.model.ui.tab1.constant.Tab1Constants;
import cn.lc.model.ui.tab1.presenter.OrderDetailPresenter;
import cn.lc.model.ui.tab1.view.OrderDetailView;

import static cn.lc.model.R.id.iv_goodsIcon;

/**
 * Created by Administrator on 2017/12/19.
 */

public class OrderWaterDetailFragment extends MvpSimpleFragment<OrderDetailView, OrderDetailPresenter> implements OrderDetailView {

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
    public OrderDetailPresenter createPresenter() {
        return new OrderDetailPresenter();
    }

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_stationery_order_detail);
    }

    @Override
    public void initView(View v) {
        getPresenter().getOrderWaterDetail(serviceType,orderid);

        if (type== Tab1Constants.WATER_UNRECEIVE_ORDER){
            tv_funtionTop.setVisibility(View.VISIBLE);
            tv_funtionBottom.setVisibility(View.VISIBLE);
            tv_funtionTop.setText("立即接单");
            tv_funtionBottom.setText("拒绝接单");
        }else if (type== Tab1Constants.WATER_RECEIVED_ORDER){
            tv_funtionTop.setVisibility(View.VISIBLE);
            tv_funtionBottom.setVisibility(View.VISIBLE);
            tv_funtionTop.setText("立即配送");
            tv_funtionBottom.setText("取消订单");
        }else if (type== Tab1Constants.WATER_DELIVERY){
            tv_funtionTop.setVisibility(View.VISIBLE);
            tv_funtionBottom.setVisibility(View.GONE);
            tv_funtionTop.setText("已送达");
        }else if (type==Tab1Constants.WATER_FINISH){
            tv_funtionTop.setVisibility(View.GONE);
            tv_funtionBottom.setVisibility(View.GONE);
        }else if (type==Tab1Constants.WATER_CANCEL){
            tv_funtionTop.setVisibility(View.VISIBLE);
            tv_funtionBottom.setVisibility(View.GONE);
            tv_funtionTop.setText("删除订单");
        }
    }

    @OnClick({R.id.tv_stationery_functionTop,R.id.tv_stationery_functionBottom})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_stationery_functionTop:
                if (type== Tab1Constants.WATER_UNRECEIVE_ORDER){            // 立即接单
                    getPresenter().goService(serviceType,orderid);
                }else if (type== Tab1Constants.WATER_RECEIVED_ORDER){       // 立即配送
                    getPresenter().peiSongOrder(serviceType,orderid);
                }else if (type== Tab1Constants.WATER_DELIVERY){             // 立即送达
                    getPresenter().finishService(serviceType,orderid);
                }else if (type==Tab1Constants.WATER_CANCEL){                // 删除订单
                    getPresenter().deleteOrder(serviceType,orderid);
                }
                break;
            case R.id.tv_stationery_functionBottom:                         // 取消订单.
                //getPresenter().cancelOrder(serviceType,orderid);


                if (type== Tab1Constants.WATER_UNRECEIVE_ORDER){            // 立即接单
                    /*OrderDetailActivity.isReason = true;
                    Bundle bundle = new Bundle();
                    bundle.putString("serviceType", SharedPrefHelper.getInstance().getServicetype() + "");
                    bundle.putString("orderid",orderid);
                    bundle.putInt("type",type);
                    UIManager.turnToAct(getActivity(), OrderDetailActivity.class,bundle);*/
                    getPresenter().refuseOrder(serviceType,orderid);

                }else if (type== Tab1Constants.WATER_RECEIVED_ORDER){       // 立即配送
                    /*OrderDetailActivity.isReason = true;
                    Bundle bundle = new Bundle();
                    bundle.putString("serviceType", SharedPrefHelper.getInstance().getServicetype() + "");
                    bundle.putString("orderid",orderid);
                    bundle.putInt("type",type);
                    UIManager.turnToAct(getActivity(), OrderDetailActivity.class,bundle);*/

                    getPresenter().cancelOrder(serviceType,orderid,"");
                }
                break;
        }
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
    public void getSucc(OrderWaterDetailBean data) {
        OrderWaterDetailBean.DataBean bean = data.getData();

        txPersonname.setText(bean.getRealname());
        txPersonphone.setText(bean.getMobile());
        txPersonaddress.setText(bean.getAddress());
        ivGoodsIcon.setImageURI(bean.getImg());
        tvGoodsName.setText(bean.getName());
        tvGoodsCount.setText("x" + bean.getCount());
        tvGoodsMoney.setText("¥" + bean.getTotalprice());
        txOrdertime.setText(bean.getCreatetime());
        txTotime.setText(bean.getEndtime());
        txBeizhu.setText(bean.getRemark());
        txOrdernum.setText(bean.getOrdercode());
    }

    @Override
    public void getSucc(MeetingDetailBean bean) {

    }
}
