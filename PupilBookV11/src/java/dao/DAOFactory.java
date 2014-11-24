/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


/**
 *
 * @author KastanNotas
 */
public interface DAOFactory {

    public abstract SchooyearDAOjpa getSchoolYearDAO();
    
    public abstract StudygroupDAOjpa getStudygroupDAO();
    
    /**
     *
     * @return
     */
    public abstract UsersDAOjpa getUsersDAO();
    
}
