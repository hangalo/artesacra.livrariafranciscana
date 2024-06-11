/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package artesacra.mb;

import artesacra.dao.SaidaMateriaPrimaDetalhesDAO;
import artesacra.modelo.SaidaMateriaPrimaDetalhes;
import artesacra.modelo.SaidaMateriaPrimaTotalPorMateriaPrima;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;


@Named(value = "saidaMateriaPrimaDetalhesAuxBean")
@RequestScoped
public class SaidaMateriaPrimaDetalhesAuxBean {
    
    

    
private Date inicio, fim;
 SaidaMateriaPrimaDetalhesDAO saidaMateriaPrimaDetalhesDAO = new SaidaMateriaPrimaDetalhesDAO();
 
 private List<SaidaMateriaPrimaTotalPorMateriaPrima> saidasTotaisPorMateriaPrimaEntreDatas = new ArrayList<>();

 private List<SaidaMateriaPrimaDetalhes> saidasRealizadasEntreDatas = new ArrayList<>();

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public List<SaidaMateriaPrimaTotalPorMateriaPrima> getSaidasTotaisPorMateriaPrimaEntreDatas() {
        return saidasTotaisPorMateriaPrimaEntreDatas;
    }

    public void setSaidasTotaisPorMateriaPrimaEntreDatas(List<SaidaMateriaPrimaTotalPorMateriaPrima> saidasTotaisPorMateriaPrimaEntreDatas) {
        this.saidasTotaisPorMateriaPrimaEntreDatas = saidasTotaisPorMateriaPrimaEntreDatas;
    }

    public List<SaidaMateriaPrimaDetalhes> getSaidasRealizadasEntreDatas() {
        return saidasRealizadasEntreDatas;
    }

    public void setSaidasRealizadasEntreDatas(List<SaidaMateriaPrimaDetalhes> saidasRealizadasEntreDatas) {
        this.saidasRealizadasEntreDatas = saidasRealizadasEntreDatas;
    }

 
 
   public void buscaVendasTotaisPorMateriaPrimaEntreDatas(ActionEvent event) {
        saidasTotaisPorMateriaPrimaEntreDatas = saidaMateriaPrimaDetalhesDAO.totalSaidasPorMateriaPrimaEntreDatas(inicio, fim);
    } 
   
   
    public void executarBuscaVendasEntreDatas(ActionEvent event) {
        saidasRealizadasEntreDatas = saidaMateriaPrimaDetalhesDAO.findAllPorIntervaloData(inicio, fim);
    }
    
}
