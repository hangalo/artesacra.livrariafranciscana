/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.converter;


import artesacra.dao.TipoProdutoDAO;
import artesacra.modelo.TipoProduto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;




@FacesConverter(value = "tipoProdutoConverter")
public class TipoProdutoConverter implements Converter {

   TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return tipoProdutoDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }
    

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
      
      if (value != null) {
            TipoProduto  tipoProduto = (TipoProduto) value;
            return String.valueOf(tipoProduto.getIdTipoProduto());
        }
        return null;
    }

}
