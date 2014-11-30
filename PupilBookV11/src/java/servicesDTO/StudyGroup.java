package servicesDTO;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Kastan on 11/27/2014.
 */
@XmlRootElement
public class StudyGroup {
    public StudyGroup(){
        
    }
    public StudyGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }
    private Integer id;

    private String name;

    public void setId(Integer idStudyGroup) {
        this.id = idStudyGroup;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }




}
