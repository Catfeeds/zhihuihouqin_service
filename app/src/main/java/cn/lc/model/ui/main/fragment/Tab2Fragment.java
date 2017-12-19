package cn.lc.model.ui.main.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.ui.main.presenter.Tab2Presenter;
import cn.lc.model.ui.main.view.Tab2View;
import cn.lc.model.ui.tab2.activity.MessageDetailActivity;


/**
 * Created by hh on 2016/5/18.
 */
public class Tab2Fragment extends MvpSimpleFragment<Tab2View,Tab2Presenter> implements Tab2View {
    @BindView(R.id.rl1)
    RelativeLayout layout_System;
    @BindView(R.id.rl2)
    RelativeLayout layout_Order;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.f_tab2);
    }

    @Override
    public void initView(View v) {

    }

    @OnClick({R.id.rl1,R.id.rl2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl1:
                UIManager.turnToAct(getActivity(), MessageDetailActivity.class);
                break;
            case R.id.rl2:
                UIManager.turnToAct(getActivity(), MessageDetailActivity.class);
                break;
        }
    }

    @Override
    public Tab2Presenter createPresenter() {
        return new Tab2Presenter();
    }

}
