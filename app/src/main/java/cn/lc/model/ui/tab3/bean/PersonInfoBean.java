package cn.lc.model.ui.tab3.bean;

import cn.lc.model.framework.base.BaseResponse;

/**
 * Created by Administrator on 2017/12/19.
 */

public class PersonInfoBean extends BaseResponse {


    /**
     * userInfo : {"createtime":null,"id":16,"mobile":"15137167843","password":"25f9e794323b453885f5181f1b624d0b","photo":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201711131510587842176.png","realname":"大海","servicetypeid":1,"username":"15137167843","valid":1}
     */

    private UserInfoBean userInfo;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * createtime : null
         * id : 16
         * mobile : 15137167843
         * password : 25f9e794323b453885f5181f1b624d0b
         * photo : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201711131510587842176.png
         * realname : 大海
         * servicetypeid : 1
         * username : 15137167843
         * valid : 1
         */

        private Object createtime;
        private int id;
        private String mobile;
        private String password;
        private String photo;
        private String realname;
        private int servicetypeid;
        private String username;
        private int valid;

        public Object getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Object createtime) {
            this.createtime = createtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public int getServicetypeid() {
            return servicetypeid;
        }

        public void setServicetypeid(int servicetypeid) {
            this.servicetypeid = servicetypeid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getValid() {
            return valid;
        }

        public void setValid(int valid) {
            this.valid = valid;
        }
    }
}
