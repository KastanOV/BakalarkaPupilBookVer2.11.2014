package src.DBAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KastanNotas on 2.12.2014.
 */
public class ResultsTable extends DBMain {


    public ResultsTable(Context context) {
        super(context);

    }

    public void createResult(Result r){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utils.RESULTS_KEY_ID, r.getId());
        values.put(Utils.RESULTS_DESCRIPTION, r.getDesc());
        values.put(Utils.RESULTS_SCORE, r.getScore());
        values.put(Utils.RESULTS_DATE, r.getDate().toString());
        values.put(Utils.RESULTS_STUDY_SUBJECT_ID, r.getSsId());
        values.put(Utils.RESULTS_STUDENT_LOGIN, r.getsL());
        values.put(Utils.RESULTS_TEACHER_LOGIN, r.gettL());

        db.insert(Utils.TABLE_RESULTS, null, values);
        db.close();
    }
    public Result getResult(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Utils.TABLE_RESULTS, new String[] {Utils.RESULTS_KEY_ID,Utils.RESULTS_DESCRIPTION, Utils.RESULTS_SCORE,Utils.RESULTS_DATE,Utils.RESULTS_STUDY_SUBJECT_ID,Utils.RESULTS_STUDENT_LOGIN}, Utils.RESULTS_KEY_ID + "=?", new String[] {String.valueOf(id) }, null,null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Result sy = new Result(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), Integer.parseInt(cursor.getString(4)), cursor.getString(5),cursor.getString(6));
        db.close();
        return sy;

    }
    public void deleteResult(Result r){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Utils.TABLE_RESULTS, Utils.RESULTS_KEY_ID + "=?", new String[]{String.valueOf(r.getId()) });
        db.close();
    }
    public int getResultCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_RESULTS, null);
        int tmp = cursor.getCount();
        cursor.close();
        return tmp;
    }
    public int updateResult(Result r){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utils.RESULTS_KEY_ID, r.getId());
        values.put(Utils.RESULTS_DESCRIPTION, r.getDesc());
        values.put(Utils.RESULTS_SCORE, r.getScore());
        values.put(Utils.RESULTS_DATE, r.getDate().toString());
        values.put(Utils.RESULTS_STUDY_SUBJECT_ID, r.getSsId());
        values.put(Utils.RESULTS_STUDENT_LOGIN, r.getsL());
        int tmp = db.update(Utils.TABLE_RESULTS,values, Utils.RESULTS_KEY_ID + "=?", new String[] {String.valueOf(r.getId())});
        return tmp;

    }
    public List<Result> getAllResult(){
        List<Result> sys = new ArrayList<Result>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_RESULTS, null);
        Integer resultId;
        if(cursor.moveToFirst()){
            do{
                try{
                    resultId = Integer.parseInt(cursor.getString(0));
                } catch (Exception e){
                    resultId = null;
                }
                Result sy = new Result(resultId, cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), Integer.parseInt(cursor.getString(4)), cursor.getString(5), cursor.getString(6));

                sys.add(sy);
            } while(cursor.moveToNext());
        }
        db.close();
        return sys;
    }
    public List<Result> getNewResultsForUpload(){
        List<Result> sys = new ArrayList<Result>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_RESULTS
                + " WHERE " + Utils.RESULTS_KEY_ID + " IS NULL", null);
        Integer resultId;
        if(cursor.moveToFirst()){
            do{
                try{
                    resultId = Integer.parseInt(cursor.getString(0));
                } catch (Exception e){
                    resultId = null;
                }
                Result sy = new Result(resultId, cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), Integer.parseInt(cursor.getString(4)), cursor.getString(5), cursor.getString(6));

                sys.add(sy);
            } while(cursor.moveToNext());
        }
        db.close();
        return sys;
    }
    public List<Result> getResults(String StudentLogin, int StudySubjectID){
        List<Result> sys = new ArrayList<Result>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_RESULTS
                + " WHERE " + Utils.RESULTS_STUDENT_LOGIN + " = '" + StudentLogin
                + "' AND " + Utils.RESULTS_STUDY_SUBJECT_ID + " = " + StudySubjectID, null);

        Integer resultId;
        if(cursor.moveToFirst()){
            do{
                try{
                    resultId = Integer.parseInt(cursor.getString(0));
                } catch (Exception e){
                    resultId = null;
                }
                Result sy = new Result(resultId, cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), Integer.parseInt(cursor.getString(4)), cursor.getString(5), cursor.getString(6));

                sys.add(sy);
            } while(cursor.moveToNext());
        }
        db.close();
        return  sys;
    }
    public void deleteAllResult(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + Utils.TABLE_RESULTS + " WHERE " + Utils.RESULTS_KEY_ID + " IS NOT NULL");
    }
    public void updateUploadedResult(Result res){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("UPDATE " + Utils.TABLE_RESULTS
        + " SET "
                + Utils.RESULTS_KEY_ID + " = " + res.getId()
        + " WHERE "
                + Utils.RESULTS_DATE + " = '" + res.getDate() + "' AND "
                + Utils.RESULTS_DESCRIPTION + " = '" + res.getDesc() + "' AND "
                + Utils.RESULTS_SCORE  + " = " + res.getScore() + " AND "
                + Utils.RESULTS_STUDENT_LOGIN +  " = '" + res.getsL() + "' AND "
                + Utils.RESULTS_STUDY_SUBJECT_ID + " = " + res.getSsId());
        db.close();

    }
}
