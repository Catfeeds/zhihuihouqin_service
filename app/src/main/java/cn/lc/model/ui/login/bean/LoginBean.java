package cn.lc.model.ui.login.bean;

import cn.lc.model.framework.base.BaseResponse;

/**
 * Created by Administrator on 2017/11/9.
 */

public class LoginBean extends BaseResponse{


    /**
     * token : b0cbad90-fb97-4f6b-a91a-8ff0f400be86
     * userinfo : {"uid":5,"username":"13263280712","servicetypeid":8,"photo":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201711131510587842176.png","mobile":"13263280712"}
     */

    private String token;
    private UserinfoBean userinfo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public static class UserinfoBean {
        /**
         * uid : 5
         * username : 13263280712
         * servicetypeid : 8
         * photo : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201711131510587842176.png
         * mobile : 13263280712
         */

        private int uid;
        private String username;
        private int servicetypeid;
        private String photo;
        private String mobile;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getServicetypeid() {
            return servicetypeid;
        }

        public void setServicetypeid(int servicetypeid) {
            this.servicetypeid = servicetypeid;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
