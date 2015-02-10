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

import Entity.Users;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Topr
 */
@Stateless
public class UsersSB implements UsersSBLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Collection<Users> getUsers(Boolean teachers, Boolean isDeleted, String LastName){
        String role = teachers ? "T" : "S";
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM Users u WHERE u.Role = ?s ");
        if(!LastName.equals("")){
            LastName = LastName + "%";
            query.append(" AND u.lastName LIKE ?lastName ");
        }
        if(isDeleted == null){
            isDeleted = false;
        }
        query.append(" AND deleted = ?del ");
        
        Collection<Users> listTmp = em.createNativeQuery(query.toString(), Users.class)
                .setParameter("lastName", LastName)
                .setParameter("s", role)
                .setParameter("del", isDeleted)
                .getResultList();
        return listTmp;
    }
    @Override
    public void deleteUser(Users u){
        try{
            
            em.remove(em.find(Users.class, u.getLogin()));
        } catch (Exception e){
            u.setDeleted(true);
            em.merge(em.find(Users.class, u.getLogin()));
        }
        em.flush();
    }
}
