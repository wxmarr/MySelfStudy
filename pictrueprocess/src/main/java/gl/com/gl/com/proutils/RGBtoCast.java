package gl.com.gl.com.proutils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

/**
 * Created by mac on 15-11-3.
 * 熔铸效果
 * 当前RGB分量*128然后对(其他2个分量之和+1)取整,并保留分量的修改
 */
public class RGBtoCast extends AbsProUtil {
    @Override
    public Bitmap doPro(Bitmap src) {
        return doProByPix(src);
    }

    @Override
    public Bitmap doProByPix(Bitmap src) {
        long startTime = System.currentTimeMillis();
        int width = src.getWidth();
        int height = src.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);

        int[] pixels = new int[width*height];
        src.getPixels(pixels, 0, width, 0, 0, width, height);
        int color;
        int pixR,pixG,pixB;
        for (int i = 0; i < width * height; i++) {
            color=pixels[i];
            pixR = Color.red(color);
            pixG = Color.green(color);
            pixB = Color.blue(color);
            //R 分量
            pixR = pixR * 128/(pixG+pixB+1);
            pixR = Math.min(255,Math.max(0,pixR));
            //G 分量
            pixG = pixG*128/(pixB+pixR+1);
            pixG =Math.min(255,Math.max(0,pixG));
            //B 分量
            pixB = pixB*128/(pixR+pixG+1);
            pixB = Math.min(255,Math.max(0,pixB));

            pixels[i]=Color.argb(Color.alpha(color), pixR, pixG, pixB);
        }
        bitmap.setPixels(pixels,0,width,0,0,width,height);
        long endTime = System.currentTimeMillis();
        Log.e("tag","cast used time is"+(endTime-startTime)+"ms");
        return bitmap;
    }
}
