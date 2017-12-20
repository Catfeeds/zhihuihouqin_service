package cn.lc.model.ui.tab3.activity;

import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.ui.tab3.fragment.SettingFragment;
import cn.lc.model.ui.tab3.presenter.SystemSettingPresent;
import cn.lc.model.ui.tab3.view.SystemSettingView;

/**
 * Created by Administrator on 2017/11/9.
 */

public class SysSettingActivity extends MvpSimpleActivity<SystemSettingView,SystemSettingPresent> {


    @Override
    public void setContentLayout() {
        setContentView(R.layout.s_setting);
    }

    @Override
    public void initView() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.setting_content,new SettingFragment())
                .commit();
    }


    @Override
    public SystemSettingPresent createPresenter() {
        return new SystemSettingPresent();
    }
}
