package gl.com.gl.com.proutils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by mac on 15-11-3.
 * 怀旧效果
 */
public class RGBtoOld extends AbsProUtil {
    @Override
    public Bitmap doPro(Bitmap src) {
        /**
         * 利用颜色矩阵
         */
        int width = src.getWidth();
        int height = src.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{
                (float) 0.393, (float) 0.768, (float) 0.189, 0, 0,
                (float) 0.349, (float) 0.686, (float) 0.168, 0, 0,
                (float) 0.272, (float) 0.534, (float) 0.131, 0, 0,
                0, 0, 0, 1, 0
        });
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(src,0,0,paint);
        return bitmap;
    }

    @Override
    public Bitmap doProByPix(Bitmap src) {
        /**
         * 怀旧效果原理
         * R=0.393r+0.769g+0.189b
         * G=0.349r+0.686g+0.168b
         * B=0.272r+0.534g+0.131b
         */
        long startTime = System.currentTimeMillis();
        int width = src.getWidth();
        int height = src.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int pixColor,pixR,pixG,pixB,newR,newG,newB;
        int[] pixels= new int[width*height];
        src.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //获取对应点的像素
                pixColor  = pixels[width*i+j];

                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                newR = (int) (0.393 * pixR + 0.769 * pixG + 0.189 * pixB);
                newG = (int) (0.349 * pixR + 0.686 * pixG + 0.168 * pixB);
                newB = (int) (0.272 * pixR + 0.534 * pixG + 0.131 * pixB);
                int newColor = Color.argb(255,Math.min(255,newR),Math.min(255,newG),Math.min(255, newB));
                pixels[width*i+j]=newColor;
            }
        }
        bitmap.setPixels(pixels,0,width,0,0,width,height);
        long endTime = System.currentTimeMillis();
        Log.e("tag","this is old used time "+(endTime-startTime)+"ms");
        return bitmap;
    }
}
