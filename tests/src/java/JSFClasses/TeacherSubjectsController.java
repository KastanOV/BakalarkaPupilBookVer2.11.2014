package JSFClasses;

import EntityClasses.TeacherSubjects;
import JSFClasses.util.JsfUtil;
import JSFClasses.util.JsfUtil.PersistAction;
import SessionBeans.TeacherSubjectsFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "teacherSubjectsController")
@SessionScoped
public class TeacherSubjectsController implements Serializable {

    @EJB
    private SessionBeans.TeacherSubjectsFacade ejbFacade;
    private List<TeacherSubjects> items = null;
    private TeacherSubjects selected;

    public TeacherSubjectsController() {
    }

    public TeacherSubjects getSelected() {
        return selected;
    }

    public void setSelected(TeacherSubjects selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TeacherSubjectsFacade getFacade() {
        return ejbFacade;
    }

    public TeacherSubjects prepareCreate() {
        selected = new TeacherSubjects();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Names").getString("TeacherSubjectsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Names").getString("TeacherSubjectsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Names").getString("TeacherSubjectsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TeacherSubjects> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Names").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Names").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<TeacherSubjects> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TeacherSubjects> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TeacherSubjects.class)
    public static class TeacherSubjectsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TeacherSubjectsController controller = (TeacherSubjectsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "teacherSubjectsController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TeacherSubjects) {
                TeacherSubjects o = (TeacherSubjects) object;
                return getStringKey(o.getIdTeacherSubjects());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TeacherSubjects.class.getName()});
                return null;
            }
        }

    }

}
