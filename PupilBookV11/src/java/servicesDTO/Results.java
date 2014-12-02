/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicesDTO;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KastanNotas
 */
@XmlRootElement
public class Results {
    private Integer id;
    private String desc;
    private int score;
    private Date date;
    private Integer ssId;
    private String sL;
    
    public Results() {
        
    }
    public Results(Integer id, String desc, short score, Date date, Integer ssId, String sL) {
        this.id = id;
        this.desc = desc;
        this.score = score;
        this.date = date;
        this.ssId = ssId;
        this.sL = sL;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSsId() {
        return ssId;
    }

    public void setSsId(Integer ssId) {
        this.ssId = ssId;
    }

    public String getsL() {
        return sL;
    }

    public void setsL(String sL) {
        this.sL = sL;
    }

    
    
    
}
