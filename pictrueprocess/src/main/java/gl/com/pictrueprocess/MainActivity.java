package gl.com.pictrueprocess;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

import gl.com.adapter.RecyclerViewAdapter;
import gl.com.utils.PicKinds;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> mDatas;
    private List<Class> mClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        initData();
        initView();
        initAdapter();
    }

    private void initAdapter() {
        if (mDatas!=null && mClass!=null){
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,mDatas,mClass);
            if (recyclerView!=null){
                adapter.setOnClickListener(new RecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position, Class clazz) {
                        Intent intent = new Intent(MainActivity.this,clazz);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        }
    }


    private void initData() {
        mDatas = Arrays.asList(PicKinds.kinds);
        mClass = Arrays.asList(PicKinds.classes);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.my_recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL));


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
