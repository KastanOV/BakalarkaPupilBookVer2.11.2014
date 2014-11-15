/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entity.Users;
import SessionBeans.loginSessionBeanLocal;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author KastanNotas
 */
@ManagedBean
@SessionScoped
public class loginBean implements Serializable{
        
    @EJB
    private loginSessionBeanLocal sb;
    
    private Users loggedUser = new Users();
    private boolean loggedIn;
    private String user;

    
    private String Password;
    
    /**
     * Creates a new instance of loginBean
     */
    public loginBean() {
        
    }
    public String login() throws IOException{
        loggedUser.setLogin(user);
        loggedUser.setPassword(Password);
        loggedUser = sb.doLogin(loggedUser);
        if(loggedUser == null){
            loggedIn = false;
            return null;
        } 
        else loggedIn = true;
        if (loggedUser.getRole() == 'A'){
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect("faces/Admin/index.xhtml");
        }
        return null;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
