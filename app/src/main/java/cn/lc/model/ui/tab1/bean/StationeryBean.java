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
         * avatar : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201711061509934801134.png
         * createtime : 2017-12-12
         * id : 126
         * invitetime : 2017-12-12 9:00-10:00
         * itemname : 电气焊
         * mendcontent : 哦啦啦
         * menderid : 16
         * mendermobile : 15137167843
         * mendername : 大海
         * menderphoto : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201711131510587842176.png
         * mendimgs :
         * menditem : 0
         * orderchange : 0
         * ordercode : 0011222230601398
         * orderstatus : 1
         * paystatus : 0
         * phone : 15201025750
         * score : 0
         * serviceplace : 哦啦啦
         * uid : 45
         * username : 王
         */

        private String avatar;
        private String createtime;
        private int id;
        private String invitetime;
        private String itemname;
        private String mendcontent;
        private int menderid;
        private String mendermobile;
        private String mendername;
        private String menderphoto;
        private String mendimgs;
        private int menditem;
        private int orderchange;
        private String ordercode;
        private int orderstatus;
        private int paystatus;
        private String phone;
        private int score;
        private String serviceplace;
        private int uid;
        private String username;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getMendcontent() {
            return mendcontent;
        }

        public void setMendcontent(String mendcontent) {
            this.mendcontent = mendcontent;
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

        public String getMendimgs() {
            return mendimgs;
        }

        public void setMendimgs(String mendimgs) {
            this.mendimgs = mendimgs;
        }

        public int getMenditem() {
            return menditem;
        }

        public void setMenditem(int menditem) {
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

        public int getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(int orderstatus) {
            this.orderstatus = orderstatus;
        }

        public int getPaystatus() {
            return paystatus;
        }

        public void setPaystatus(int paystatus) {
            this.paystatus = paystatus;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
