package cn.lc.model.ui.tab3.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.framework.utils.SelectImageHelper;
import cn.lc.model.ui.tab3.presenter.PersonInfoPresenter;
import cn.lc.model.ui.tab3.view.PersonInfoView;
import mvp.cn.util.LogUtil;

/**
 * Created by Administrator on 2017/11/8.
 */

public class PersonInfoActivity extends MvpSimpleActivity<PersonInfoView,PersonInfoPresenter>implements PersonInfoView{
    @BindView(R.id.iv_back)
    ImageView iv_back;

    @BindView(R.id.iv_header)
    ImageView iv_header;
    @BindView(R.id.rl_name)
    RelativeLayout rl_name;
    @BindView(R.id.tx_name)
    TextView tx_name;
    @BindView(R.id.rl_phone)
   RelativeLayout rl_phone;
    @BindView(R.id.tx_phone)
    TextView tx_phone;
    SelectImageHelper imgHelper;
    private List<LocalMedia> selectList = new ArrayList<>();
    @Override
    public void setContentLayout() {
        setContentView(R.layout.personinfo);
        ButterKnife.bind(this);
    }
    @Override
    public void initView() {
    }
@OnClick({R.id.iv_back,R.id.iv_header,R.id.rl_name,R.id.rl_phone})
public  void onClick(View view ){
    switch (view.getId()){
        case R.id.iv_back:
            finish();
            break;
        case R.id.iv_header:
            imgHelper = new SelectImageHelper(this);
            imgHelper.setCropParams(1, 1, 160, 160);
            imgHelper.showChooseImgDialog();
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
        case R.id.rl_name:
            break;
        case R.id.rl_phone:
            break;
    }
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (imgHelper == null) {
            return;
        }
        imgHelper.doResult(requestCode, resultCode, data, new SelectImageHelper.OnGetPhotoListener() {
            @Override
            public void onGetPhoto(File photoFile) {
                LogUtil.log("photoFile==" + photoFile.getAbsolutePath());
                GlideLoading.getInstance().loadImgFile(getActivity(), photoFile ,iv_header);
             String   mPhotoFile = photoFile.getAbsolutePath();
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
