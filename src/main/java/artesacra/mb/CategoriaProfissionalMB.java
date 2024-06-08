/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package artesacra.mb;

import artesacra.dao.CategoriaProfissionalDAO;
import artesacra.modelo.CategoriaProfissional;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author informatica
 */
@Named(value = "categoriaProfissionalMB")
@ViewScoped
public class CategoriaProfissionalMB implements Serializable {

   
    
    CategoriaProfissional categoriaProfissional = new CategoriaProfissional();

    CategoriaProfissionalDAO categoriaProfissionalDAO = new CategoriaProfissionalDAO();

    List<CategoriaProfissional> categoriasProfissionais = new ArrayList<>();
    
     @PostConstruct
    public void init() {
        categoriasProfissionais = categoriaProfissionalDAO.findAll();
    }

    public CategoriaProfissional getCategoriaProfissional() {
        return categoriaProfissional;
    }

    public void setCategoriaProfissional(CategoriaProfissional categoriaProfissional) {
        this.categoriaProfissional = categoriaProfissional;
    }

    public List<CategoriaProfissional> getCategoriasProfissionais() {
        return categoriasProfissionais;
    }

    public void setCategoriasProfissionais(List<CategoriaProfissional> categoriasProfissionais) {
        this.categoriasProfissionais = categoriasProfissionais;
    }
    
    
    
    
}
