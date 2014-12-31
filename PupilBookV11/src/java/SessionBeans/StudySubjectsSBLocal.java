/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Studysubject;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Topr
 */
@Local
public interface StudySubjectsSBLocal {
    public Collection<Studysubject> getAllStudySubjects();
    public Studysubject insertNewStudySubject(Studysubject s);
    public Studysubject saveStudySubject(Studysubject s);
    public Studysubject getStudysubject(int id);
    
}
