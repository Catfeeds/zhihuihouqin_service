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
import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.framework.contant.Constants;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.ui.login.activity.RegistStep1Activity;
import cn.lc.model.ui.tab3.presenter.GetMoneyPresenter;
import cn.lc.model.ui.tab3.view.GetMoneyView;
import mvp.cn.util.StringUtil;

/**
 * Created by Administrator on 2017/11/20.
 */

public class PwdManagerActivity extends MvpSimpleActivity<GetMoneyView,GetMoneyPresenter>implements GetMoneyView {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tx_setpaypwd)
    TextView tx_setpaypwd;
   @BindView(R.id.tx_paypwd)
   TextView tx_paypwd;
    @BindView(R.id.tx_forgetpaypwd)
    TextView tx_forgetpaypwd;
    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_pwdmanager);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back,R.id.tx_setpaypwd,R.id.tx_paypwd,R.id.tx_forgetpaypwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tx_setpaypwd:
                Bundle b1 = new Bundle();
                b1.putInt("from", Constants.SETPAY);
                UIManager.turnToAct(this, RegistStep1Activity.class, b1);
                break;

            case R.id.tx_paypwd:
                Bundle b2 = new Bundle();
                b2.putInt("from", Constants.RESETPAY);
                UIManager.turnToAct(this, RegistStep1Activity.class, b2);
                break;
            case R.id.tx_forgetpaypwd:
                Bundle b3 = new Bundle();
                b3.putInt("from", Constants.FORGETPAY);
                UIManager.turnToAct(this, RegistStep1Activity.class, b3);
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
