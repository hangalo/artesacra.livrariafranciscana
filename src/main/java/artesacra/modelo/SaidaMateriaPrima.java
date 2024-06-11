/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package artesacra.modelo;

import java.util.Date;

/**
 *
 * @author informatica
 */
public class SaidaMateriaPrima {
    private Integer idSaida;
    private Date dataSaida;
    private Profissional profissional;
    private SectorProducao sectorProducao;
    private Date dataRegisto;

    public SaidaMateriaPrima() {
    }

    public SaidaMateriaPrima(Integer idSaida, Date dataSaida, Profissional profissional, SectorProducao sectorProducao, Date dataRegisto) {
        this.idSaida = idSaida;
        this.dataSaida = dataSaida;
        this.profissional = profissional;
        this.sectorProducao = sectorProducao;
        this.dataRegisto = dataRegisto;
    }

    

    public Integer getIdSaida() {
        return idSaida;
    }

    public void setIdSaida(Integer idSaida) {
        this.idSaida = idSaida;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public SectorProducao getSectorProducao() {
        return sectorProducao;
    }

    public void setSectorProducao(SectorProducao sectorProducao) {
        this.sectorProducao = sectorProducao;
    }

    public Date getDataRegisto() {
        return dataRegisto;
    }

    public void setDataRegisto(Date dataRegisto) {
        this.dataRegisto = dataRegisto;
    }
    
    
}
