package cn.lc.model.ui.tab3.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.framework.utils.CommonUtils;
import cn.lc.model.ui.tab3.presenter.GetMoneyPresenter;
import cn.lc.model.ui.tab3.view.GetMoneyView;
import mvp.cn.util.StringUtil;

/**
 * Created by Administrator on 2017/11/20.
 */

public class GetMoneyActivity extends MvpSimpleActivity<GetMoneyView,GetMoneyPresenter>implements GetMoneyView {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.ll_getcard)
    LinearLayout ll_getcard;
    @BindView(R.id.tx_cardtype)
    TextView tx_cardtype;
    @BindView(R.id.ed_money)
    EditText ed_money;
    @BindView(R.id.tx_allmoney)
    TextView tx_allmoney;
    @BindView(R.id.tx_next)
    TextView tx_next;
    private String money;
    private String allmoney;
    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_getmoney);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back,R.id.ll_getcard,R.id.tx_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                CommonUtils.hintKbTwo(this);
                finish();
                break;
            case R.id.ll_getcard:
                break;
            case R.id.tx_next:
                money = ed_money.getText().toString();
                allmoney=tx_allmoney.getText().toString();
                CommonUtils.hintKbTwo(this);
                if (StringUtil.isNullOrEmpty(allmoney)||allmoney.equals("0")){
                    showToast("您的可提现金额为0");
                    return;
                }
                if (StringUtil.isNullOrEmpty(money)) {
                    showToast("请输入提现金额");
                    return;
                }
                if (Integer.parseInt(money)>Integer.parseInt(allmoney)){
                    showToast("可提现金额不足，请重新输入！");
                    return;
                }
                break;
        }

    }
    @Override
    public void initView() {

    }

    @Override
    public void getSucc(CommonBean bean) {

    }

    @Override
    public GetMoneyPresenter createPresenter() {
        return new GetMoneyPresenter();
    }
}
