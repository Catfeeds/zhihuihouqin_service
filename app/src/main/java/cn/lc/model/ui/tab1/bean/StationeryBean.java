package cn.lc.model.ui.tab1.bean;

import java.util.List;

import cn.lc.model.framework.base.BaseResponse;

/**
 * Created by Administrator on 2017/11/16.
 */

public class StationeryBean extends BaseResponse{

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * createtime : 2017-12-05
         * invitetime : 2017/12/10 17:00
         * itemname : 电气焊
         * menderid : 11
         * mendermobile : 18765903804
         * mendername : 方法
         * menderphoto : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:201711221511337744522.jpg
         * menditem : null
         * orderchange : 0
         * ordercode : 0011511839030020
         * orderid : 105
         * orderstatus : 2
         * paystatus : null
         * score : null
         * serviceplace : 大屯路东
         * uid : 14
         *
         */

        private String createtime;
        private String invitetime;
        private String itemname;
        private int menderid;
        private String mendermobile;
        private String mendername;
        private String menderphoto;
        private Object menditem;
        private int orderchange;
        private String ordercode;
        private int orderid;
        private int orderstatus;
        private Object paystatus;
        private Object score;
        private String serviceplace;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
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

        public int getMenderid() {
            return menderid;
        }

        public void setMenderid(int menderid) {
            this.menderid = menderid;
        }

        public String getMendermobile() {
            return mendermobile;
        }

        public void setMendermobile(String mendermobile) {
            this.mendermobile = mendermobile;
        }

        public String getMendername() {
            return mendername;
        }

        public void setMendername(String mendername) {
            this.mendername = mendername;
        }

        public String getMenderphoto() {
            return menderphoto;
        }

        public void setMenderphoto(String menderphoto) {
            this.menderphoto = menderphoto;
        }

        public Object getMenditem() {
            return menditem;
        }

        public void setMenditem(Object menditem) {
            this.menditem = menditem;
        }

        public int getOrderchange() {
            return orderchange;
        }

        public void setOrderchange(int orderchange) {
            this.orderchange = orderchange;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public int getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(int orderstatus) {
            this.orderstatus = orderstatus;
        }

        public Object getPaystatus() {
            return paystatus;
        }

        public void setPaystatus(Object paystatus) {
            this.paystatus = paystatus;
        }

        public Object getScore() {
            return score;
        }

        public void setScore(Object score) {
            this.score = score;
        }

        public String getServiceplace() {
            return serviceplace;
        }

        public void setServiceplace(String serviceplace) {
            this.serviceplace = serviceplace;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
