package gl.com.loaderdemo.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import gl.com.loaderdemo.R;
import gl.com.loaderdemo.bean.Person;

/**
 * Created by mac on 15-10-26.
 */
public class PersonAdapter extends CursorAdapter {

    public PersonAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    //返回布局
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return view;
    }
    //给view绑定数据
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv_name = (TextView) view.findViewById(R.id.person_name);
        TextView tv_age = (TextView) view.findViewById(R.id.person_age);

        tv_name.setText("姓名："+cursor.getString(cursor.getColumnIndex(Person.COLUMN_NAME)));
        tv_age.setText("年龄："+cursor.getString(cursor.getColumnIndex(Person.COLUMN_AGE)));
    }
}
