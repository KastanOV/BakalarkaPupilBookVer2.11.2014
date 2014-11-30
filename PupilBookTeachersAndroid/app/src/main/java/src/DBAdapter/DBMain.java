package src.DBAdapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Topr on 11/30/2014.
 */
public abstract class DBMain extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;


    public DBMain(Context context) {
        super(context, Utils.DATABASE_NAME,  null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Utils.TABLE_SCHOOLYEAR + "(" + Utils.SCHOOL_YEAR_KEY_ID + " INTEGER PRIMARY KEY," + Utils.SCHOOL_YEAR_KEY_NAME + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Utils.TABLE_SHEDULEITEM + "(" + Utils.SHEDULE_ITEM_KEY_ID + " INTEGER PRIMARY KEY,"
                + Utils.SHEDULE_ITEM_KEY_DAY + " INTEGER, "
                + Utils.SHEDULE_ITEM_KEY_HOUR + " INTEGER, "
                + Utils.SHEDULE_ITEM_KEY_ID_STUDY_GROUP + " INTEGER, "
                + Utils.SHEDULE_ITEM_KEY_ID_STUDY_SUBJECT + " INETEGER, "
                + Utils.SHEDULE_ITEM_KEY_LOGIN + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Utils.TABLE_STUDENT + "(" + Utils.STUDENT_KEY_ID + " TEXT PRIMARY KEY,"
                + Utils.STUDENT_KEY_FIRSTNAME + " TEXT, "
                + Utils.STUDENT_KEY_MIDDLENAME + " TEXT, "
                + Utils.STUDENT_KEY_LASTNAME + " TEXT, "
                + Utils.STUDENT_KEY_PHONE + " TEXT, "
                + Utils.STUDENT_KEY_EMAIL + " TEXT, "
                + Utils.STUDENT_KEY_PASSWORD + " TEXT, "
                + Utils.STUDENT_KEY_STUDYGROUP + " INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Utils.TABLE_STUDYGROUP + "(" + Utils.STUDY_GROUP_KEY_ID + " INTEGER PRIMARY KEY," + Utils.STUDY_GROUP_KEY_NAME + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Utils.TABLE_STUDYSUBJECT + "(" + Utils.STUDY_SUBJECT_KEY_ID + " INTEGER PRIMARY KEY, " + Utils.STUDY_SUBJECT_KEY_NAME + " TEXT, " + Utils.STUDY_SUBJECT_KEY_SHORT_NAME + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_SCHOOLYEAR);
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_SHEDULEITEM);
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_STUDYGROUP);
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_STUDYSUBJECT);
        onCreate(db);
    }
}
