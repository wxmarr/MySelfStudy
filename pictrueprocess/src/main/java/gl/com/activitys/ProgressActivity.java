package gl.com.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import gl.com.gl.com.proutils.RGBtoGray;
import gl.com.pictrueprocess.MyApplication;
import gl.com.pictrueprocess.R;
import gl.com.utils.PicKinds;

public class ProgressActivity extends ActionBarActivity {

    private ImageView pro_imageview;
    private TextView pro_text;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        position = getIntent().getIntExtra("position",0);
        initView();
        showProImage();
    }

    private void showProImage() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.src_image);
        pro_imageview.setImageBitmap(MyApplication.uitl.getProClass(position).doPro(bitmap));

        pro_text.setText(PicKinds.text_list.get(position));
    }

    private void initView() {

        pro_imageview = (ImageView) findViewById(R.id.pro_image);
        pro_text = (TextView) findViewById(R.id.pro_text);

    }
}
