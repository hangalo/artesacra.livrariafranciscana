
package artesacra.mb;

import artesacra.dao.FacturaVendaProdutoDAO;
import artesacra.dao.FacturaVendaProdutoDetalhesDAO;
import artesacra.dao.ProdutoDAO;
import artesacra.dbutil.DateUtil;
import artesacra.modelo.Cliente;
import artesacra.modelo.FacturaVendaProduto;
import artesacra.modelo.FacturaVendaProdutoDetalhes;
import artesacra.modelo.FacturaVendaTotalPorProduto;
import artesacra.modelo.FormaPagamento;
import artesacra.modelo.Produto;
import artesacra.modelo.TipoProduto;
import java.io.IOException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Named(value = "facturaVendaProdutoDetalhesAuxBean")
@ViewScoped
public class FacturaVendaProdutoDetalhesAuxBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private FacturaVendaProdutoDAO facturaDAO;
    private ProdutoDAO produtoDAO;
    private FacturaVendaProdutoDetalhesDAO itemDAO;

    @Inject
    FacturaVendaProdutoBean facturaMBean;

    private List<FacturaVendaProdutoDetalhes> carrinho = new ArrayList<>();
    private List<FacturaVendaProdutoDetalhes> vendasRealizadas;
    private List<FacturaVendaProdutoDetalhes> vendasDoDiaRealizadas = new ArrayList<>();

    private List<FacturaVendaProdutoDetalhes> vendasRealizadasPorTipoEntreDatas = new ArrayList<>();
    private List<FacturaVendaProdutoDetalhes> vendasRealizadasPorFormaPagamentoEntreDatas = new ArrayList<>();
    private List<FacturaVendaProdutoDetalhes> vendasRealizadasPorProdutoEntreDatas = new ArrayList<>();
    private List<FacturaVendaProdutoDetalhes> vendasRealizadasEntreDatas;
   
    //Estatistica
    private List<FacturaVendaTotalPorProduto> vendasTotaisRealizadasPorProdutoEntreDatas = new ArrayList<>();
    private List<FacturaVendaTotalPorProduto> vendasTotaisDia = new ArrayList<>();

    private List<Cliente> clientes;
    private Date inicio, fim, dataActual;
    private JasperPrint jasperPrint;
    private FacturaVendaProdutoDetalhes facturaVendaProdutoDetalhes = new FacturaVendaProdutoDetalhes();
    private TipoProduto tipoProduto = new TipoProduto();
    private FormaPagamento formaPagamento = new FormaPagamento();
    private Produto produto = new Produto();
    private Cliente cliente;
    private FacturaVendaProduto factura;

    private float total;

    @PostConstruct
    public void inti() {
        facturaDAO = new FacturaVendaProdutoDAO();
        itemDAO = new FacturaVendaProdutoDetalhesDAO();
        produtoDAO = new ProdutoDAO();
        cliente = new Cliente();
        factura = new FacturaVendaProduto();

        clientes = new ArrayList<>();
        vendasRealizadas = new ArrayList<>();
        vendasRealizadas = itemDAO.findAll();
        vendasDoDiaRealizadas = itemDAO.findVendasDoDia(DateUtil.getDataActual());
        vendasTotaisDia = itemDAO.totalVendasDia(DateUtil.getDataActual());

        this.setDataActual(DateUtil.getDataActual());
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public FacturaVendaProdutoDetalhes getFacturaVendaProdutoDetalhes() {
        return facturaVendaProdutoDetalhes;
    }

    public void setFacturaVendaProdutoDetalhes(FacturaVendaProdutoDetalhes facturaVendaProdutoDetalhes) {
        this.facturaVendaProdutoDetalhes = facturaVendaProdutoDetalhes;
    }

    public List<FacturaVendaProdutoDetalhes> getVendasDoDiaRealizadas() {
        return vendasDoDiaRealizadas;
    }

    public List<FacturaVendaProdutoDetalhes> getVendasRealizadasPorTipoEntreDatas() {
        return vendasRealizadasPorTipoEntreDatas;
    }

    public void setVendasRealizadasPorTipoEntreDatas(List<FacturaVendaProdutoDetalhes> vendasRealizadasPorTipoEntreDatas) {
        this.vendasRealizadasPorTipoEntreDatas = vendasRealizadasPorTipoEntreDatas;
    }

    public void setVendasDoDiaRealizadas(List<FacturaVendaProdutoDetalhes> vendasDoDiaRealizadas) {
        this.vendasDoDiaRealizadas = vendasDoDiaRealizadas;
    }

    public List<FacturaVendaTotalPorProduto> getVendasTotaisDia() {
        return vendasTotaisDia;
    }

    public void setVendasTotaisDia(List<FacturaVendaTotalPorProduto> vendasTotaisDia) {
        this.vendasTotaisDia = vendasTotaisDia;
    }

    public Date getDataActual() {
        return dataActual;
    }

    public void setDataActual(Date dataActual) {
        this.dataActual = dataActual;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    
    
    
    private int isExisting(Produto p) {
        for (int i = 0; i < this.carrinho.size(); i++) {
            if (this.carrinho.get(i).getProduto().getIdProduto() == p.getIdProduto()) {
                return i;
            }
        }
        return -1;

    }

    public long soma() {
        long s = 0;
        for (FacturaVendaProdutoDetalhes it : this.carrinho) {
            s += it.getQuantidade() * it.getProduto().getPrecoVenda();
        }
        return s;
    }

    public long soma(long valor) {
        long s = 0;
        for (FacturaVendaProdutoDetalhes it : this.carrinho) {
            s += valor + it.getQuantidade() * it.getProduto().getPrecoVenda();
        }
        /* for (Student student : studentList) {
           s += valor+student.getRank();
        }*/
        return s;
    }

    public void delete(FacturaVendaProdutoDetalhes it) {
        System.out.println("Delelte >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if (this.carrinho != null && !this.carrinho.isEmpty()) {
            if (it != null) {
                this.carrinho.remove(it);
            }
        }

    }

    public String addicionarProdutoCarrinho(Produto p) {
        int index = isExisting(p);
        if (index == -1) {
            this.carrinho.add(new FacturaVendaProdutoDetalhes(p, p.getPrecoVenda(), 1));
        } else {
            int quantidade = this.carrinho.get(index).getQuantidade() + 1;
            this.carrinho.get(index).setQuantidade(quantidade);
        }
        // return "carrinho?face-redirect=true";
        return null;
    }

    public List<FacturaVendaProdutoDetalhes> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<FacturaVendaProdutoDetalhes> carrinho) {
        this.carrinho = carrinho;
    }

    public List<FacturaVendaProdutoDetalhes> getVendasRealizadas() {
        return vendasRealizadas;
    }

    public void setVendasRealizadas(List<FacturaVendaProdutoDetalhes> vendasRealizadas) {
        this.vendasRealizadas = vendasRealizadas;
    }

    public FacturaVendaProdutoBean getFacturaMBean() {
        return facturaMBean;
    }

    public void setFacturaMBean(FacturaVendaProdutoBean facturaMBean) {
        this.facturaMBean = facturaMBean;
    }

    public List<FacturaVendaProdutoDetalhes> getVendasRealizadasEntreDatas() {
        return vendasRealizadasEntreDatas;
    }

    public void setVendasRealizadasEntreDatas(List<FacturaVendaProdutoDetalhes> vendasRealizadasEntreDatas) {
        this.vendasRealizadasEntreDatas = vendasRealizadasEntreDatas;
    }

    public List<FacturaVendaTotalPorProduto> getVendasTotaisRealizadasPorProdutoEntreDatas() {
        return vendasTotaisRealizadasPorProdutoEntreDatas;
    }

    public void setVendasTotaisRealizadasPorProdutoEntreDatas(List<FacturaVendaTotalPorProduto> vendasTotaisRealizadasPorProdutoEntreDatas) {
        this.vendasTotaisRealizadasPorProdutoEntreDatas = vendasTotaisRealizadasPorProdutoEntreDatas;
    }

    public List<FacturaVendaProdutoDetalhes> getVendasRealizadasPorFormaPagamentoEntreDatas() {
        return vendasRealizadasPorFormaPagamentoEntreDatas;
    }

    public void setVendasRealizadasPorFormaPagamentoEntreDatas(List<FacturaVendaProdutoDetalhes> vendasRealizadasPorFormaPagamentoEntreDatas) {
        this.vendasRealizadasPorFormaPagamentoEntreDatas = vendasRealizadasPorFormaPagamentoEntreDatas;
    }

    public List<FacturaVendaProdutoDetalhes> getVendasRealizadasPorProdutoEntreDatas() {
        return vendasRealizadasPorProdutoEntreDatas;
    }

    public void setVendasRealizadasPorProdutoEntreDatas(List<FacturaVendaProdutoDetalhes> vendasRealizadasPorProdutoEntreDatas) {
        this.vendasRealizadasPorProdutoEntreDatas = vendasRealizadasPorProdutoEntreDatas;
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

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    

    public void executarBuscaVendasEntreDatas(ActionEvent event) {

        vendasRealizadasEntreDatas = itemDAO.findAllPorIntervaloData(inicio, fim);
    }
    
    
      public void executarBuscaVendasEntreDatasEPeriodo(ActionEvent event) {

        vendasRealizadasEntreDatas = itemDAO.findAllPorIntervaloDataPeriodo(inicio, fim);
    }

    public void buscaVendasTotaisPorProdutoEntreDatas(ActionEvent event) {
        vendasTotaisRealizadasPorProdutoEntreDatas = itemDAO.totalVendasPorProdutoEntreDatas(inicio, fim);
    }

    public void executarBuscaVendasPorTipoEntreDatas(ActionEvent event) {

        vendasRealizadasPorTipoEntreDatas = itemDAO.findAllPorTipoIntervaloData(tipoProduto, inicio, fim);

    }
    
    public void executarBuscaVendasPorFormaPagamentoEntreDatas(ActionEvent event) {

        vendasRealizadasPorFormaPagamentoEntreDatas = itemDAO.findAllPorFormaPagamentoIntervaloData(formaPagamento, inicio, fim);

    }
    
    public void executarBuscaVendasPorProdutoEntreDatas(ActionEvent event) {

        vendasRealizadasPorProdutoEntreDatas = itemDAO.findAllPorProdutoIntervaloData(produto, inicio, fim);

    }

    public void executarBuscaVendasPorDia(ActionEvent event) {

        vendasTotaisDia = itemDAO.totalVendasDia(dataActual);
    }

    public void registarCompra() {
        // guarda a factura -> busca a ultima factura

        double totalFactura = soma();
        // define o total da factura actual
        facturaMBean.getFactura().setValorTotal(totalFactura);

        //chamada do metodo que regista a factura actual
        facturaMBean.registarFactura();

        //busca a ultima factura registada
        Integer numeroFatura = facturaDAO.buscaUltimaFactura();
        //define o nunmeo da factura actual
        factura.setIdFactura(numeroFatura);
        //percorre o carrinho e regista cada item
        for (FacturaVendaProdutoDetalhes item : carrinho) {
            item.setFacturaVendaProduto(factura);
            itemDAO.save(item);
            produtoDAO.updateDimunuirQuantidade(item.getProduto(), item.getQuantidade());
        }
        carrinho.clear();
    }

    public void imprimePDFByVendasEntreDatas(ActionEvent event) {

        vendasRealizadasEntreDatas = itemDAO.findAllPorIntervaloData(inicio, fim);
        Map parameters = new HashMap();
        parameters.put("inicio", inicio);
        parameters.put("fim", fim);
        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(vendasRealizadasEntreDatas);

            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/relatorios/diario_detalhado_fraternitas_grupo.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
           // httpServletResponse.setContentType("application/pdf");
          ///  httpServletResponse.addHeader("Content-disposition", "attachment; filename=diario_vendas_fraternitas.pdf");

            try ( ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                //JasperPrintManager.printPage(jasperPrint, 0, false);

                servletOutputStream.flush();
            }
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        vendasRealizadasEntreDatas = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();
    }

     public void imprimePDFTotalVendasPorProdutoEntreDatas(ActionEvent event) {
     
       vendasTotaisRealizadasPorProdutoEntreDatas =itemDAO.totalVendasPorProdutoEntreDatas(inicio, fim);
        Map parameters = new HashMap();
        parameters.put("inicio",inicio);
          parameters.put("fim",fim);
      
        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(vendasTotaisRealizadasPorProdutoEntreDatas);

            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/relatorios/vendas_totais_do_dia_fraternitas.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
           // httpServletResponse.setContentType("application/pdf");
          //  httpServletResponse.addHeader("Content-disposition", "attachment; filename=vendas_entre_datas_fraternitas.pdf");

            try ( ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                //JasperPrintManager.printPage(jasperPrint, 0, false);
                System.err.println("Repors >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Passou 02");
                servletOutputStream.flush();
            }
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        vendasTotaisRealizadasPorProdutoEntreDatas = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();
     
     }
   
    public void imprimePDFByVendasPorTipoProdutoEntreDatas(ActionEvent event) {

        vendasRealizadasPorTipoEntreDatas = itemDAO.findAllPorTipoIntervaloData(tipoProduto, inicio, fim);
         Map parameters = new HashMap();
        parameters.put("inicio", inicio);
        parameters.put("fim", fim);
        parameters.put("tipoProduto",tipoProduto.getDescricaoTipoProduto());
        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(vendasRealizadasPorTipoEntreDatas);

            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/relatorios/diario_detalhado_grupo_tipo_de_produto.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath,parameters, beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
         //   httpServletResponse.setContentType("application/pdf");
           // httpServletResponse.addHeader("Content-disposition", "attachment; filename=diario_vendas_fraternitas.pdf");

            try ( ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                //JasperPrintManager.printPage(jasperPrint, 0, false);
                System.err.println("Repors >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Passou 02");
                servletOutputStream.flush();
            }
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        vendasRealizadasEntreDatas = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();
    }

     
    public void imprimePDFByVendasPorTpoEntreDatas(ActionEvent event) {

        vendasRealizadasPorTipoEntreDatas = itemDAO.findAllPorTipoIntervaloData(tipoProduto, inicio, fim);

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(vendasRealizadasPorTipoEntreDatas);

            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/relatorios/diario_fraternitas.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
         //   httpServletResponse.setContentType("application/pdf");
          //  httpServletResponse.addHeader("Content-disposition", "attachment; filename=diario_vendas_fraternitas.pdf");

            try ( ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                //JasperPrintManager.printPage(jasperPrint, 0, false);
                System.err.println("Repors >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Passou 02");
                servletOutputStream.flush();
            }
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        vendasRealizadasEntreDatas = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();
    }


    public void imprimePDFByVendasPorFormaPagamentoEntreDatas(ActionEvent event) {

        vendasRealizadasPorFormaPagamentoEntreDatas = itemDAO.findAllPorFormaPagamentoIntervaloData(formaPagamento, inicio, fim);
         Map parameters = new HashMap();
        parameters.put("inicio", inicio);
        parameters.put("fim", fim);
        parameters.put("formaPagamento",formaPagamento.getDescricaoFormaPagamento());
        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(vendasRealizadasPorFormaPagamentoEntreDatas);

            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/relatorios/diario_detalhado_fraternitas_grupo_forma_pagamento.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath,parameters, beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
         //   httpServletResponse.setContentType("application/pdf");
           // httpServletResponse.addHeader("Content-disposition", "attachment; filename=diario_vendas_fraternitas.pdf");

            try ( ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                //JasperPrintManager.printPage(jasperPrint, 0, false);
                System.err.println("Repors >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Passou 02");
                servletOutputStream.flush();
            }
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        vendasRealizadasEntreDatas = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void imprimePDFByVendasDoDia(ActionEvent event) {

        Map parameters = new HashMap();
        parameters.put("dia", DateUtil.getDataActual());

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(vendasTotaisDia);

            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/relatorios/vendas_totais_do_dia_fraternitas.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
          //  httpServletResponse.setContentType("application/pdf");
          //  httpServletResponse.addHeader("Content-disposition", "attachment; filename=vendas_totais_do_dia_fraternitas.pdf");

            try ( ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                // JasperPrintManager.printPage(jasperPrint, 0, false);

                servletOutputStream.flush();
            }
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        vendasRealizadasEntreDatas = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void imprimePDFByVendasPesquisaPorDia(ActionEvent event) {

        Map parameters = new HashMap();
        parameters.put("dia", dataActual);

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(vendasTotaisDia);

            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/relatorios/vendas_totais_do_dia_fraternitas.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
           // httpServletResponse.setContentType("application/pdf");
           // httpServletResponse.addHeader("Content-disposition", "attachment; filename=vendas_totais_do_dia_fraternitas.pdf");

            try ( ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                // JasperPrintManager.printPage(jasperPrint, 0, false);
                System.err.println("Repors >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Passou imprimePDFByVendasPesquisaPorDia");
                servletOutputStream.flush();
            }
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        vendasRealizadasEntreDatas = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();
    }
}
