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
public class StudentTable extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "pupil_book",
            TABLE_STUDENT = "students",
            KEY_ID = "login",
            KEY_FIRSTNAME = "firstname",
            KEY_MIDDLENAME = "middlename",
            KEY_LASTNAME = "lastname",
            KEY_PHONE = "phone",
            KEY_EMAIL = "email",
            KEY_PASSWORD = "password",
            KEY_STUDYGROUP = "studygroup";

    public StudentTable(Context context) {
        super(context, DATABASE_NAME,  null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS" + TABLE_STUDENT + "(" + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_FIRSTNAME + " TEXT, "
                + KEY_MIDDLENAME + " TEXT, "
                + KEY_LASTNAME + " TEXT, "
                + KEY_PHONE + " TEXT, "
                + KEY_EMAIL + " TEXT, "
                + KEY_PASSWORD + " TEXT, "

                + KEY_STUDYGROUP + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }
    public void createStudent(Student s){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, s.getLogin());
        values.put(KEY_FIRSTNAME, s.getFirstName());
        values.put(KEY_MIDDLENAME, s.getMiddleName());
        values.put(KEY_LASTNAME, s.getLastName());
        values.put(KEY_PHONE, s.getPhone());
        values.put(KEY_EMAIL, s.getEmail());
        values.put(KEY_PASSWORD, s.getPassword());
        values.put(KEY_STUDYGROUP, s.getStudyGroupID());

        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }
    public Student getStudent(String login){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENT, new String[] {KEY_ID, KEY_FIRSTNAME,KEY_MIDDLENAME,KEY_LASTNAME,KEY_PHONE,KEY_EMAIL,KEY_PASSWORD,KEY_STUDYGROUP}, KEY_ID + "=?", new String[] {login }, null,null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        Student sy = new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),Integer.parseInt(cursor.getString(7)) );
        return sy;
    }
    public void deleteStudent(Student s){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_STUDENT, KEY_ID + "=?", new String[]{s.getLogin()});
        db.close();
    }
    public int getStudentCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENT, null);
        int tmp = cursor.getCount();
        cursor.close();
        return tmp;
    }
    public int updateStudent(Student s){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, s.getLogin());
        values.put(KEY_FIRSTNAME, s.getFirstName());
        values.put(KEY_MIDDLENAME, s.getMiddleName());
        values.put(KEY_LASTNAME, s.getLastName());
        values.put(KEY_PHONE, s.getPhone());
        values.put(KEY_EMAIL, s.getEmail());
        values.put(KEY_PASSWORD, s.getPassword());
        values.put(KEY_STUDYGROUP, s.getStudyGroupID());

        return db.update(TABLE_STUDENT,values, KEY_ID + "=?", new String[] {s.getLogin()});
    }
    public List<Student> getAllStudent(){
        List<Student> sys = new ArrayList<Student>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENT, null);
        if(cursor.moveToFirst()){
            do{
                Student sy = new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),Integer.parseInt(cursor.getString(7)) );
                sys.add(sy);
            } while(cursor.moveToNext());
        }
        return sys;
    }
}
