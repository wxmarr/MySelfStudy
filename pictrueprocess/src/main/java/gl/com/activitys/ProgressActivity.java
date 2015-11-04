package gl.com.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import gl.com.pictrueprocess.MyApplication;
import gl.com.pictrueprocess.R;
import gl.com.utils.PicKinds;

public class ProgressActivity extends AppCompatActivity {
    private ImageView src_imageview;
    private ImageView pro_imageview;
    private TextView pro_text;
    private int position;
    private static int[] images = {R.mipmap.text,R.mipmap.src_image};
    int index = 0;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_pro);
        setSupportActionBar(toolbar);
        position = getIntent().getIntExtra("position",0);
        initView();
        showProImage(images[0]);
    }

    private void showProImage(int image_id) {
        src_imageview.setImageResource(image_id);
        bitmap = BitmapFactory.decodeResource(getResources(),image_id);
        pro_imageview.setImageBitmap(MyApplication.uitl.getProClass(position).doPro(bitmap));

        pro_text.setText(PicKinds.text_list.get(position));
    }

    private void initView() {
        src_imageview = (ImageView) findViewById(R.id.src_image);
        pro_imageview = (ImageView) findViewById(R.id.pro_image);
        pro_text = (TextView) findViewById(R.id.pro_text);
    }

    /**
     * 修改图片
     */
    public void changeImageSrc(){
        bitmap.recycle();
        index++;
        showProImage(images[index%2]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_progress, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_settings){
            changeImageSrc();
        }

        return super.onOptionsItemSelected(item);
    }
}
