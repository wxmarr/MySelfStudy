package gl.com.gl.com.proutils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by mac on 15-11-2.
 * RGB to gray
 * 转为灰度图 0.3r+0.59g+0.11b
 */
public class RGBtoGray extends AbsProUtil {



    @Override
    public Bitmap doPro(Bitmap src) {
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

    @Override
    public Bitmap doProByPix(Bitmap src) {
        int width = src.getWidth();
        int height = src.getHeight();
        //创建像素点数组
        int[] pixels = new int[width*height];
        int alpha,grey,red,green,blue;
        src.getPixels(pixels,0,width,0,0,width,height);
        alpha = 0xFF<<24;
        for (int i = 0 ; i < height ; i++){
            for (int j = 0 ; j < width ; j++){
                grey  = pixels[width*i+j];
                red = ((grey & 0x00FF0000)>>16);
                green = ((grey & 0x0000FF00)>>8);
                blue = ((grey & 0x000000FF));

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
