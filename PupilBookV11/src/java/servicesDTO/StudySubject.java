package servicesDTO;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Kastan on 11/27/2014.
 */
@XmlRootElement
public class StudySubject {
    private Integer id;
    private String name;
    private String SName;

    public StudySubject() {
    }

    public StudySubject(int id, String name, String shortName) {
        this.id = id;
        this.name = name;
        this.SName = shortName;
    }
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSName() {
        return SName;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }
   
    
    
    

}
