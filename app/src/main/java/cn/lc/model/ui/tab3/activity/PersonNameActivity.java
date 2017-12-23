package cn.lc.model.ui.tab3.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.utils.CommonUtils;
import cn.lc.model.ui.tab3.presenter.PersonNamePresenter;
import cn.lc.model.ui.tab3.view.PersonNameView;

/**
 * Created by Administrator on 2017/11/9.
 */

public class PersonNameActivity extends MvpSimpleActivity<PersonNameView, PersonNamePresenter> implements PersonNameView {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_sure)
    TextView tv_sure;
    @BindView(R.id.ed_name)
    EditText ed_name;

    String name;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.person_name);
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
                if (ed_name.getText().toString().equals("")) {
                    showToast("请输入姓名");
                    return;
                }
                CommonUtils.hintKbTwo(this);
                name = ed_name.getText().toString();
                getPresenter().UpdateName(name);
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
        SharedPrefHelper.getInstance().setUserName(ed_name.getText().toString());
        PersonInfoActivity.isChangeName = true;
        PersonInfoActivity.name = name;
        finish();
    }
}
