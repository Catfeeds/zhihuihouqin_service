///**
// * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
// * <p>
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * http://www.apache.org/licenses/LICENSE-2.0
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package cn.lc.model.easeui.init.db;
//
//import android.content.Context;
//
//import com.lidroid.xutils.db.sqlite.WhereBuilder;
//import com.lidroid.xutils.exception.DbException;
//
//import java.nio.channels.Selector;
//
///**
// *
// */
//public class M_UserInfoDao extends BaseDao {
//
//    public M_UserInfoDao(Context context) {
//        super(context);
//    }
//
//    public void saveBean(SearcePersonInfoBean bean) {
//
//        if (bean == null || bean.userName == null) {
//            return;
//        }
////        try {
////            deleteBean(bean);
////        } catch (Exception e) {
////
////        }
//
//        try {
//            dbUtils.save(bean);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public SearcePersonInfoBean findBeanByHXName(String hxName) {
//
//        SearcePersonInfoBean userInfo_im = null;
//        try {
//            userInfo_im = dbUtils.findFirst(Selector.from(SearcePersonInfoBean.class).where("userName", "=", hxName));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return userInfo_im;
//    }
//
//    public void deleteBeanByHXName(String hxName) {
//
//        try {
//            dbUtils.delete(SearcePersonInfoBean.class, WhereBuilder.b("userName", "=", hxName));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public SearcePersonInfoBean findBean(String uid) {
//
//        SearcePersonInfoBean userInfo_im = null;
//        try {
//            userInfo_im = dbUtils.findById(SearcePersonInfoBean.class, uid);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return userInfo_im;
//    }
//
//    public void deleteBean(SearcePersonInfoBean bean) {
//        try {
//            dbUtils.delete(SearcePersonInfoBean.class, WhereBuilder.b("userName", "=", bean.userName));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<SearcePersonInfoBean> findAll() {
//
//        try {
//            return dbUtils.findAll(SearcePersonInfoBean.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public void insertToListDB(List<SearcePersonInfoBean> loginBeanList) throws DbException {
//        if (loginBeanList != null && loginBeanList.size() > 0) {
////            List<SearcePersonInfoBean> list = dbUtils.findAll(Selector.from(SearcePersonInfoBean.class));
////            if (list != null && list.size() > 0) {
////                dbUtils.deleteAll(list);
////            }
//            dbUtils.saveAll(loginBeanList);
//        }
//    }
//
//    public void update(SearcePersonInfoBean bean) throws DbException {
//        dbUtils.update(bean, WhereBuilder.b("userName", "=", bean.userName));
//    }
//
//    public void saveAll(List<SearcePersonInfoBean> beans) {
//        try {
//            deleteAll(beans);
//        } catch (Exception e) {
//
//        }
//        try {
//            dbUtils.saveAll(beans);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteAll(List<SearcePersonInfoBean> beans) {
//        try {
//            dbUtils.deleteAll(beans);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
