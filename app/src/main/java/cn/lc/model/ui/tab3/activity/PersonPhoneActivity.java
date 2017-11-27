package cn.lc.model.ui.tab3.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.utils.CommonUtils;
import cn.lc.model.ui.tab3.event.PersonInfoEnvent;
import cn.lc.model.ui.tab3.presenter.PersonNamePresenter;
import cn.lc.model.ui.tab3.view.PersonNameView;
import mvp.cn.util.VerifyCheck;

/**
 * Created by Administrator on 2017/11/9.
 */

public class PersonPhoneActivity extends MvpSimpleActivity<PersonNameView,PersonNamePresenter> implements PersonNameView {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_sure)
    TextView tv_sure;
    @BindView(R.id.ed_phone)
    EditText ed_phone;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.person_phone);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                CommonUtils.hintKbTwo(this);
                finish();
                break;
            case R.id.tv_sure:
                if (!VerifyCheck.isMobilePhoneVerify(ed_phone.getText().toString())) {
                    showToast("请输入正确的手机号");
                    return;
                }
                CommonUtils.hintKbTwo(this);
                getPresenter().UpdateName("",ed_phone.getText().toString());
                break;
        }
    }

    @Override
    public void initView() {

    }


    @Override
    public PersonNamePresenter createPresenter() {
        return new PersonNamePresenter();
    }

    @Override
    public void updateSucc(CommonBean bean) {
        SharedPrefHelper.getInstance().setUserName(ed_phone.getText().toString());
        EventBus.getDefault().post(new PersonInfoEnvent());
        finish();
    }
}