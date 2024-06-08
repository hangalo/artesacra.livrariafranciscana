/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.modelo;

import java.io.Serializable;
import java.util.Objects;


public class FacturaVendaProdutoDetalhes implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer idFacturaVendaProdutoDetalhes;  
    private FacturaVendaProduto facturaVendaProduto; 
    private Produto produto;
    private Integer quantidade;
    private Double precoVenda;
    private Double desconto;
   

    public FacturaVendaProdutoDetalhes() {
    }

    public FacturaVendaProdutoDetalhes(Integer idFacturaVendaProdutoDetalhes, Integer quantidade, Double precoVenda, FacturaVendaProduto facturaVendaProduto, Produto produto) {
        this.idFacturaVendaProdutoDetalhes = idFacturaVendaProdutoDetalhes;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
        this.facturaVendaProduto = facturaVendaProduto;
        this.produto = produto;
    }
  public FacturaVendaProdutoDetalhes(Produto p, Double precoProduto, int quantidade) {
      this.produto=p;
      this.precoVenda=precoProduto;
      this.quantidade = quantidade;
    }

    public Integer getIdFacturaVendaProdutoDetalhes() {
        return idFacturaVendaProdutoDetalhes;
    }

    public void setIdFacturaVendaProdutoDetalhes(Integer idFacturaVendaProdutoDetalhes) {
        this.idFacturaVendaProdutoDetalhes = idFacturaVendaProdutoDetalhes;
    }
    
    
    
  

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public FacturaVendaProduto getFacturaVendaProduto() {
        return facturaVendaProduto;
    }

    public void setFacturaVendaProduto(FacturaVendaProduto facturaVendaProduto) {
        this.facturaVendaProduto = facturaVendaProduto;
    }

  
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    
     public void incrementQuantidade() {
        quantidade++;
    }

    public void decrementaQuantidade() {
        quantidade--;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.idFacturaVendaProdutoDetalhes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FacturaVendaProdutoDetalhes other = (FacturaVendaProdutoDetalhes) obj;
        return Objects.equals(this.idFacturaVendaProdutoDetalhes, other.idFacturaVendaProdutoDetalhes);
    }

    
    
    

   
    
    
}
