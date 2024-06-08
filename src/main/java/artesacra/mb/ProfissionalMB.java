/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.mb;

import artesacra.dao.MunicipioDAO;
import artesacra.dao.ProfissionalDAO;
import artesacra.dao.ProvinciaDAO;
import artesacra.modelo.Municipio;
import artesacra.modelo.Profissional;
import artesacra.modelo.Provincia;
import artesacra.modelo.Sexo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

@Named(value = "profissionalMB")
@ViewScoped
public class ProfissionalMB implements Serializable {

    private MunicipioDAO municipioDAO;

    private List<Municipio> municipios;
    private Provincia provincia;

    List<Profissional> profissionaisLista = new ArrayList<>();
    
    ProvinciaDAO provinciaDAO = new ProvinciaDAO();

    private List<Provincia> provincias;

    Profissional profissional = new Profissional();
    ProfissionalDAO profissionalDAO = new ProfissionalDAO();

    @PostConstruct
    public void inicializar() {
        municipioDAO = new MunicipioDAO();
        provincia = new Provincia();
        provincias = new ArrayList<>();
        provincias = provinciaDAO.findAll();
        profissionaisLista = profissionalDAO.findAll();
       
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public List<Profissional> getProfissionaisLista() {
        return profissionaisLista;
    }

    public void setProfissionaisLista(List<Profissional> profissionaisLista) {
        this.profissionaisLista = profissionaisLista;
    }
    
    
    

    /*
    
   

    public String eliminar() {
        funcionarioDao.delete(funcionario);
        funcionario = new Profissional();
        return null;
    }

    public String prepararEditar() {
        return "funcionario-editar";
    }

    public String editar() {
        funcionarioDao.update(funcionario);
        funcionario = new Profissional();
        return "funcionario-lista?faces-redirect=true";
    }
    
     public List<SelectItem> getOpSexos() {
        List<SelectItem> list = new ArrayList<>();
        for (Sexo sexo : Sexo.values()) {
            list.add(new SelectItem(sexo, sexo.getExtensao()));
        }
        return list;
    }
     */
    // carrega municipios em função da provincia
    public void carregaMunicipiosDaProvincia() {
        System.out.println("Provncia >>>>>" + provincia);
        municipios = municipioDAO.findByIdProvincia(provincia);
    }

    public List<SelectItem> getOpSexos() {
        List<SelectItem> list = new ArrayList<>();
        for (Sexo sexo : Sexo.values()) {
            list.add(new SelectItem(sexo, sexo.getExtensao()));
        }
        return list;
    }

    public String save() {
        if (profissionalDAO.save(profissional)) {
            profissional = new Profissional();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardart", "Sucesso ao guardar os dados"));
            return null;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar", "Erro ao guardar os dados"));
            return null;
        }
    }

}
