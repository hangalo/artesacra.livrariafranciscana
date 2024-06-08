/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.mb;

import artesacra.dao.MunicipioDAO;
import artesacra.dao.ProvinciaDAO;
import artesacra.modelo.Municipio;
import artesacra.modelo.Provincia;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author informatica
 */
@Named(value = "provinciMB")
@SessionScoped
public class ProvinciaMB implements Serializable {

    Provincia provincia = new Provincia();

   ProvinciaDAO provinciaDAO = new ProvinciaDAO();

    List<Provincia> provincias = new ArrayList<>();

    @PostConstruct
    public void init() {
        provincias = provinciaDAO.findAll();
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    
}
