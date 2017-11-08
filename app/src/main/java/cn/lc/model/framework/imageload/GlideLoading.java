package cn.lc.model.framework.imageload;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;

/**
 * Created by hh on 2017/6/13.
 */

public class GlideLoading {


    private static GlideLoading load;

//
    private GlideLoading() {

    }

    public static GlideLoading getInstance() {
        if (load == null) {
            load = new GlideLoading();
        }
        return load;
    }
    public void loadImgFile(Context ct, File file,ImageView iv) {
        Glide.with(ct).load(file).into(iv);
    }
    public void loadImgUrlNyImgLoader(Context ct, String url, ImageView iv) {
        Glide.with(ct).load(url).into(iv);

    }
    //加载圆形头像
    public void LoaderCircle(final Context ct, String url, final ImageView iv) {
        Glide.with(ct).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv);
//        Glide.with(ct).load(url).asBitmap().centerCrop().placeholder(drawable).into(new BitmapImageViewTarget(iv) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable =
//                        RoundedBitmapDrawableFactory.create(ct.getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                iv.setImageDrawable(circularBitmapDrawable);
//            }
//        });
    }
}
