/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package artesacra.mb;

import artesacra.dao.ProdutoDAO;
import artesacra.dao.StockProdutoDAO;
import artesacra.dao.TipoProdutoDAO;
import artesacra.dbutil.DateUtil;
import artesacra.modelo.Produto;
import artesacra.modelo.StockProduto;
import artesacra.modelo.StockProdutoAcumulado;
import artesacra.modelo.TipoProduto;
import java.io.IOException;
import javax.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Named(value = "stockProdutoBean")
@SessionScoped
public class StockProdutoBean implements Serializable {

    private String designacaoProduto;

    private Date inicio, fim, dataActual;
    private JasperPrint jasperPrint;

    TipoProduto tipoProduto = new TipoProduto();
    StockProduto stockProduto = new StockProduto();
    Produto produto = new Produto();
    ProdutoDAO produtoDAO = new ProdutoDAO();
    StockProdutoDAO stockProdutoDAO = new StockProdutoDAO();
    List<Produto> listaProdutos = new ArrayList<>();
    List<StockProduto> listaStockProdutos = new ArrayList<>();
    List<StockProduto> listaEntredadasStockProdutos = new ArrayList<>();
    List<StockProdutoAcumulado> listaEntredatasStockAcumuladoProdutos = new ArrayList<>();
    List<Produto> pesquisaProdutos = new ArrayList<>();
    List<StockProduto> pesquisaStockProdutos = new ArrayList<>();
    List<StockProduto> produtosSeleccionados;
    List<StockProduto> produtosEntradaStockSeleccionados = new ArrayList<>();
    TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();

