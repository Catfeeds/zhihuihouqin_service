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
         * createtime : 2017-10-21 20:44:18
         * id : 3
         * username : 老弟
         * address : 钢笔和我一起看电影的
         * img : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503054658127&di=47bddeef1d2cf2f
         * sendtime : 2017-09-19 15:57:00
         * ordercode : 081505807872418
         * mobile : 13688887777
         */

        private String createtime;
        private int id;
        private String username;
        private String address;
        private String img;
        private String sendtime;
        private String ordercode;
        private String mobile;

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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getSendtime() {
            return sendtime;
        }

        public void setSendtime(String sendtime) {
            this.sendtime = sendtime;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
