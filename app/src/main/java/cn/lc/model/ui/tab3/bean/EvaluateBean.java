package cn.lc.model.ui.tab3.bean;

import java.util.List;

import cn.lc.model.framework.base.BaseResponse;

/**
 * Created by Administrator on 2017/12/20.
 */

public class EvaluateBean extends BaseResponse {

    /**
     * page : {"currPage":1,"list":[{"anonymous":0,"content":"测试专用","createtime":"2017-12-22 14:51:07","id":24,"imgs":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:img201710111507706335796.jpg,http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:img201710111507706335848.jpg","menderid":14,"orderid":4,"productscore":5,"replyList":[],"replyname":"快看看","replyphoto":"http://dentist.oss-cn-beijing.aliyuncs.com/2017-12-22/1513924929460.png","score":5,"servicescore":5,"type":"1","uid":14},{"anonymous":0,"content":"好的","createtime":"2017-12-22 14:51:14","id":23,"imgs":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:img201710111507706335796.jpg,http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:img201710111507706335848.jpg","menderid":14,"orderid":4,"productscore":0,"replyList":[],"replyname":"快看看","replyphoto":"http://dentist.oss-cn-beijing.aliyuncs.com/2017-12-22/1513924929460.png","score":0,"servicescore":0,"type":"1","uid":14},{"anonymous":1,"content":"123","createtime":"2017-12-22 14:48:40","id":6,"imgs":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:img201708161502864259736.jpg","menderid":14,"orderid":0,"productscore":0,"replyList":[],"replyname":"快看看","replyphoto":"http://dentist.oss-cn-beijing.aliyuncs.com/2017-12-22/1513924929460.png","score":5,"servicescore":3,"type":"1","uid":14},{"anonymous":0,"content":"123","createtime":"2017-12-22 14:48:39","id":5,"imgs":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:img201708161502861587589.jpg","menderid":14,"orderid":6,"productscore":0,"replyList":[],"replyname":"快看看","replyphoto":"http://dentist.oss-cn-beijing.aliyuncs.com/2017-12-22/1513924929460.png","score":5,"servicescore":2,"type":"1","uid":14},{"anonymous":0,"content":"我教你学吧","createtime":"2017-12-22 14:48:38","id":1,"imgs":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:img201708161502855025970.jpg","menderid":14,"orderid":22,"productscore":0,"replyList":[],"replyname":"快看看","replyphoto":"http://dentist.oss-cn-beijing.aliyuncs.com/2017-12-22/1513924929460.png","score":5,"servicescore":1,"type":"1","uid":14}],"pageSize":10,"totalCount":5,"totalPage":1}
     */

    private PageBean page;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public static class PageBean {
        /**
         * currPage : 1
         * list : [{"anonymous":0,"content":"测试专用","createtime":"2017-12-22 14:51:07","id":24,"imgs":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:img201710111507706335796.jpg,http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:img201710111507706335848.jpg","menderid":14,"orderid":4,"productscore":5,"replyList":[],"replyname":"快看看","replyphoto":"http://dentist.oss-cn-beijing.aliyuncs.com/2017-12-22/1513924929460.png","score":5,"servicescore":5,"type":"1","uid":14},{"anonymous":0,"content":"好的","createtime":"2017-12-22 14:51:14","id":23,"imgs":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:img201710111507706335796.jpg,http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:img201710111507706335848.jpg","menderid":14,"orderid":4,"productscore":0,"replyList":[],"replyname":"快看看","replyphoto":"http://dentist.oss-cn-beijing.aliyuncs.com/2017-12-22/1513924929460.png","score":0,"servicescore":0,"type":"1","uid":14},{"anonymous":1,"content":"123","createtime":"2017-12-22 14:48:40","id":6,"imgs":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:img201708161502864259736.jpg","menderid":14,"orderid":0,"productscore":0,"replyList":[],"replyname":"快看看","replyphoto":"http://dentist.oss-cn-beijing.aliyuncs.com/2017-12-22/1513924929460.png","score":5,"servicescore":3,"type":"1","uid":14},{"anonymous":0,"content":"123","createtime":"2017-12-22 14:48:39","id":5,"imgs":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:img201708161502861587589.jpg","menderid":14,"orderid":6,"productscore":0,"replyList":[],"replyname":"快看看","replyphoto":"http://dentist.oss-cn-beijing.aliyuncs.com/2017-12-22/1513924929460.png","score":5,"servicescore":2,"type":"1","uid":14},{"anonymous":0,"content":"我教你学吧","createtime":"2017-12-22 14:48:38","id":1,"imgs":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:img201708161502855025970.jpg","menderid":14,"orderid":22,"productscore":0,"replyList":[],"replyname":"快看看","replyphoto":"http://dentist.oss-cn-beijing.aliyuncs.com/2017-12-22/1513924929460.png","score":5,"servicescore":1,"type":"1","uid":14}]
         * pageSize : 10
         * totalCount : 5
         * totalPage : 1
         */

        private int currPage;
        private int pageSize;
        private int totalCount;
        private int totalPage;
        private List<ListBean> list;

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * anonymous : 0
             * content : 测试专用
             * createtime : 2017-12-22 14:51:07
             * id : 24
             * imgs : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:img201710111507706335796.jpg,http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:bxwx:img201710111507706335848.jpg
             * menderid : 14
             * orderid : 4
             * productscore : 5
             * replyList : []
             * replyname : 快看看
             * replyphoto : http://dentist.oss-cn-beijing.aliyuncs.com/2017-12-22/1513924929460.png
             * score : 5
             * servicescore : 5
             * type : 1
             * uid : 14
             */

            private int anonymous;
            private String content;
            private String createtime;
            private int id;
            private String imgs;
            private int menderid;
            private int orderid;
            private int productscore;
            private String replyname;
            private String replyphoto;
            private int score;
            private int servicescore;
            private String type;
            private int uid;
            private List<?> replyList;

            public int getAnonymous() {
                return anonymous;
            }

            public void setAnonymous(int anonymous) {
                this.anonymous = anonymous;
            }

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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImgs() {
                return imgs;
            }

            public void setImgs(String imgs) {
                this.imgs = imgs;
            }

            public int getMenderid() {
                return menderid;
            }

            public void setMenderid(int menderid) {
                this.menderid = menderid;
            }

            public int getOrderid() {
                return orderid;
            }

            public void setOrderid(int orderid) {
                this.orderid = orderid;
            }

            public int getProductscore() {
                return productscore;
            }

            public void setProductscore(int productscore) {
                this.productscore = productscore;
            }

            public String getReplyname() {
                return replyname;
            }

            public void setReplyname(String replyname) {
                this.replyname = replyname;
            }

            public String getReplyphoto() {
                return replyphoto;
            }

            public void setReplyphoto(String replyphoto) {
                this.replyphoto = replyphoto;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getServicescore() {
                return servicescore;
            }

            public void setServicescore(int servicescore) {
                this.servicescore = servicescore;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public List<?> getReplyList() {
                return replyList;
            }

            public void setReplyList(List<?> replyList) {
                this.replyList = replyList;
            }
        }
    }
}
