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
public class StudySubjectTable extends DBMain{

    public StudySubjectTable(Context context) {
        super(context);
    }


    public void createStudySubject(StudySubject s){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utils.STUDY_SUBJECT_KEY_ID, s.getIdStudySubject());
        values.put(Utils.STUDY_SUBJECT_KEY_NAME, s.getName());
        values.put(Utils.STUDY_SUBJECT_KEY_SHORT_NAME, s.getShortName());

        db.insert(Utils.TABLE_STUDYSUBJECT, null, values);
        db.close();
        }

    public StudySubject getStudySubject(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Utils.TABLE_STUDYSUBJECT, new String[] {Utils.STUDY_SUBJECT_KEY_ID, Utils.STUDY_SUBJECT_KEY_NAME, Utils.STUDY_SUBJECT_KEY_SHORT_NAME}, Utils.STUDY_SUBJECT_KEY_ID + "=?", new String[] {String.valueOf(id) }, null,null, null, null);
        if(cursor != null){
        cursor.moveToFirst();
        }
        StudySubject sy = new StudySubject(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return sy;
        }

    public void deleteStudySubject(StudySubject s){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Utils.TABLE_STUDYSUBJECT, Utils.STUDY_SUBJECT_KEY_ID + "=?", new String[]{String.valueOf(s.getIdStudySubject()) });
        db.close();
        }
    public int getStudySubjectCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_STUDYSUBJECT, null);
        int tmp = cursor.getCount();
        cursor.close();
        return tmp;
        }
    public int updateStudySubject(StudySubject s){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utils.STUDY_SUBJECT_KEY_ID, s.getIdStudySubject());
        values.put(Utils.STUDY_SUBJECT_KEY_NAME, s.getName());
        values.put(Utils.STUDY_SUBJECT_KEY_SHORT_NAME, s.getShortName());

        return db.update(Utils.TABLE_STUDYSUBJECT,values, Utils.STUDY_SUBJECT_KEY_ID + "=?", new String[] {String.valueOf(s.getIdStudySubject())});
        }
    public List<StudySubject> getAllStudySubject(){
        List<StudySubject> sys = new ArrayList<StudySubject>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_STUDYSUBJECT, null);
        if(cursor.moveToFirst()){
        do{
        StudySubject sy = new StudySubject(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        sys.add(sy);
        } while(cursor.moveToNext());
        }
        return sys;
    }
    public List<StudySubject> getStudySubjectsFromStudyGroup(int StudyGroupId){
        List<StudySubject> sys = new ArrayList<StudySubject>();

        SQLiteDatabase db = getWritableDatabase();
        /*select studysubject.idStudySubject, studysubject.Name, studysubject.ShortName from StudyGroup
        join sheduleitem on sheduleitem.StudyGroup_idStudyGroup = StudyGroup.idStudyGroup
        join studysubject on sheduleitem.StudySubject_idStudySubject = studysubject.idStudySubject
        where Users_Login = 'JIR000'*/
        String Query = "SELECT DISTINCT " + Utils.TABLE_STUDYSUBJECT + "." + Utils.STUDY_SUBJECT_KEY_ID + "," + Utils.TABLE_STUDYSUBJECT + "." + Utils.STUDY_GROUP_KEY_NAME + "," + Utils.TABLE_STUDYSUBJECT + "." + Utils.STUDY_SUBJECT_KEY_SHORT_NAME + " " +
                " FROM " + Utils.TABLE_STUDYGROUP +
                " JOIN " + Utils.TABLE_SHEDULEITEM + " ON " + Utils.TABLE_SHEDULEITEM + "." + Utils.SHEDULE_ITEM_KEY_ID_STUDY_GROUP + " = " + Utils.TABLE_STUDYGROUP + "." + Utils.STUDY_GROUP_KEY_ID +
                " JOIN " + Utils.TABLE_STUDYSUBJECT + " ON " + Utils.TABLE_SHEDULEITEM + "." + Utils.SHEDULE_ITEM_KEY_ID_STUDY_SUBJECT + " = " + Utils.TABLE_STUDYSUBJECT + "." + Utils.STUDY_SUBJECT_KEY_ID +
                " WHERE " + Utils.TABLE_STUDYGROUP + "." + Utils.STUDY_GROUP_KEY_ID + " = " + StudyGroupId;

        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.moveToFirst()){
            do{
                StudySubject sy = new StudySubject(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
                sys.add(sy);
            } while(cursor.moveToNext());
        }
        return sys;
    }
    public void deleteAllStudySubjects(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + Utils.TABLE_STUDYSUBJECT);
}
}
