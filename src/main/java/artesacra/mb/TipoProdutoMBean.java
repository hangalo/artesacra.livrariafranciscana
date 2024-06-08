/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.mb;

import artesacra.dao.TipoProdutoDAO;
import artesacra.modelo.TipoProduto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;


/**
 *
 * @author informatica
 */
@Named(value = "tipoProdutoMBean")
@SessionScoped
public class TipoProdutoMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private TipoProdutoDAO tipoProdutoDAO;

    private List<TipoProduto> tipoProdutos;
    private TipoProduto tipoProduto = new TipoProduto();

    @PostConstruct
    public void init() {
        tipoProdutoDAO = new TipoProdutoDAO();
        tipoProdutos = tipoProdutoDAO.findAll();

    }

    public List<TipoProduto> getTipoProdutos() {
        return tipoProdutos;
    }

    public void setTipoProdutos(List<TipoProduto> tipoProdutos) {
        this.tipoProdutos = tipoProdutos;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

  

    public String save() {
        if (tipoProdutoDAO.save(tipoProduto)) {
            tipoProduto = new TipoProduto();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
            return "/pages/produtos/tipo_produto_novo?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
            return null;
        }
    }

}
