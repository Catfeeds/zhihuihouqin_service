package cn.lc.model.ui.tab3.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.framework.contant.Constants;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.framework.utils.CommonUtils;
import cn.lc.model.ui.login.activity.RegistStep1Activity;
import cn.lc.model.ui.tab3.presenter.SysFeedBackPresenter;
import cn.lc.model.ui.tab3.view.SysFeedBackView;
import mvp.cn.util.StringUtil;
import mvp.cn.util.VerifyCheck;

/**
 * Created by Administrator on 2017/11/20.
 */

public class SysFeedBackActivity extends MvpSimpleActivity<SysFeedBackView, SysFeedBackPresenter> implements SysFeedBackView {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.btn_commit)
    Button btn_commit;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.sys_feedback);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        et_content.addTextChangedListener(watcher);
    }

    @OnClick({R.id.iv_back, R.id.et_content, R.id.et_phone, R.id.btn_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                CommonUtils.hintKbTwo(this);
                finish();
                break;
            case R.id.btn_commit:
                String content = et_content.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();
                CommonUtils.hintKbTwo(this);
                if (StringUtil.isNullOrEmpty(content)) {
                    showToast("请输入内容");
                    return;
                }
                if (!VerifyCheck.isMobilePhoneVerify(phone)) {
                    showToast("请输入正确的手机号");
                    return;
                }
                break;
        }

    }

    @Override
    public void getSucc(CommonBean bean) {

    }

    @Override
    public SysFeedBackPresenter createPresenter() {
        return new SysFeedBackPresenter();
    }

    TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 200) {
                showToast("超出最大数字限制");
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            tvNum.setText(s.length() + "/200");
        }
    };
}
