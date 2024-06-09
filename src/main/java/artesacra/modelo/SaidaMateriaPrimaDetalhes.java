/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package artesacra.modelo;

/**
 *
 * @author informatica
 */
public class SaidaMateriaPrimaDetalhes {
    private SaidaMateriaPrima saidaMateriaPrima;
    private MateriaPrima materiaPrima;
    private Integer quanditadeSaida;

    public SaidaMateriaPrimaDetalhes() {
    }

    public SaidaMateriaPrimaDetalhes(SaidaMateriaPrima saidaMateriaPrima, MateriaPrima materiaPrima, Integer quanditadeSaida) {
        this.saidaMateriaPrima = saidaMateriaPrima;
        this.materiaPrima = materiaPrima;
        this.quanditadeSaida = quanditadeSaida;
    }
    
      public SaidaMateriaPrimaDetalhes(MateriaPrima materiaPrima, Integer quanditadeSaida) {
     
          this.materiaPrima = materiaPrima;
        this.quanditadeSaida = quanditadeSaida;
    }

    public SaidaMateriaPrima getSaidaMateriaPrima() {
        return saidaMateriaPrima;
    }

    public void setSaidaMateriaPrima(SaidaMateriaPrima saidaMateriaPrima) {
        this.saidaMateriaPrima = saidaMateriaPrima;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public Integer getQuanditadeSaida() {
        return quanditadeSaida;
    }

    public void setQuanditadeSaida(Integer quanditadeSaida) {
        this.quanditadeSaida = quanditadeSaida;
    }
    
    
    
    public void incrementQuantity() {
        quanditadeSaida++;
    }

    public void decrementQuantity() {
        quanditadeSaida--;
    }
    
}
