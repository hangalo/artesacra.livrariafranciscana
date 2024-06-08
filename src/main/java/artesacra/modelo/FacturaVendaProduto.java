/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author informatica
 */
public class FacturaVendaProduto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idFactura;
    private Date dataFactura;
    private Cliente cliente;
    private Double valorTotal;
    private Date dataHoraRegisto;
    private FormaPagamento formaPagamento;
    private Profissional profissional;

    public FacturaVendaProduto() {
    }

    public FacturaVendaProduto(Integer idFactura, Date dataFactura, Cliente cliente, Double valorTotal) {
        this.idFactura = idFactura;
        this.dataFactura = dataFactura;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Date getDataFactura() {
        return dataFactura;
    }

    public void setDataFactura(Date dataFactura) {
        this.dataFactura = dataFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Date getDataHoraRegisto() {
        return dataHoraRegisto;
    }

    public void setDataHoraRegisto(Date dataHoraRegisto) {
        this.dataHoraRegisto = dataHoraRegisto;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.idFactura);
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
        final FacturaVendaProduto other = (FacturaVendaProduto) obj;
        if (!Objects.equals(this.idFactura, other.idFactura)) {
            return false;
        }
        return true;
    }
    
    

}
