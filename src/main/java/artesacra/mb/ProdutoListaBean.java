/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package artesacra.mb;

import artesacra.dao.ProdutoDAO;
import artesacra.dao.TipoProdutoDAO;
import artesacra.dbutil.DateUtil;
import artesacra.modelo.Produto;
import artesacra.modelo.TipoProduto;
import artesacra.relatorios.ReportsBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author informatica
 */
@Named(value = "produtoListaBean")
@RequestScoped
public class ProdutoListaBean {

  @Inject
    ReportsBean reportsBean;

    private String designacaoProduto;

    TipoProduto tipoProduto = new TipoProduto();
    Produto produto = new Produto();
    ProdutoDAO produtoDAO = new ProdutoDAO();
    List<Produto> listaProdutos = new ArrayList<>();
    List<Produto> pesquisaProdutos = new ArrayList<>();
    List<Produto> produtosSeleccionados;
    List<Produto> produtosGestaoStockSeleccionados = new ArrayList<>();
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

    public List<Produto> getProdutosGestaoStockSeleccionados() {
        return produtosGestaoStockSeleccionados;
    }

    public void setProdutosGestaoStockSeleccionados(List<Produto> produtosGestaoStockSeleccionados) {
        this.produtosGestaoStockSeleccionados = produtosGestaoStockSeleccionados;
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

    public String prepararEditar() {
        return "/pages/produtos/produto_editar";
    }

    public String editar() {
        if (produtoDAO.update(produto)) {
            produto = new Produto();
            addMessage(FacesMessage.SEVERITY_INFO, "Editar", "Dados do produto alterados com sucesso");
            return "/pages/produtos/produtos_lista_gestao_editar_eliminar?faces-redirect=true";
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao guardar", "Erro ao guardar os dados");
            return null;
        }
    }

    public void carregarProdutosPorTipo() {

        produtosSeleccionados = produtoDAO.findByTipo(tipoProduto);

    }

    public void carregarProdutosSotckPositivoPorTipo() {
        produtosSeleccionados = produtoDAO.findByTipoStockPositivo(tipoProduto);

    }

    public void carregarProdutosPorTipoEditar() {

        pesquisaProdutos = produtoDAO.findByTipo(tipoProduto);

    }

    public void carregarProdutosPorTipoStock() {
        produtosGestaoStockSeleccionados = produtoDAO.findByTipoQuantidadeMenor10(tipoProduto);

    }

    public void executarProcuraProdutoPorNome(ActionEvent event) {

        procuraProdutoPorDesignacao();

    }

    public List<Produto> procuraProdutoPorDesignacao() {

        pesquisaProdutos = produtoDAO.findByNomes(designacaoProduto);

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

    public String situacaoProdutoStock(int quanditade) {

        if (quanditade >= 10) {
            return "";
        } else {

            return "Disponiveis apenas " + quanditade + " unidades. Reforçar o stock ";
        }

    }

    public String cancelarStock() {
        if (produtosGestaoStockSeleccionados != null) {
            produtosGestaoStockSeleccionados.clear();
        } else {
            return "/pages/stock/stock_gestao_stock?faces-redirect=true";
        }
        return "/pages/stock/stock_gestao_stock?faces-redirect=true";

    }

    public String novaVendaAdministracao() {

        if (produtosSeleccionados != null) {
            produtosSeleccionados.clear();
        } else {
            return "/pages/geral/vendas/factura_iniciar_venda?faces-redirect=true";
        }

        return "/pages/vendas/factura_iniciar_venda?faces-redirect=true";
    }

    public String novaVendaGeral() {

        if (produtosSeleccionados != null) {
            produtosSeleccionados.clear();
        } else {
            return "/pages/geral/vendas/factura_iniciar_venda?faces-redirect=true";
        }

        return "/pages/geral/vendas/factura_iniciar_venda?faces-redirect=true";
    }

   

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
