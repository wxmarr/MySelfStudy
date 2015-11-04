package gl.com.gl.com.proutils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

/**
 * Created by mac on 15-11-3.
 * 冰冻特效
 * 分量值 = （当前分量值-其他2个分量值的绝对值）*3/2；
 */
public class RGBtoFrozen extends AbsProUtil {
    @Override
    public Bitmap doPro(Bitmap src) {
        return doProByPix(src);
    }

    @Override
    public Bitmap doProByPix(Bitmap src) {
        long startTime = System.currentTimeMillis();
        int width =src.getWidth();
        int height = src.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        int[] pixels = new int[width*height];
        int color;
        int pixR,pixG,pixB;
        src.getPixels(pixels,0,width,0,0,width,height);
        for (int i = 0; i < width * height; i++) {
            color = pixels[i];
            pixR = Color.red(color);
            pixG = Color.green(color);
            pixB = Color.blue(color);
            pixR = Math.abs((pixR - pixG - pixB) * 3 / 2);
            pixR = Math.min(255, pixR);
            pixG = Math.abs((pixG - pixR - pixB) * 3 / 2);
            pixG = Math.min(255, pixG);
            pixB = Math.abs((pixB - pixR - pixG) * 3 / 2);
            pixB = Math.min(255,pixB);
            pixels[i]=Color.argb(Color.alpha(color), pixR, pixG, pixB);

        }

        bitmap.setPixels(pixels,0,width,0,0,width,height);
        long endTime = System.currentTimeMillis();
        Log.e("tag","frozen used time is "+(endTime-startTime)+"ms");
        return bitmap;
    }
}
