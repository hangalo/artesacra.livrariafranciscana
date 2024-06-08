
package artesacra.modelo;

import java.util.Objects;

public class UnidadeMedida {
    private Integer idUnidadeMedida;
    private String descricaoUnidadeMedida;

    public UnidadeMedida() {
    }

    public Integer getIdUnidadeMedida() {
        return idUnidadeMedida;
    }

    public void setIdUnidadeMedida(Integer idUnidadeMedida) {
        this.idUnidadeMedida = idUnidadeMedida;
    }

    public String getDescricaoUnidadeMedida() {
        return descricaoUnidadeMedida;
    }

    public void setDescricaoUnidadeMedida(String descricaoUnidadeMedida) {
        this.descricaoUnidadeMedida = descricaoUnidadeMedida;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idUnidadeMedida);
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
        final UnidadeMedida other = (UnidadeMedida) obj;
        return Objects.equals(this.idUnidadeMedida, other.idUnidadeMedida);
    }

    @Override
    public String toString() {
        return "UnidadeMedida{" + "descricaoUnidadeMedida=" + descricaoUnidadeMedida + '}';
    }
    
    
    
}
