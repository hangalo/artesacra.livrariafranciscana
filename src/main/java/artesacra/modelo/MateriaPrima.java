
package artesacra.modelo;

import java.util.Date;
import java.util.Objects;

public class MateriaPrima {
    private Integer idMateriaPrima;
    private String nomeMateriaPrima;
    private Date dataExpiracao;
    private Integer quantidadeStock;
    private TipoProduto tipoProduto;
   

    public Integer getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public void setIdMateriaPrima(Integer idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public String getNomeMateriaPrima() {
        return nomeMateriaPrima;
    }

    public void setNomeMateriaPrima(String nomeMateriaPrima) {
        this.nomeMateriaPrima = nomeMateriaPrima;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Integer getQuantidadeStock() {
        return quantidadeStock;
    }

    public void setQuantidadeStock(Integer quantidadeStock) {
        this.quantidadeStock = quantidadeStock;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

  

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.idMateriaPrima);
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
        final MateriaPrima other = (MateriaPrima) obj;
        return Objects.equals(this.idMateriaPrima, other.idMateriaPrima);
    }
    
    
    
}
