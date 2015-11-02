package gl.com.gl.com.proutils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by mac on 15-11-2.
 * RGB to gray
 * 转为灰度图
 */
public class RGBtoGray {

    /**
     * 方法一 通过滤镜
     * @param src 原bitmap
     * @return 返回灰度化后的bitmap
     */
    public static Bitmap toGray(Bitmap src){

        int width,height;
        height = src.getHeight();
        width = src.getWidth();
        Bitmap bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(filter);
        canvas.drawBitmap(src, 0, 0, paint);
        return bitmap;
    }

    /**
     * 方法二  通过YUV的
     * http://blog.csdn.net/chenamo9651/article/details/886699
     * @param src
     * @return
     */
    public static Bitmap toGrayByPixs(Bitmap src){

        int width = src.getWidth();
        int height = src.getHeight();
        //创建像素点数组
        int[] pixels = new int[width*height];
        src.getPixels(pixels,0,width,0,0,width,height);
        int alpha = 0xFF<<24;
        for (int i = 0 ; i < height ; i++){
            for (int j = 0 ; j < width ; j++){
                int grey  = pixels[width*i+j];
                int red = ((grey & 0x00FF0000)>>16);
                int green = ((grey & 0x0000FF00)>>8);
                int blue = ((grey & 0x000000FF));

                grey = (int)((float)red*0.3+(float)green*0.59+(float)blue*0.11);
                grey = alpha | (grey<<16)|(grey<<8)|grey;

                pixels[width*i+j]=grey;
            }
        }
        Bitmap pro = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        pro.setPixels(pixels,0,width,0,0,width,height);

        return pro;
    }

}
