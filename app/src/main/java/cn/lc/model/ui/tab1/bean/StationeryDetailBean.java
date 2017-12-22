package cn.lc.model.ui.tab1.bean;

import cn.lc.model.framework.base.BaseResponse;

/**
 * Created by Administrator on 2017/12/18.
 */

public class StationeryDetailBean extends BaseResponse {


    /**
     * data : {"address":"大屯","code":"0081513076344350","count":"","createtime":"2017-12-22 15:07:36","expertarrivaltme":"2017-12-15 17:06:23","id":188,"img":"","mobile":"13260444370","productname":"","remark":"","status":0,"totalprice":"","uid":0,"username":"哈哈哈哈哈"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address : 大屯
         * code : 0081513076344350
         * count :
         * createtime : 2017-12-22 15:07:36
         * expertarrivaltme : 2017-12-15 17:06:23
         * id : 188
         * img :
         * mobile : 13260444370
         * productname :
         * remark :
         * status : 0
         * totalprice :
         * uid : 0
         * username : 哈哈哈哈哈
         */

        private String address;
        private String code;
        private String count;
        private String createtime;
        private String expertarrivaltme;
        private int id;
        private String img;
        private String mobile;
        private String productname;
        private String remark;
        private int status;
        private String totalprice;
        private int uid;
        private String username;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getExpertarrivaltme() {
            return expertarrivaltme;
        }

        public void setExpertarrivaltme(String expertarrivaltme) {
            this.expertarrivaltme = expertarrivaltme;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

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
    }
}
