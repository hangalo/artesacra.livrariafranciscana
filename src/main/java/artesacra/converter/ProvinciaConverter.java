/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.converter;


import artesacra.dao.ProvinciaDAO;
import artesacra.modelo.Provincia;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author PENA
 */
@FacesConverter(value = "provinciaConverter")
public class ProvinciaConverter implements Converter {

    ProvinciaDAO provinciaDAO = new ProvinciaDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return provinciaDAO.findById(id);
        } catch (Exception e) {
            System.err.println("Erro na conversão " + e.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
                 
        
        if (value != null) {
            Provincia provincia =(Provincia)value;
            return String.valueOf(provincia.getIdProvincia());
        }
        return "";
        
        
        
        
    }

}
