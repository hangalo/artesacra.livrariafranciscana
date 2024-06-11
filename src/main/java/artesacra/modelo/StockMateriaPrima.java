
package artesacra.modelo;

import java.util.Date;
import java.util.Objects;


public class StockMateriaPrima {

    private Integer idStock;
    private Date dataCompra;
    private Date dataActualizacao;
    private Integer quantidade;
    private Double precoCompra;
    private String colocacao;
    private MateriaPrima materiaPrima;
    private Profissional profissional;

    public StockMateriaPrima() {
    }

    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Date getDataActualizacao() {
        return dataActualizacao;
    }

    public void setDataActualizacao(Date dataActualizacao) {
        this.dataActualizacao = dataActualizacao;
    }

    public String getColocacao() {
        return colocacao;
    }

    public void setColocacao(String colocacao) {
        this.colocacao = colocacao;
    }

  

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

  

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idStock);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StockMateriaPrima other = (StockMateriaPrima) obj;
        return Objects.equals(this.idStock, other.idStock);
    }

    @Override
    public String toString() {
        return this.materiaPrima.getNomeMateriaPrima();
    }
    
    
    
    
}
