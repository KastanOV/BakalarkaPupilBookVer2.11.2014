/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServicesEntities;


import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;


/**
 *
 * @author Topr
 */
@Entity
public class ResultService implements Serializable{
    @Id
    @Column(name = "idResults")
    private Integer idResults;
    @Column(name = "Description")
    private String description;
    @Column(name = "Score")
    private short score;
    @Column(name = "Date")
    private Date date;
    @Column(name = "StudySubject_idStudySubject")
    private int studySubjectidStudySubject;
    @Column(name = "Teacher_Login")
    private String teacherLogin;
    @Column(name = "Student_Login")
    private String studentLogin;

    public Integer getIdResults() {
        return idResults;
    }

    public void setIdResults(Integer idResults) {
        this.idResults = idResults;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getScore() {
        return score;
    }

    public void setScore(short score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStudySubjectidStudySubject() {
        return studySubjectidStudySubject;
    }

    public void setStudySubjectidStudySubject(int studySubjectidStudySubject) {
        this.studySubjectidStudySubject = studySubjectidStudySubject;
    }

    public String getTeacherLogin() {
        return teacherLogin;
    }

    public void setTeacherLogin(String teacherLogin) {
        this.teacherLogin = teacherLogin;
    }

    public String getStudentLogin() {
        return studentLogin;
    }

    public void setStudentLogin(String studentLogin) {
        this.studentLogin = studentLogin;
    }
}
