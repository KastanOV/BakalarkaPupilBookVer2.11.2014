package src.DBAdapter;

/**
 * Created by Topr on 11/29/2014.
 */
public class SheduleItem {
    private Integer id;

    private int day;

    private int hour;

    private int idStudyGroup;

    private int idStudySubject;

    private String Login;

    public SheduleItem(Integer id, int day, int hour, int idStudyGroup, int idStudySubject, String Login) {
        this.id = id;
        this.day = day;
        this.hour = hour;
        this.idStudyGroup = idStudyGroup;
        this.idStudySubject = idStudySubject;
        this.Login = Login;
    }

    public Integer getId() {
        return id;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getIdStudyGroup() {
        return idStudyGroup;
    }

    public int getIdStudySubject() {
        return idStudySubject;
    }

    public String getLogin() {
        return Login;
    }
}
