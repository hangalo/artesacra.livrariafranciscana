package artesacra.mb;

import artesacra.dao.FacturaVendaProdutoDAO;
import artesacra.dao.FacturaVendaProdutoDetalhesDAO;
import artesacra.dao.ProdutoDAO;
import artesacra.modelo.Cliente;
import artesacra.modelo.FacturaVendaProduto;
import artesacra.modelo.FacturaVendaProdutoDetalhes;
import artesacra.modelo.Produto;
import artesacra.modelo.Profissional;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Named(value = "facturaVendaProdutoDetalhesBean")
@SessionScoped
public class FacturaVendaProdutoDetalhesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private FacturaVendaProdutoDAO facturaDAO;

    private FacturaVendaProdutoDetalhesDAO itemDAO;
    private ProdutoDAO produtoDAO;
    private int contador = 0;
    @Inject
    FacturaVendaProdutoBean facturaMBean;

    @Inject
    ProdutoMBean produtoMBean;

    private List<FacturaVendaProdutoDetalhes> carrinho = new ArrayList<>();
    private List<Cliente> clientes;

    private Cliente cliente;
    private FacturaVendaProduto factura;
    private Profissional profissional;
    int numeroDeItens = 0;

    private float total;

    @PostConstruct
    public void inti() {
        facturaDAO = new FacturaVendaProdutoDAO();
        itemDAO = new FacturaVendaProdutoDetalhesDAO();
        produtoDAO = new ProdutoDAO();
        cliente = new Cliente();
        factura = new FacturaVendaProduto();

        clientes = new ArrayList<>();

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public long numeroItens() {
        long s = 0;
        for (FacturaVendaProdutoDetalhes it : this.carrinho) {
            s += it.getQuantidade();
        }
        return s;
    }

    public synchronized int getNumeroDeItens() {
        numeroDeItens = 0;
        for (FacturaVendaProdutoDetalhes item : carrinho) {
            numeroDeItens += item.getQuantidade();
        }
        return numeroDeItens;
    }

    public void delete(FacturaVendaProdutoDetalhes it) {
        if (this.carrinho != null && !this.carrinho.isEmpty()) {
            if (it != null) {
                this.carrinho.remove(it);
            }
        }

    }

    public String addicionarProdutoCarrinho(Produto p) {
        Produto produtoEncontrado = produtoDAO.findById(p);

        int index = isExisting(p);
        if (index == -1) {
            this.carrinho.add(new FacturaVendaProdutoDetalhes(p, p.getPrecoVenda(), 1));

        } else {
            int quantidade = this.carrinho.get(index).getQuantidade() + 1;
            if (produtoEncontrado.getQuantidadeStock() >= quantidade) {
                this.carrinho.get(index).setQuantidade(quantidade);

            } else {
                quantidade = quantidade - 1;
                addMessage(FacesMessage.SEVERITY_WARN, "Sem stock" + quantidade + "unidades adicionadas", "Sem stok");
            }

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

    public int getQuantidateArtigos(Produto produto) {
        int quantidadeActual = 0;

        if (produto == null) {
             
            return quantidadeActual;
        }
         List<FacturaVendaProdutoDetalhes> results = getItems();
        for (FacturaVendaProdutoDetalhes item : results) {
                 
            if ((item.getProduto().getIdProduto() == produto.getIdProduto())) {
                quantidadeActual = item.getQuantidade();

                break;
            }
        }

        return quantidadeActual;
    }

    
    public synchronized List<FacturaVendaProdutoDetalhes> getItems() {
        List<FacturaVendaProdutoDetalhes> results = new ArrayList<>();
        results.addAll(carrinho);
        return results;
    }
    public void registarCompra() {
        // guarda a factura -> busca a ultima factura
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

//False because we do not want it to create a new session if it does not exist.
        if (session != null) {
            profissional = (Profissional) session.getAttribute("usuario");
            System.out.println(">>>>>>>>>>>>>>>>>>>Usuário actual A guardar" + profissional);

        }

        double totalFactura = soma();
        // define o total da factura actual
        facturaMBean.getFactura().setValorTotal(totalFactura);
        facturaMBean.getFactura().setProfissional(profissional);

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

    public String cancelarVendaAdministracao() {
        carrinho.clear();
        if (produtoMBean.produtosSeleccionados != null) {
            produtoMBean.produtosSeleccionados.clear();
        } else {
            return "/pages/vendas/factura_iniciar_venda?faces-redirect=true";
        }

        return "/pages/vendas/factura_iniciar_venda?faces-redirect=true";
    }

    public String cancelarVendaGeral() {
        carrinho.clear();
        if (produtoMBean.produtosSeleccionados != null) {
            produtoMBean.produtosSeleccionados.clear();
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
