package cn.lc.model.ui.login.bean;


import cn.lc.model.framework.base.BaseResponse;

/**
 *  验证码
 */
public class CaptchaBean extends BaseResponse {


    /**
     * captcha : 757532
     */

    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
