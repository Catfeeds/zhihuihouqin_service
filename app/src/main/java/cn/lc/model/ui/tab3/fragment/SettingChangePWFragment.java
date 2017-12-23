package cn.lc.model.ui.tab3.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import cn.lc.model.R;
import cn.lc.model.framework.application.SoftApplication;
import cn.lc.model.framework.base.BaseResponse;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.ui.login.activity.LoginActivity;
import cn.lc.model.ui.tab3.presenter.SystemSettingPresent;
import cn.lc.model.ui.tab3.view.SystemSettingView;
import mvp.cn.util.CrcUtil;

/**
 * Created by Administrator on 2017/12/20.
 */

public class SettingChangePWFragment extends MvpSimpleFragment<SystemSettingView,SystemSettingPresent> implements SystemSettingView,View.OnClickListener {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.et_Newpwd1)
    EditText et_pwd1;
    @BindView(R.id.et_Newpwd2)
    EditText et_pwd2;
    @BindView(R.id.tv_changePW_confirm)
    TextView tv_confirm;

    @Override
    public SystemSettingPresent createPresenter() {
        return new SystemSettingPresent();
    }

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_change_password);
    }

    @Override
    public void initView(View v) {
        iv_back.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);


    }

    @Override
    public void getSucc(BaseResponse bean) {
        showToast("密码修改成功");
        UIManager.turnToAct(getActivity(), LoginActivity.class);
        SoftApplication.softApplication.finishOther();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                getActivity().getSupportFragmentManager().popBackStackImmediate();
                break;
            case R.id.tv_changePW_confirm:
                if (et_pwd1.getText().toString().equals(et_pwd2.getText().toString())) {
                    String pw = et_pwd1.getText().toString();
                    try {
                        pw = CrcUtil.MD5(pw);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    getPresenter().changePW(pw);
                }
                break;
        }
    }
}
