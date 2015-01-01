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

import Entity.Studygroup;
import Entity.Studysubject;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Topr
 */
public class StudySubjectsDAO {
    
    private final EntityManager em;

    public StudySubjectsDAO(EntityManager em) {
        super();
        this.em = em;
    }
    
    public Collection<Studysubject> getAllStudySubjects(){
        return em.createNamedQuery("Studysubject.findAll").getResultList();
    }

    public Studysubject insertNewStudySubject(Studysubject s){
        em.persist(s);
        em.flush();
        return s;
    }

    public Studysubject saveStudySubject(Studysubject s){
        em.merge(s);
        em.flush();
        return s;
    }

    public Studysubject getStudysubject(int id) {
        return em.find(Studysubject.class, id);
    }

    public List<Studysubject> getStudySubjects(){
        return  em.createNativeQuery("SELECT * FROM studysubject", Studysubject.class).getResultList();
    }

    public List<Studysubject> getStudySubjects(Studygroup group, String login){
        return em.createNativeQuery("select distinct studysubject.idStudySubject, studysubject.Name, studysubject.ShortName from sheduleitem "
	+ " join studysubject on studysubject.idStudySubject = sheduleitem.StudySubject_idStudySubject "
        + " join studygroup on sheduleitem.StudyGroup_idStudyGroup = studygroup.idStudyGroup "
        + " where studygroup.idStudyGroup =  ?group and sheduleitem.Users_Login = ?login", Studysubject.class)
                .setParameter("group", group.getIdStudyGroup())
                .setParameter("login", login)
                .getResultList();
        
    }
    
}
