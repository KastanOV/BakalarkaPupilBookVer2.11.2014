package src.DBAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Topr on 11/29/2014.
 */
public class SheduleItemTable extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "pupil_book",
            TABLE_SHEDULEITEM = "shedule_item",
            KEY_ID = "id",
            KEY_DAY = "day",
            KEY_HOUR = "hour",
            KEY_ID_STUDY_GROUP = "id_study_group",
            KEY_ID_STUDY_SUBJECT = "id_study_subject",
            KEY_LOGIN = "id_login";

    public SheduleItemTable(Context context) {
        super(context, DATABASE_NAME,  null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_SHEDULEITEM + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_DAY + " INTEGER, "
                + KEY_HOUR + " INTEGER, "
                + KEY_ID_STUDY_GROUP + " INTEGER, "
                + KEY_ID_STUDY_SUBJECT + " INETEGER, "
                + KEY_LOGIN + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHEDULEITEM);

        onCreate(db);
    }
    public void createSheduleItem(SheduleItem s){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, s.getId());
        values.put(KEY_DAY, s.getDay());
        values.put(KEY_HOUR, s.getHour());
        values.put(KEY_ID_STUDY_GROUP, s.getIdStudyGroup());
        values.put(KEY_ID_STUDY_SUBJECT, s.getIdStudySubject());
        values.put(KEY_LOGIN, s.getLogin());

        db.insert(TABLE_SHEDULEITEM, null, values);
        db.close();
    }
    public SheduleItem getSheduleItem(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_SHEDULEITEM, new String[] {KEY_ID, KEY_DAY, KEY_HOUR,KEY_ID_STUDY_GROUP,KEY_ID_STUDY_SUBJECT,KEY_LOGIN}, KEY_ID + "=?", new String[] {String.valueOf(id) }, null,null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        SheduleItem sy = new SheduleItem(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), cursor.getString(5));
        return sy;
    }
    public void deleteSheduleItem(SheduleItem s){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_SHEDULEITEM, KEY_ID + "=?", new String[]{String.valueOf(s.getId()) });
        db.close();
    }
    public int getSheduleItemCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SHEDULEITEM, null);
        int tmp = cursor.getCount();
        cursor.close();
        return tmp;
    }
    public int updateSheduleItem(SheduleItem s){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, s.getId());
        values.put(KEY_DAY, s.getDay());
        values.put(KEY_HOUR, s.getHour());
        values.put(KEY_ID_STUDY_GROUP, s.getIdStudyGroup());
        values.put(KEY_ID_STUDY_SUBJECT, s.getIdStudySubject());
        values.put(KEY_LOGIN, s.getLogin());

        return db.update(TABLE_SHEDULEITEM,values, KEY_ID + "=?", new String[] {String.valueOf(s.getId())});
    }
    public List<SheduleItem> getAllSheduleItem(){
        List<SheduleItem> sys = new ArrayList<SheduleItem>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SHEDULEITEM, null);
        if(cursor.moveToFirst()){
            do{
                SheduleItem sy = new SheduleItem(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), cursor.getString(5));

                sys.add(sy);
            } while(cursor.moveToNext());
        }
        return sys;
    }
}
