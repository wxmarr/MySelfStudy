package gl.com.loaderdemo.dbUtil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import gl.com.loaderdemo.bean.Person;

/**
 * Created by mac on 15-10-26.
 */
public class PersonDbOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME= "person.db";
    private static final int DB_VERSION= 1;

    private static PersonDbOpenHelper mHelper;

    public static PersonDbOpenHelper getInstance(Context context){
        if (mHelper==null){
            synchronized (PersonDbOpenHelper.class){
                if (mHelper==null){
                    mHelper = new PersonDbOpenHelper(context);
                }
            }
        }
        return mHelper;
    }

    private PersonDbOpenHelper(Context context) {
        super(context.getApplicationContext(),DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table " + Person.TABLE_NAME +" ("+
                " _id integer primary key autoincrement , "+
                Person.COLUMN_NAME + " text, "+
                Person.COLUMN_AGE +" text "+
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
