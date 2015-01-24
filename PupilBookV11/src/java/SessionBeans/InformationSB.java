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

import Entity.Informations;
import Entity.Teacher;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Topr
 */
@Stateless
public class InformationSB implements InformationSBLocal {
    @PersistenceContext
    private EntityManager em;
    
    /**
     *
     * @param i
     * @return
     */
    @Override
    public Informations saveInformation(Informations i){
        if(i.getIdinformations() == null){
            em.persist(i);
        } else {
            em.merge(i);
        }
        em.flush();
        return i;
    }

    @Override
    public void delete(Informations i) {
        em.remove(i);
    }
    
    @Override
    public List<Informations> getInformations(Teacher t) {
        return em.createNativeQuery("SELECT * FROM informations WHERE Teacher_Login = ?tl order by CreateDate desc", Informations.class)
                .setParameter("tl", t.getLogin())
                .getResultList();
    }
    
    
}
