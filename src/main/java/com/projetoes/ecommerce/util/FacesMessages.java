package com.projetoes.ecommerce.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesMessages implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private void add(String msg, FacesMessage.Severity severity) {
        FacesMessage facesMessage = new FacesMessage(msg);
        facesMessage.setSeverity(severity);
        
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
    
    private void add(String sumario, String detalhe, FacesMessage.Severity severity) {
    	FacesMessage facesMessage = new FacesMessage(sumario, detalhe);
        facesMessage.setSeverity(severity);
        
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
    
    public void info(String msg) {
        add(msg, FacesMessage.SEVERITY_INFO);
    }
    
    public void info(String sumario, String detalhe) {
    	add(sumario, detalhe, FacesMessage.SEVERITY_INFO);
    }
    
    public void erro(String msg) {
        add(msg, FacesMessage.SEVERITY_ERROR);
    }
    
    public void erro(String sumario, String detalhe) {
    	add(sumario, detalhe, FacesMessage.SEVERITY_ERROR);
    }
    
    public void aviso(String msg) {
        add(msg, FacesMessage.SEVERITY_WARN);
    }
    
    public void aviso(String sumario, String detalhe) {
    	add(sumario, detalhe, FacesMessage.SEVERITY_WARN);
    }
}
