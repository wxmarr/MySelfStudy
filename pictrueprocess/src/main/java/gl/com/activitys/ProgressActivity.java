package gl.com.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import gl.com.gl.com.proutils.RGBtoGray;
import gl.com.pictrueprocess.MyApplication;
import gl.com.pictrueprocess.R;

public class ProgressActivity extends ActionBarActivity {

    private ImageView pro_imageview;
    private TextView pro_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        initView();
        showProImage();
    }

    private void showProImage() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.src_image);

        pro_imageview.setImageBitmap(MyApplication.uitl.getProClass(0).doPro(bitmap));

        pro_text.setText("灰度化之后");
    }

    private void initView() {

        pro_imageview = (ImageView) findViewById(R.id.pro_image);
        pro_text = (TextView) findViewById(R.id.pro_text);

    }
}
