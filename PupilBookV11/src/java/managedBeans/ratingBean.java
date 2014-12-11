/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author KastanNotas
 */
@ManagedBean
@SessionScoped
public class ratingBean {
    private Integer rating1 = 5;

    public Integer getRating1() {
        return rating1;
    }

    public void setRating1(Integer rating1) {
        this.rating1 = rating1;
    }

    /**
     * Creates a new instance of ratingBean
     */
    public ratingBean() {
    }
    
}
