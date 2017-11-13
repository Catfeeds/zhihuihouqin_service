package cn.lc.model.ui.main.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.ui.main.presenter.Tab3Presenter;
import cn.lc.model.ui.main.view.Tab3View;
import cn.lc.model.ui.tab3.activity.MyWalletActivity;
import cn.lc.model.ui.tab3.activity.PersonInfoActivity;
import cn.lc.model.ui.tab3.activity.SysSettingActivity;

/**
 * Created by hh on 2016/5/18.
 */
public class Tab3Fragment extends MvpSimpleFragment<Tab3View, Tab3Presenter> implements Tab3View {
    @BindView(R.id.iv_header)
    ImageView iv_header;
    @BindView(R.id.tx_name)
    TextView tx_name;
    @BindView(R.id.tx_dis)
    TextView tx_dis;
    @BindView(R.id.rl_order)
    RelativeLayout rl_order;
    @BindView(R.id.rl_comment)
    RelativeLayout rl_comment;
    @BindView(R.id.rl_wallet)
    RelativeLayout rl_wallet;
    @BindView(R.id.rl_sys)
    RelativeLayout rl_sys;
    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.f_tab3);
        ButterKnife.bind(getActivity());
    }

    @Override
    public void initView(View v) {

    }
@OnClick({R.id.iv_header,R.id.tx_name,R.id.tx_dis,R.id.rl_order,R.id.rl_comment,R.id.rl_wallet,R.id.rl_sys})
public void onClick(View view ){
    switch (view.getId()){
        case R.id.iv_header:
            UIManager.turnToAct(getActivity(), PersonInfoActivity.class);
            break;
        case R.id.tx_name:

            break;
        case R.id.tx_dis:

            break;
        case R.id.rl_order:

            break;
        case R.id.rl_comment:

            break;
        case R.id.rl_wallet:
            UIManager.turnToAct(getActivity(), MyWalletActivity.class);
            break;
        case R.id.rl_sys:
    UIManager.turnToAct(getActivity(), SysSettingActivity.class);
            break;
    }
}
    @Override
    public Tab3Presenter createPresenter() {
        return new Tab3Presenter();
    }
}
