package src.DBAdapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Topr on 11/27/2014.
 */
public class StudyGroupTable extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_STUDYGROUP = "study_group",
            KEY_ID = "id",
            KEY_NAME = "name";


    public StudyGroupTable(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
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
