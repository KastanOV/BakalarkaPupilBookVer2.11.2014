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
public class StudySubjectTable extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pupil_book",
            TABLE_STUDYSUBJECT = "study_subject",
            KEY_ID = "id",
            KEY_NAME = "name",
            KEY_SHORTNAME = "short_name";

    public StudySubjectTable(Context context) {
        super(context, DATABASE_NAME,  null, DATABASE_VERSION);
    }


    public void createStudySubject(StudySubject s){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, s.getIdStudySubject());
        values.put(KEY_NAME, s.getName());
        values.put(KEY_SHORTNAME, s.getShortName());

        db.insert(TABLE_STUDYSUBJECT, null, values);
        db.close();
    }
    public void recreateStudySubject(List<StudySubject> s){
        
    }
    public StudySubject getStudySubject(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDYSUBJECT, new String[] {KEY_ID, KEY_NAME, KEY_SHORTNAME}, KEY_ID + "=?", new String[] {String.valueOf(id) }, null,null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        StudySubject sy = new StudySubject(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return sy;
    }
    public void deleteStudySubject(StudySubject s){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_STUDYSUBJECT, KEY_ID + "=?", new String[]{String.valueOf(s.getIdStudySubject()) });
        db.close();
    }
    public int getStudySubjectCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDYSUBJECT, null);
        int tmp = cursor.getCount();
        cursor.close();
        return tmp;
    }
    public int updateStudySubject(StudySubject s){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, s.getIdStudySubject());
        values.put(KEY_NAME, s.getName());
        values.put(KEY_SHORTNAME, s.getShortName());

        return db.update(TABLE_STUDYSUBJECT,values, KEY_ID + "=?", new String[] {String.valueOf(s.getIdStudySubject())});
    }
    public List<StudySubject> getAllStudySubject(){
        List<StudySubject> sys = new ArrayList<StudySubject>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDYSUBJECT, null);
        if(cursor.moveToFirst()){
            do{
                StudySubject sy = new StudySubject(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
                sys.add(sy);
            } while(cursor.moveToNext());
        }
        return sys;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS" + TABLE_STUDYSUBJECT + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, " + KEY_SHORTNAME + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDYSUBJECT);
        onCreate(db);
    }
}
