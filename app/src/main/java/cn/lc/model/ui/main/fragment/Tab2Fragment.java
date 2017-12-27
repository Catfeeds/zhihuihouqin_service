package cn.lc.model.ui.main.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.ui.main.presenter.Tab2Presenter;
import cn.lc.model.ui.main.view.Tab2View;
import cn.lc.model.ui.tab2.activity.MessageDetailActivity;
import cn.lc.model.ui.tab2.bean.OrderMessageBean;


/**
 * Created by hh on 2016/5/18.
 */
public class Tab2Fragment extends MvpSimpleFragment<Tab2View,Tab2Presenter> implements Tab2View {
    @BindView(R.id.rl1)
    RelativeLayout layout_System;
    @BindView(R.id.rl2)
    RelativeLayout layout_Order;
    @BindView(R.id.tx_unread2)
    TextView tv_unReadOrder;
    @BindView(R.id.tx_unread1)
    TextView tv_unReadSystem;


    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.f_tab2);
    }

    @Override
    public void initView(View v) {
        getPresenter().getOrderMessage(SharedPrefHelper.getInstance().getServicetype() + "","1","10");
    }

    @OnClick({R.id.rl1,R.id.rl2,R.id.rl3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl1:
                UIManager.turnToAct(getActivity(), MessageDetailActivity.class);
                break;
            case R.id.rl2:
                UIManager.turnToAct(getActivity(), MessageDetailActivity.class);
                break;
            case R.id.rl3:

                break;
        }
    }

    @Override
    public Tab2Presenter createPresenter() {
        return new Tab2Presenter();
    }


    @Override
    public void orderMessageSucc(OrderMessageBean bean) {
        int unReadCount = 0;
        List<OrderMessageBean.DataBean> data = bean.getData();
        for (OrderMessageBean.DataBean bean1 : data) {
            if (bean1.getIsRead() == 0) {
                unReadCount++;
            }
        }
        if (unReadCount != 0) {
            tv_unReadOrder.setVisibility(View.VISIBLE);
        }
        tv_unReadOrder.setText(String.valueOf(unReadCount));
    }
}
