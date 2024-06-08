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
public class CategoriaProfissional {
    private Integer idCategoriaProfissional;
    private String nomeCategoriaProfissional;

    public CategoriaProfissional() {
    }

    public Integer getIdCategoriaProfissional() {
        return idCategoriaProfissional;
    }

    public void setIdCategoriaProfissional(Integer idCategoriaProfissional) {
        this.idCategoriaProfissional = idCategoriaProfissional;
    }

    public String getNomeCategoriaProfissional() {
        return nomeCategoriaProfissional;
    }

    public void setNomeCategoriaProfissional(String nomeCategoriaProfissional) {
        this.nomeCategoriaProfissional = nomeCategoriaProfissional;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idCategoriaProfissional);
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
        final CategoriaProfissional other = (CategoriaProfissional) obj;
        return Objects.equals(this.idCategoriaProfissional, other.idCategoriaProfissional);
    }
    
    
}
