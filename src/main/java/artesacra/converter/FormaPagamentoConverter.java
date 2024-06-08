/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.converter;


import artesacra.dao.FormaPagamentoDAO;
import artesacra.modelo.FormaPagamento;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;




@FacesConverter(value = "formaPagamentoConverter")
public class FormaPagamentoConverter implements Converter {

   FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return formaPagamentoDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }
    

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
      
      if (value != null) {
            FormaPagamento formaPagamento = (FormaPagamento) value;
            return String.valueOf(formaPagamento.getIdFormaPagamento());
        }
        return null;
    }

}
