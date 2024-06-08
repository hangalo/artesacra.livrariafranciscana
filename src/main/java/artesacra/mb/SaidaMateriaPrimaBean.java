
package artesacra.mb;

import artesacra.dao.SaidaMateriaPrimaDAO;
import artesacra.modelo.SaidaMateriaPrima;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;


@Named(value = "saidaMateriaPrimaBean")
@RequestScoped
public class SaidaMateriaPrimaBean {
  
    private SaidaMateriaPrimaDAO saidaMateriaPrimaDAO;

    private SaidaMateriaPrima saidaMateriaPrima;

    @PostConstruct
    public void init() {
        saidaMateriaPrimaDAO = new SaidaMateriaPrimaDAO();
        saidaMateriaPrima = new SaidaMateriaPrima();
       
    }

    public void registarSaidaDeMateriaPrima() {       
        saidaMateriaPrimaDAO.save(saidaMateriaPrima);
    }

    public SaidaMateriaPrima getSaidaMateriaPrima() {
        return saidaMateriaPrima;
    }

    public void setSaidaMateriaPrima(SaidaMateriaPrima saidaMateriaPrima) {
        this.saidaMateriaPrima = saidaMateriaPrima;
    }

  

    
    
   
}
