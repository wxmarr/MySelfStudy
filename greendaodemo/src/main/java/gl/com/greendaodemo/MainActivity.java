package gl.com.greendaodemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

import de.greenrobot.dao.query.Query;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText name,age;
    private Button add,delate;
    private ListView listView;

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private PersonDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = initDao();
        initView();

        initColumnsAndData(dao);

    }

    private void initColumnsAndData(PersonDao dao) {
        String nameCoulmn = PersonDao.Properties.Name.columnName;
        String ageCoulmn =PersonDao.Properties.Age.columnName;
        String cardCoulmn =PersonDao.Properties.Card.columnName;
        String  orderby = ageCoulmn + " COLLATE LOCALIZED ASC";
        cursor  = db.query(dao.getTablename(),dao.getAllColumns(),
                null,null,null,null,orderby);

        String[] from = {nameCoulmn,ageCoulmn};
        int[] to = {android.R.id.text1,android.R.id.text2};
        adapter = new SimpleCursorAdapter(this,android.R.layout.simple_expandable_list_item_2,
               cursor,from,to,SimpleCursorAdapter.NO_SELECTION);
        listView.setAdapter(adapter);
    }

    private PersonDao initDao() {

        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        //实体名小写+s+"-db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"persons-db",null);
        db = helper.getWritableDatabase();
        Log.e("tag","this is db version ->"+db.getVersion());
        // 该数据库连接属于DaoMaster，所以多个Session指的是想用的数据库连接
        daoMaster = new DaoMaster(db);
        daoSession =daoMaster.newSession();
        return daoSession.getPersonDao();
    }

    private void initView() {
        name= (EditText) findViewById(R.id.et_name);
        age= (EditText) findViewById(R.id.et_age);

        add= (Button) findViewById(R.id.bt_add);
        delate = (Button) findViewById(R.id.bt_delate);

        listView = (ListView) findViewById(R.id.my_listview);

        add.setOnClickListener(this);
        delate.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.bt_add:
                String name_string = name.getText().toString().trim();
                int age_int = Integer.parseInt(age.getText().toString().trim());
                Person person = new Person(null,name_string,age_int,age_int+"");
                Log.e("tag","name-->"+person.getName()+"---"+"age---->"+person.getAge());
                dao.insert(person);
                NotifyDataSetChanged();
                break;
            case R.id.bt_delate:
                String name_delate = name.getText().toString().trim();
                Query<Person> query = dao.queryBuilder().where(PersonDao.Properties.Name.eq(name_delate))
                        .build();
                List<Person> persons = query.list();
                dao.delete(persons.get(0));
                NotifyDataSetChanged();
                break;
        }
    }

    private void NotifyDataSetChanged() {
        String ageCoulmn =PersonDao.Properties.Age.columnName;
        String  orderby = ageCoulmn + "  COLLATE LOCALIZED ASC";
        cursor  = db.query(dao.getTablename(),dao.getAllColumns(),
                null,null,null,null,orderby);
        adapter.swapCursor(cursor);
    }
}
