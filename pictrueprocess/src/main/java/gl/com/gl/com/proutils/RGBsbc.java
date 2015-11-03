package gl.com.gl.com.proutils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by mac on 15-11-3.
 * 饱和度 亮度 和对比度
 */
public class RGBsbc extends AbsProUtil {
    @Override
    public Bitmap doPro(Bitmap src) {
        int width = src.getWidth();
        int height = src.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        //设置饱和度 设置为0.7  范围 0 - 1
        //colorMatrix.setSaturation((float) 0.7);
        //设置亮度
        colorMatrix.set(new float[]{1,0,0,0,70,
                                    0,1,0,0,70,
                                    0,0,1,0,70,
                                    0,0,0,1,0});
        //改变对比度
//        colorMatrix.set(new float[]{2, 0, 0, 0, 0,
//                                    0, 2, 0, 0, 0,
//                                    0, 0, 2, 0, 0,
//                                    0, 0, 0, 1, 0});
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(src,0,0,paint);
        return bitmap;
    }

    @Override
    public Bitmap doProByPix(Bitmap src) {
        return null;
    }
}
