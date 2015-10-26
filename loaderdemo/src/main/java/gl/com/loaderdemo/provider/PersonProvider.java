package gl.com.loaderdemo.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import gl.com.loaderdemo.bean.Person;
import gl.com.loaderdemo.dbUtil.PersonDbOpenHelper;

/**
 * Created by mac on 15-10-26.
 */
public class PersonProvider extends ContentProvider {
    private static final String AUTHORITY = "com.gl.person.provider.personprovider";
    public static final Uri URI_PERSOM_ALL = Uri.parse("content://"+AUTHORITY+"/person");

    private static UriMatcher matcher;

    private static final int PERSON_ALL = 0;
    private static final int PERSON_ONE = 1;

    static{
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY,"person",PERSON_ALL);
        matcher.addURI(AUTHORITY,"person/#",PERSON_ONE);
    }

    private PersonDbOpenHelper mHelper;
    private SQLiteDatabase mDb;

    @Override
    public boolean onCreate() {

        mHelper = PersonDbOpenHelper.getInstance(getContext());

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int match = matcher.match(uri);
        switch (match){
            case PERSON_ALL:

                break;
            case PERSON_ONE:
                long id = ContentUris.parseId(uri);
                selection="_id = ?";
                selectionArgs = new String[]{String.valueOf(id)};
                break;
            default:
                throw new IllegalArgumentException("Wrong Uri:"+uri);


        }
        mDb = mHelper.getReadableDatabase();
        Cursor c= mDb.query(Person.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(),URI_PERSOM_ALL);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        int match = matcher.match(uri);
        if (match!=PERSON_ALL){
            throw new IllegalArgumentException("Wrong Uri:"+uri);
        }
        mDb = mHelper.getReadableDatabase();
        long rowId = mDb.insert(Person.TABLE_NAME,null,values);
        if (rowId>0){
            notifyDataSetChanged();
            return ContentUris.withAppendedId(uri,rowId);
        }
        return null;
    }

    private void notifyDataSetChanged() {
        getContext().getContentResolver().notifyChange(URI_PERSOM_ALL,null);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
