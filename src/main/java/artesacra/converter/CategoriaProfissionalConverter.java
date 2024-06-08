/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.converter;


import artesacra.dao.CategoriaProfissionalDAO;
import artesacra.dao.MunicipioDAO;
import artesacra.modelo.CategoriaProfissional;
import artesacra.modelo.Municipio;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



/**
 *
 * @author informatica
 */
@FacesConverter(value = "categoriaProfissionalConverter")
public class CategoriaProfissionalConverter implements Converter {

    CategoriaProfissionalDAO categoriaProfissionalDAO = new CategoriaProfissionalDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return categoriaProfissionalDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }
    

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
      
      if (value != null) {
            CategoriaProfissional categoriaProfissional = (CategoriaProfissional) value;
            return String.valueOf(categoriaProfissional.getIdCategoriaProfissional());
        }
        return null;
    }

}
