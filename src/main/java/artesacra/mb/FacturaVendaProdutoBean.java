
package artesacra.mb;

import artesacra.dao.FacturaVendaProdutoDAO;
import artesacra.dbutil.DateUtil;
import artesacra.modelo.FacturaVendaProduto;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;


@Named(value = "facturaVendaProdutoBean")
@SessionScoped
public class FacturaVendaProdutoBean implements Serializable {
private static final long serialVersionUID = 1L;

    private FacturaVendaProdutoDAO facturaDAO;

    private FacturaVendaProduto factura;

   

    @PostConstruct
    public void init() {
        facturaDAO = new FacturaVendaProdutoDAO();
        factura = new FacturaVendaProduto();
        factura.setDataFactura(DateUtil.getDataActual());
    }

    public void registarFactura() {
       
        facturaDAO.save(factura);

    }

    public FacturaVendaProduto getFactura() {
        return factura;
    }

    public void setFactura(FacturaVendaProduto factura) {
        this.factura = factura;
    }

    public void definirValorTotal(Double valor) {
        factura.setValorTotal(valor);
    }

}
