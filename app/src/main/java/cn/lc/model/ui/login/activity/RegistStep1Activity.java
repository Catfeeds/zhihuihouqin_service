package cn.lc.model.ui.login.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.login.bean.CaptchaBean;
import cn.lc.model.ui.login.presenter.RegistStep1Presenter;
import cn.lc.model.ui.login.view.RegistStep1View;
import mvp.cn.util.CommonUtil;
import mvp.cn.util.StringUtil;
import mvp.cn.util.VerifyCheck;


public class RegistStep1Activity extends MvpSimpleActivity<RegistStep1View, RegistStep1Presenter> implements RegistStep1View {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ed_mobile)
    EditText ed_mobile;
    @BindView(R.id.ed_code)
    EditText ed_code;
    @BindView(R.id.tx_gainecode)
    TextView tx_gainecode;
    @BindView(R.id.bt_next)
    Button btNext;
    private Handler handler = new Handler();
    public static final int MAX_TIME = 60;// 按钮 60秒内不能点击
    private int totalSecond = MAX_TIME;// 按钮 60秒内不能点击
    private int from = -1;
    private int type = 1;
    private String mobile;
    private  String captcha;
    /**
     * 服务器返回的验证码
     */
    private String mCaptchaServer = "";

    @Override
    public void setContentLayout() {
        setContentView(R.layout.login_regist_1);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        getPerfromData();
    }

    @Override
    public RegistStep1Presenter createPresenter() {
        return new RegistStep1Presenter();
    }

    private void getPerfromData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            from = extras.getInt("from");
           type= extras.getInt("type");
            if (from == Constants.FORGET) {
                tv_title.setText("找回密码");
            } else if (from == Constants.REGIST) {
                tv_title.setText("注册");
            }else if (from == Constants.SETPAY) {
                tv_title.setText("设置交易密码");
            }else if (from == Constants.RESETPAY) {
                tv_title.setText("修改交易密码");
            }else if (from == Constants.FORGETPAY) {
                tv_title.setText("找回交易密码");
            }
        }
    }

    @OnClick({R.id.iv_back, R.id.tx_gainecode, R.id.bt_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                CommonUtils.hintKbTwo(this);
                finish();
                break;
            case R.id.tx_gainecode:
                doGetCode();
                break;
            case R.id.bt_next:
                doNext();
                break;
        }
    }

    /**
     * 下一步
     */
    public void doNext() {
         captcha = ed_code.getText().toString().trim();
        mobile = ed_mobile.getText().toString().trim();
        if (!isPhoneChecked(mobile)) {
            return;
        }
        if (TextUtils.isEmpty(captcha)) {
            showToast("请填写验证码");
            return;
        }
//        if (!captcha.equals(mCaptchaServer)) {
//            showToast("验证码错误");
//            return;
//        }
        CommonUtil.closeSoftKeyboard(this, ed_code);
      //  getPresenter().checkcapture(mobile, SharedPrefHelper.getInstance().getServicetype()+"",captcha,"");
        turnToPwdSet(captcha);
    }

    private void turnToPwdSet(String captcha) {
        CommonUtils.hintKbTwo(this);
        Bundle b = new Bundle();
        b.putString("mobile", mobile);
        b.putString("captcha", captcha);
        b.putInt("from", from);
        UIManager.turnToAct(RegistStep1Activity.this, RegistStep2Activity.class, b);
    }

    private void turnToAgreen() {
        UIManager.turnToAct(this, AgreeActivity.class);
    }

    /**
     * 获取验证码
     */
    public void doGetCode() {
        mobile = ed_mobile.getText().toString();
        if (!isPhoneChecked(mobile)) {
            return;
        }

        getPresenter().getData(mobile);
//        doGetCodeRequest(mobile);
    }


    private void doTimer() {
        if (runnable == null) {
            runnable = new MyRunnable();
        }
        handler.post(runnable);
        tx_gainecode.setClickable(false);
    }

    void stopTimmer() {
        if (runnable != null) {
            handler.removeCallbacks(runnable);
            runnable = null;
        }
        totalSecond = MAX_TIME;
        // 倒计时完成后让按钮可点击
        tx_gainecode.setEnabled(true);
        tx_gainecode.setClickable(true);
        tx_gainecode.setText("重新获取验证码");
    }
    public MyRunnable runnable;
    @Override
    public void getSucc(CaptchaBean bean) {
        showToast("验证码发送成功，请注意查收！");
        doTimer();
        LogUtils.d("验证码"+bean.getCaptcha());
    }
    @Override
    public void checkSucc(CommonBean bean) {

    }
    public class MyRunnable implements Runnable {

        @SuppressLint("NewApi")
        @Override
        public void run() {
            handler.postDelayed(runnable, 1000);
            tx_gainecode.setText(totalSecond + "s后重新发送");
            totalSecond--;
            if (totalSecond < 0) {
                stopTimmer();
            }
        }
    }
    /**
     * 手机号校验
     *
     * @param mobile
     * @return
     */
    private boolean isPhoneChecked(String mobile) {
        if (StringUtil.isNullOrEmpty(mobile)) {
            showToast("请输入手机号");
            return false;
        }
        if (!VerifyCheck.isMobilePhoneVerify(mobile)) {
            showToast("请输入正确的手机号码");
            return false;
        }
        return true;
    }

    /**
     * 获取验证码
     *
     * @param mobile
     */
    String flag;

    /*private void doGetCodeRequest(final String mobile) {
        btNext.setClickable(false);
        showProgressDialog();
        String userId = null;
        if (from == Constants.BIND) {
            userId = softApplication.getUserInfo() == null ? null : softApplication.getUserInfo().uid;
        }


        if (from == Constants.REGIST)
            flag = "0";
        else
            flag = "1";


        Request request = RequestMaker.getInstance().getCodeRequest(mobile, userId, flag);
        getNetWorkDate(request, new SubBaseParser<>(CaptchaBean.class), new OnCompleteListener<CaptchaBean>(this) {
            @Override
            public void onSuccessed(CaptchaBean result, String resultString) {
                showToast("获取验证码成功");
                if (result.data != null) {
                    mCaptchaServer = result.data.captcha;
                    etCode.setText(mCaptchaServer);
                }
                doTimer();
            }

            @Override
            public void onCompleted(CaptchaBean result, String resultString) {
                dismissProgressDialog();
                btNext.setClickable(true);
            }

        });
    }


    *//**
     * 校验验证码,下一步
     *
     * @param captcha
     * @param mobile
     *//*
    private void doBindRequest(final String mobile, final String captcha) {
        showProgressDialog();
        String userId = softApplication.getUserInfo() == null ? null : softApplication.getUserInfo().uid;
        Request request = RequestMaker.getInstance().doBindRequest(thirdType, thirdNum, mobile, captcha, "0", null);
        getNetWorkDate(request, new SubBaseParser<UserResponse>(UserResponse.class), new OnCompleteListener<UserResponse>(this) {
            @Override
            public void onSuccessed(UserResponse result, String resultString) {
                SoftApplication.softApplication.setUserInfo(result.data);
                finishActivityAndAboveIt(LoginActivity.class.getName());
            }


            @Override
            public void onCodeError(UserResponse result) {
                if (result.errCode == -7) {
                    turnToPwdSet(captcha);
                } else {
                    super.onCodeError(result);
                }
            }

            @Override
            public void onCompleted(UserResponse result, String resultString) {
                dismissProgressDialog();
            }
        });
    }


    *//**
     * 校验验证码,下一步
     *
     * @param captcha
     * @param mobile
     *//*
    private void doNextRequest(final String mobile, final String captcha) {
        showProgressDialog();
        Request request = RequestMaker.getInstance().getCheckCaptchaRequest(mobile, captcha);
        getNetWorkDate(request, new SubBaseParser<SubBaseResponse>(SubBaseResponse.class), new OnCompleteListener<SubBaseResponse>(this) {
            @Override
            public void onSuccessed(SubBaseResponse result, String resultString) {
                Bundle b = new Bundle();
                b.putString("mobile", mobile);
                b.putString("captcha", captcha);
                b.putInt("from", from);
                UIManager.turnToAct(RegistStep1Activity.this, RegistStep2Activity.class, b);
            }

            @Override
            public void onCompleted(SubBaseResponse result, String resultString) {
                dismissProgressDialog();
            }
        });
    }*/

}
