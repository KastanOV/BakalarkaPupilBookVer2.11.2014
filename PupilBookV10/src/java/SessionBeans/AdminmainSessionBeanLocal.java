/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Schoolyear;
import Entity.Sheduleitem;
import Entity.Studygroup;
import Entity.Studysubject;
import Entity.Users;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KastanNotas
 */
@Local
public interface AdminmainSessionBeanLocal {
//Shedule Items
    public void insertNewSheduleItem(Sheduleitem s);
    public Sheduleitem saveSheduleItem(Sheduleitem s);

// Subjects
    public Collection<Studysubject> getAllStudySubjects();
    public Studysubject insertNewStudySubject(Studysubject s);
    public Studysubject saveStudySubject(Studysubject s);
    public Studysubject getStudysubject(int id);

//Study Groups
    public Studygroup saveStudygroup(Studygroup p);
    public List<Studygroup> getAllStudygroup();
    public List<Studygroup> getEditedStudyGroup(Schoolyear s);
    public Studygroup getStudygroup(int StudygroupId);
    public void deleteStudygroup(Studygroup p);
    public void deleteStudygroup(int StudygroupId);
//Users
    public Users createNewUser(Users s);
    public Users saveUser(Users s);
    public Collection<Users> getAllStudents();
    public Collection<Users> getAllTeachers();
    public Collection<Users> getByLastName(String lastName);
    public Users getUser(String UserId);
    public Collection<Users> getStudentByStudyGroup(Studygroup s);
    public Collection<Users> getTeachersByStudyGroup(Studygroup s);
//SchoolYear Method
    public Schoolyear saveSchoolyear(Schoolyear s);
    public List<Schoolyear> getAllSchoolYears();
    public Schoolyear getSchoolyear(int id);
    public void deleteSchooYear(Schoolyear s);
    public void deleteSchooYear(int id);
}
