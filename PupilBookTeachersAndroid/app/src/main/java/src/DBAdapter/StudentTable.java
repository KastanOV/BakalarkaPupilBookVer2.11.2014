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
public class StudentTable extends DBMain{

    public StudentTable(Context context) {
        super(context);
    }

    public void createStudent(Student s){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utils.STUDENT_KEY_ID, s.getLogin());
        values.put(Utils.STUDENT_KEY_FIRSTNAME, s.getFirstName());
        values.put(Utils.STUDENT_KEY_MIDDLENAME, s.getMiddleName());
        values.put(Utils.STUDENT_KEY_LASTNAME, s.getLastName());
        values.put(Utils.STUDENT_KEY_PHONE, s.getPhone());
        values.put(Utils.STUDENT_KEY_EMAIL, s.getEmail());
        values.put(Utils.STUDENT_KEY_PASSWORD, s.getPassword());
        values.put(Utils.STUDENT_KEY_STUDYGROUP, s.getStudyGroupID());

        db.insert(Utils.TABLE_STUDENT, null, values);
        db.close();
    }
    public Student getStudent(String login){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Utils.TABLE_STUDENT, new String[] {Utils.STUDENT_KEY_ID, Utils.STUDENT_KEY_FIRSTNAME,Utils.STUDENT_KEY_MIDDLENAME,Utils.STUDENT_KEY_LASTNAME,Utils.STUDENT_KEY_PHONE,Utils.STUDENT_KEY_EMAIL,Utils.STUDENT_KEY_PASSWORD,Utils.STUDENT_KEY_STUDYGROUP}, Utils.STUDENT_KEY_ID + "=?", new String[] {login }, null,null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        Student sy = new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),Integer.parseInt(cursor.getString(7)) );
        return sy;
    }
    public void deleteStudent(Student s){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Utils.TABLE_STUDENT, Utils.STUDENT_KEY_ID + "=?", new String[]{s.getLogin()});
        db.close();
    }
    public int getStudentCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_STUDENT, null);
        int tmp = cursor.getCount();
        cursor.close();
        return tmp;
    }
    public int updateStudent(Student s){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utils.STUDENT_KEY_ID, s.getLogin());
        values.put(Utils.STUDENT_KEY_FIRSTNAME, s.getFirstName());
        values.put(Utils.STUDENT_KEY_MIDDLENAME, s.getMiddleName());
        values.put(Utils.STUDENT_KEY_LASTNAME, s.getLastName());
        values.put(Utils.STUDENT_KEY_PHONE, s.getPhone());
        values.put(Utils.STUDENT_KEY_EMAIL, s.getEmail());
        values.put(Utils.STUDENT_KEY_PASSWORD, s.getPassword());
        values.put(Utils.STUDENT_KEY_STUDYGROUP, s.getStudyGroupID());

        return db.update(Utils.TABLE_STUDENT,values, Utils.STUDENT_KEY_ID + "=?", new String[] {s.getLogin()});
    }
    public List<Student> getAllStudent(){
        List<Student> sys = new ArrayList<Student>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_STUDENT, null);
        if(cursor.moveToFirst()){
            do{
                Student sy = new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),Integer.parseInt(cursor.getString(7)) );
                sys.add(sy);
            } while(cursor.moveToNext());
        }
        return sys;
    }
    public List<Student> getStudentsAsStudyGroup(int StudyGroup){
        List<Student> sys = new ArrayList<Student>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_STUDENT + " WHERE " + Utils.STUDENT_KEY_STUDYGROUP + " = " + StudyGroup, null);
        if(cursor.moveToFirst()){
            do{
                Student sy = new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),Integer.parseInt(cursor.getString(7)) );
                sys.add(sy);
            } while(cursor.moveToNext());
        }
        return sys;
    }
    public void deleteAllStudents(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + Utils.TABLE_STUDENT);
        db.close();
    }
}
