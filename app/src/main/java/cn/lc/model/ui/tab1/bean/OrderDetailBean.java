package cn.lc.model.ui.tab1.bean;

import cn.lc.model.framework.base.BaseResponse;

/**
 * Created by Administrator on 2017/12/16.
 */

public class OrderDetailBean extends BaseResponse {

    /**
     * data : {"uid":14,"orderstatus":2,"orderchange":0,"menderid":11,"score":5,"mendermobile":"13260444370","menderphoto":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201711131510587842176.png","createtime":"2017-12-05","serviceplace":"大屯路东","paystatus":null,"invitetime":"2017/12/10 17:00","itemname":"电气焊","mendername":"董建","menditem":null,"orderid":105,"ordercode":"0011511839030020"}
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
         * uid : 14
         * orderstatus : 2
         * orderchange : 0
         * menderid : 11
         * score : 5.0
         * mendermobile : 13260444370
         * menderphoto : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201711131510587842176.png
         * createtime : 2017-12-05
         * serviceplace : 大屯路东
         * paystatus : null
         * invitetime : 2017/12/10 17:00
         * itemname : 电气焊
         * mendername : 董建
         * menditem : null
         * orderid : 105
         * ordercode : 0011511839030020
         */

        private int uid;
        private int orderstatus;
        private int orderchange;
        private int menderid;
        private double score;
        private String mendermobile;
        private String menderphoto;
        private String createtime;
        private String serviceplace;
        private Object paystatus;
        private String invitetime;
        private String itemname;
        private String mendername;
        private Object menditem;
        private int orderid;
        private String ordercode;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(int orderstatus) {
            this.orderstatus = orderstatus;
        }

        public int getOrderchange() {
            return orderchange;
        }

        public void setOrderchange(int orderchange) {
            this.orderchange = orderchange;
        }

        public int getMenderid() {
            return menderid;
        }

        public void setMenderid(int menderid) {
            this.menderid = menderid;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getMendermobile() {
            return mendermobile;
        }

        public void setMendermobile(String mendermobile) {
            this.mendermobile = mendermobile;
        }

        public String getMenderphoto() {
            return menderphoto;
        }

        public void setMenderphoto(String menderphoto) {
            this.menderphoto = menderphoto;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getServiceplace() {
            return serviceplace;
        }

        public void setServiceplace(String serviceplace) {
            this.serviceplace = serviceplace;
        }

        public Object getPaystatus() {
            return paystatus;
        }

        public void setPaystatus(Object paystatus) {
            this.paystatus = paystatus;
        }

        public String getInvitetime() {
            return invitetime;
        }

        public void setInvitetime(String invitetime) {
            this.invitetime = invitetime;
        }

        public String getItemname() {
            return itemname;
        }

        public void setItemname(String itemname) {
            this.itemname = itemname;
        }

        public String getMendername() {
            return mendername;
        }

        public void setMendername(String mendername) {
            this.mendername = mendername;
        }

        public Object getMenditem() {
            return menditem;
        }

        public void setMenditem(Object menditem) {
            this.menditem = menditem;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }
    }
}
