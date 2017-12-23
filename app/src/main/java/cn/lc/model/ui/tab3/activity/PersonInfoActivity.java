package cn.lc.model.ui.tab3.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.luck.picture.lib.entity.LocalMedia;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.framework.utils.SelectImageHelper;
import cn.lc.model.framework.widget.MySettingView;
import cn.lc.model.ui.tab3.event.PersonInfoEnvent;
import cn.lc.model.ui.tab3.presenter.PersonInfoPresenter;
import cn.lc.model.ui.tab3.view.PersonInfoView;

/**
 * Created by Administrator on 2017/11/8.
 */

public class PersonInfoActivity extends MvpSimpleActivity<PersonInfoView, PersonInfoPresenter> implements PersonInfoView {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.iv_header)
    SimpleDraweeView iv_header;
    @BindView(R.id.s_name)
    MySettingView s_name;
    @BindView(R.id.s_phone)
    MySettingView s_phone;
    SelectImageHelper imgHelper;
    private List<LocalMedia> selectList = new ArrayList<>();

    public static String name;
    public static String phoneNum;
    public static boolean isChangeName = false;
    public static boolean isChangePhone = false;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.personinfo);
        //ButterKnife.bind(this);
        EventBus.getDefault().register(this);//订阅
    }

    @Subscribe //在ui线程执行
    public void onEventMainThread(PersonInfoEnvent event) {
        s_name.setRightText(SharedPrefHelper.getInstance().getUserName());
        s_phone.setRightText(SharedPrefHelper.getInstance().getRealPhone());
        EventBus.getDefault().unregister(event);
    }

    @Override
    public void initView() {
        s_name.setRightText(SharedPrefHelper.getInstance().getUserName());
        s_phone.setRightText(SharedPrefHelper.getInstance().getRealPhone());
        iv_header.setImageURI(SharedPrefHelper.getInstance().getPhoto());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isChangeName) {
            s_name.setRightText(name);
            isChangeName = false;
        }
        if (isChangePhone) {
            s_phone.setRightText(phoneNum);
            isChangePhone = false;
        }
    }

    @OnClick({R.id.iv_back, R.id.iv_header, R.id.s_phone, R.id.s_name})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_header:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},1);
                }else {
                    imgHelper = new SelectImageHelper(this);
                    imgHelper.setCropParams(1, 1, 160, 160);
                    imgHelper.showChooseImgDialog();
                }

//            PictureSelector.create(PersonInfoActivity.this)
//                    .openGallery(PictureMimeType.ofImage())
//                    .maxSelectNum(1)
//                    .enableCrop(true)
//                    .selectionMode(PictureConfig.SINGLE)
//                    .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                    .circleDimmedLayer(true)
//                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
//                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
//                    .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.s_name:
                UIManager.turnToAct(PersonInfoActivity.this, PersonNameActivity.class);
                break;
            case R.id.s_phone:
                UIManager.turnToAct(PersonInfoActivity.this, PersonPhoneActivity.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("photo","111111111111111111111111");

        if (imgHelper == null) {
            return;
        }
        imgHelper.NewdoResult(requestCode, resultCode, data, new SelectImageHelper.OnGetPhotoListener() {
            @Override
            public void onGetPhoto(File photoFile) {
                Log.e("photo",photoFile.getAbsolutePath());
                LogUtils.d("photoFile==" + photoFile.getAbsolutePath());
               /* Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                iv_header.setImageBitmap(bitmap);*/
                GlideLoading.getInstance().loadImgFile(getActivity(), photoFile, iv_header);
                String mPhotoFile = photoFile.getAbsolutePath();
            }

            @Override
            public void onGetPhoto(File photoFile, Bitmap bitmap) {
                iv_header.setImageBitmap(bitmap);
            }
        });
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case PictureConfig.CHOOSE_REQUEST:
//                    // 图片选择结果回调
//              selectList = PictureSelector.obtainMultipleResult(data);
//                    // 例如 LocalMedia 里面返回三种path
//                    // 1.media.getPath(); 为原图path
//                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
//                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
//                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
//                    if (selectList!=null&&selectList.size()>0)
//                        for (int i = 0; i <selectList.size() ; i++) {
//                            LogUtils.d("路径"+selectList.get(i).getPath());
//                        }
//
//                    break;
//            }
//        }
    }

    @Override
    public PersonInfoPresenter createPresenter() {
        return new PersonInfoPresenter();
    }
}
