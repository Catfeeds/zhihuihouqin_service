package cn.lc.model.ui.tab1.bean;

import cn.lc.model.framework.base.BaseResponse;

/**
 * Created by Administrator on 2017/12/21.
 */

public class MeetingDetailBean extends BaseResponse {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String attendnum;
        private String attentdleader;
        private String conferencename;
        private String conferencetype;
        private String createtime;
        private String date;
        private String equipmentids;
        private String id;
        private String imgs;
        private String mobile;
        private String ordercode;
        private String remark;
        private String status;
        private String uid;
        private String username;
        private String name;

        public String getAttendnum() {
            return attendnum;
        }

        public void setAttendnum(String attendnum) {
            this.attendnum = attendnum;
        }

        public String getAttentdleader() {
            return attentdleader;
        }

        public void setAttentdleader(String attentdleader) {
            this.attentdleader = attentdleader;
        }

        public String getConferencename() {
            return conferencename;
        }

        public void setConferencename(String conferencename) {
            this.conferencename = conferencename;
        }

        public String getConferencetype() {
            return conferencetype;
        }

        public void setConferencetype(String conferencetype) {
            this.conferencetype = conferencetype;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getEquipmentids() {
            return equipmentids;
        }

        public void setEquipmentids(String equipmentids) {
            this.equipmentids = equipmentids;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

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
    }
}
