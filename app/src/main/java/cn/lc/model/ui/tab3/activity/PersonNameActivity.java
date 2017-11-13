package cn.lc.model.ui.tab3.activity;

import android.view.View;
import android.widget.EditText;
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

public class PersonNameActivity extends BaseActivity{
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_sure)
    TextView tv_sure;
    @BindView(R.id.ed_name)
    EditText ed_phone;
    @Override
    public void setContentLayout() {
        setContentView(R.layout.person_name);
        ButterKnife.bind(this);
    }
@OnClick({R.id.iv_back,R.id.tv_sure})
public void onClick(View view){
    switch (view.getId()){
        case R.id.iv_back:
            finish();
            break;
        case R.id.tv_sure:
            break;
    }
}
    @Override
    public void initView() {

    }
}
