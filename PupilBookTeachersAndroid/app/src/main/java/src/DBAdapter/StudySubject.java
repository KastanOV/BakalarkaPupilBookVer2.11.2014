package src.DBAdapter;

/**
 * Created by Topr on 11/27/2014.
 */
public class StudySubject {
    private Integer idStudySubject;
    private String name;
    private String ShortName;

    public StudySubject(int id, String name, String shortName) {
        this.idStudySubject = id;
        this.name = name;
        this.ShortName = shortName;
    }
    public String getShortName() {
        return ShortName;
    }

    public Integer getIdStudySubject() {
        return idStudySubject;
    }

    public String getName() {
        return name;
    }

}
