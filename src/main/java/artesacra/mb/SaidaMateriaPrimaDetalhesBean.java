package artesacra.mb;

import artesacra.dao.MateriaPrimaDAO;
import artesacra.dao.SaidaMateriaPrimaDAO;
import artesacra.dao.SaidaMateriaPrimaDetalhesDAO;
import artesacra.modelo.MateriaPrima;
import artesacra.modelo.Profissional;
import artesacra.modelo.SaidaMateriaPrima;
import artesacra.modelo.SaidaMateriaPrimaDetalhes;
import artesacra.modelo.SectorProducao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@Named(value = "saidaMateriaPrimaDetalhesBean")
@SessionScoped
public class SaidaMateriaPrimaDetalhesBean implements Serializable {

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
            System.out.println(">>>>>>>>>>>>>>>>>>>" + materiaPrima.getNomeMateriaPrima());
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

    public String situacaoProdutoStock(MateriaPrima materiaPrima) {
        int quanditade = materiaPrima.getQuantidadeStock();

        if (quanditade >= 10) {
            return "";
        } else {

            return "Disponiveis apenas " + (quanditade - getQuantidateItens(materiaPrima)) + " unidades. Reforçar o stock ";
        }

    }

    public int situacaoStock(MateriaPrima materiaPrima) {

        int quanditade = materiaPrima.getQuantidadeStock();

        if ((quanditade - getQuantidateItens(materiaPrima)) >= 5) {
            return 1;
        } else {

            return 0;
        }

    }

    public int getQuantidateItens(MateriaPrima materiaPrima) {
        int quantidadeActual = 0;

        if (materiaPrima == null) {

            return quantidadeActual;
        }
        List<SaidaMateriaPrimaDetalhes> results = getItems();
        for (SaidaMateriaPrimaDetalhes item : results) {

            if ((item.getMateriaPrima().getIdMateriaPrima() == materiaPrima.getIdMateriaPrima())) {
                quantidadeActual = item.getQuanditadeSaida();

                break;
            }
        }

        return quantidadeActual;
    }

    public synchronized List<SaidaMateriaPrimaDetalhes> getItems() {
        List<SaidaMateriaPrimaDetalhes> results = new ArrayList<>();
        results.addAll(carrinho);
        return results;
    }

    public long numeroItens() {
        long s = 0;
        for (SaidaMateriaPrimaDetalhes it : this.carrinho) {
            s += it.getQuanditadeSaida();
        }
        return s;
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
