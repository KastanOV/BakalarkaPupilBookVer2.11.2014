/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Schoolyear;

import WebServicesEntities.SheduleItemService;
import WebServicesEntities.StudyGroupService;
import WebServicesEntities.StudySubjectService;

import WebServicesEntities.ServiceTransactionObject;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Topr
 */
@Singleton
public class WebServicesDao implements WebServicesDaoLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    private ServiceTransactionObject tr = new ServiceTransactionObject();
    
    @Override
    public ServiceTransactionObject getAlldata(String TeacherLogin) {
        
        getActualSchoolYear(tr);
        tr.setSheduleItems(getSheduleItems(TeacherLogin));
        tr.setStudyGroups(getStudyGroups());
        tr.setStudySubjects(getStudySubjects());
        return tr;
    }
    private List<StudySubjectService> getStudySubjects(){
        List<StudySubjectService> itemList = new ArrayList<>();
        List<Integer> SubjectNumbers = new ArrayList<>();
        for(SheduleItemService item : tr.getSheduleItems()){
            if(!SubjectNumbers.contains(item.getStudySubjectidStudySubject())){
                SubjectNumbers.add(item.getStudySubjectidStudySubject());
            }
        }
        for(Integer item : SubjectNumbers){
            itemList.add((StudySubjectService)em.createNativeQuery("Select * from studysubject where idStudySubject = ?1",StudySubjectService.class)
                    .setParameter("1", item)
                    .getSingleResult());
        }
        return itemList;
    }
    private List<StudyGroupService> getStudyGroups(){
        List<StudyGroupService> itemList = new ArrayList<>();
        List<Integer> GroupNumbers = new ArrayList<>();
        for(SheduleItemService item : tr.getSheduleItems()){
            if(!GroupNumbers.contains(item.getStudyGroupidStudyGroup())){
                GroupNumbers.add(item.getStudyGroupidStudyGroup());
            }
        }
        for(Integer item : GroupNumbers){
            itemList.add((StudyGroupService)em.createNativeQuery("Select * from studygroup where idStudyGroup = ?1",StudyGroupService.class)
                    .setParameter("1", item)
                    .getSingleResult());
        }
        return itemList;
    }
    private void getActualSchoolYear(ServiceTransactionObject tr){
        Schoolyear sy = (Schoolyear) em.createNamedQuery("Schoolyear.findByIsactualyear")
                .setParameter("isactualyear", true)
                .getSingleResult();
        tr.setSchoolYearID(sy.getIdSchoolYear());
        tr.setSchoolYearName(sy.getName());
    }
    private List<SheduleItemService> getSheduleItems(String TeacherLogin){
            List<SheduleItemService> tmp = (List<SheduleItemService>) em.createNativeQuery("select si.idSheduleItem, si.day,si.hour,si.StudyGroup_idStudyGroup,si.StudySubject_idStudySubject from  sheduleitem si " +
                "join schoolyear sy " +
                "left join studygroup sg on si.StudyGroup_idStudyGroup = sg.idStudyGroup " +
                "where sy.isactualyear = ?isact and si.Users_Login = ?login ", SheduleItemService.class)
                .setParameter("login", TeacherLogin)
                .setParameter("isact", 1)
                .getResultList();
            return tmp;
    }
    
}
