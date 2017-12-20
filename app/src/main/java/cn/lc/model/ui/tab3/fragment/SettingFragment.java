package cn.lc.model.ui.tab3.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.application.SoftApplication;
import cn.lc.model.framework.base.BaseResponse;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.framework.widget.MySettingView;
import cn.lc.model.ui.login.activity.LoginActivity;
import cn.lc.model.ui.tab3.activity.SysFeedBackActivity;
import cn.lc.model.ui.tab3.presenter.SystemSettingPresent;
import cn.lc.model.ui.tab3.view.SystemSettingView;

/**
 * Created by Administrator on 2017/12/20.
 */

public class SettingFragment extends MvpSimpleFragment<SystemSettingView,SystemSettingPresent> implements SystemSettingView{
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.s_psw)
    MySettingView s_psw;
    @BindView(R.id.s_cache)
    MySettingView s_cache;
    @BindView(R.id.s_version)
    MySettingView s_version;
    @BindView(R.id.s_aboatus)
    MySettingView s_aboutus;
    @BindView(R.id.s_feedBack)
    MySettingView s_feedBack;
    @BindView(R.id.s_loginOut)
    MySettingView s_loginOut;

    @Override
    public SystemSettingPresent createPresenter() {
        return new SystemSettingPresent();
    }

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_setting);
    }

    @Override
    public void initView(View v) {

    }

    @OnClick({R.id.iv_back,R.id.s_psw,R.id.s_cache,R.id.s_version,R.id.s_aboatus,R.id.s_feedBack,R.id.s_loginOut})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.s_psw :
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.setting_content,new SettingChangePWFragment())
                        .addToBackStack("")
                        .commit();
                break;
            case R.id.s_cache :

                break;
            case R.id.s_version :

                break;
            case R.id.s_aboatus :

                break;
            case R.id.s_feedBack:
                UIManager.turnToAct(getActivity(),SysFeedBackActivity.class);
                break;
            case R.id.s_loginOut :
                UIManager.turnToAct(getActivity(), LoginActivity.class);
                SoftApplication.softApplication.finishOther();
                break;
        }
    }

    @Override
    public void getSucc(BaseResponse bean) {

    }
}
