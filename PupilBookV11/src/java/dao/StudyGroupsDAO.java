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
package dao;

import Entity.Schoolyear;
import Entity.Studygroup;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Topr
 */
public class StudyGroupsDAO {
    private final EntityManager em;

    public StudyGroupsDAO(EntityManager em) {
        super();
        this.em = em;
    }
    
    public Studygroup saveStudygroup(Studygroup s) {
        if(s.getIdStudyGroup() != null){
            em.merge(s);
        } else {
            em.persist(s);
        }
        em.flush();
        return s;
    }

    public List<Studygroup> getAllStudygroup() {
        return em.createNamedQuery("Studygroup.findAll").getResultList();
    }
 
    public Studygroup getStudygroup(int StudygroupId) {
        return em.find(Studygroup.class, StudygroupId);
    }

    public void deleteStudygroup(Studygroup p) {
        em.remove(em.find(Studygroup.class, p.getIdStudyGroup()));
    }

    public void deleteStudygroup(int StudygroupId) {
        em.remove(em.find(Studygroup.class, StudygroupId));
    }


    public List<Studygroup> getEditedStudyGroup(Schoolyear s){
        return em.createNamedQuery("Studygroup.findBySchoolyear")
                .setParameter("SchoolYearID", s)
                .getResultList();
    }
    

    public List<Studygroup> getStudyGroups(String login, String password){
        //DISTINCT studygroup.idStudyGroup, studygroup.Name
        if(checkTeacher(login, password)){
            return em.createNativeQuery("SELECT DISTINCT studygroup.idStudyGroup, studygroup.Name FROM SheduleItem"
                    + " left join studygroup on sheduleitem.StudyGroup_idStudyGroup = studygroup.idStudyGroup"
                    + " join schoolyear on schoolyear.idSchoolYear = studygroup.SchoolYear_idSchoolYear"
                    + " WHERE Users_Login = ?login AND schoolyear.isactualyear = true", Studygroup.class)
                    .setParameter("login", login)
                    .getResultList();
        }else {
            return null;
        }
    }
    
    private boolean checkTeacher(String login, String password){
        long tmp = (long)em.createNativeQuery("SELECT count(*) FROM Users u WHERE u.login = ?login AND u.password = ?password AND Role = 'T'")
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
        if(tmp > 0){
            return true;
        }else return false;
    }
}
