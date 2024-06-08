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
public class FamiliaProduto {
    private Integer idFamiliaProduto;
    private String descricaoFamiliaProduto;

    public FamiliaProduto() {
    }

    public Integer getIdFamiliaProduto() {
        return idFamiliaProduto;
    }

    public void setIdFamiliaProduto(Integer idFamiliaProduto) {
        this.idFamiliaProduto = idFamiliaProduto;
    }

    public String getDescricaoFamiliaProduto() {
        return descricaoFamiliaProduto;
    }

    public void setDescricaoFamiliaProduto(String descricaoFamiliaProduto) {
        this.descricaoFamiliaProduto = descricaoFamiliaProduto;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.idFamiliaProduto);
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
        final FamiliaProduto other = (FamiliaProduto) obj;
        return Objects.equals(this.idFamiliaProduto, other.idFamiliaProduto);
    }

    @Override
    public String toString() {
        return "FamiliaProduto{" + "descricaoFamiliaProduto=" + descricaoFamiliaProduto + '}';
    }
    
    
}
