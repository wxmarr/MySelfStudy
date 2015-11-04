package gl.com.gl.com.proutils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

/**
 * Created by mac on 15-11-3.
 * 光照效果
 * 原理：指定光照中心，光照半径，计算点到光照中心的距离，根据距离增加光照值
 */
public class RGBtoLight extends AbsProUtil{
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
        int color;
        int pixR,pixG,pixB;
        //围绕圆形光照
        int centerX = width/2;
        int centerY = height/2;

        int radius = Math.min(centerX,centerY);

        float strength = 150F; //光照强度，100-150
        int[] pixels = new int[width*height];
        src.getPixels(pixels,0,width,0,0,width,height);
        for (int i = 0; i < width; i++) {
            for (int j = 0 ; j <height ; j++) {
                color = pixels[j*width+i];
                pixR= Color.red(color);
                pixG= Color.green(color);
                pixB = Color.blue(color);
                int distance = (int) (Math.pow((centerY-j),2)+Math.pow((centerX-i),2));
                if (distance<radius*radius);
                {
                    //按照距离大小计算增强的光照值
                    int result = (int) (strength*(1.0-Math.sqrt(distance)/radius));
                    pixR = pixR+result;
                    pixG = pixG+result;
                    pixB = pixB+result;
                }
                //做左右限制
                pixR = Math.min(255,Math.max(0,pixR));
                pixG = Math.min(255,Math.max(0,pixG));
                pixB = Math.min(255,Math.max(0,pixB));
                pixels[j*width+i] = Color.argb(Color.alpha(color), pixR, pixG, pixB);
            }

        }
        bitmap.setPixels(pixels,0,width,0,0,width,height);
        long endTime =  System.currentTimeMillis();
        Log.e("tag","this is light used time "+(endTime-startTime)+"ms");
        return bitmap;
    }
}
