package gl.com.gl.com.proutils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

/**
 * Created by mac on 15-11-3.
 * 连环画效果
 * 原理 R=|g-b+g+r|*r/256
 *     G=|b-g+b+r|*r/256
 *     B=|b-g+b+r|*g/256
 */
public class RGBtoPicBook extends AbsProUtil {
    @Override
    public Bitmap doPro(Bitmap src) {
        return doProByPix(src);
    }

    @Override
    public Bitmap doProByPix(Bitmap src) {

        long startTime = System.currentTimeMillis();
        int width = src.getWidth();
        int height =src.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] pixels = new int[width*height];
        int color;
        int pixR,pixG,pixB;
        src.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < width * height; i++) {
            color=pixels[i];
            pixR= Color.red(color);
            pixG = Color.green(color);
            pixB = Color.blue(color);
            //r
            pixR = Math.abs(pixG-pixB+pixG+pixR) * pixR /256;
            if (pixR>255){
                pixR = 255;
            }

            pixG =Math.abs(pixB-pixG+pixB+pixR) *pixR/256;
            if (pixG>255){
                pixG = 255;

            }
            //B=|b-g+b+r|*g/256
            pixB = Math.abs(pixB-pixG+pixB+pixR) * pixG/256;
            if (pixB>256){
                pixB=255;
            }

            pixels[i]=Color.argb(Color.alpha(color),pixR,pixG,pixB);
        }
        bitmap.setPixels(pixels,0,width,0,0,width,height);
        long endTime = System.currentTimeMillis();
        Log.e("tag","this is PicBook used time "+(endTime-startTime)+"ms");
        return bitmap;
    }
}
