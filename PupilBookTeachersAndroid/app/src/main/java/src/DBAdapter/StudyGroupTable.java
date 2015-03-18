package src.DBAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Topr on 11/27/2014.
 */
public class StudyGroupTable extends DBMain{

    public StudyGroupTable(Context context) {
        super(context);
    }

    public void createStudyGroup(StudyGroup s){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utils.STUDY_GROUP_KEY_ID, s.getIdStudyGroup());
        values.put(Utils.STUDY_GROUP_KEY_NAME, s.getName());

        db.insert(Utils.TABLE_STUDYGROUP, null, values);
        db.close();
    }
    public StudyGroup getStudygroup(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Utils.TABLE_STUDYGROUP, new String[] {Utils.STUDY_GROUP_KEY_ID, Utils.STUDY_GROUP_KEY_NAME}, Utils.STUDY_GROUP_KEY_ID + "=?", new String[] {String.valueOf(id) }, null,null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        StudyGroup sy = new StudyGroup(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        db.close();
        return sy;
    }
    public void deleteStudyGroup(StudyGroup s){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Utils.TABLE_STUDYGROUP, Utils.STUDY_GROUP_KEY_ID + "=?", new String[]{String.valueOf(s.getIdStudyGroup()) });
        db.close();
    }
    public int getStudyGroupCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_STUDYGROUP, null);
        int tmp = cursor.getCount();
        cursor.close();
        db.close();
        return tmp;
    }
    public int updateStudyGroup(StudyGroup s){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utils.STUDY_GROUP_KEY_ID, s.getIdStudyGroup());
        values.put(Utils.STUDY_GROUP_KEY_NAME, s.getName());
        int tmp = db.update(Utils.TABLE_STUDYGROUP,values, Utils.STUDY_GROUP_KEY_ID + "=?", new String[] {String.valueOf(s.getIdStudyGroup())});
        db.close();
        return tmp;
    }
    public List<StudyGroup> getAllStudyGroup(){
        List<StudyGroup> sys = new ArrayList<StudyGroup>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_STUDYGROUP, null);
        if(cursor.moveToFirst()){
            do{
                StudyGroup sy = new StudyGroup(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
                sys.add(sy);
            } while(cursor.moveToNext());
        }
        db.close();
        return sys;
    }
    public void deleteAllStudyGrops(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + Utils.TABLE_STUDYGROUP);
        db.close();
    }
}
