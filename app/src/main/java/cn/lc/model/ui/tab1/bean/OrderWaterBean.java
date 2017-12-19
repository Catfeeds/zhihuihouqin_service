package cn.lc.model.ui.tab1.bean;

import java.util.List;

import cn.lc.model.framework.base.BaseResponse;

/**
 * Created by Administrator on 2017/12/19.
 */

public class OrderWaterBean extends BaseResponse {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String address;         //地址
        private String ordercode;        //订单号
        private String createtime;       // 下单时间
        private String endtime;          // 送货时间
        private String id;              // 订单id
        private String img;             // 图片
        private String mobile;          // 手机号
        private String status;          // 订单状态
        private String uid;             // 用户id
        private String realname;        // 用户名
        private String count;           // 总数
        private String name;            // 商品名称
        private String remark;          // 备注
        private String totalprice;      // 总价

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }
    }
}
