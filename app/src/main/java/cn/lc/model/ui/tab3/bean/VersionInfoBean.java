package cn.lc.model.ui.tab3.bean;

import cn.lc.model.framework.base.BaseResponse;

/**
 * Created by Administrator on 2017/12/21.
 */

public class VersionInfoBean extends BaseResponse {

    /**
     * version : {"content":"xxxx","createTime":"2017-07-12 18:43:11","isForceUpdate":1,"type":0,"url":"http://image.lcworld.cn/20170712/f0bfefd20e364fc9a2697f9439de08df","versionCode":1,"versionId":5,"versionName":"1.0.0"}
     */

    private VersionBean version;

    public VersionBean getVersion() {
        return version;
    }

    public void setVersion(VersionBean version) {
        this.version = version;
    }

    public static class VersionBean {
        /**
         * content : xxxx
         * createTime : 2017-07-12 18:43:11
         * isForceUpdate : 1
         * type : 0
         * url : http://image.lcworld.cn/20170712/f0bfefd20e364fc9a2697f9439de08df
         * versionCode : 1
         * versionId : 5
         * versionName : 1.0.0
         */

        private String content;
        private String createTime;
        private int isForceUpdate;
        private int type;
        private String url;
        private int versionCode;
        private int versionId;
        private String versionName;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getIsForceUpdate() {
            return isForceUpdate;
        }

        public void setIsForceUpdate(int isForceUpdate) {
            this.isForceUpdate = isForceUpdate;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public int getVersionId() {
            return versionId;
        }

        public void setVersionId(int versionId) {
            this.versionId = versionId;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }
    }
}
