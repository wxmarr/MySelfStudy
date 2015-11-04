package gl.com.gl.com.proutils;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by mac on 15-11-3.
 * 积木效果 取平均值，如>128，取255
 */
public class RGBtoBrick extends AbsProUtil{
    @Override
    public Bitmap doPro(Bitmap src) {
        return doProByPix(src);
    }

    @Override
    public Bitmap doProByPix(Bitmap src) {
        int width = src.getWidth();
        int height = src.getHeight();

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        int[] pixels = new int[width*height];
        int color;
        int sum;
        src.getPixels(pixels,0,width,0,0,width,height);
        for (int i = 0; i < width * height; i++) {
            color = pixels[i];
            sum = (Color.red(color)+Color.blue(color)+Color.green(color))/3;
            if (sum>=128){
                sum = 255;
            }else{
                sum = 0;
            }
            pixels[i]=Color.argb(Color.alpha(color),sum,sum,sum);
        }
        bitmap.setPixels(pixels,0,width,0,0,width,height);
        return bitmap;
    }
}
