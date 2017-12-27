package cn.lc.model.ui.tab2.bean;

import java.util.List;

import cn.lc.model.framework.base.BaseResponse;

/**
 * Created by Administrator on 2017/12/26.
 */

public class OrderMessageBean extends BaseResponse {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * autoId : 1
         * createTime : 2017-12-26 14:07:42
         * isDelete : 0
         * isRead : 0
         * messageContent : 测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content测试-content
         * messageTitle : 测试-title0
         * orderId : 0
         * serverTypeId : 1
         * uid : 14
         */

        private int autoId;
        private String createTime;
        private int isDelete;
        private int isRead;
        private String messageContent;
        private String messageTitle;
        private int orderId;
        private int serverTypeId;
        private int uid;

        public int getAutoId() {
            return autoId;
        }

        public void setAutoId(int autoId) {
            this.autoId = autoId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public String getMessageContent() {
            return messageContent;
        }

        public void setMessageContent(String messageContent) {
            this.messageContent = messageContent;
        }

        public String getMessageTitle() {
            return messageTitle;
        }

        public void setMessageTitle(String messageTitle) {
            this.messageTitle = messageTitle;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getServerTypeId() {
            return serverTypeId;
        }

        public void setServerTypeId(int serverTypeId) {
            this.serverTypeId = serverTypeId;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
