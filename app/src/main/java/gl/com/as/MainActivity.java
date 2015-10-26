package gl.com.as;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    private List<String> mDatas;
    private RecyclerViewAdapter mAdapter;
    private ImageView image;
    private ImageView paletteImage;
    private Palette palette;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setEnterTransition(new Slide());
        getWindow().setExitTransition(new Explode());
//        getWindow().setSharedElementEnterTransition(new Fade());
//        getWindow().setSharedElementExitTransition(new ChangeBounds());

        initDatas();
        initView();
    }


    private void initDatas() {
        mDatas=new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add(""+(char)i);
        }
    }

    private void initView() {
        paletteImage= (ImageView) findViewById(R.id.palette);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        final RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(manager);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter = new RecyclerViewAdapter(MainActivity.this, mDatas));
//        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL_LIST));
        mAdapter.setOnclickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Toast.makeText(MainActivity.this,pos+"click",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int pos) {
                Toast.makeText(MainActivity.this,pos+"long click",Toast.LENGTH_SHORT).show();
            }
        });
        image= (ImageView) findViewById(R.id.image);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisiableItem = ((LinearLayoutManager)manager) .findLastVisibleItemPosition();
                if (lastVisiableItem+1==mDatas.size()){
                    image.setVisibility(View.VISIBLE);
                }
            }
        });
        Drawable dr = paletteImage.getDrawable();
        Bitmap bitmap = drawableTo(dr);

        palette = Palette.generate(bitmap);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(palette.getLightVibrantColor(Color.YELLOW)));
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
        switch (id){
            case R.id.action_settings:
                return true;

            case R.id.action_gridview:
                recyclerView.setLayoutManager(new GridLayoutManager(this,3));
                break;
            case R.id.action_hor_gridview:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.action_list:
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_stag:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.add:
                mAdapter.addData(1);
                break;
            case R.id.delect:
                mAdapter.removeData(1);
                break;
            case R.id.animation:
                Intent intent = new Intent(MainActivity.this,AnimationActivity.class);
                /**
                 * 启动一般的transition
                 */
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                /**
                 * 启动一个共享元素
                 */
               // startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,image,"sharename").toBundle());
                /**
                 * 启动多个共享的transition
                 */
//                ActivityOptions options=  ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
//                    Pair.create(image, "image"),
//                    Pair.create(paletteImage, "paletteImage"));
//                startActivity(intent,options.toBundle());
        }
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
    }

    public Bitmap drawableTo(Drawable dr){
        Bitmap bitmap = Bitmap.createBitmap(dr.getIntrinsicWidth(),
                dr.getIntrinsicHeight(),
                dr.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);
        dr.setBounds(0,0,dr.getIntrinsicWidth(),dr.getIntrinsicHeight());
        dr.draw(canvas);

        return bitmap;
    }

}
