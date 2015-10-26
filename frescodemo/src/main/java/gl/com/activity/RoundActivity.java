package gl.com.activity;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import gl.com.frescodemo.R;

public class RoundActivity extends ActionBarActivity {

    private SimpleDraweeView imageview_1;
    private SimpleDraweeView imageview_2;
    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        initView();
    }

    private void initView() {
        imageview_1= (SimpleDraweeView) findViewById(R.id.RoundImage_one);
        imageview_2= (SimpleDraweeView) findViewById(R.id.RoundImage_two);
        uri = Uri.parse("http://img3.3lian.com/2014/f1/1/d/32.jpg");
        imageview_2.setImageURI(uri);
        RoundingParams params = RoundingParams.fromCornersRadii(15f,45f,30f,10f);

        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setRoundingParams(params)
                .build();
        imageview_1.setHierarchy(hierarchy);
        imageview_1.setImageURI(uri);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_round, menu);
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
