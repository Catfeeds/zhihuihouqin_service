package cn.lc.model.ui.tab3.bean;

import cn.lc.model.framework.base.BaseResponse;

/**
 * Created by Administrator on 2017/12/19.
 */

public class PersonInfoBean extends BaseResponse {

    /**
     * error_code : 0
     * data : {"uid":"1","username":"12154545","name":"吴系挂","photo":"","realname":"1436864169","mobile":"0"}
     */

    private int error_code;
    private DataBean data;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 1
         * username : 12154545
         * name : 吴系挂
         * photo :
         * realname : 1436864169
         * mobile : 0
         */

        private String uid;
        private String username;
        private String name;
        private String photo;
        private String realname;
        private String mobile;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
