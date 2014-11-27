package src.DBAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Topr on 11/27/2014.
 */
public class StudyGroupTable extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "pupil_book",
            TABLE_STUDYGROUP = "study_group",
            KEY_ID = "id",
            KEY_NAME = "name";


    public StudyGroupTable(Context context) {
        super(context, DATABASE_NAME,  null, DATABASE_VERSION);
    }

    public void createStudyGroup(StudyGroup s){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, s.getIdStudyGroup());
        values.put(KEY_NAME, s.getName());

        db.insert(TABLE_STUDYGROUP, null, values);
        db.close();
    }
    public StudyGroup getStudygroup(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDYGROUP, new String[] {KEY_ID, KEY_NAME}, KEY_ID + "=?", new String[] {String.valueOf(id) }, null,null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        StudyGroup sy = new StudyGroup(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        return sy;
    }
    public void deleteStudyGroup(StudyGroup s){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_STUDYGROUP, KEY_ID + "=?", new String[]{String.valueOf(s.getIdStudyGroup()) });
        db.close();
    }
    public int getStudyGroupCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDYGROUP, null);
        int tmp = cursor.getCount();
        cursor.close();
        return tmp;
    }
    public int updateStudyGroup(StudyGroup s){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, s.getIdStudyGroup());
        values.put(KEY_NAME, s.getName());

        return db.update(TABLE_STUDYGROUP,values, KEY_ID + "=?", new String[] {String.valueOf(s.getIdStudyGroup())});
    }
    public List<StudyGroup> getAllStudyGroup(){
        List<StudyGroup> sys = new ArrayList<StudyGroup>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDYGROUP, null);
        if(cursor.moveToFirst()){
            do{
                StudyGroup sy = new StudyGroup(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
                sys.add(sy);
            } while(cursor.moveToNext());
        }
        return sys;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS" + TABLE_STUDYGROUP + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDYGROUP);

        onCreate(db);
    }

}
