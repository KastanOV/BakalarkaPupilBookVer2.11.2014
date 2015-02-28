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
package SessionBeans;

import Entity.Attendance;
import Entity.Student;
import Entity.Studygroup;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Topr
 */
@Local
public interface AttendanceSBLocal {
    public void deleteAttendance(Attendance a);
    public Attendance saveAttendance(Attendance a);
    public void setAttendanceExcused(Attendance a);
    
    //public List<Attendance> getAttendance(Student t);
    public List<Attendance> getAttendance(Student s, Studygroup sg);
    public List<Object> getAttendanceService(String login);
}
