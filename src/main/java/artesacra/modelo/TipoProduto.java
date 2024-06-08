/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package artesacra.modelo;

import java.util.Objects;

/**
 *
 * @author informatica
 */
public class TipoProduto {
    private Integer idTipoProduto;
    private String descricaoTipoProduto;
    private FamiliaProduto familiaProduto;

    public TipoProduto() {
    }

    public Integer getIdTipoProduto() {
        return idTipoProduto;
    }

    public void setIdTipoProduto(Integer idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }

    public String getDescricaoTipoProduto() {
        return descricaoTipoProduto;
    }

    public void setDescricaoTipoProduto(String descricaoTipoProduto) {
        this.descricaoTipoProduto = descricaoTipoProduto;
    }

    public FamiliaProduto getFamiliaProduto() {
        return familiaProduto;
    }

    public void setFamiliaProduto(FamiliaProduto familiaProduto) {
        this.familiaProduto = familiaProduto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idTipoProduto);
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
        final TipoProduto other = (TipoProduto) obj;
        return Objects.equals(this.idTipoProduto, other.idTipoProduto);
    }

    @Override
    public String toString() {
        return "TipoProduto{" + "descricaoTipoProduto=" + descricaoTipoProduto + '}';
    }
    
    
}
