package cn.lc.model.easeui.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cn.lc.model.R;
import cn.lc.model.easeui.controller.EaseUI;
import cn.lc.model.easeui.domain.EaseUser;

public class EaseUserUtils {
    static EaseUI.EaseUserProfileProvider userProvider;

    static {
        userProvider = EaseUI.getInstance().getUserProfileProvider();
    }

    /**
     * get EaseUser according username
     *
     * @param username
     * @return
     */
    public static EaseUser getUserInfo(String username) {
        if (userProvider != null)
            return userProvider.getUser(username);

        return null;
    }

    /**
     * set user avatar
     *
     * @param username
     */
    public static void setUserAvatar(Context context, String username, ImageView imageView) {
        EaseUser user = getUserInfo(username);
        if (user != null && user.getAvatar() != null) {
            try {
                int avatarResId = Integer.parseInt(user.getAvatar());
                Glide.with(context).load(avatarResId).into(imageView);
            } catch (Exception e) {
                //use default avatar
                Glide.with(context).load("").into(imageView);
//                Glide.with(context).load("").(DiskCacheStrategy.ALL).placeholder(R.mipmap.head_default_icon).into(imageView);
//                Glide.with(context).load(user.getAvatar()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.head_default_icon).into(imageView);
            }
        } else {
            Glide.with(context).load(R.mipmap.ease_default_avatar).into(imageView);
        }
    }

    /**
     * set user's nickname
     */
    public static void setUserNick(String username, TextView textView) {
        if (textView != null) {
            EaseUser user = getUserInfo(username);
            if (user != null && user.getNick() != null) {
                textView.setText(user.getNick());
            } else {
                textView.setText(username);
            }
        }
    }

//    static EaseUI.EaseUserProfileProvider userProvider;
//    static M_UserInfoDao m_userInfoDao;
//
//    static {
//        userProvider = EaseUI.getInstance().getUserProfileProvider();
//        m_userInfoDao = new M_UserInfoDao(SoftApplication.softApplication);
//    }
//
//    /**
//     * get EaseUser according username
//     *
//     * @param username
//     * @return
//     */
//    public static EaseUser getUserInfo(String username) {
//        if (userProvider != null)
//            return userProvider.getUser(username);
//
//        return null;
//    }
//
//    /**
//     * set user avatar
//     *
//     * @param username
//     */
//    public static void setUserAvatar(Context context, String username, ImageView imageView) {
//        LogUtil.log(username + "===============名字是否一致================");
//        if (username.toLowerCase().equals("u" + SoftApplication.softApplication.getUserInfo().userName.toLowerCase())) {
//            LogUtil.log(SoftApplication.softApplication.getUserInfo().fullImgPath + "====================fullImgPath==================");
//            LogUtil.log("是自己的用户名");
//            Glide.with(context).load(SoftApplication.softApplication.getUserInfo().fullImgPath).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.head_default_icon).into(imageView);
//        } else {
//            LogUtil.log("不是自己的用户名");
//            SearcePersonInfoBean user = m_userInfoDao.findBeanByHXName(username.replace("m", ""));
//            if (user != null && user.fullImgPath != null) {
//                try {
//                    Glide.with(context).load(user.fullImgPath).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.head_default_icon).into(imageView);
//                } catch (Exception e) {
//                    //use default avatar
//                    Glide.with(context).load(R.mipmap.head_default_icon).into(imageView);
//                }
//            } else {
//                Glide.with(context).load(R.mipmap.head_default_icon).into(imageView);
//            }
//        }
//    }
//
//    /**
//     * set user's nickname
//     */
//    public static void setUserNick(String username, TextView textView) {
//        if (textView != null) {
//            SearcePersonInfoBean user = m_userInfoDao.findBeanByHXName(username.replace("m" , ""));
//            LogUtil.log(username + "===========用户名不展示呀================");
//            if (user != null && user.userName != null) {
//                textView.setText(user.realName == null ? "" : user.realName);
//            } else {
//                textView.setText(username);
//            }
//        } else {
//            LogUtil.log("用户名没有此空间呀");
//        }
//    }
}
