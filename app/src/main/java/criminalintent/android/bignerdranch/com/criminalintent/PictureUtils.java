package criminalintent.android.bignerdranch.com.criminalintent;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by hh on 2017/9/11.
 */
//处理获得图片（压缩）
public class PictureUtils {
    //利用屏幕大小估算缩放方法
    public static Bitmap getScaledBitmap(String path, Activity activity){
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return getScaledBitmap(path, size.x, size.y);
    }
    //读取原图片尺寸大小
    public static Bitmap getScaledBitmap(String path, int destWidth, int destHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        //计算出要减少的尺寸
        int inSampleSize = 1;
        if(srcHeight>destHeight || srcWidth>destWidth){
            if(srcHeight>destHeight){
                inSampleSize = Math.round(srcHeight / destHeight);
            }
            if(srcWidth>destWidth){
                inSampleSize = Math.round(srcWidth / destWidth);
            }
        }
        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        //写入和创建最后的bitmap
        return BitmapFactory.decodeFile(path, options);
    }

}
