package cn.lc.model.ui.tab1.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.ui.tab1.bean.MeetingDetailBean;
import cn.lc.model.ui.tab1.bean.OrderDetailBean;
import cn.lc.model.ui.tab1.bean.OrderWaterDetailBean;
import cn.lc.model.ui.tab1.bean.StationeryDetailBean;
import cn.lc.model.ui.tab1.constant.Tab1Constants;
import cn.lc.model.ui.tab1.presenter.OrderDetailPresenter;
import cn.lc.model.ui.tab1.view.OrderDetailView;

/**
 * Created by zhy on 2017/12/19.
 */

public class MeettingRoomDetailFragment extends MvpSimpleFragment<OrderDetailView, OrderDetailPresenter> implements OrderDetailView,View.OnClickListener{
    private String serviceType;
    private String orderid;
    private int type;

    @BindView(R.id.tx_personname)
    TextView tv_personName;
    @BindView(R.id.tx_personphone)
    TextView tv_phone;
    @BindView(R.id.tx_ordernum)
    TextView tv_orderNum;
    @BindView(R.id.tx_ordertime)
    TextView tv_orderTime;
    @BindView(R.id.tx_totime)
    TextView tv_toTime;
    @BindView(R.id.tx_content)
    TextView tv_content;
    @BindView(R.id.tx_beizhu)
    TextView tv_beizhu;
    @BindView(R.id.tx_meettingTyep)
    TextView tv_meetingtype;
    @BindView(R.id.tx_meetEquipment)
    TextView tv_meetingEquipment;
    @BindView(R.id.tx_meetPersonCount)
    TextView tv_personCount;
    @BindView(R.id.tx_meetBeizhu)
    TextView tv_meetBeizhu;
    @BindView(R.id.tv_meeting_functionTop)
    TextView tv_functionTop;
    @BindView(R.id.tv_meeting_functionBottom)
    TextView tv_functionBottom;
    @BindView(R.id.iv_meetting_img1)
    SimpleDraweeView iv_img1;
    @BindView(R.id.iv_meetting_img2)
    SimpleDraweeView iv_img2;
    @BindView(R.id.iv_meetting_img3)
    SimpleDraweeView iv_img3;

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
        tv_functionTop.setOnClickListener(this);
        tv_functionBottom.setOnClickListener(this);



        getPresenter().getMeetingOrderDetail(serviceType,orderid);
        switch (type) {
            case Tab1Constants.MEETINGROOM_UNSERVICE:
                tv_functionTop.setVisibility(View.VISIBLE);
                tv_functionBottom.setVisibility(View.VISIBLE);
                tv_functionTop.setText("立即接单");
                tv_functionBottom.setText("拒绝接单");
                break;
            case Tab1Constants.MEETINGROOM_SERVICING:
                tv_functionTop.setVisibility(View.VISIBLE);
                tv_functionBottom.setVisibility(View.VISIBLE);
                tv_functionTop.setText("已完成");
                tv_functionBottom.setText("取消订单");
                break;
            case Tab1Constants.MEETINGROOM_FINISH:
                tv_functionTop.setVisibility(View.GONE);
                tv_functionBottom.setVisibility(View.GONE);
                break;
            case Tab1Constants.MEETINGROOM_CANCEL:
                tv_functionTop.setVisibility(View.VISIBLE);
                tv_functionBottom.setVisibility(View.GONE);
                tv_functionTop.setText("删除订单");
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
    public void getSucc(OrderWaterDetailBean bean) {}

    @Override
    public void getSucc(MeetingDetailBean data) {
        MeetingDetailBean.DataBean bean = data.getData();

        tv_personName.setText(bean.getUsername());
        tv_phone.setText(bean.getMobile());
        tv_orderNum.setText(bean.getOrdercode());
        tv_orderTime.setText(bean.getCreatetime());
        tv_toTime.setText(bean.getConferencename());
        tv_content.setText(bean.getDate());
        tv_beizhu.setText(bean.getAttentdleader());
        tv_meetingtype.setText(bean.getConferencetype());
        tv_meetingEquipment.setText(bean.getName());
        tv_personCount.setText(bean.getAttendnum());
        tv_meetBeizhu.setText(bean.getRemark());

        iv_img1.setVisibility(View.GONE);
        iv_img2.setVisibility(View.GONE);
        iv_img3.setVisibility(View.GONE);
        String imgs = bean.getImgs();
        String[] split = imgs.split(",");
        for (int i = 0;i < split.length;i++) {
            switch (i) {
                case 0:
                    iv_img1.setVisibility(View.VISIBLE);
                    iv_img1.setImageURI(split[i]);
                    break;
                case 1:
                    iv_img2.setVisibility(View.VISIBLE);
                    iv_img2.setImageURI(split[i]);
                    break;
                case 2:
                    iv_img3.setVisibility(View.VISIBLE);
                    iv_img3.setImageURI(split[i]);
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_meeting_functionTop:
                switch (type) {
                    case Tab1Constants.MEETINGROOM_UNSERVICE:
                        getPresenter().goService(serviceType,orderid);
                        break;
                    case Tab1Constants.MEETINGROOM_SERVICING:
                        getPresenter().finishService(serviceType,orderid);
                        break;
                    case Tab1Constants.MEETINGROOM_CANCEL:
                        getPresenter().deleteOrder(serviceType,orderid);
                        break;
                }
                break;
            case R.id.tv_meeting_functionBottom:
                switch (type) {
                    case Tab1Constants.MEETINGROOM_UNSERVICE:
                        /*OrderDetailActivity.isReason = true;
                        Bundle bundle = new Bundle();
                        bundle.putString("serviceType", serviceType);
                        bundle.putString("orderid",orderid);
                        bundle.putInt("type",type);

                        UIManager.turnToAct(getActivity(),OrderDetailActivity.class,bundle);*/
                        getPresenter().refuseOrder(serviceType,orderid);

                        break;
                    case Tab1Constants.MEETINGROOM_SERVICING:
                        /*OrderDetailActivity.isReason = true;
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("serviceType", serviceType);
                        bundle1.putString("orderid",orderid);
                        bundle1.putInt("type",type);
                        UIManager.turnToAct(getActivity(), OrderDetailActivity.class,bundle1);*/
                        getPresenter().cancelOrder(serviceType,orderid,"");

                        break;
                }
                break;
        }
    }
}
