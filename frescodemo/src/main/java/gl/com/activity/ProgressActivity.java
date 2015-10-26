package gl.com.activity;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import gl.com.drawables.MySelfProgress;
import gl.com.frescodemo.R;

public class ProgressActivity extends AppCompatActivity {

    private SimpleDraweeView imageView;
    private Uri uri;
    private GenericDraweeHierarchyBuilder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        init();

    }

    private void setHAndI(GenericDraweeHierarchy hierarchy) {
        if (hierarchy!=null && uri !=null){
            if (imageView!=null){
                imageView.setHierarchy(hierarchy);
                imageView.setImageURI(uri);
            }
        }
    }

    private void init() {
        imageView= (SimpleDraweeView) findViewById(R.id.progress_image);
        uri=Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png");
        builder = new GenericDraweeHierarchyBuilder(getResources());

        GenericDraweeHierarchy hierarchy = builder.setFadeDuration(300)
                .setProgressBarImage(new ProgressBarDrawable())
                .build();

        setHAndI(hierarchy);
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

        //noinspection SimplifiableIfStatemen
        switch (id){
            case R.id.action_normal:
                Log.e("tag","系统自带");

                if (imageView.hasHierarchy()){
                    GenericDraweeHierarchy hierarchy = imageView.getHierarchy();
                    hierarchy.reset();
                    hierarchy=builder.setFadeDuration(300)
                            .setProgressBarImage(new ProgressBarDrawable())
                            .build();
                    setHAndI(hierarchy);
                }else{
                    Toast.makeText(this,"haven't hierarchy",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.action_myself:
                Log.e("tag","自定义的");
                if (imageView.hasHierarchy()){
                    GenericDraweeHierarchy hierarchy = imageView.getHierarchy();
                    hierarchy.reset();
                    hierarchy=builder.setFadeDuration(300)
                            .setProgressBarImage(new MySelfProgress())
                            .build();
                    setHAndI(hierarchy);
                }else{
                    Toast.makeText(this,"haven't hierarchy",Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
