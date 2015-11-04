package gl.com.gl.com.proutils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;

import gl.com.pictrueprocess.MyApplication;
import gl.com.utils.FastBlur;

/**
 * Created by mac on 15-11-3.
 * 模糊效果（这里会给出几种模糊方案）
 * 参考链接  http://blog.csdn.net/xu_fu/article/details/23131241
 */
public class RGBtoFuzzy extends AbsProUtil {
    @Override
    public Bitmap doPro(Bitmap src) {
        Bitmap bitmap;
        bitmap = RSblur(src);
        //如果不先获取一张位图的话就会抛出异常，异常原因，穿进去的原位图不可变
//        bitmap = FBblur(getBitmap(src));
        return bitmap;
//        return GaussBlur(src);
    }

    @Override
    public Bitmap doProByPix(Bitmap src) {
        return null;
    }


    public Bitmap RSblur(Bitmap src){
        long startTime = System.currentTimeMillis();
        Bitmap bitmap = Bitmap.createBitmap(src.getWidth(),src.getHeight(), Bitmap.Config.ARGB_8888);

        RenderScript renderScript = RenderScript.create(MyApplication.app);
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));

        Allocation allIn = Allocation.createFromBitmap(renderScript, src);
        Allocation allOut = Allocation.createFromBitmap(renderScript,bitmap);

        // 控制模糊程度
        scriptIntrinsicBlur.setRadius(2.f);

        scriptIntrinsicBlur.setInput(allIn);
        scriptIntrinsicBlur.forEach(allOut);

        allOut.copyTo(bitmap);

        src.recycle();
        renderScript.destroy();

        long endTime = System.currentTimeMillis();

        Log.e("tag","this is RenderScript blur use time "+(endTime-startTime)+"ms");
        return bitmap;
    }

    public Bitmap getBitmap(Bitmap src){
        Bitmap bitmap = Bitmap.createBitmap(src.getWidth(),src.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        canvas.drawBitmap(src,0,0,paint);

        return bitmap;
    }

    public Bitmap FBblur(Bitmap src){

        long startTime = System.currentTimeMillis();
        Bitmap bitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
        Bitmap newBitmap = FastBlur.doBlur(src,4,true);

        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);

        canvas.drawBitmap(newBitmap,0,0,paint);
        long endTime = System.currentTimeMillis();
        Log.e("tag", "this is fastblur use time " + (endTime - startTime) + "ms");
        return bitmap;
    }


    /**
     *  高斯模糊 加权平均，权值为高斯局长呢，但是这个貌似是错误的
     *  高斯矩阵 int[] gauss= new int[] {1,2,1,2,4,2,1,2,1};
     * @param src
     * @return
     */
    public Bitmap GaussBlur(Bitmap src){
        long startTime = System.currentTimeMillis();
        int[] gauss = new int[] { 1, 2, 1, 2, 4, 2, 1, 2, 1 };

        int width = src.getWidth();
        int height = src.getHeight();

        Bitmap bitmap =  Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;
        int newR = 0 ;
        int newG = 0 ;
        int newB = 0 ;

        int delta = 16; //权值总和 值越小图片越亮
        int idx = 0 ;
        int[] pixels = new int[width*height];
        src.getPixels(pixels, 0, width, 0, 0, width, height);

        for (int i = 1, length = height - 1; i < length; i++)
        {
            for (int k = 1, len = width - 1; k < len; k++)
            {
                idx = 0;
                for (int m = -1; m <= 1; m++)
                {
                    for (int n = -1; n <= 1; n++)
                    {
                        pixColor = pixels[(i + m) * width + k + n];
                        pixR = Color.red(pixColor);
                        pixG = Color.green(pixColor);
                        pixB = Color.blue(pixColor);

                        newR = newR + (pixR * gauss[idx]);
                        newG = newG + (pixG * gauss[idx]);
                        newB = newB + (pixB * gauss[idx]);
                        idx++;
                    }
                }

                newR /= delta;
                newG /= delta;
                newB /= delta;

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                pixels[i * width + k] = Color.argb(255, newR, newG, newB);

                newR = 0;
                newG = 0;
                newB = 0;
            }
        }

        bitmap.setPixels(pixels,0,width,0,0,width,height);
        long endTime = System.currentTimeMillis();
        Log.e("tag","this is gauss blur used time"+(endTime-startTime)+"ms");
        return bitmap;
    }
}
