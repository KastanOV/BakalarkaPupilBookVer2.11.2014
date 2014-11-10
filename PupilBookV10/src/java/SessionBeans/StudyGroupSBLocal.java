/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Studygroup;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KastanNotas
 */
@Local
public interface StudyGroupSBLocal {
    public Studygroup saveStudygroup(Studygroup p);
    public List<Studygroup> getAllStudygroup();
    public Collection<Studygroup> getStudyGroupFromYear(int id);
    public Studygroup getStudygroup(int StudygroupId);
    
    public void deleteStudygroup(Studygroup p);
    public void deleteStudygroup(int StudygroupId);
    
}
