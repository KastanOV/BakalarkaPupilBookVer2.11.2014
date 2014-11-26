package src.DBAdapter;

/**
 * Created by Topr on 11/27/2014.
 */
public class StudyGroup {
    public StudyGroup(int id, String name) {
        this.idStudyGroup = id;
        this.name = name;
    }
    private Integer idStudyGroup;

    private String name;

    public String getName() {
        return name;
    }

    public Integer getIdStudyGroup() {
        return idStudyGroup;
    }




}
