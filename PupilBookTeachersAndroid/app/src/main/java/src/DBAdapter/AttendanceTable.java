package src.DBAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaroslav on 2/28/2015.
 */
public class AttendanceTable extends DBMain {

    public AttendanceTable(Context context) {
        super(context);

    }

    public void createAttendance(Attendance a){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utils.ATTENDANCE_KEY_ID, a.getId());
        values.put(Utils.ATTENDANCE_START, String.valueOf(a.getStart()));
        if(a.getEnd() != null){
            values.put(Utils.ATTENDANCE_END, String.valueOf(a.getEnd()));
        }
        values.put(Utils.ATTENDANCE_EXCUSED, String.valueOf(a.getExcused()));
        values.put(Utils.ATTENDANCE_LOGIN, a.getLogin());

        db.insert(Utils.TABLE_ATTENDANCE, null, values);
        db.close();
    }
    public Attendance getAttendance(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Utils.TABLE_ATTENDANCE, new String[] {Utils.ATTENDANCE_KEY_ID,Utils.ATTENDANCE_START, Utils.ATTENDANCE_END,Utils.ATTENDANCE_EXCUSED,Utils.ATTENDANCE_LOGIN}, Utils.ATTENDANCE_KEY_ID + "=?", new String[] {String.valueOf(id) }, null,null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Integer attId;
        Long end;
        try{
            attId = Integer.parseInt(cursor.getString(0));
        } catch (Exception e){
            attId = null;
        }
        try{
            end = Long.valueOf(cursor.getString(2));
        } catch (Exception e){
            end = null;
        }
        Attendance sy = new Attendance(attId, Long.valueOf(cursor.getString(1)), end, Boolean.valueOf(cursor.getString(3)), cursor.getString(4));
        return sy;
    }
    public void deleteAttendance(Attendance a){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Utils.TABLE_ATTENDANCE, Utils.ATTENDANCE_KEY_ID + "=?", new String[]{String.valueOf(a.getId()) });
        db.close();
    }
    public int getAttendanceCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_ATTENDANCE, null);
        int tmp = cursor.getCount();
        cursor.close();
        return tmp;
    }
    public int updateAttendance(Attendance a){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utils.ATTENDANCE_KEY_ID, a.getId());
        values.put(Utils.ATTENDANCE_START, String.valueOf(a.getStart()));
        values.put(Utils.ATTENDANCE_END, String.valueOf(a.getEnd()));
        values.put(Utils.ATTENDANCE_EXCUSED, String.valueOf(a.getExcused()));
        values.put(Utils.ATTENDANCE_LOGIN, a.getLogin());
        return db.update(Utils.TABLE_ATTENDANCE,values, Utils.ATTENDANCE_KEY_ID + "=?", new String[] {String.valueOf(a.getId())});

    }
    public List<Attendance> getAllAttendance(){
        List<Attendance> sys = new ArrayList<Attendance>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_ATTENDANCE, null);
        Integer attId;
        Long end;
        if(cursor.moveToFirst()){
            do{
                try{
                    attId = Integer.parseInt(cursor.getString(0));
                } catch (Exception e){
                    attId = null;
                }
                try{
                    end = Long.valueOf(cursor.getString(2));
                } catch (Exception e){
                    end = null;
                }
                Attendance sy = new Attendance(attId, Long.valueOf(cursor.getString(1)), end, Boolean.valueOf(cursor.getString(3)), cursor.getString(4));

                sys.add(sy);
            } while(cursor.moveToNext());
        }
        return sys;
    }
    public List<Attendance> getNewAttendanceForUpload(){
        List<Attendance> sys = new ArrayList<Attendance>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_ATTENDANCE
                + " WHERE " + Utils.ATTENDANCE_KEY_ID + " IS NULL", null);
        Integer attId;
        Long end;
        if(cursor.moveToFirst()){
            do{
                try{
                    attId = Integer.parseInt(cursor.getString(0));
                } catch (Exception e){
                    attId = null;
                }
                try{
                    end = Long.valueOf(cursor.getString(2));
                } catch (Exception e){
                    end = null;
                }
                Attendance sy = new Attendance(attId, Long.valueOf(cursor.getString(1)), end, Boolean.valueOf(cursor.getString(3)), cursor.getString(4));
            } while(cursor.moveToNext());
        }
        return sys;
    }
    public List<Attendance> getAttendance(String StudentLogin){
        List<Attendance> sys = new ArrayList<Attendance>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_ATTENDANCE
                + " WHERE " + Utils.ATTENDANCE_LOGIN + " = '" + StudentLogin
                + "'", null);

        Integer attId;
        Long end;
        if(cursor.moveToFirst()){
            do{
                try{
                    attId = Integer.parseInt(cursor.getString(0));
                } catch (Exception e){
                    attId = null;
                }
                try{
                    end = Long.valueOf(cursor.getString(2));
                } catch (Exception e){
                    end = null;
                }
                Attendance sy = new Attendance(attId, Long.valueOf(cursor.getString(1)), end, Boolean.valueOf(cursor.getString(3)), cursor.getString(4));
            } while(cursor.moveToNext());
        }
        return sys;
    }
    public void deleteAllAttendance(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + Utils.TABLE_ATTENDANCE + " WHERE " + Utils.ATTENDANCE_KEY_ID + " IS NOT NULL");
    }
    public void updateUploadedAttendance(Attendance a){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("UPDATE " + Utils.TABLE_ATTENDANCE
                + " SET "
                + Utils.ATTENDANCE_KEY_ID + " = " + a.getId()
                + " WHERE "
                + Utils.ATTENDANCE_START + " = '" + String.valueOf(a.getStart()) + "' AND "
                + Utils.ATTENDANCE_LOGIN + " = " + a.getLogin());
        db.close();

    }
}
