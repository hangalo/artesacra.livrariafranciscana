package artesacra.mb;

import artesacra.dao.MateriaPrimaDAO;
import artesacra.dao.SaidaMateriaPrimaDAO;
import artesacra.dao.SaidaMateriaPrimaDetalhesDAO;
import artesacra.modelo.MateriaPrima;
import artesacra.modelo.Profissional;
import artesacra.modelo.SaidaMateriaPrima;
import artesacra.modelo.SaidaMateriaPrimaDetalhes;
import artesacra.modelo.SectorProducao;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Named(value = "saidaMateriaPrimaDetalhesBean")
@RequestScoped
public class SaidaMateriaPrimaDetalhesBean {

    @Inject
    SaidaMateriaPrimaBean saidaMateriaPrimaBean;
    private List<SaidaMateriaPrimaDetalhes> carrinho = new ArrayList<>();

    private Profissional profissional = new Profissional();
    private SectorProducao sectorProducao = new SectorProducao();
    private SaidaMateriaPrimaDAO saidaMateriaPrimaDAO = new SaidaMateriaPrimaDAO();
    private SaidaMateriaPrima saidaMateriaPrima = new SaidaMateriaPrima();
    private SaidaMateriaPrimaDetalhesDAO itemDAO = new SaidaMateriaPrimaDetalhesDAO();
    private MateriaPrimaDAO materiaPrimaDAO = new MateriaPrimaDAO();

    public String addicionarProdutoCarrinho(MateriaPrima materiaPrima) {
        int index = isExisting(materiaPrima);
        if (index == -1) {
            this.carrinho.add(new SaidaMateriaPrimaDetalhes(materiaPrima, 1));
        } else {
            int quantidade = this.carrinho.get(index).getQuanditadeSaida() + 1;
            this.carrinho.get(index).setQuanditadeSaida(quantidade);
        }
        // return "carrinho?face-redirect=true";
        return null;
    }

    private int isExisting(MateriaPrima materiaPrima) {
        for (int i = 0; i < this.carrinho.size(); i++) {
            if (this.carrinho.get(i).getMateriaPrima().getIdMateriaPrima() == materiaPrima.getIdMateriaPrima()) {
                return i;
            }
        }
        return -1;
    }

    public void registarSaida() {

        saidaMateriaPrimaBean.getSaidaMateriaPrima().setProfissional(profissional);
        saidaMateriaPrimaBean.getSaidaMateriaPrima().setSectorProducao(sectorProducao);

        //chamada do metodo que regista a factura actual
        saidaMateriaPrimaBean.registarSaidaDeMateriaPrima();

        //busca a ultima factura registada
        Integer numeroSaida = saidaMateriaPrimaDAO.buscaUltimoRegisto();
        //define o nunmeo da factura actual
        saidaMateriaPrima.setIdSaida(numeroSaida);
        //percorre o carrinho e regista cada item
        for (SaidaMateriaPrimaDetalhes item : carrinho) {
            item.setSaidaMateriaPrima(saidaMateriaPrima);
            itemDAO.save(item);
            materiaPrimaDAO.updateDimunuirQuantidade(item.getQuanditadeSaida(), item.getMateriaPrima());
        }
        carrinho.clear();
    }

}
