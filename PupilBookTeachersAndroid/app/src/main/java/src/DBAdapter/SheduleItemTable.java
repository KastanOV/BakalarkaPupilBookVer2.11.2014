package src.DBAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Topr on 11/29/2014.
 */
public class SheduleItemTable extends DBMain {

    public SheduleItemTable(Context context) {
        super(context);
    }

    public void createSheduleItem(SheduleItem s){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utils.SHEDULE_ITEM_KEY_ID, s.getId());
        values.put(Utils.SHEDULE_ITEM_KEY_DAY, s.getDay());
        values.put(Utils.SHEDULE_ITEM_KEY_HOUR, s.getHour());
        values.put(Utils.SHEDULE_ITEM_KEY_ID_STUDY_GROUP, s.getIdStudyGroup());
        values.put(Utils.SHEDULE_ITEM_KEY_ID_STUDY_SUBJECT, s.getIdStudySubject());
        values.put(Utils.SHEDULE_ITEM_KEY_LOGIN, s.getLogin());

        db.insert(Utils.TABLE_SHEDULEITEM, null, values);
        db.close();
    }
    public SheduleItem getSheduleItem(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Utils.TABLE_SHEDULEITEM, new String[] {Utils.SHEDULE_ITEM_KEY_ID,Utils.SHEDULE_ITEM_KEY_DAY, Utils.SHEDULE_ITEM_KEY_HOUR,Utils.SHEDULE_ITEM_KEY_ID_STUDY_GROUP,Utils.SHEDULE_ITEM_KEY_ID_STUDY_SUBJECT,Utils.SHEDULE_ITEM_KEY_LOGIN}, Utils.SHEDULE_ITEM_KEY_ID + "=?", new String[] {String.valueOf(id) }, null,null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        SheduleItem sy = new SheduleItem(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), cursor.getString(5));
        db.close();
        return sy;
    }
    public void deleteSheduleItem(SheduleItem s){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Utils.TABLE_SHEDULEITEM, Utils.SHEDULE_ITEM_KEY_ID + "=?", new String[]{String.valueOf(s.getId()) });
        db.close();
    }
    public int getSheduleItemCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_SHEDULEITEM, null);
        int tmp = cursor.getCount();
        cursor.close();
        db.close();
        return tmp;
    }
    public int updateSheduleItem(SheduleItem s){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utils.SHEDULE_ITEM_KEY_ID, s.getId());
        values.put(Utils.SHEDULE_ITEM_KEY_DAY, s.getDay());
        values.put(Utils.SHEDULE_ITEM_KEY_HOUR, s.getHour());
        values.put(Utils.SHEDULE_ITEM_KEY_ID_STUDY_GROUP, s.getIdStudyGroup());
        values.put(Utils.SHEDULE_ITEM_KEY_ID_STUDY_SUBJECT, s.getIdStudySubject());
        values.put(Utils.SHEDULE_ITEM_KEY_LOGIN, s.getLogin());
        int tmp = db.update(Utils.TABLE_SHEDULEITEM,values, Utils.SHEDULE_ITEM_KEY_ID + "=?", new String[] {String.valueOf(s.getId())});
        db.close();
        return tmp;

    }
    public List<SheduleItem> getAllSheduleItem(){
        List<SheduleItem> sys = new ArrayList<SheduleItem>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_SHEDULEITEM, null);
        if(cursor.moveToFirst()){
            do{
                SheduleItem sy = new SheduleItem(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), cursor.getString(5));

                sys.add(sy);
            } while(cursor.moveToNext());
        }
        db.close();
        return sys;
    }
    public List<SheduleItem> getSheduleitemsByDay(int day){
        List<SheduleItem> sys = new ArrayList<SheduleItem>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_SHEDULEITEM + " WHERE " + Utils.SHEDULE_ITEM_KEY_DAY + " = " + day , null);
        if(cursor.moveToFirst()){
            do{
                SheduleItem sy = new SheduleItem(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), cursor.getString(5));

                sys.add(sy);
            } while(cursor.moveToNext());
        }
        db.close();
        return sys;
    }
    public void deleteAllSheduleItems(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + Utils.TABLE_SHEDULEITEM);
        db.close();
    }
}
