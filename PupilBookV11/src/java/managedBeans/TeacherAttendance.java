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

import Entity.Attendance;
import Entity.Student;
import Entity.Studygroup;
import SessionBeans.AttendanceSBLocal;
import java.util.Date;
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
public class TeacherAttendance {
    @EJB
    private AttendanceSBLocal atSB;
    
    private Student editedStudent;
    private Studygroup editedStudyGroup;

    /**
     * Creates a new instance of TeacherAttendance
     */
    public TeacherAttendance() {
    }
    public List<Attendance> getAttendance(){
        return atSB.getAttendance(editedStudent, editedStudyGroup);
    }
    public void setAttendanceExcused(Attendance a){
        atSB.setAttendanceExcused(a);
    }
    public void addAttendance(){
        Attendance at = new Attendance();
        at.setMissingStart(new Date());
        at.setExcussed(false);
        at.setUsersLogin(editedStudent);
        atSB.saveAttendance(at);
    }
    public void ExcuseStudent(Attendance a){
        a.setMissingEnd(new Date());
        atSB.saveAttendance(a);
    }
    public Student getEditedStudent() {
        return editedStudent;
    }
    public void setEditedStudent(Student editedStudent) {
            this.editedStudent = editedStudent;
    }
    public Studygroup getEditedStudyGroup() {
        return editedStudyGroup;
    }
    public void setEditedStudyGroup(Studygroup editedStudyGroup) {
        this.editedStudyGroup = editedStudyGroup;
    }
}
