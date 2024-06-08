/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.converter;


import artesacra.dao.FamiliaProdutoDAO;
import artesacra.modelo.FamiliaProduto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;




@FacesConverter(value = "familiaProdutoConverter")
public class FamiliaProdutoConverter implements Converter {

  FamiliaProdutoDAO familiaProdutoDAO = new FamiliaProdutoDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return familiaProdutoDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }
    

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
      
      if (value != null) {
             FamiliaProduto  familiaProduto = ( FamiliaProduto) value;
            return String.valueOf(familiaProduto.getIdFamiliaProduto());
        }
        return null;
    }

}
