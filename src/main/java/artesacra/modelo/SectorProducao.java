
package artesacra.modelo;

import java.util.Objects;


public class SectorProducao {
    private Integer idSector;
    private String nomeSector;

    public SectorProducao() {
    }

    public SectorProducao(Integer idSector, String nomeSector) {
        this.idSector = idSector;
        this.nomeSector = nomeSector;
    }

    public Integer getIdSector() {
        return idSector;
    }

    public void setIdSector(Integer idSector) {
        this.idSector = idSector;
    }

    public String getNomeSector() {
        return nomeSector;
    }

    public void setNomeSector(String nomeSector) {
        this.nomeSector = nomeSector;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idSector);
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
        final SectorProducao other = (SectorProducao) obj;
        return Objects.equals(this.idSector, other.idSector);
    }
    
    
}
