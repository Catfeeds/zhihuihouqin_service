package cn.lc.model.ui.tab3.bean;

import java.util.List;

import cn.lc.model.framework.base.BaseResponse;

/**
 * Created by Administrator on 2017/12/20.
 */

public class IncomeDetailBean extends BaseResponse{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content : 办公用品订单完成
         * creatTime : 2017-12-23 11:11:05
         * money : 15
         * useType : 1
         */

        private String content;
        private String creatTime;
        private int money;
        private int useType;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreatTime() {
            return creatTime;
        }

        public void setCreatTime(String creatTime) {
            this.creatTime = creatTime;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getUseType() {
            return useType;
        }

        public void setUseType(int useType) {
            this.useType = useType;
        }
    }
}
