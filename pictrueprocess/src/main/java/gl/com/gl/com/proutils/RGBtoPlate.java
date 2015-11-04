package gl.com.gl.com.proutils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

/**
 * Created by mac on 15-11-3.
 * 底片效果
 * 原理，当前像素点的RGB值与255的差值作为当前的RGB
 */
public class RGBtoPlate extends AbsProUtil{
    @Override
    public Bitmap doPro(Bitmap src) {

        return doProByPix(src);
    }

    @Override
    public Bitmap doProByPix(Bitmap src) {
        long startTime = System.currentTimeMillis();
        int width = src.getWidth();
        int height = src.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        int[] piexls = new int[width*height];
        src.getPixels(piexls,0,width,0,0,width,height);

        int color ;
        int pixR,pixG,pixB;
        for (int i = 0; i < width*height; i++) {
            color = piexls[i];
            pixR = 255 - Color.red(color);
            pixG = 255 - Color.green(color);
            pixB = 255 - Color.blue(color);

            piexls[i]=Color.argb(Color.alpha(color), pixR, pixG, pixB);
        }

        bitmap.setPixels(piexls,0,width,0,0,width,height);
        long endTime = System.currentTimeMillis();
        Log.e("tag","plate used time"+(endTime-startTime)+"ms");
        return bitmap;
    }
}
