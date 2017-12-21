package cn.lc.model.ui.tab3.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.ui.tab1.bean.OrderDetailBean;
import cn.lc.model.ui.tab1.bean.OrderWaterDetailBean;
import cn.lc.model.ui.tab1.bean.StationeryDetailBean;
import cn.lc.model.ui.tab1.presenter.OrderDetailPresenter;
import cn.lc.model.ui.tab1.view.OrderDetailView;

/**
 * Created by Administrator on 2017/12/21.
 */

public class SelectReasonFragment extends MvpSimpleFragment<OrderDetailView,OrderDetailPresenter> implements View.OnClickListener,OrderDetailView{
    public static final int CANCEL_TYPE = 1;            // 取消
    public static final int REFUSE_TYPE = 2;            // 拒绝
    private int type;

    @BindView(R.id.radioGroup_reason)
    RadioGroup radioGroup;
    @BindView(R.id.tv_reason1)
    TextView tv_reason1;
    @BindView(R.id.tv_reason2)
    TextView tv_reason2;
    @BindView(R.id.tv_reason3)
    TextView tv_reason3;
    @BindView(R.id.tv_reason4)
    TextView tv_reason4;
    @BindView(R.id.tv_reason5)
    TextView tv_reason5;
    @BindView(R.id.otherReason_title)
    TextView tv_otherReasonTitle;
    @BindView(R.id.otherReason_content)
    EditText et_otherReasonTitle;

    @BindView(R.id.selectReason_cancel)
    TextView tv_cancel;
    @BindView(R.id.selectReason_confirm)
    TextView tv_confirm;

    private String reason = "原因1";
    String serviceType;
    String orderid;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_select_reason);
    }

    @Override
    public void initView(View v) {

        tv_otherReasonTitle.setVisibility(View.GONE);
        et_otherReasonTitle.setVisibility(View.GONE);

        radioGroup.check(R.id.radio_reason1);
        tv_cancel.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_reason1:
                        tv_otherReasonTitle.setVisibility(View.GONE);
                        et_otherReasonTitle.setVisibility(View.GONE);
                        reason = "原因1";
                        break;
                    case R.id.radio_reason2:
                        tv_otherReasonTitle.setVisibility(View.GONE);
                        et_otherReasonTitle.setVisibility(View.GONE);
                        reason = "原因2";
                        break;
                    case R.id.radio_reason3:
                        tv_otherReasonTitle.setVisibility(View.GONE);
                        et_otherReasonTitle.setVisibility(View.GONE);
                        reason = "原因3";
                        break;
                    case R.id.radio_reason4:
                        tv_otherReasonTitle.setVisibility(View.GONE);
                        et_otherReasonTitle.setVisibility(View.GONE);
                        reason = "原因4";
                        break;
                    case R.id.radio_reason5:
                        tv_otherReasonTitle.setVisibility(View.VISIBLE);
                        et_otherReasonTitle.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setOrderId(String serviceType,String orderid) {
        this.serviceType = serviceType;
        this.orderid = orderid;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectReason_cancel:
                getActivity().finish();
                break;
            case R.id.selectReason_confirm:
                if (tv_otherReasonTitle.getVisibility() == View.VISIBLE) {
                    reason = et_otherReasonTitle.getText().toString();
                }
                if (type == CANCEL_TYPE) {      //取消订单.
                    getPresenter().cancelOrder(serviceType,orderid,reason);
                } else {                        // 拒绝订单.
                    getPresenter().refuseOrder(serviceType,orderid);
                }

                break;
        }
    }

    @Override
    public OrderDetailPresenter createPresenter() {
        return new OrderDetailPresenter();
    }

    @Override
    public void getSucc(OrderDetailBean bean) {

    }

    @Override
    public void getSucc(StationeryDetailBean bean) {

    }

    @Override
    public void getSucc(OrderWaterDetailBean bean) {

    }
}
