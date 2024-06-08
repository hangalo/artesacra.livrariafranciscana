
package artesacra.relatorios;

import artesacra.dao.FacturaVendaProdutoDetalhesDAO;
import artesacra.dao.ProdutoDAO;
import artesacra.modelo.FacturaVendaProdutoDetalhes;
import artesacra.modelo.Produto;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Named("reportsBean")
@ViewScoped
public class ReportsBean implements Serializable {

    private List<Produto> produtos;
    private List<FacturaVendaProdutoDetalhes> vendasEntreDatas;
   
   FacturaVendaProdutoDetalhesDAO facturaVendaProdutoDetalhesDAO = new FacturaVendaProdutoDetalhesDAO();
   

    private ProdutoDAO produtoDAO = new ProdutoDAO();
    JasperPrint jasperPrint;
    
    
    
     public void imprimirPDFTotalPorVender() {

        produtos = new ArrayList<>();

        produtos = produtoDAO.findAll();

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(produtos);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/relatorios/produtos_valor_em_stock_precario.jasper");

            //    String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/rapporti/frate.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            //httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");
             httpServletResponse.setContentType("application/pdf");            
         //   httpServletResponse.addHeader("Content-disposition", "attachment; filename=diario_vendas.pdf");
            
            try (ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                //   JasperPrintManager.printPage(jasperPrint, 0, false); //manda para a impressora
                
                servletOutputStream.flush();
            }
            //   JasperPrintManager.printPage(jasperPrint, 0, false); //manda para a impressora
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        produtos = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();

    }

     public void imprimirPDFprecario() {

        produtos = new ArrayList<>();

        produtos = produtoDAO.findAll();

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(produtos);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/relatorios/produtos_precario.jasper");

            //    String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/rapporti/frate.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            //httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");
             httpServletResponse.setContentType("application/pdf");            
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=diario_vendas.pdf");
            
            try (ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                //   JasperPrintManager.printPage(jasperPrint, 0, false); //manda para a impressora
                
                servletOutputStream.flush();
            }
            //   JasperPrintManager.printPage(jasperPrint, 0, false); //manda para a impressora
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        produtos = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();

    }

   
/*
    public void stampaFratePDFById(int parametro) {

        frates = new ArrayList<>();

        frates = frateDAO.findByID(parametro);

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(frates);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/reports/frate_scheda.jasper");

            //    String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/rapporti/frate.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            //httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");

            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            //   JasperPrintManager.printPage(jasperPrint, 0, false); //manda para a impressora

            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        frates = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void stampaFratePDFById2(int paramentro) {

        frates = frateDAO.findByID(paramentro);

        try {
            System.err.println("Repors >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Passou 01");
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(frates);

            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/reports/frate.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            // httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            //JasperPrintManager.printPage(jasperPrint, 0, false);
            System.err.println("Repors >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Passou 02");
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        frates = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void stampaFratePDFByCustodiaDelegazione(int parametro) {

        frates = new ArrayList<>();

        frates = frateDAO.findFratiByDomicilioCustodieDelegazioni(parametro);

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(frates);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/reports/ellenco_frati_per_custodie_delegazioni.jasper");

            //    String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/rapporti/frate.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            //httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");

            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            //    JasperPrintManager.printPage(jasperPrint, 0, false); //manda para a impressora

            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        frates = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void stampaFratePDFByCircoscrizione(int parametro) {

        frates = new ArrayList<>();

        frates = frateDAO.findByCircoscrizioni(parametro);

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(frates);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/reports/ellenco_frati_per_circoscrizione.jasper");

            //    String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/rapporti/frate.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            //httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");

            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            //    JasperPrintManager.printPage(jasperPrint, 0, false); //manda para a impressora

            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        frates = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void stampaFratePDFByCircoscrizioneAnno(Integer anno, Integer idCircoscrizione) {

        frates = new ArrayList<>();

        frates = frateDAO.findFratiByaCircoscrioneAnnoConDomicilio(anno, idCircoscrizione);

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(frates);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/reports/ellenco_frati_per_circoscrizione_anno.jasper");

            //    String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/rapporti/frate.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            //httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");

            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            //    JasperPrintManager.printPage(jasperPrint, 0, false); //manda para a impressora

            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        frates = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void stampaFratePDFByDomicilioAnno(Integer anno, Integer idCircoscrizione) {

        frates = new ArrayList<>();

        frates = frateDAO.findFratiByDomicilioAnnoConDomicilio(anno, idCircoscrizione);

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(frates);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/reports/ellenco_frati_per_domicilio_anno.jasper");

            //    String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/rapporti/frate.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            //httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");

            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            //    JasperPrintManager.printPage(jasperPrint, 0, false); //manda para a impressora

            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        frates = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void stampaCircoscrizionePDF(ActionEvent event) {

        circoscriziones = new ArrayList<>();

        circoscriziones = circoscrizioneDAO.findAllExistis();

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(circoscriziones);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/reports/lista_circoscrizione_atuali.jasper");

            //    String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/rapporti/frate.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            //httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");

            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            //    JasperPrintManager.printPage(jasperPrint, 0, false); //manda para a impressora

            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        frates = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void stampaProvinciePDF(ActionEvent event) {

        circoscriziones = new ArrayList<>();

        circoscriziones = circoscrizioneDAO.findProvincie();

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(circoscriziones);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/reports/lista_circoscrizione_atuali.jasper");

            //    String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/rapporti/frate.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            //httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");

            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            //    JasperPrintManager.printPage(jasperPrint, 0, false); //manda para a impressora

            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        frates = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void stampaConferenzePDF(ActionEvent event) {

        conferenzas = new ArrayList<>();

        conferenzas = conferenzaDAO.findAllExistis();

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(conferenzas);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/reports/lista_conferenza_atuali.jasper");

            //    String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/rapporti/frate.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            //httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");

            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            //    JasperPrintManager.printPage(jasperPrint, 0, false); //manda para a impressora

            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        frates = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();

    }

    /*Assenza Reports*/
  //  public void stampaFratiAssentiPDFByTipoAssenza(int parametro) {

     //   assenzaFraternitas = new ArrayList<>();

     //   assenzaFraternitas = assenzaFraternitaDAO.findByTipoAssenza(parametro);

      //  try {

        //    JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(assenzaFraternitas);
       //     String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/reports/ellenco_frati_assenti_per_tipo_assenza.jasper");

            //    String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/rapporti/frate.jasper");
        //    jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);

        //    HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            //httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");

         //   ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
           // JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            //    JasperPrintManager.printPage(jasperPrint, 0, false); //manda para a impressora

          //  servletOutputStream.flush();
            //servletOutputStream.close();
        //} catch (IOException | JRException ioe) {

          //  ioe.printStackTrace();
        //}
     //   frates = null;
    //    FacesContext.getCurrentInstance().responseComplete();
    //    FacesContext.getCurrentInstance().responseComplete();

  //  }*/

}
