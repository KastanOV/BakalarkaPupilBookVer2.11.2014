package src.DBAdapter;

import java.util.Date;

/**
 * Created by Jaroslav on 2/28/2015.
 */
public class Attendance {
    private Integer id;
    private Long start;
    private Long end;
    private Boolean excused;
    private String login;


    public Attendance(Integer id, Long start, Long end, Boolean excused, String login) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.excused = excused;
        this.login = login;
    }

    public Attendance() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Boolean getExcused() {
        return excused;
    }

    public void setExcused(Boolean excused) {
        this.excused = excused;
    }
}
