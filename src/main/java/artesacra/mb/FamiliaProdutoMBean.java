/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.mb;

import artesacra.dao.FamiliaProdutoDAO;
import artesacra.modelo.FamiliaProduto;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author informatica
 */
@Named(value = "familiaProdutoMBean")
@RequestScoped
public class FamiliaProdutoMBean implements Serializable {

    FamiliaProduto familiaProduto = new FamiliaProduto();

    FamiliaProdutoDAO familiaProdutoDAO = new FamiliaProdutoDAO();

    List<FamiliaProduto> familiaProdutos = new ArrayList<>();

    @PostConstruct
    public void init() {
        familiaProdutos = familiaProdutoDAO.findAll();
    }

    public FamiliaProduto getFamiliaProduto() {
        return familiaProduto;
    }

    public void setFamiliaProduto(FamiliaProduto familiaProduto) {
        this.familiaProduto = familiaProduto;
    }

    public List<FamiliaProduto> getFamiliaProdutos() {
        return familiaProdutos;
    }

    public void setFamiliaProdutos(List<FamiliaProduto> familiaProdutos) {
        this.familiaProdutos = familiaProdutos;
    }

    
    

    public String save() {
        if (familiaProdutoDAO.save(familiaProduto)) {
            familiaProduto = new FamiliaProduto();
            addMessage(FacesMessage.SEVERITY_INFO, "Guardar", "Sucesso ao guardar os dados");
           
            return "/pages/produtos/familia_produto?faces-redirect=true";
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
