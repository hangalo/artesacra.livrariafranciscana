package artesacra.mb;

import artesacra.dao.SaidaMateriaPrimaDetalhesDAO;
import artesacra.dao.StockMateriaPrimaDAO;
import artesacra.modelo.SaidaMateriaPrimaTotalPorMateriaPrima;
import artesacra.modelo.StockMateriaPrima;
import artesacra.modelo.StockMateriaPrimaAcumulado;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;

@Named(value = "stockMateriaPrimaAuxMBean")
@RequestScoped
public class StockMateriaPrimaAuxMBean {

    private Date inicio, fim;

    StockMateriaPrimaDAO materiaPrimaDAO = new StockMateriaPrimaDAO();
   

    List<StockMateriaPrima> listaEntredadasStockMateriasPrimas = new ArrayList<>();
    List<StockMateriaPrimaAcumulado> listaEntredatasStockAcumuladoMateriasPrimas = new ArrayList<>();
   

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

    public List<StockMateriaPrima> getListaEntredadasStockMateriasPrimas() {
        return listaEntredadasStockMateriasPrimas;
    }

    public void setListaEntredadasStockMateriasPrimas(List<StockMateriaPrima> listaEntredadasStockMateriasPrimas) {
        this.listaEntredadasStockMateriasPrimas = listaEntredadasStockMateriasPrimas;
    }

    public List<StockMateriaPrimaAcumulado> getListaEntredatasStockAcumuladoMateriasPrimas() {
        return listaEntredatasStockAcumuladoMateriasPrimas;
    }

    public void setListaEntredatasStockAcumuladoMateriasPrimas(List<StockMateriaPrimaAcumulado> listaEntredatasStockAcumuladoMateriasPrimas) {
        this.listaEntredatasStockAcumuladoMateriasPrimas = listaEntredatasStockAcumuladoMateriasPrimas;
    }

 

    public void executarBuscaStockEntreDatas(ActionEvent event) {

        listaEntredadasStockMateriasPrimas = materiaPrimaDAO.findStockMateriaPrimaPorIntervaloData(inicio, fim);
    }

    public void buscaStockAcumuladoEntreDatas(ActionEvent event) {
        listaEntredatasStockAcumuladoMateriasPrimas = materiaPrimaDAO.findStockAcumuladoEntreDatas(inicio, fim);
    }

}
