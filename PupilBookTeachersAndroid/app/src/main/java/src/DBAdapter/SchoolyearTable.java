package src.DBAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Topr on 11/25/2014.
 */
public class SchoolyearTable extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "pupil_book",
        TABLE_SCHOOLYEAR = "school_year",
        KEY_ID = "id",
        KEY_NAME = "name";

    public SchoolyearTable(Context context) {
        super(context, DATABASE_NAME,  null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_SCHOOLYEAR + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHOOLYEAR);

        onCreate(db);
    }
    public void createSchoolyear(Schoolyear s){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, s.get_id());
        values.put(KEY_NAME, s.get_name());

        db.insert(TABLE_SCHOOLYEAR, null, values);
        db.close();
    }
    public Schoolyear getSchoolyear(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_SCHOOLYEAR, new String[] {KEY_ID, KEY_NAME}, KEY_ID + "=?", new String[] {String.valueOf(id) }, null,null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        Schoolyear sy = new Schoolyear(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        return sy;
    }
    public void deleteSchoolyear(Schoolyear s){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_SCHOOLYEAR, KEY_ID + "=?", new String[]{String.valueOf(s.get_id()) });
        db.close();
    }
    public int getSchoolyearCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SCHOOLYEAR, null);
        int tmp = cursor.getCount();
        cursor.close();
        return tmp;
    }
    public int updateSchoolyear(Schoolyear s){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, s.get_id());
        values.put(KEY_NAME, s.get_name());

        return db.update(TABLE_SCHOOLYEAR,values, KEY_ID + "=?", new String[] {String.valueOf(s.get_id())});
    }
    public List<Schoolyear> getAllSchoolyears(){
        List<Schoolyear> sys = new ArrayList<Schoolyear>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SCHOOLYEAR, null);
        if(cursor.moveToFirst()){
            do{
                Schoolyear sy = new Schoolyear(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
                sys.add(sy);
            } while(cursor.moveToNext());
        }
        return sys;
    }
}
