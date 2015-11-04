package gl.com.gl.com.proutils;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.Random;

/**
 * Created by mac on 15-11-3.
 * 雾化效果
 * 原理：在图像中引入一定的随机值，打乱图像中的像素值
 */
public class RGBtoAtomization extends AbsProUtil {
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
        Random random = new Random();
        int pos,pos1,k;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                k = random.nextInt(123456);
                int dx = i+k%8;
                int dy = j+k%8;
                if (dx>=width){
                    dx=width-1;
                }
                if (dy>=height){
                    dy=height-1;
                }
                pos = dy*width +dx;
                pos1 = j*width+i;

                pixels[pos1]=pixels[pos];
            }
        }
        bitmap.setPixels(pixels,0,width,0,0,width,height);
        long endTime = System.currentTimeMillis();
        Log.e("tag","this is atomization used time"+(endTime-startTime)+"ms");
        return bitmap;
    }
}
