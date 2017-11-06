package cn.lc.model.ui.login.activity;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.ui.main.presenter.MainPresenter;
import cn.lc.model.ui.main.view.MainView;

/**
 * Created by Administrator on 2017/11/6.
 */

public class SplashActivity extends MvpSimpleActivity<MainView,MainPresenter> {
    @BindView(R.id.tv_jump)
    TextView tv_jump;
    @Override
    public void setContentLayout() {
            setContentView(R.layout.adapter_simple_guide);
        ButterKnife.bind(this);
    }
@OnClick({R.id.tv_jump})
public  void onClick(View view){
    switch (view.getId()){
        case R.id.tv_jump:
            UIManager.turnToAct(this,LoginActivity.class);
            finish();
            break;
    }
}
    @Override
    public void initView() {

    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }
}
