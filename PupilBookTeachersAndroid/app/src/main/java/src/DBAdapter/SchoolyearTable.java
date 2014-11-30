package src.DBAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Topr on 11/25/2014.
 */
public class SchoolyearTable extends DBMain {

    public SchoolyearTable(Context context) {
        super(context);
    }

    public void createSchoolyear(Schoolyear s){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utils.SCHOOL_YEAR_KEY_ID, s.get_id());
        values.put(Utils.SCHOOL_YEAR_KEY_NAME, s.get_name());

        db.insert(Utils.TABLE_SCHOOLYEAR, null, values);
        db.close();
    }
    public Schoolyear getSchoolyear(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Utils.TABLE_SCHOOLYEAR, new String[] {Utils.SCHOOL_YEAR_KEY_ID, Utils.SCHOOL_YEAR_KEY_NAME}, Utils.SCHOOL_YEAR_KEY_ID + "=?", new String[] {String.valueOf(id) }, null,null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        Schoolyear sy = new Schoolyear(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        return sy;
    }
    public void deleteSchoolyear(Schoolyear s){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Utils.TABLE_SCHOOLYEAR, Utils.SCHOOL_YEAR_KEY_ID + "=?", new String[]{String.valueOf(s.get_id()) });
        db.close();
    }
    public int getSchoolyearCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_SCHOOLYEAR, null);
        int tmp = cursor.getCount();
        cursor.close();
        return tmp;
    }
    public int updateSchoolyear(Schoolyear s){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utils.SCHOOL_YEAR_KEY_ID, s.get_id());
        values.put(Utils.SCHOOL_YEAR_KEY_NAME, s.get_name());

        return db.update(Utils.TABLE_SCHOOLYEAR,values, Utils.SCHOOL_YEAR_KEY_ID + "=?", new String[] {String.valueOf(s.get_id())});
    }
    public List<Schoolyear> getAllSchoolyears(){
        List<Schoolyear> sys = new ArrayList<Schoolyear>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_SCHOOLYEAR, null);
        if(cursor.moveToFirst()){
            do{
                Schoolyear sy = new Schoolyear(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
                sys.add(sy);
            } while(cursor.moveToNext());
        }
        return sys;
    }
}
