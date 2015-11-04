package gl.com.gl.com.proutils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

/**
 * Created by mac on 15-11-3.
 * 浮雕效果
 * 前一像素分量值-当前像素分量值+127
 */
public class RGBtoRelief extends AbsProUtil {
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

        int[] oldPixels = new int[width*height];
        int[] newPixels = new int[width*height];

        src.getPixels(oldPixels,0,width,0,0,width,height);
        src.getPixels(newPixels,0,width,0,0,width,height);
        int oldColor,newColor;
        int oldPixR,oldPixG,oldPixB,newPixR,newPixG,newPixB;
        for (int i = 1; i < height*width; i++) {
            oldColor=oldPixels[i-1];
            oldPixR = Color.red(oldColor);
            oldPixG = Color.green(oldColor);
            oldPixB = Color.blue(oldColor);

            newColor = newPixels[i];
            newPixR = Color.red(newColor);
            newPixG = Color.green(newColor);
            newPixB = Color.blue(newColor);

            //计算
            newPixR = (oldPixR-newPixR +127)>255?255:(oldPixR-newPixR +127) ;
            newPixG = (oldPixG -newPixG+127)>255?255:(oldPixG -newPixG+127) ;
            newPixB = (oldPixB-newPixB+127)>255?255:(oldPixB-newPixB+127);

            newPixels[i]=Color.argb(Color.alpha(newColor),newPixR,newPixG,newPixB);

        }

        bitmap.setPixels(newPixels,0,width,0,0,width,height);
        long endTime = System.currentTimeMillis();
        Log.e("tag"," relief is used time "+(endTime-startTime)+"ms");
        return bitmap;
    }
}
