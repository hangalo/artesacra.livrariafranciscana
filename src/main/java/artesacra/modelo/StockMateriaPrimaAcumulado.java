
package artesacra.modelo;

public class StockMateriaPrimaAcumulado {
     private MateriaPrima materiaPrima;
    private Integer quantidadeTotal;

    public StockMateriaPrimaAcumulado() {
    }

    public StockMateriaPrimaAcumulado(MateriaPrima materiaPrima, Integer quantidadeTotal) {
        this.materiaPrima = materiaPrima;
        this.quantidadeTotal = quantidadeTotal;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(Integer quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }
    
    
    
}
