package gl.com.activity;

import android.graphics.PointF;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import gl.com.frescodemo.R;

public class ZoomActivity extends ActionBarActivity {

    private SimpleDraweeView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
        initView();
    }

    private void initView() {
        imageView = (SimpleDraweeView) findViewById(R.id.ZoomImageView);
        //fresco:actualImageScaleType="focusCrop" xml中这样设置
        // 0.5f 0.5f 表示以中心点为准
        PointF focusPoint = new PointF(0.5f,0.5f);
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
        GenericDraweeHierarchy hierarchy = builder.setActualImageFocusPoint(focusPoint)
                .build();
        Uri uri = Uri.parse("http://img3.3lian.com/2014/f1/1/d/32.jpg");

        imageView.setHierarchy(hierarchy);
        imageView.setImageURI(uri);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zoom, menu);
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
