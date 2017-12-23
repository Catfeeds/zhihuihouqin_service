package cn.lc.model.framework.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.lc.model.R;


public class SelectImageHelper {

    /* 用来标识请求照相功能的activity */
    private static final int CAMERA_WITHOUT_CROP = 3025;

    /* 用来标识请求照相功能的activity */
    private static final int CAMERA_WITH_DATA = 3023;

    /* 用来标识请求gallery的activity */
    private static final int PHOTO_PICKED_WITH_DATA = 3021;

    /* 用来标识请求裁切 */
    private static final int CROP_REQUEST_CODE = 3020;

    /* 拍照的照片存储位置 */
    public static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + "/casemeet" + "/images");
    private static File mCurrentPhotoFile;// 照相机拍照得到的图片

    private Activity activity;

    private int aspectX = 1;
    private int aspectY = 1;
    private int outputX = 320;
    private int outputY = 320;

    public SelectImageHelper(Activity activity) {
        this.activity = activity;
    }

    private Fragment fragment;

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    /**
     * 拍照带裁剪
     */
    public void doTakePhoto() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            try {
                boolean dd = PHOTO_DIR.mkdirs();// 创建照片的存储目录
                mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());// 给新照的照片文件命名
                final Intent intent = getTakePickIntent(mCurrentPhotoFile);
                if (fragment != null) {
                    fragment.startActivityForResult(intent, CAMERA_WITH_DATA);
                } else {
                    activity.startActivityForResult(intent, CAMERA_WITH_DATA);
                }
            } catch (ActivityNotFoundException e) {
                Toast.makeText(activity, "没有照相机程序", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(activity, "没有SD卡", Toast.LENGTH_SHORT).show();
        }
    }

    String pic_path;
    Uri pictureUri;
    private static final int PHOTO_GRAPH = 1;// 拍照
    private static final int PHOTO_ZOOM = 2; // 缩放
    private static final int PHOTO_RESOULT = 3;// 结果
    private static final String IMAGE_UNSPECIFIED = "image/*";
    /**
     * 打开系统照相机
     */
    private void startOpenCamera(){
        //新建图片sd卡路径
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String path = Environment.getExternalStorageDirectory() + "/anxin"; /*File.pathSeparator*/
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        pic_path = path + "/" + System.currentTimeMillis() + "_test.jpg";
        File imageFile = new File(pic_path);
        pictureUri = Uri.fromFile(imageFile);
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
        activity.startActivityForResult(intent, PHOTO_GRAPH);
    }

    /**
     * 拍照不裁剪
     */
    public void doTakePhotoWithoutCrop() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            try {
                boolean dd = PHOTO_DIR.mkdirs();// 创建照片的存储目录
                mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());// 给新照的照片文件命名
                final Intent intent = getTakePickIntent(mCurrentPhotoFile);
                if (fragment != null) {
                    fragment.startActivityForResult(intent, CAMERA_WITHOUT_CROP);
                } else {
                    activity.startActivityForResult(intent, CAMERA_WITHOUT_CROP);
                }
            } catch (ActivityNotFoundException e) {
                Toast.makeText(activity, "没有照相机程序", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(activity, "没有SD卡", Toast.LENGTH_SHORT).show();
        }
    }

    public Intent getTakePickIntent(File f) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        return intent;
    }

    /**
     * 从图库选择
     */
    public void doPickPhotoFromGallery() {
        try {
            PHOTO_DIR.mkdirs();
            mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());

            Intent itPhoto = new Intent(Intent.ACTION_PICK);
            itPhoto.setType("image/*");
            if (fragment != null) {
                fragment.startActivityForResult(itPhoto, PHOTO_PICKED_WITH_DATA);
            } else {
                activity.startActivityForResult(itPhoto, PHOTO_PICKED_WITH_DATA);
            }
        } catch (ActivityNotFoundException e) {
            Toast.makeText(activity, "not find photo", Toast.LENGTH_LONG).show();
        }
    }

    public Intent getPhotoPickIntent(File f) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        return intent;
    }

    /**
     * 裁剪图片
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("return-data", false);
        if (!PHOTO_DIR.exists()) {
            PHOTO_DIR.mkdirs();
        }
        mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentPhotoFile));

        if (fragment != null) {
            fragment.startActivityForResult(intent, CROP_REQUEST_CODE);
        } else {
            activity.startActivityForResult(intent, CROP_REQUEST_CODE);
        }
    }

    /**
     * 用当前时间给取得的图片命名
     */
    public String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    /**
     * 处理结果
     *
     * @param requestCode
     * @param data
     * @param onGetPhotoListener
     */
    @SuppressLint("NewApi")
    public void doResult(int requestCode, int resultCode, Intent data, OnGetPhotoListener onGetPhotoListener) {
        if (requestCode == CAMERA_WITHOUT_CROP) {// 拍照返回的
            if (mCurrentPhotoFile != null) {
                if (mCurrentPhotoFile.getTotalSpace() == 0) {
                    return;
                }
                if (onGetPhotoListener != null) {
                    onGetPhotoListener.onGetPhoto(mCurrentPhotoFile);
                }
            }
        } else if (requestCode == CAMERA_WITH_DATA) {// 拍照返回的
            if (mCurrentPhotoFile != null) {
                startPhotoZoom(Uri.fromFile(mCurrentPhotoFile));
            }
        } else if (requestCode == PHOTO_PICKED_WITH_DATA) {// 从图库选择的2
            if (data == null) {
                return;
            }
            startPhotoZoom(data.getData());
        } else if (requestCode == CROP_REQUEST_CODE) {// 裁切返回
            String path = mCurrentPhotoFile.getAbsolutePath();
            Log.e("photo",path);
            if (mCurrentPhotoFile.getTotalSpace() == 0) {
                return;
            }
            Log.e("photo",path);
            if (path != null) {
                File uploadFile = new File(PHOTO_DIR, getPhotoFileName());// 给新照的照片文件命名
                if (onGetPhotoListener != null) {
                    Log.e("photo",path);
                    onGetPhotoListener.onGetPhoto(mCurrentPhotoFile);
                }
            }
        }
    }
    //上传文件头像
    private File upLoadHeaderFile = null;

    @SuppressLint("NewApi")
    public void NewdoResult(int requestCode, int resultCode, Intent data, OnGetPhotoListener onGetPhotoListener) {
        if(requestCode == PHOTO_GRAPH){
            if(pictureUri!=null){
                startNewPhotoZoom(pictureUri);
            }
        }
        //从相册选取照片
        if(requestCode == PHOTO_ZOOM ){
            //解决部分机型选取图库打开方式，返回data为空的问题
            if(data!=null){
                if(data.getData()!=null){
                    startNewPhotoZoom(data.getData());
                }
            }
        }
        //剪切图片后的处理结果
        if(requestCode == PHOTO_RESOULT && resultCode == Activity.RESULT_OK){
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                Bitmap chu = FileUtils.toRoundCorner(photo,2);
                upLoadHeaderFile = FileUtils.getFirByBitmap(chu,   "zhhq_" + System.currentTimeMillis()+".jpg");

                onGetPhotoListener.onGetPhoto(upLoadHeaderFile,chu);
                //person_info_header.setImageBitmap(chu); // 把图片显示在ImageView控件上
            }
        }
    }

    /**
     * 调用系统裁剪功能
     *
     * @param uri
     */
    private void startNewPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");// 调用Android系统自带的一个图片剪裁页面,
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");// 进行修剪shezhi
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, PHOTO_RESOULT);
    }


    /**
     * 选择上传头像对话框
     */
    @SuppressLint("NewApi")
    public void showChooseImgDialog() {
        final StringListDialog dialog = new StringListDialog(activity, R.style.dialog_style);
        List<String> itemList = new ArrayList<String>();
        itemList.add("相机拍摄");
        itemList.add("手机相册");
        itemList.add("取消");
        dialog.setData(itemList);
        dialog.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:// 拍照上传
                        startOpenCamera();
                        dialog.dismiss();
                        break;
                    case 1:// 从gallery选择
                        //doPickPhotoFromGallery();
                        Intent intent = new Intent(Intent.ACTION_PICK, null);
                        intent.setDataAndType(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                IMAGE_UNSPECIFIED);
                        activity.startActivityForResult(intent, PHOTO_ZOOM);
                        dialog.dismiss();
                        break;
                    case 2:// 取消
                        dialog.dismiss();
                        break;
                }
            }
        });
        dialog.show();
    }


    /**
     * 裁剪比例,输出宽高参数
     *
     * @param aspectX
     * @param aspectY
     * @param outputX
     * @param outputY
     */
    public void setCropParams(int aspectX, int aspectY, int outputX, int outputY) {
        this.aspectX = aspectX;
        this.aspectY = aspectY;
        this.outputX = outputX;
        this.outputY = outputY;
    }


    /**
     * 得到照片文件的回调
     *
     * @author wdf
     *         <p>
     *         2014-12-16
     */
    public interface OnGetPhotoListener {
        void onGetPhoto(File photoFile);
        void onGetPhoto(File photoFile,Bitmap bitmap);
    }

}