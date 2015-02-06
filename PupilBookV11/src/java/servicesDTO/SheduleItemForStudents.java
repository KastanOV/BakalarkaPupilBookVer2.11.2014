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
package servicesDTO;

/**
 *
 * @author Topr
 */
public class SheduleItemForStudents extends SheduleItem{
    private String SubjectName;
    private String TeacherName;
    SheduleItemForStudents(){
        
    }
    public SheduleItemForStudents(String SubjectName, String TeacherName, Integer id, short day, short hour, Integer idStudyGroup, Integer idStudySubject, String Login) {
        super(id, day, hour, idStudyGroup, idStudySubject, Login);
        this.SubjectName = SubjectName;
        this.TeacherName = TeacherName;
    }
    

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String TeacherName) {
        this.TeacherName = TeacherName;
    }
}
