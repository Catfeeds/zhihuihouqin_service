package cn.lc.model.ui.tab3.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;

/**
 * Created by Administrator on 2017/11/9.
 */

public class MyWalletActivity extends BaseActivity{
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_card)
    TextView tv_card;
    @BindView(R.id.tv_tixian)
    TextView tv_tixian;
    @BindView(R.id.tv_pwd)
    TextView tv_psw;
    @Override
    public void setContentLayout() {
        setContentView(R.layout.mywallet);
        ButterKnife.bind(this);
    }
@OnClick({R.id.iv_back,R.id.tv_card,R.id.tv_tixian,R.id.tv_pwd})
public void onClick(View view){
    switch (view.getId()){
        case R.id.iv_back:
            finish();
            break;
        case R.id.tv_card:
            break;
        case  R.id.tv_tixian:
            break;
        case R.id.tv_pwd:
            break;
    }
}
    @Override
    public void initView() {

    }
}
