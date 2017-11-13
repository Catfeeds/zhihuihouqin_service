package cn.lc.model.ui.login.bean;

import cn.lc.model.framework.base.BaseResponse;

/**
 * Created by Administrator on 2017/11/9.
 */

public class LoginBean extends BaseResponse{

    /**
     * loginresult : {"servicetypeid":2,"token":"48611854-c4a9-4c24-b0e0-e67897c40ad0","uid":1,"userName":"18515826536"}
     */

    private LoginresultBean loginresult;

    public LoginresultBean getLoginresult() {
        return loginresult;
    }

    public void setLoginresult(LoginresultBean loginresult) {
        this.loginresult = loginresult;
    }

    public static class LoginresultBean {
        /**
         * servicetypeid : 2
         * token : 48611854-c4a9-4c24-b0e0-e67897c40ad0
         * uid : 1
         * userName : 18515826536
         */

        private int servicetypeid;
        private String token;
        private int uid;
        private String userName;

        public int getServicetypeid() {
            return servicetypeid;
        }

        public void setServicetypeid(int servicetypeid) {
            this.servicetypeid = servicetypeid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
