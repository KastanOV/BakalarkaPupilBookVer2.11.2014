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
        if(a.getId() != null){
            values.put(Utils.ATTENDANCE_KEY_ID, a.getId());
        }

        values.put(Utils.ATTENDANCE_START, String.valueOf(a.getStart()));
        if(a.getEnd() != null){
            values.put(Utils.ATTENDANCE_END, String.valueOf(a.getEnd()));
        }
        values.put(Utils.ATTENDANCE_EXCUSED, String.valueOf(a.getExcused()));
        values.put(Utils.ATTENDANCE_LOGIN, a.getLogin());

        db.insert(Utils.TABLE_ATTENDANCE, null, values);
        db.close();
    }
    //TODO This method dont work
    public Attendance getAttendance(int id){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + Utils.TABLE_ATTENDANCE
                + " WHERE " + Utils.ATTENDANCE_KEY_ID + " = " + id ;
        Cursor cursor = db.rawQuery(query, null);
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
    public void deleteAttendance(Integer id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Utils.TABLE_ATTENDANCE, Utils.ATTENDANCE_KEY_ID + "=?", new String[]{String.valueOf(id) });
        db.close();
    }
    public void deleteAttendance(Long start){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Utils.TABLE_ATTENDANCE, Utils.ATTENDANCE_START + "=?", new String[]{String.valueOf(start) });
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
        values.put(Utils.ATTENDANCE_CHANGED, 1);
        return db.update(Utils.TABLE_ATTENDANCE,values, Utils.ATTENDANCE_START + "=?", new String[] {String.valueOf(a.getStart())});
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
    public List<Attendance> getAttendance(String StudentLogin){
        List<Attendance> sys = new ArrayList<Attendance>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + Utils.TABLE_ATTENDANCE
                + " WHERE " + Utils.ATTENDANCE_LOGIN + " = '" + StudentLogin + "' ORDER BY " + Utils.ATTENDANCE_START + " DESC ";
        Cursor cursor = db.rawQuery(query, null);

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
    public void deleteAllAttendance(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + Utils.TABLE_ATTENDANCE + " WHERE " + Utils.ATTENDANCE_KEY_ID + " IS NOT NULL");
    }
    public List<Attendance> getNewAttendanceForUpload(){
        List<Attendance> sys = new ArrayList<Attendance>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_ATTENDANCE
                + " WHERE " + Utils.ATTENDANCE_KEY_ID + " IS NULL OR " + Utils.ATTENDANCE_CHANGED + " = 1", null);
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
    public void updateUploadedAttendance(Attendance a){
        Integer id = a.getId();
        Long start = a.getStart();
        if(id == null){
            createAttendance(a);

        }else{
            deleteAttendance(start);
            createAttendance(a);
        }
    }
}
