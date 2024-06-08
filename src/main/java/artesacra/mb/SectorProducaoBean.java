/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package artesacra.mb;

import artesacra.dao.SectorProducaoDAO;
import artesacra.modelo.SectorProducao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


@Named(value = "sectorProducaoBean")
@RequestScoped
public class SectorProducaoBean {
 SectorProducao sectorProducao = new SectorProducao();
    SectorProducaoDAO sectorProducaoDAO = new SectorProducaoDAO();
     List<SectorProducao> listaSectoresProducoes = new ArrayList<>();
     
     
     @PostConstruct
    public void inicializar() {

        listaSectoresProducoes = sectorProducaoDAO.findAll();
       

    }

    public SectorProducao getSectorProducao() {
        return sectorProducao;
    }

    public void setSectorProducao(SectorProducao sectorProducao) {
        this.sectorProducao = sectorProducao;
    }

    public List<SectorProducao> getListaSectoresProducoes() {
        return listaSectoresProducoes;
    }

    public void setListaSectoresProducoes(List<SectorProducao> listaSectoresProducoes) {
        this.listaSectoresProducoes = listaSectoresProducoes;
    }
    
    
    
    
     public String save() {
        if (sectorProducaoDAO.save(sectorProducao)) {
            sectorProducao = new SectorProducao();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardart", "tSucesso ao guardar os dados"));
            return "/pages/sector_producao/sector_produicao_registar?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardart", "tErro ao guardar os dados"));
            return null;
        }
    }
    
}
