package src.DBAdapter;

/**
 * Created by Topr on 11/30/2014.
 */
public class Utils {
    //Tables NAMES
    protected static final String DATABASE_NAME = "pupil_book_v2",
            TABLE_SCHOOLYEAR = "school_year",
            TABLE_STUDYGROUP = "study_group",
            TABLE_STUDYSUBJECT = "study_subject",
            TABLE_SHEDULEITEM = "shedule_item",
            TABLE_STUDENT = "students",
            TABLE_RESULTS = "results",
            TABLE_ATTENDANCE = "attendance";

    //SCHOOL_YEAR
    protected static final String
            SCHOOL_YEAR_KEY_ID = "id",
            SCHOOL_YEAR_KEY_NAME = "name";
    //SHEDULE_ITEM
    protected static final String
            SHEDULE_ITEM_KEY_ID = "id",
            SHEDULE_ITEM_KEY_DAY = "day",
            SHEDULE_ITEM_KEY_HOUR = "hour",
            SHEDULE_ITEM_KEY_ID_STUDY_GROUP = "id_study_group",
            SHEDULE_ITEM_KEY_ID_STUDY_SUBJECT = "id_study_subject",
            SHEDULE_ITEM_KEY_LOGIN = "id_login";
    //STUDENT
    protected static final String
            STUDENT_KEY_ID = "login",
            STUDENT_KEY_FIRSTNAME = "firstname",
            STUDENT_KEY_MIDDLENAME = "middlename",
            STUDENT_KEY_LASTNAME = "lastname",
            STUDENT_KEY_PHONE = "phone",
            STUDENT_KEY_EMAIL = "email",
            STUDENT_KEY_PASSWORD = "password",
            STUDENT_KEY_STUDYGROUP = "studygroup";
    //STUDY_GROUP
    protected static final String
            STUDY_GROUP_KEY_ID = "id",
            STUDY_GROUP_KEY_NAME = "name";
    //STUDY_SUBJECT
    protected static final String
            STUDY_SUBJECT_KEY_ID = "id",
            STUDY_SUBJECT_KEY_NAME = "name",
            STUDY_SUBJECT_KEY_SHORT_NAME = "short_name";
    //RESULTS
    protected static final String
            RESULTS_KEY_ID = "id",
            RESULTS_DESCRIPTION = "description",
            RESULTS_SCORE = "score",
            RESULTS_DATE = "date",
            RESULTS_STUDY_SUBJECT_ID = "study_subject_id",
            RESULTS_STUDENT_LOGIN = "student_login",
            RESULTS_TEACHER_LOGIN = "teacher_login";
    //AttendanceActivity
    protected static final String
            ATTENDANCE_KEY_ID = "id",
            ATTENDANCE_START = "start",
            ATTENDANCE_END = "end",
            ATTENDANCE_EXCUSED = "ex",
            ATTENDANCE_LOGIN = "login",
            ATTENDANCE_CHANGED = "changed";

    //public ResultsActivity(Integer id, String desc, short score, Date date, Integer ssId, String sL)
}
