
package artesacra.mb;

import artesacra.dao.ProdutoDAO;
import artesacra.dao.TipoProdutoDAO;
import artesacra.dbutil.DateUtil;
import artesacra.modelo.Produto;
import artesacra.modelo.TipoProduto;
import artesacra.relatorios.ReportsBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author informatica
 */
@Named(value = "produtoAuxMBean")
@SessionScoped
public class ProdutoAuxMBean implements Serializable {
 @Inject
    ReportsBean reportsBean;

    private String designacaoProduto;

    TipoProduto tipoProduto = new TipoProduto();
    Produto produto = new Produto();
    ProdutoDAO produtoDAO = new ProdutoDAO();
    List<Produto> listaProdutos = new ArrayList<>();
    List<Produto> pesquisaProdutos = new ArrayList<>();
    List<Produto> produtosSeleccionados;
    TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();

    @PostConstruct
    public void inicializar() {

        listaProdutos = produtoDAO.findAll();
        produtosSeleccionados = new ArrayList<>();
        produto.setColocacaoEstante("prateleira");
        produto.setValidadeProduto(DateUtil.getDataActual());

    }

    public String getDesignacaoProduto() {
        return designacaoProduto;
    }

    public void setDesignacaoProduto(String designacaoProduto) {
        this.designacaoProduto = designacaoProduto;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public List<Produto> getPesquisaProdutos() {
        return pesquisaProdutos;
    }

    public void setPesquisaProdutos(List<Produto> pesquisaProdutos) {
        this.pesquisaProdutos = pesquisaProdutos;
    }

    public List<Produto> getProdutosSeleccionados() {
        return produtosSeleccionados;
    }

    public void setProdutosSeleccionados(List<Produto> produtosSeleccionados) {
        this.produtosSeleccionados = produtosSeleccionados;
    }

    public List<SelectItem> getSelectItemTipoProdutos() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (TipoProduto tipoProduto : tipoProdutoDAO.findAll()) {
            list.add(new SelectItem(tipoProduto, tipoProduto.getDescricaoTipoProduto()));
        }

        return list;
    }

    public String save() {
        if (produtoDAO.save(produto)) {
            produto = new Produto();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardart", "tSucesso ao guardar os dados"));
            return "/pages/produtos/produto_novo?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardart", "tErro ao guardar os dados"));
            return null;
        }
    }

    public String eliminar() {
        produtoDAO.delete(produto);
        produto = new Produto();
        return "/pages/produtos/produtos_lista_gestao_editar_eliminar?faces-redirect=true";
    }

    
     public String goToEditarProduto(Produto produto) {
      
        this.produto = produtoDAO.findById(produto);
      
        return "/pages/produtos/produto_editar?faces-redirect=true";
    }
    public String prepararEditar() {
        return "/pages/produtos/produto_editar?faces-redirect=true";
    }

    public String editar() {
        produtoDAO.update(produto);
        
        produto = new Produto();
        return "/pages/produtos/produtos_lista_all?faces-redirect=true";
    }

    public void carregarProdutosPorTipo() {
        System.out.println("fraternitas.mb.ProdutoMBean.carregarProdutosPorTipo()");

        produtosSeleccionados = produtoDAO.findByTipo(tipoProduto);
        for (Produto listaProduto : produtosSeleccionados) {
            System.out.println("Produto" + listaProduto.getNomeProduto());
            System.out.println("Produto" + listaProduto.getPrecoVenda());

        }
    }

    public void carregarProdutosPorTipoEditar() {
          pesquisaProdutos = produtoDAO.findByTipo(tipoProduto);
        
    }

    public void carregarProdutosPorTipoStock() {
        System.out.println("fraternitas.mb.ProdutoMBean.carregarProdutosPorTipo()");

        produtosSeleccionados = produtoDAO.findByTipoQuantidadeMenor10(tipoProduto);
        for (Produto listaProduto : produtosSeleccionados) {
            System.out.println("Produto" + listaProduto.getNomeProduto());
            System.out.println("Produto" + listaProduto.getPrecoVenda());

        }
    }

    public void executarProcuraProdutoPorNome(ActionEvent event) {

        procuraProdutoPorDesignacao();

    }

    public List<Produto> procuraProdutoPorDesignacao() {

        pesquisaProdutos = produtoDAO.findByNomes(designacaoProduto);
        for (Produto pesquisaProduto : pesquisaProdutos) {
            System.out.println(">>>>>>>>>>>>>>>>>>>" + pesquisaProduto.getNomeProduto());
        }
        return pesquisaProdutos;
    }

    public void imprimePDFByPrecario(ActionEvent event) {

        reportsBean.imprimirPDFprecario();
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public String irParaGestaoStock() {
        return "produto_gestao_stock";

    }
    
}
