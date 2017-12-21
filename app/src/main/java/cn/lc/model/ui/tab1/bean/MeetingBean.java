package cn.lc.model.ui.tab1.bean;

import java.util.List;

import cn.lc.model.framework.base.BaseResponse;

/**
 * Created by Administrator on 2017/12/21.
 */

public class MeetingBean extends BaseResponse {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String attendnum;       // 会场人数
        private String attentdleader;   // 参会领导
        private String conferencename;  // 会议室名称;
        private String conferencetype;  //会议类型
        private String createtime;      // 下单时间;
        private String date;            // 送达时间
        private String equipmentids;    // 设备ID;
        private String id;              // 订单id
        private String imgs;            // 图片;
        private String mobile;          // 手机号;
        private String ordercode;       // 订单号
        private String remark;          // 备注
        private String status;          // 订单状态;
        private String uid;             // 用户id
        private String username;        // 下单人姓名.
        private String name;            // 设备名.

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
