package src.DBAdapter;

/**
 * Created by KastanNotas on 2.12.2014.
 */
public class Result {
    private Integer id;
    private String desc;
    private int score;
    private String date;
    private Integer ssId;
    private String sL;
    private String tL;

    public Result(Integer id, String desc, int score, String date, Integer ssId, String sL, String tL) {
        this.id = id;
        this.desc = desc;
        this.score = score;
        this.date = date;
        this.ssId = ssId;
        this.sL = sL;
        this.tL = tL;
    }

    public Result() {
    }
    public String getsL() {
        return sL;
    }

    public void setsL(String sL) {
        this.sL = sL;
    }

    public String gettL() {
        return tL;
    }

    public void settL(String tL) {
        this.tL = tL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getSsId() {
        return ssId;
    }

    public void setSsId(Integer ssId) {
        this.ssId = ssId;
    }

}
