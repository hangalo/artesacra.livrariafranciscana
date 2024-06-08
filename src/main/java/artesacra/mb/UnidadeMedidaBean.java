/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package artesacra.mb;

import artesacra.dao.UnidadeMedidaDAO;
import artesacra.modelo.UnidadeMedida;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author informatica
 */
@Named(value = "unidadeMedidaBean")
@ViewScoped
public class UnidadeMedidaBean implements Serializable {

  
    UnidadeMedida unidadeMedida = new UnidadeMedida();

    UnidadeMedidaDAO unidadeMedidaDAO = new UnidadeMedidaDAO();

    List<UnidadeMedida> unidadeMedidas = new ArrayList<>();

    @PostConstruct
    public void init() {
        unidadeMedidas = unidadeMedidaDAO.findAll();
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public List<UnidadeMedida> getUnidadeMedidas() {
        return unidadeMedidas;
    }

    public void setUnidadeMedidas(List<UnidadeMedida> unidadeMedidas) {
        this.unidadeMedidas = unidadeMedidas;
    }

   
    
    public String save() {
        if (unidadeMedidaDAO.save(unidadeMedida)) {
            unidadeMedida = new UnidadeMedida();
            addMessage(FacesMessage.SEVERITY_INFO, "Guardar", "Sucesso ao guardar os dados");
            refresh();
            return "/pages/produtos/unidade_media_produtos?faces-redirect=true";
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao guardar", "Erro ao guardar os dados");

            return null;
        }
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        String refreshpage = context.getViewRoot().getViewId();
        ViewHandler handler = context.getApplication().getViewHandler();
        UIViewRoot root = handler.createView(context, refreshpage);
        root.setViewId(refreshpage);
        context.setViewRoot(root);
        
        HttpServletResponse response = (HttpServletResponse) FacesContext
                .getCurrentInstance().getExternalContext().getResponse();

        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

    }

    public String refresh2() {
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }

    
}
