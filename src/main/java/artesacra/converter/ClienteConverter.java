
package artesacra.converter;


import artesacra.dao.ClienteDAO;
import artesacra.modelo.Cliente;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;




@FacesConverter(value = "clienteConverter")
public class ClienteConverter implements Converter {

   ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return clienteDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }
    

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
      
      if (value != null) {
            Cliente  cliente  = (Cliente) value;
            return String.valueOf(cliente.getIdCliente());
        }
        return null;
    }

}
