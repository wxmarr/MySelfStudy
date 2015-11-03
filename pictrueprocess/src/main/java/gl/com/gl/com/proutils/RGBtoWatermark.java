package gl.com.gl.com.proutils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by mac on 15-11-3.
 * 给图片添加水印文字
 */
public class RGBtoWatermark extends AbsProUtil {
    @Override
    public Bitmap doPro(Bitmap src) {
        int width = src.getWidth();
        int height = src.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas= new Canvas(bitmap);

        canvas.drawBitmap(src,0,0,new Paint());
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG  | Paint.DEV_KERN_TEXT_FLAG);
        textPaint.setTextSize(20.0f);
        textPaint.setColor(Color.parseColor("#00bb00"));
        canvas.drawText("柯南",30,30,textPaint);


        return bitmap;
    }

    @Override
    public Bitmap doProByPix(Bitmap src) {
        return null;
    }
}
