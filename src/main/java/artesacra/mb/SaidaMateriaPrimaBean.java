package artesacra.mb;

import artesacra.dao.SaidaMateriaPrimaDAO;
import artesacra.dbutil.DateUtil;
import artesacra.modelo.SaidaMateriaPrima;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named(value = "saidaMateriaPrimaBean")
@SessionScoped
public class SaidaMateriaPrimaBean implements Serializable {

    private SaidaMateriaPrimaDAO saidaMateriaPrimaDAO;

    private SaidaMateriaPrima saidaMateriaPrima;

    @PostConstruct
    public void init() {
        saidaMateriaPrimaDAO = new SaidaMateriaPrimaDAO();
        saidaMateriaPrima = new SaidaMateriaPrima();
        saidaMateriaPrima.setDataSaida(DateUtil.getDataActual());

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
