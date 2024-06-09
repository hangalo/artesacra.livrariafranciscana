/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package artesacra.mb;

import artesacra.dao.MateriaPrimaDAO;
import artesacra.modelo.MateriaPrima;
import artesacra.modelo.TipoProduto;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;


@Named(value = "materiaPrimaAuxBean")
@SessionScoped
public class MateriaPrimaAuxBean implements Serializable {

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

   

    
    public String situacaoProdutoStock(int quanditade) {

        if (quanditade >= 10) {
            return "";
        } else {

            return "Disponiveis apenas " + quanditade + " unidades. Reforçar o stock ";
        }

    }

}
