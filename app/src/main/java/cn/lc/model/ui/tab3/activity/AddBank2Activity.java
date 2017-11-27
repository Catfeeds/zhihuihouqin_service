package cn.lc.model.ui.tab3.activity;

import android.os.Bundle;
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
import cn.lc.model.framework.utils.CommonUtils;
import cn.lc.model.ui.tab3.presenter.AddBankPresenter;
import cn.lc.model.ui.tab3.view.AddBankView;
import mvp.cn.util.StringUtil;
import mvp.cn.util.VerifyCheck;

/**
 * Created by Administrator on 2017/11/20.
 */

public class AddBank2Activity extends MvpSimpleActivity<AddBankView,AddBankPresenter>implements AddBankView {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.ed_cardtype)
    EditText ed_cardtype;
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.iv_check)
    ImageView iv_check;
    @BindView(R.id.tx_agreement)
    TextView tx_agreement;
    @BindView(R.id.tx_next)
    TextView tx_next;
    private String cardtype;
    private String phone;
    private String name;
    private String code;
    private int ischeck=0;
    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_addbank2);
        ButterKnife.bind(this);
    }
    @Override
    public void initView() {
            Bundle extra=getIntent().getExtras();
        if (extra!=null){
            name=extra.getString("name");
            code=extra.getString("code");
        }
    }
    @OnClick({R.id.iv_back,R.id.iv_check,R.id.tx_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                CommonUtils.hintKbTwo(this);
                finish();
                break;
            case R.id.iv_check:
                if (ischeck==0){
                    iv_check.setImageResource(R.mipmap.check_n);
                    ischeck=1;
                }else {
                    iv_check.setImageResource(R.mipmap.check_y);
                    ischeck=0;
                }
                break;
            case R.id.tx_next:
               cardtype=ed_cardtype.getText().toString();
               phone=ed_phone.getText().toString();
                CommonUtils.hintKbTwo(this);
                if (StringUtil.isNullOrEmpty(cardtype)) {
                showToast("请输入卡片类型");
                return;
            }
                if (StringUtil.isNullOrEmpty(phone)) {
                    showToast("请输入银行预留手机号");
                    return;
                }
                if (!VerifyCheck.isMobilePhoneVerify(phone)) {
                    showToast("请输入正确的手机号");
                    return;
                }
                if (ischeck==1){
                    showToast("请阅读并同意用户协议");
                    return;
                }
                break;
        }
    }


    @Override
    public void getSucc(CommonBean bean) {

    }

    @Override
    public AddBankPresenter createPresenter() {
        return new AddBankPresenter();
    }
}
