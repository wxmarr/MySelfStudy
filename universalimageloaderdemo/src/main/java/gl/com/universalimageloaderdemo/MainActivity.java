package gl.com.universalimageloaderdemo;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;


public class MainActivity extends AppCompatActivity {

    public ImageView normal_image,head_image;
    public TextView tv_progress;
    public ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageLoader();
        initImageView();
    }

    private void initImageView() {
        tv_progress = (TextView) findViewById(R.id.text_progress);
        normal_image = (ImageView) findViewById(R.id.normal_image);
        head_image = (ImageView) findViewById(R.id.head_image);

        if (imageLoader==null){
            initImageLoader();
        }
        imageLoader.displayImage("http://pic.33.la/20141114bztp/1764.jpg",
                normal_image,MyApplication.normal_options);
        imageLoader.displayImage("http://www.juzi2.com/uploads/allimg/130524/1_130524115230_1.jpg",
                head_image, MyApplication.head_options,
                new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {

                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {
                        Log.e("tag","还是可以进来的");
                        Log.e("tag","imageUri--->"+imageUri+"|||urrent--->"+current+"|||total---->"+total);
                        tv_progress.setText(current+"/"+total);
                    }
                });

    }

    private void initImageLoader() {
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
