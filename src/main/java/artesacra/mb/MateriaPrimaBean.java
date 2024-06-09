/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package artesacra.mb;

import artesacra.dao.MateriaPrimaDAO;
import artesacra.modelo.MateriaPrima;
import artesacra.modelo.Produto;
import artesacra.modelo.TipoProduto;
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
@Named(value = "materiaPrimaBean")
@RequestScoped
public class MateriaPrimaBean {

    MateriaPrima materiaPrima = new MateriaPrima();
    MateriaPrimaDAO materiaPrimaDAO = new MateriaPrimaDAO();
    List<MateriaPrima> listaMateriasPrimas = new ArrayList<>();
    List<MateriaPrima> materiasPrimasSeleccionadas = new ArrayList<>();

    @PostConstruct
    public void inicializar() {

        listaMateriasPrimas = materiaPrimaDAO.findAll();

    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public List<MateriaPrima> getListaMateriasPrimas() {
        return listaMateriasPrimas;
    }

    public void setListaMateriasPrimas(List<MateriaPrima> listaMateriasPrimas) {
        this.listaMateriasPrimas = listaMateriasPrimas;
    }

    public List<MateriaPrima> getMateriasPrimasSeleccionadas() {
        return materiasPrimasSeleccionadas;
    }

    public void setMateriasPrimasSeleccionadas(List<MateriaPrima> materiasPrimasSeleccionadas) {
        this.materiasPrimasSeleccionadas = materiasPrimasSeleccionadas;
    }

    public void carregarMateriaPrimaPorTipo(TipoProduto tipoProduto) {
        materiasPrimasSeleccionadas = materiaPrimaDAO.findByTipoProduto(tipoProduto);
    }

    public String save() {
        if (materiaPrimaDAO.save(materiaPrima)) {
            materiaPrima = new MateriaPrima();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardart", "tSucesso ao guardar os dados"));
            return "/pages/materia_prima/materia_prima_registar?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardart", "tErro ao guardar os dados"));
            return null;
        }
    }

   

  
}
