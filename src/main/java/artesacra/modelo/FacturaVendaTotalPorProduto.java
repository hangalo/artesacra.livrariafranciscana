/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package artesacra.modelo;

/**
 *
 * @author informatica
 */
public class FacturaVendaTotalPorProduto {
    private Produto produto;
    private FacturaVendaProduto facturaVendaProduto;
    private FormaPagamento formaPagamento;
    private Integer quantidadeVendida;
    private Double totalVendas;

    public FacturaVendaTotalPorProduto() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public FacturaVendaProduto getFacturaVendaProduto() {
        return facturaVendaProduto;
    }

    public void setFacturaVendaProduto(FacturaVendaProduto facturaVendaProduto) {
        this.facturaVendaProduto = facturaVendaProduto;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    
    
    public Integer getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(Integer quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public Double getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(Double totalVendas) {
        this.totalVendas = totalVendas;
    }
    
    
}
