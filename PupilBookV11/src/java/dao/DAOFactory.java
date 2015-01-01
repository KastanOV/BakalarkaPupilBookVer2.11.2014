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
    public abstract ResultsDAO getResultsDAO();
    public abstract SchoolYearDAO getSchoolYearDAO();
    public abstract SheduleItemsDAO getSheduleItemsDAO();
    public abstract StudentsDAO getStudentsDAO();
    public abstract StudyGroupsDAO getStudyGroupDAO();
    public abstract StudySubjectsDAO getStudySubjectsDAO();
    public abstract TeachersDAO getTeachersDAO();
    public abstract LoginDAO getLoginDAO();

    
}
