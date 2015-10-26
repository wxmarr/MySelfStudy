package gl.com.loaderdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;

import gl.com.loaderdemo.Adapter.PersonAdapter;
import gl.com.loaderdemo.bean.Person;
import gl.com.loaderdemo.provider.PersonProvider;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private AlertDialog dialog;
    private PersonAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAdapter();
        initLoader();
        initView();
    }

    private void initAdapter() {
        if (mAdapter==null){
            mAdapter = new PersonAdapter(MainActivity.this,null,false);
        }

    }

    private void initLoader() {
        getLoaderManager().initLoader(1, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                CursorLoader loader = new CursorLoader(MainActivity.this,PersonProvider.URI_PERSOM_ALL,null,null,null,null);
                return loader;
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

                mAdapter.swapCursor(data);
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                mAdapter.swapCursor(null);
            }
        });
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.my_list);
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
        if (id == R.id.action_add){
            showMyDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showMyDialog() {
        if(dialog==null){
            dialog = new AlertDialog.Builder(MainActivity.this).create();
        }
        dialog.show();
        dialog.setCancelable(false);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_dialog,null);
        dialog.setContentView(view);
        final EditText name = (EditText) view.findViewById(R.id.add_dialog_person_name);
        final EditText age = (EditText) view.findViewById(R.id.add_dialog_person_age);
        Button cancel = (Button) view.findViewById(R.id.button_cancel);
        Button sure = (Button) view.findViewById(R.id.button_sure);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_string = name.getText().toString().trim();
                String age_string = age.getText().toString().trim();
                Person person = new Person();
                person.setAge(age_string);
                person.setName(name_string);
                save(person);
                dialog.dismiss();
            }
        });

    }

    private void save(Person person){
        ContentValues  values = new ContentValues();
        values.put(Person.COLUMN_NAME,person.getName());
        values.put(Person.COLUMN_AGE,person.getAge());

        MainActivity.this.getContentResolver().insert(PersonProvider.URI_PERSOM_ALL, values);
    }
}
