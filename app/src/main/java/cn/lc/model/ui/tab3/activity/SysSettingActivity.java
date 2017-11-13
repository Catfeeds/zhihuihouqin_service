package cn.lc.model.ui.tab3.activity;

import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.MySettingView;

/**
 * Created by Administrator on 2017/11/9.
 */

public class SysSettingActivity extends BaseActivity{
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
    public void setContentLayout() {
        setContentView(R.layout.s_setting);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

    }
    @OnClick({R.id.iv_back,R.id.s_psw,R.id.s_cache,R.id.s_version,R.id.s_aboatus,R.id.s_feedBack,R.id.s_loginOut})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.s_psw :

                break;
            case R.id.s_cache :

                break;
            case R.id.s_version :

                break;
            case R.id.s_aboatus :

                break;
            case R.id.s_feedBack:

                break;
            case R.id.s_loginOut :

                break;
        }
    }
}
