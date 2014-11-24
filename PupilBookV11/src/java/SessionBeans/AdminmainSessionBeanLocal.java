/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Schoolyear;
import Entity.Sheduleitem;
import Entity.Student;
import Entity.Studygroup;
import Entity.Studysubject;
import Entity.Teacher;

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
    public Student createNewUser(Student s);
    public Student saveUser(Student s);
    public Collection<Student> getAllStudents();
    
    public Collection<Student> getByLastName(String lastName);
    public Student getStudent(String UserId);
    public Collection<Student> getStudentByStudyGroup(Studygroup s);
//Teachers
    public Collection<Teacher> getAllTeachers();
    public Teacher getTeacher(String UserId);
    public Teacher saveTeacher(Teacher t);
    public Teacher createNewTeacher(Teacher t);
//SchoolYear Method
    public Collection<Teacher> getTeachersByStudyGroup(Studygroup s);

    public Schoolyear saveSchoolyear(Schoolyear s);
    public List<Schoolyear> getAllSchoolYears();
    public Schoolyear getSchoolyear(int id);
    public void deleteSchooYear(Schoolyear s);
    public void deleteSchooYear(int id);
}
