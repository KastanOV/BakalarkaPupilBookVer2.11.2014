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
import Entity.Schoolyear;
import Entity.Student;
import Entity.Studygroup;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import servicesDTO.AttendanceDTO;

/**
 *
 * @author Topr
 */
@Stateless
public class AttendanceSB implements AttendanceSBLocal {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Attendance saveAttendance(Attendance a){
        if(a.getIdAttendance() == null){
            em.persist(a);
        } else{
            
            em.merge(a);
        }
        em.flush();
       return a;
    }
    @Override
    public void deleteAttendance(Attendance a){
        em.remove(em.find(Attendance.class, a.getIdAttendance()));
    }
//    @Override
//    public List<Attendance> getAttendance(Student t){
//        return em.createNativeQuery("SELECT * FROM attendance where Users_Login = ?ul", Attendance.class)
//                .setParameter("ul", t.getLogin())
//                .getResultList();
//    }

    @Override
    public List<Attendance> getAttendance(Student s, Studygroup sg) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM attendance ");
        
        if(s != null && sg == null){
            return em.createNativeQuery("SELECT * FROM attendance where Users_Login = ?ul", Attendance.class)
                .setParameter("ul", s.getLogin())
                .getResultList();
        }else if (s == null && sg == null){
            return em.createNativeQuery("SELECT * FROM attendance", Attendance.class)
                .getResultList();
        } else if (s != null && sg != null){
            return em.createNativeQuery("SELECT * FROM attendance where Users_Login = ?ul", Attendance.class)
                .setParameter("ul", s.getLogin())
                .getResultList();
        }else if(s == null && sg != null){
            return em.createNativeQuery("SELECT * FROM attendance a " +
            " where exists (select * from users u join studygroup on StudyGroup_idStudyGroup = idStudyGroup where idStudyGroup = ?id and u.login = a.users_login)", Attendance.class)
                .setParameter("id", sg.getIdStudyGroup())
                .getResultList();
        }
        return null;
    }

    @Override
    public void setAttendanceExcused(Attendance a) {
        a = em.find(Attendance.class, a.getIdAttendance());
        a.setExcussed(true);
        em.merge(a);
    }

    @Override
    public List<AttendanceDTO> getAttendanceService(String login) {
        List<AttendanceDTO> retval = new ArrayList<>();
        try{
            List<Object[]> tmp = em.createNativeQuery("select * from attendance a " +
                " where a.users_login in (" +
                " select distinct us.Login from sheduleitem si " +
                " join studygroup sg on si.StudyGroup_idStudyGroup = sg.idStudyGroup " +
                " join users us on us.StudyGroup_idStudyGroup = sg.idStudyGroup " +
                " where si.Users_Login = ?login and sg.SchoolYear_idSchoolYear = ?si)")
                    .setParameter("login", login)
                    .setParameter("si", getActualSchoolYear())
                    .getResultList();
            AttendanceDTO addvat;
            for(Object[] item : tmp){
                addvat = new AttendanceDTO();
                addvat.setId((Integer) item[0]);
                addvat.setStart(String.valueOf(((Date) item[1]).getTime()));
                Long timeTmp;
                try {
                    timeTmp =  ((Date) item[2]).getTime();
                } catch (Exception e){
                    timeTmp = null;
                }
                addvat.setEnd(String.valueOf(timeTmp));
                
                addvat.setExcused((String) String.valueOf(item[3]));
                addvat.setLogin((String) item[4]);
                retval.add(addvat);
                
            }
        return retval;
        } catch( Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private int getActualSchoolYear(){
        Schoolyear idActualYear = (Schoolyear) em.createNativeQuery("SELECT * FROM schoolyear WHERE schoolyear.isactualyear = true", Schoolyear.class)
                .getSingleResult();
        return idActualYear.getIdSchoolYear();
    }
}
