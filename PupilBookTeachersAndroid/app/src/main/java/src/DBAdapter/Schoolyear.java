package src.DBAdapter;

import android.provider.BaseColumns;



/**
 * Created by Topr on 11/25/2014.
 */

public class Schoolyear {

    private int _id;
    private String _name;

    public Schoolyear(int id, String name){
        this._id = id;
        this._name = name;
    }

    public String get_name() {
        return _name;
    }

    public int get_id() {
        return _id;
    }

    public static abstract class TableInfo implements BaseColumns{
        public static final String SCHOOLYEAR_ID = "schoolyear_id";
        public static final String SCHOOLYEAR_NAME = "schoolyear_name";
        public static final String DATABASE_NAME = "pupil_book";
        public static final String TABLE_NAME = "school_year";

    }
}
