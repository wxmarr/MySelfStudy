package gl.com.frescodemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import gl.com.activity.ControllerActivity;
import gl.com.activity.DownImageListenerActivity;
import gl.com.activity.GifActivity;
import gl.com.activity.JPEGActivity;
import gl.com.activity.MutiImageActivity;
import gl.com.activity.NormalActivity;
import gl.com.activity.ProgressActivity;
import gl.com.activity.RomotaActivity;
import gl.com.activity.RoundActivity;
import gl.com.activity.ZoomActivity;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ListViewAdapter mAdapter;
    private List<String> mDatas;
    private List<Class> mActivitys ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initEvent();
    }

    private void initEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("tag","this position is"+position);
                Intent intent = new Intent(MainActivity.this,mActivitys.get(position));
                startActivity(intent);
            }
        });
    }

    private void initData() {
        mDatas = new ArrayList<String>();
        mDatas.add("普通案例");
        mDatas.add("进度条");
        mDatas.add("缩放");
        mDatas.add("圆角/圆圈");
        mDatas.add("使用ControllerBuilder");
        mDatas.add("渐进式显示JPG");
        mDatas.add("GIF");
        mDatas.add("多图请求及图片服用");
        mDatas.add("监听下载事件");
        mDatas.add("缩放和旋转");
        mDatas.add("图片的修改");
        mDatas.add("图片请求");
        mDatas.add("自定义view");
        mDatas.add("其他");

        mActivitys=new ArrayList<Class>();
        mActivitys.add(NormalActivity.class);
        mActivitys.add(ProgressActivity.class);
        mActivitys.add(ZoomActivity.class);
        mActivitys.add(RoundActivity.class);
        mActivitys.add(ControllerActivity.class);
        mActivitys.add(JPEGActivity.class);
        mActivitys.add(GifActivity.class);
        mActivitys.add(MutiImageActivity.class);
        mActivitys.add(DownImageListenerActivity.class);
        mActivitys.add(RomotaActivity.class);
    }

    private void initView() {

//        DraweeController draweeController = Fresco.newDraweeControllerBuilder().setUri(uri)
//                .setAutoPlayAnimations(true).build();
//        draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
//        Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png");
////        draweeView.setController(draweeController);
//        draweeView.setImageURI(uri);
//        Fresco.newDraweeControllerBuilder().setUri(uri).build();

        listView= (ListView) findViewById(R.id.my_list);
        mAdapter=new ListViewAdapter(MainActivity.this,mDatas);
        listView.setAdapter(mAdapter);

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
