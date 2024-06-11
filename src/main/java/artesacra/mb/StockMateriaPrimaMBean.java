/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package artesacra.mb;

import artesacra.dao.MateriaPrimaDAO;
import artesacra.dao.StockMateriaPrimaDAO;
import artesacra.modelo.StockMateriaPrima;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author informatica
 */
@Named(value = "stockMateriaPrimaMBean")
@SessionScoped
public class StockMateriaPrimaMBean implements Serializable {

    StockMateriaPrima stockMateriaPrima = new StockMateriaPrima();
    StockMateriaPrimaDAO stockMateriaPrimaDAO = new StockMateriaPrimaDAO();
    MateriaPrimaDAO materiaPrimaDAO = new MateriaPrimaDAO();

    public StockMateriaPrima getStockMateriaPrima() {
        return stockMateriaPrima;
    }

    public void setStockMateriaPrima(StockMateriaPrima stockMateriaPrima) {
        this.stockMateriaPrima = stockMateriaPrima;
    }
   
    
    
    
     public String save() {
        if (stockMateriaPrimaDAO.save(stockMateriaPrima)) {
          
            materiaPrimaDAO.updateAumentarQuantidade( stockMateriaPrima.getQuantidade(),stockMateriaPrima.getMateriaPrima());
            stockMateriaPrima = new StockMateriaPrima();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardart", "tSucesso ao guardar os dados"));
            return "/pages/materia_prima/stock/stock_gestao_stock_nova_entrada?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardart", "tErro ao guardar os dados"));
            return null;
        }
    }
    
     
      
    public String cancelarStock() {
        
        return "/pages/materia_prima/stock/stock_gestao_stock_nova_entrada?faces-redirect=true";
        
    }
}
