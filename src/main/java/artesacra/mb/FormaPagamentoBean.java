/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package artesacra.mb;

import artesacra.dao.FormaPagamentoDAO;
import artesacra.modelo.FormaPagamento;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author informatica
 */
@Named(value = "formaPagamentoBean")
@RequestScoped
public class FormaPagamentoBean {

    FormaPagamento  formaPagamento = new FormaPagamento();

   FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();

    List<FormaPagamento> formaPagamentos = new ArrayList<>();

    @PostConstruct
    public void init() {
        formaPagamentos = new ArrayList<>();
        formaPagamentos=formaPagamentoDAO.findAll();
       
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<FormaPagamento> getFormaPagamentos() {
        return formaPagamentos;
    }

    public void setFormaPagamentos(List<FormaPagamento> formaPagamentos) {
        this.formaPagamentos = formaPagamentos;
    }

   
   
    public String save() {
        if (formaPagamentoDAO.save(formaPagamento)) {
            formaPagamento = new FormaPagamento();
     
            return "/pages/vendas/forma_pagamento_novo?faces-redirect=true";
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao guardar", "Erro ao guardar os dados");

            return null;
        }
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    
}
