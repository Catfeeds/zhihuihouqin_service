package cn.lc.model.ui.login.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseResponse;
import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.framework.contant.Constants;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.utils.CommonUtils;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.login.presenter.RegistStep2Presenter;
import cn.lc.model.ui.login.view.RegistStep2View;
import mvp.cn.util.CommonUtil;
import mvp.cn.util.CrcUtil;
import mvp.cn.util.Md5Util;


public class RegistStep2Activity extends MvpSimpleActivity<RegistStep2View, RegistStep2Presenter> implements View.OnClickListener,RegistStep2View {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ed_psw1)
    EditText ed_psw1;
    @BindView(R.id.ed_psw2)
    EditText ed_psw2;
    @BindView(R.id.bt_next)
    Button btNext;
    private String mMobile;
    private String mCptcha;
    private int from=-1;
    private int type=1;
    private String md5Pwd;
    @Override
    public void setContentLayout() {
        setContentView(R.layout.login_regist_2);
    }
    @Override
    public void initView() {
        ButterKnife.bind(this);
        getPerformData();

    }

    @Override
    public RegistStep2Presenter createPresenter() {
        return new RegistStep2Presenter();
    }

    private void getPerformData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mMobile = extras.getString("mobile");
            mCptcha = extras.getString("captcha");
            from = extras.getInt("from");
            type = extras.getInt("type");
            if (from == Constants.FORGET) {
                tv_title.setText("设置密码");
                btNext.setText("注册");
            } else if (from == Constants.REGIST) {
                tv_title.setText("设置新密码");
                btNext.setText("完成");
            }else if (from == Constants.SETPAY) {
                tv_title.setText("设置交易密码");
                btNext.setText("完成");
            }else if (from == Constants.RESETPAY) {
                tv_title.setText("修改交易密码");
                btNext.setText("完成");
            }else if (from == Constants.FORGETPAY) {
                tv_title.setText("找回交易密码");
                btNext.setText("完成");
            }
        }
    }

    @OnClick({R.id.iv_back, R.id.bt_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                CommonUtils.hintKbTwo(this);
                finish();
                break;
            case R.id.bt_next:
                if (from == Constants.FORGET) {
                    doRegist();
                } else if (from == Constants.REGIST) {
                    doRegist();
                }else if (from == Constants.SETPAY) {

                }else if (from == Constants.RESETPAY) {

                }else if (from == Constants.FORGETPAY) {

                }
                break;
        }
    }

    /**
     * 注册
     */
    public void doRegist() {
        String pwd1 = ed_psw1.getText().toString().trim();
        String pwd2 = ed_psw2.getText().toString().trim();

        if (!isOtherChecked(pwd1, pwd2)) {
            return;
        }

        try {
          md5Pwd = CrcUtil.MD5(pwd1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CommonUtils.hintKbTwo(this);
        getPresenter().register(mMobile, md5Pwd, SharedPrefHelper.getInstance().getServicetype()+"",mCptcha);
          //doResistRequest(mMobile, mCptcha, pwd1);''
    }

    /**
     * 其他校验
     *
     * @param pwd1
     * @param pwd2
     * @return
     */
    private boolean isOtherChecked(String pwd1, String pwd2) {

        if (TextUtils.isEmpty(pwd1) || TextUtils.isEmpty(pwd2)) {
            showToast("请输入密码");
            return false;
        } else if (pwd1.length() < 6) {
            showToast("密码长度不能小于6位");
            return false;
        } else if (pwd1.length() > 20) {
            showToast("密码长度不能大于20位");
            return false;
        }

        if (!pwd1.equals(pwd2)) {
            showToast("密码输入不一致");
            return false;
        }
        return true;
    }

    @Override
    public void RegisterSucc(CommonBean bean) {
        showToast("注册成功");
        UIManager.turnToAct(RegistStep2Activity.this, LoginActivity.class);
        finishActivityAndAboveIt(RegistStep1Activity.class.getName());
        finish();;
    }

    /**
     * 提交,设置密码,根据from来判断是忘记密码提交,还是注册提交
     *
     * @param pwd
     * @param captcha
     * @param mobile
     */
    /*private void doResistRequest(final String mobile, String captcha, String pwd) {
        String md5Pwd = null;
        try {
            md5Pwd = CrcUtil.MD5(pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showProgressDialog();
        Request request = RequestMaker.getInstance().getRegistRequest(mobile, captcha, md5Pwd);
        getNetWorkDate(request, new SubBaseParser<UserResponse>(UserResponse.class), new OnCompleteListener<UserResponse>(this) {
            @Override
            public void onSuccessed(UserResponse result, String resultString) {
                showToast("注册成功");
                SoftApplication.softApplication.setUserInfo(result.data);
//                SoftApplication.softApplication.setSignId(result.token);

                if (from == Constants.REGIST) {
                    UIManager.turnToAct(RegistStep2Activity.this, CompleteInfoActivity.class);
                    finishActivityAndAboveIt(LoginActivity.class.getName());
                } else if (from == Constants.FORGET) {
                    //关闭前边的两个界面
                    finishActivityAndAboveIt(RegistStep1Activity.class.getName());
                }

            }

            @Override
            public void onCompleted(UserResponse result, String resultString) {
                super.onCompleted(result, resultString);
                dismissProgressDialog();
            }
        });
    }


    *//**
     * 校验验证码,下一步
     *
     *//*
    private void doBindRequest(final String mobile, final String captcha, String pwd) {
        showProgressDialog();
        String userId = softApplication.getUserInfo() == null ? null : softApplication.getUserInfo().uid;
        Request request = RequestMaker.getInstance().doBindRequest(thirdType, thirdNum, mobile, captcha, "1", pwd);
        getNetWorkDate(request, new SubBaseParser<UserResponse>(UserResponse.class), new OnCompleteListener<UserResponse>(this) {
            @Override
            public void onSuccessed(UserResponse result, String resultString) {
                SoftApplication.softApplication.setUserInfo(result.data);
                finishActivityAndAboveIt(LoginActivity.class.getName());
            }


            @Override
            public void onCodeError(UserResponse result) {
                super.onCodeError(result);
            }

            @Override
            public void onCompleted(UserResponse result, String resultString) {
                dismissProgressDialog();
            }
        });
    }*/


}
