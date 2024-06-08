/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.converter;


import artesacra.dao.FormaPagamentoDAO;
import artesacra.dao.ProdutoDAO;
import artesacra.modelo.FormaPagamento;
import artesacra.modelo.Produto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;




@FacesConverter(value = "produtoConverter")
public class ProdutoConverter implements Converter {

   ProdutoDAO produtoDAO = new ProdutoDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return produtoDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }
    

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
      
      if (value != null) {
             Produto  produto = ( Produto) value;
            return String.valueOf(produto.getIdProduto());
        }
        return null;
    }

}
