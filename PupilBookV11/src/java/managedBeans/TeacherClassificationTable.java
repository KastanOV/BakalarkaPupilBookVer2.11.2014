/*
 * Copyright (C) 2015 Topr
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package managedBeans;

import Entity.Results;
import Entity.ResultsExtended;
import Entity.Student;
import Entity.Studygroup;
import Entity.Studysubject;
import Entity.Users;
import SessionBeans.ResultsSBLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Topr
 */
@ManagedBean
@SessionScoped
public class TeacherClassificationTable implements Serializable{
    
    @EJB
    private ResultsSBLocal ResultsSB;
    
    private ResultsExtended editedResult;
    private List<Results> Results;
    private Studysubject editedStudySubject;
    private Studygroup editedStudyGroup;

    public Studygroup getEditedStudyGroup() {
        return editedStudyGroup;
    }

    public void setEditedStudyGroup(Studygroup editedStudyGroup) {
        this.editedStudyGroup = editedStudyGroup;
    }
    private Users loggedUser;
    private Student student;
    private Boolean render;

    public Boolean getRender() {
        if(editedStudySubject != null && student != null ) render = true;
        else render = false;
        return render;
    }

    public void setRender(Boolean render) {
        this.render = render;
    }

    public TeacherClassificationTable() {
    }
    
    public List<ResultsExtended> getStudentExtendResults() {
        if(loggedUser == null || student == null || editedStudySubject == null){
            return new ArrayList<ResultsExtended>();
        }
        return ResultsSB.getStudentExtendResults(loggedUser.getLogin(), student.getLogin(), editedStudySubject.getIdStudySubject(), editedStudyGroup.getIdStudyGroup());
    }
    public void saveClassification(){
        ResultsSB.saveResult(editedResult);
    }
    public void deleteClasification(){
        ResultsSB.deleteResult(editedResult);
    }
    public Studysubject getEditedStudySubject() {
        return editedStudySubject;
    }
    public void setEditedStudySubject(Studysubject editedStudySubject) {
        this.editedStudySubject = editedStudySubject;
    }
    public Users getLoggedUser() {
        return loggedUser;
    }
    public void setLoggedUser(Users loggedUser) {
        this.loggedUser = loggedUser;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public ResultsExtended getEditedResult() {
        return editedResult;
    }
    public void setEditedResult(ResultsExtended editedResult) {
        this.editedResult = editedResult;
    }
}