    @PostConstruct
    public void inicializar() {

        listaProdutos = produtoDAO.findAll();
        produtosSeleccionados = new ArrayList<>();
        listaStockProdutos = stockProdutoDAO.findAll();
        stockProduto.setDataCompra(DateUtil.getDataActual());
//        stockProduto.setPrecentagemPrecoVenda(0.0);
        stockProduto.setDataRegisto(DateUtil.getDataActual());
        stockProduto.setLocalizacao("prateleira");
        stockProduto.setQuantidade(1);
        stockProduto.setPrecoCompra(0.0);
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

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public List<StockProduto> getListaEntredadasStockProdutos() {
        return listaEntredadasStockProdutos;
    }

    public void setListaEntredadasStockProdutos(List<StockProduto> listaEntredadasStockProdutos) {
        this.listaEntredadasStockProdutos = listaEntredadasStockProdutos;
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

    public StockProduto getStockProduto() {
        return stockProduto;
    }

    public void setStockProduto(StockProduto stockProduto) {
        this.stockProduto = stockProduto;
    }

    public List<StockProduto> getListaStockProdutos() {
        return listaStockProdutos;
    }

    public void setListaStockProdutos(List<StockProduto> listaStockProdutos) {
        this.listaStockProdutos = listaStockProdutos;
    }

    public List<StockProduto> getPesquisaStockProdutos() {
        return pesquisaStockProdutos;
    }

    public void setPesquisaStockProdutos(List<StockProduto> pesquisaStockProdutos) {
        this.pesquisaStockProdutos = pesquisaStockProdutos;
    }

    public List<StockProduto> getProdutosSeleccionados() {
        return produtosSeleccionados;
    }

    public void setProdutosSeleccionados(List<StockProduto> produtosSeleccionados) {
        this.produtosSeleccionados = produtosSeleccionados;
    }

    public List<StockProdutoAcumulado> getListaEntredatasStockAcumuladoProdutos() {
        return listaEntredatasStockAcumuladoProdutos;
    }

    public void setListaEntredatasStockAcumuladoProdutos(List<StockProdutoAcumulado> listaEntredatasStockAcumuladoProdutos) {
        this.listaEntredatasStockAcumuladoProdutos = listaEntredatasStockAcumuladoProdutos;
    }

    public List<SelectItem> getSelectItemTipoProdutos() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (TipoProduto tipoProduto : tipoProdutoDAO.findAll()) {
            list.add(new SelectItem(tipoProduto, tipoProduto.getDescricaoTipoProduto()));
        }

        return list;
    }

    public String save() {
        if (stockProdutoDAO.save(stockProduto)) {

            // Double precoVenda = (stockProduto.getPrecoCompra() + (stockProduto.getPrecoCompra()*stockProduto.getPrecentagemPrecoVenda()/100));
            //  System.out.println("Preco Venda >>>>>>>>>>>>"+precoVenda);
            produtoDAO.updateAumentarQuantidade(stockProduto.getProduto(), stockProduto.getQuantidade());
            stockProduto = new StockProduto();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardart", "tSucesso ao guardar os dados"));
            return "/pages/stock/stock_novo?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardart", "tErro ao guardar os dados"));
            return null;
        }
    }

    public String eliminar() {
        stockProdutoDAO.delete(stockProduto);
        stockProduto = new StockProduto();
        return null;
    }

    public String prepararEditar() {
        return "medicamento-editar";
    }

    public String cancelarStock() {

        return "/pages/stock/stock_gestao_stock?faces-redirect=true";

    }

    public String editar() {
        stockProdutoDAO.update(stockProduto);
        stockProduto = new StockProduto();
        return "medicamento-lista?faces-redirect=true";
    }

    public void carregarProdutosPorTipoGestaoStock() {
        produtosEntradaStockSeleccionados = stockProdutoDAO.findByTipo(tipoProduto);
    }

    public void carregarStockPorProduto(Produto produto) {
        stockProduto = stockProdutoDAO.findByIdProduto(produto);
    }

    public void executarProcuraProdutoPorNome(ActionEvent event) {

        procuraProdutoPorDesignacao();

    }

    public void executarBuscaStockEntreDatas(ActionEvent event) {

        listaEntredadasStockProdutos = stockProdutoDAO.findStockProdutoPorIntervaloData(inicio, fim);
    }

    public void buscaStockAcumuladoEntreDatas(ActionEvent event) {

        listaEntredatasStockAcumuladoProdutos = stockProdutoDAO.findStockAcumuladoEntreDatas(inicio, fim);
    }

    public String goToRegistarStock(Produto produto) {
        Produto produtoEncotrado = new Produto();
        produtoEncotrado = produtoDAO.findById(produto);
        stockProduto.setProduto(produto);
        return "stock_novo?faces-redirect=true";
    }

    public List<Produto> procuraProdutoPorDesignacao() {

        System.out.println("valor passado>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + designacaoProduto);
        if (!designacaoProduto.isEmpty() && designacaoProduto.indexOf(' ') == -1) {
            if (isNumeric(designacaoProduto)) {
                String codigo = String.valueOf(designacaoProduto);
                pesquisaProdutos = produtoDAO.findByCodigo(codigo);
                if (pesquisaProdutos != null) {
                    return pesquisaProdutos;
                }
            } else if (designacaoProduto instanceof String) {
                pesquisaProdutos = produtoDAO.findByNomes(designacaoProduto);
                return pesquisaProdutos;

            }

        }
        return null;
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

    public void imprimePDFByStockAcumladoEntreDatas(ActionEvent event) {

        Map parameters = new HashMap();
        parameters.put("inicio", inicio);
        parameters.put("fim", fim);

        listaEntredatasStockAcumuladoProdutos = stockProdutoDAO.findStockAcumuladoEntreDatas(inicio, fim);

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaEntredatasStockAcumuladoProdutos);

            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/relatorios/stock_acumulado_entre_datas.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            // httpServletResponse.setContentType("application/pdf");
            // httpServletResponse.addHeader("Content-disposition", "attachment; filename=diario_vendas_casecome_entre_datas.pdf");

            try ( ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                //JasperPrintManager.printPage(jasperPrint, 0, false);
                System.err.println("Repors >>>Fraternitas-Stock acumulado");
                servletOutputStream.flush();
            }
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        listaEntredatasStockAcumuladoProdutos = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();
    }

}
