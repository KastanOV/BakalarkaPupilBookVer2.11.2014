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
    private Integer score;
    private Date date;
    private Integer ssId;
    private String sL;
    private String tL;
    private String password;

    
    public Results() {
        
    }
    public Results(Integer id, String desc, Integer score, Date date, Integer ssId, String sL, String tL, String password) {
        this.id = id;
        this.desc = desc;
        this.score = score;
        this.date = date;
        this.ssId = ssId;
        this.sL = sL;
        this.tL = tL;
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String gettL() {
        return tL;
    }

    public void settL(String tL) {
        this.tL = tL;
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
