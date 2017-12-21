package cn.lc.model.ui.tab3.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseResponse;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.ui.tab3.bean.VersionInfoBean;
import cn.lc.model.ui.tab3.presenter.SystemSettingPresent;
import cn.lc.model.ui.tab3.view.SystemSettingView;

/**
 * Created by Administrator on 2017/12/21.
 */

public class VersionInfoFragment extends MvpSimpleFragment<SystemSettingView,SystemSettingPresent> implements SystemSettingView,View.OnClickListener {
    @BindView(R.id.versionInfo_content)
    TextView tv_content;
    @BindView(R.id.versionInfo_versionName)
    TextView tv_versionName;
    @BindView(R.id.iv_back)
    ImageView iv_back;

    @Override
    public void getSucc(BaseResponse bean) {
        if (bean instanceof VersionInfoBean) {
            VersionInfoBean data = ((VersionInfoBean) bean);
            tv_content.setText(data.getVersion().getContent());
            tv_versionName.setText(data.getVersion().getVersionName());
        }
    }

    @Override
    public SystemSettingPresent createPresenter() {
        return new SystemSettingPresent();
    }

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_version_info);
    }

    @Override
    public void initView(View v) {
        getPresenter().getVersionInfo();
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }
}
