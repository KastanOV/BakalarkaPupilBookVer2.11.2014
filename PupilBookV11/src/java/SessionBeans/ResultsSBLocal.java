/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Results;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Topr
 */
@Local
public interface ResultsSBLocal {
    public servicesDTO.Results saveUploadedResult(servicesDTO.Results res);
    public List<Results> getStudentResults(String login, int StudySubjectID);
    public void insertNewResult(Results res);
}
