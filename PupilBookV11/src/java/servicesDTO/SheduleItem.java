/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicesDTO;


import javax.xml.bind.annotation.XmlRootElement;



/**
 *
 * @author Topr
 */
@XmlRootElement
public class SheduleItem {

    public SheduleItem() {
    }

    private Integer id;
    private short day;
    private short hour;
    private int idStudyGroup;
    private int idStudySubject;
    private String Login;

    public SheduleItem(Integer id, short day, short hour, int idStudyGroup, int idStudySubject, String Login) {
        this.id = id;
        this.day = day;
        this.hour = hour;
        this.idStudyGroup = idStudyGroup;
        this.idStudySubject = idStudySubject;
        this.Login = Login;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setDay(short day) {
        this.day = day;
    }

    public void setHour(short hour) {
        this.hour = hour;
    }

    public void setIdStudyGroup(int idStudyGroup) {
        this.idStudyGroup = idStudyGroup;
    }

    public void setIdStudySubject(int idStudySubject) {
        this.idStudySubject = idStudySubject;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }
    
    public Integer getId() {
        return id;
    }

    public short getDay() {
        return day;
    }

    public short getHour() {
        return hour;
    }

    public int getIdStudyGroup() {
        return idStudyGroup;
    }

    public int getIdStudySubject() {
        return idStudySubject;
    }

    public String getLogin() {
        return Login;
    }
}
