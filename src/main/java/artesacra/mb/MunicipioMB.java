/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.mb;

import artesacra.dao.MunicipioDAO;
import artesacra.modelo.Municipio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author informatica
 */
@Named(value = "municipioMB")
@RequestScoped
public class MunicipioMB implements Serializable {

    Municipio municipio = new Municipio();

    MunicipioDAO municipioDAO = new MunicipioDAO();

    List<Municipio> municipios = new ArrayList<>();

    @PostConstruct
    public void init() {
        municipios = municipioDAO.findAll();
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
}
