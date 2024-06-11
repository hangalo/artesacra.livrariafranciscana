/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package artesacra.modelo;

/**
 *
 * @author informatica
 */
public class SaidaMateriaPrimaTotalPorMateriaPrima {
    private MateriaPrima materiaPrima;
    private SaidaMateriaPrima saidaMateriaPrima;   
    private Integer quantidadeSaida;

    public SaidaMateriaPrimaTotalPorMateriaPrima() {
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public SaidaMateriaPrima getSaidaMateriaPrima() {
        return saidaMateriaPrima;
    }

    public void setSaidaMateriaPrima(SaidaMateriaPrima saidaMateriaPrima) {
        this.saidaMateriaPrima = saidaMateriaPrima;
    }

    public Integer getQuantidadeSaida() {
        return quantidadeSaida;
    }

    public void setQuantidadeSaida(Integer quantidadeSaida) {
        this.quantidadeSaida = quantidadeSaida;
    }
    
    
}
