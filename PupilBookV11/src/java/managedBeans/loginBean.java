/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entity.Admin;
import Entity.Teacher;
import Entity.Users;
import SessionBeans.loginSessionBeanLocal;
import static com.sun.faces.facelets.util.Path.context;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
    
    private Users User = new Users();
    private String user;
    private String UserName;

    
    private String Password;
    private boolean AdminUser = false;
    private boolean TeacherUser = false;
    private boolean StudentUser = false;
    /**
     * Creates a new instance of loginBean
     */
    public loginBean() {
        
    }
    public void logout() throws IOException{
        User = new Users();
        AdminUser = false;
        TeacherUser = false;
        StudentUser = false;
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String path = context.getRequestContextPath();
        context.redirect(path + "/faces/login.xhtml");
    }
    public void checkAdminLogin() throws IOException{
        if(!(User instanceof Admin)) {
            //TODO dodelat presmerovani pri logout
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            String path = context.getRequestContextPath();
            context.redirect(path + "/faces/login.xhtml");
        }
    }
    public void login() throws IOException{
        User.setLogin(user);
        User.setPassword(Password);
        Users loggedUser = sb.doLogin(User);

        if(loggedUser instanceof Admin) {
            User = loggedUser;
            AdminUser = true;
            UserName = loggedUser.getLastName() + " " + loggedUser.getFirstName();
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect("faces/Admin/index.xhtml");
        } else if (loggedUser instanceof Teacher) {
            User = loggedUser;
            TeacherUser = true;
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect("faces/teachers/index.xhtml");
        } else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Chyba", "Sorry kámo, ale něco máš "));
        }
        
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
    public boolean isAdminUser() {
        return AdminUser;
    }

    public boolean isTeacherUser() {
        return TeacherUser;
    }

    public boolean isStudentUser() {
        return StudentUser;
    }
    public String getUserName() {
        return UserName;
    }
}
