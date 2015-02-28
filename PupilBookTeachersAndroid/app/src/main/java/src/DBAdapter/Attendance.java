package src.DBAdapter;

import java.util.Date;

/**
 * Created by Jaroslav on 2/28/2015.
 */
public class Attendance {
    private Integer id;
    private Date start;
    private Date end;
    private Boolean excused;
    private String login;

    public Attendance(Integer id, Date start, Date end, Boolean excused, String login) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.excused = excused;
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Boolean getExcused() {
        return excused;
    }

    public void setExcused(Boolean excused) {
        this.excused = excused;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
