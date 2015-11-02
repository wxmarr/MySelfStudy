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

        pro_imageview.setImageBitmap(RGBtoGray.toGrayByPixs(bitmap));

        pro_text.setText("灰度化之后");
    }

    private void initView() {

        pro_imageview = (ImageView) findViewById(R.id.pro_image);
        pro_text = (TextView) findViewById(R.id.pro_text);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_progress, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
