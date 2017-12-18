package cn.lc.model.ui.tab1.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.ui.tab1.bean.OrderDetailBean;
import cn.lc.model.ui.tab1.bean.StationeryDetailBean;
import cn.lc.model.ui.tab1.constant.Tab1Constants;
import cn.lc.model.ui.tab1.presenter.OrderDetailPresenter;
import cn.lc.model.ui.tab1.view.OrderDetailView;

/**
 * Created by Administrator on 2017/12/16.
 */

public class MaintainDetailFragment extends MvpSimpleFragment<OrderDetailView,OrderDetailPresenter> implements OrderDetailView {
    @BindView(R.id.iv_call)
    ImageView iv_call;
    @BindView(R.id.tx_personname)
    TextView tx_personname;
    @BindView(R.id.tx_personphone)
    TextView tx_personphone;
    @BindView(R.id.tx_personaddress)
    TextView tx_personaddress;
    @BindView(R.id.tx_ordertime)
    TextView tx_ordertime;
    @BindView(R.id.tx_totime)
    TextView tx_totime;
    @BindView(R.id.tx_content)
    TextView tx_content;
    @BindView(R.id.tx_beizhu)
    TextView tx_beizhu;
    @BindView(R.id.tx_ordernum)
    TextView tx_ordernum;
    @BindView(R.id.tv_maintain_function)
    TextView tv_function;

    String serviceType;
    String orderid;
    int type;
    String phoneNum;       // 电话号码;

    @Override
    public OrderDetailPresenter createPresenter() {
        return new OrderDetailPresenter();
    }

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_maintain_order_detail);
    }

    @Override
    public void initView(View v) {
        presenter.getData(serviceType,orderid);
        switch (type) {
            case Tab1Constants.MAINTAIN_UNSERVICE:          // 未服务
                tv_function.setVisibility(View.VISIBLE);
                tv_function.setText("立即服务");
                break;
            case Tab1Constants.MAINTAIN_SERVICING:          // 服务中
                tv_function.setVisibility(View.VISIBLE);
                tv_function.setText("已完成");
                break;
            case Tab1Constants.MAINTAIN_FINISH:             // 已完成.
                tv_function.setVisibility(View.GONE);
                break;
            case Tab1Constants.MAINTAIN_CANCEL:             // 取消.
                tv_function.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void getSucc(OrderDetailBean bean) {
        OrderDetailBean.DataBean data = bean.getData();
        phoneNum = data.getMendermobile();
        // 订单信息:
        tx_ordertime.setText(data.getCreatetime());
        tx_totime.setText(data.getInvitetime());
        tx_content.setText(data.getItemname());
        //tx_beizhu.setText(data.getme);
        tx_ordernum.setText(data.getOrdercode());

        // 客户信息:
        tx_personname.setText(data.getMendername());
        tx_personphone.setText(data.getMendermobile());
        tx_personaddress.setText(data.getServiceplace());
    }

    @Override
    public void getSucc(StationeryDetailBean bean) {

    }

    public void setOrderId(String serviceType,String orderId,int type) {
        this.serviceType = serviceType;
        this.orderid = orderId;
        this.type = type;
    }

    @OnClick({R.id.iv_call,R.id.tv_maintain_function})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_call:
                new AlertDialog.Builder(getActivity()).setTitle("").setMessage("拨打电话"+ phoneNum)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum));
                                try {
                                    startActivity(intent);
                                }catch (Exception e){
                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.tv_maintain_function:
                switch (type) {
                    case Tab1Constants.MAINTAIN_UNSERVICE:          // 未服务
                        tv_function.setVisibility(View.VISIBLE);
                        tv_function.setText("立即服务");
                        getPresenter().goService(serviceType,orderid);
                        break;
                    case Tab1Constants.MAINTAIN_SERVICING:          // 服务中
                        tv_function.setVisibility(View.VISIBLE);
                        tv_function.setText("已完成");
                        getPresenter().finishService(serviceType,orderid);
                        break;
                }
                break;
        }
    }
}
