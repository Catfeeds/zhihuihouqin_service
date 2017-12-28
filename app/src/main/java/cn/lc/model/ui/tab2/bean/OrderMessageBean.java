package cn.lc.model.ui.tab2.bean;

import java.util.List;

import cn.lc.model.framework.base.BaseResponse;

/**
 * Created by Administrator on 2017/12/26.
 */

public class OrderMessageBean extends BaseResponse {

    private List<DataBean> data;
    private List<SystemBean> system;

    public List<SystemBean> getSystem() {
        return system;
    }

    public void setSystem(List<SystemBean> system) {
        this.system = system;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class SystemBean{
        /**
         *   "content": "欢迎使用智慧后勤用户端",
         "createtime": "2017-09-20 15:06:45",
         "detailId": 0,
         "id": 1,
         "isDel": false,
         "isRead": false,
         "messagetitle": "系统消息:欢迎使用",
         "messagetype": 1,
         "newCount": 0,
         "read": 0,
         "uid": 0
         */
        private String content;
        private String createtime;
        private String detailId;
        private String id;
        private boolean isDel;
        private boolean isRead;
        private String messagetitle;
        private String messagetype;
        private String newCount;
        private String read;
        private String uid;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getDetailId() {
            return detailId;
        }

        public void setDetailId(String detailId) {
            this.detailId = detailId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isDel() {
            return isDel;
        }

        public void setDel(boolean del) {
            isDel = del;
        }

        public boolean isRead() {
            return isRead;
        }

        public void setRead(boolean read) {
            isRead = read;
        }

        public String getMessagetitle() {
            return messagetitle;
        }

        public void setMessagetitle(String messagetitle) {
            this.messagetitle = messagetitle;
        }

        public String getMessagetype() {
            return messagetype;
        }

        public void setMessagetype(String messagetype) {
            this.messagetype = messagetype;
        }

        public String getNewCount() {
            return newCount;
        }

        public void setNewCount(String newCount) {
            this.newCount = newCount;
        }

        public String getRead() {
            return read;
        }

        public void setRead(String read) {
            this.read = read;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
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
