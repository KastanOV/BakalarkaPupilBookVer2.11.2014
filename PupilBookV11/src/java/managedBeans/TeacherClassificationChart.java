/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entity.Results;
import Entity.Studysubject;
import Entity.Users;
import SessionBeans.ResultsSBLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;


/**
 *
 * @author Topr
 */
@ManagedBean
@ViewScoped
public class TeacherClassificationChart {
    
    @EJB
    private ResultsSBLocal sb;
    
//    private LineChartModel classificationModel;
    private BarChartModel barModel;
    private Users editedStudent;
    private Studysubject editedStudySubject;

    public TeacherClassificationChart() {
    }
    
    
    private void createClassificationModel(){
        barModel = initClassificationModel();
        barModel.setTitle("Klasifikace");
        barModel.setLegendPosition("e");
        barModel.setAnimate(true);
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setTickAngle(-50);
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Hodnocení");
        yAxis.setMin(0);
        yAxis.setMax(10);
    }
    private BarChartModel initClassificationModel(){
        BarChartModel model = new BarChartModel();
        ChartSeries classification = new ChartSeries("Známky");
            if(editedStudent != null && editedStudySubject != null){
                List<Results> res = sb.getStudentResults(editedStudent.getLogin(), editedStudySubject.getIdStudySubject());
                if(!res.isEmpty()){
                    for(Results item : res){
                    double score = (double) (item.getScore());
                    classification.set(item.getDescription(), score);
                    
                    } 
                } else {
                    classification.set("Nejsou uloženy žádné výsledky", 0.0);
                }
            model.addSeries(classification);
            
        }
        return model;
    } 
    
    public BarChartModel getClassificationModel() {
        createClassificationModel();
        return barModel;
    }
    public Studysubject getEditedStudySubject() {
        return editedStudySubject;
    }

    public void setEditedStudySubject(Studysubject editedStudySubject) {
        this.editedStudySubject = editedStudySubject;
    }
    

    public Users getEditedStudent() {
        return editedStudent;
    }

    public void setEditedStudent(Users editedStudent) {
        this.editedStudent = editedStudent;
    }
}
