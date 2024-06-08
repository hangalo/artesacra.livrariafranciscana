/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.Cliente;
import artesacra.modelo.FacturaVendaProduto;
import artesacra.modelo.FacturaVendaProdutoDetalhes;
import artesacra.modelo.FacturaVendaTotalPorProduto;
import artesacra.modelo.FormaPagamento;
import artesacra.modelo.Produto;
import artesacra.modelo.TipoProduto;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author informatica
 */
public class FacturaVendaProdutoDetalhesDAO {

    private static final String INSERT = "INSERT INTO factura_venda_produto_detalhes(id_produto,id_factura_venda,quantidade_vendida,preco_venda) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE factura_venda_detalhes SET id_produto = ?, quantidade_vendida = ?, desconto = ? WHERE id_detalhes_venda  = ?";
    private static final String DELETE = "DELETE FROM factura_venda_detalhes WHERE id_detalhes_venda =?";
    private static final String SELECT_ALL = "SELECT id_detalhes_venda, cl.numero_contribuinte, cl.nome_cliente, cl.sobrenome_cliente, pd.nome_produto, fv.id_factura_venda, fv.data_factura_venda, fv.data_hora_registo, fp.descricao_forma_de_pagamento, quantidade_vendida, preco_venda, desconto FROM factura_venda_produto_detalhes fvd INNER JOIN produto pd ON fvd.id_produto = pd.id_produto  INNER JOIN factura_venda_produto fv ON fvd.id_factura_venda = fv.id_factura_venda  INNER JOIN forma_de_pagamento fp ON fv.id_forma_de_pagamento =fp.id_forma_de_pagamento  INNER JOIN cliente cl ON fv.id_cliente = cl.id_cliente ORDER BY fv.id_factura_venda";
    private static final String SELECT_ALL_VENDAS_DO_DIA = "SELECT id_detalhes_venda, cl.numero_contribuinte, cl.nome_cliente, cl.sobrenome_cliente, pd.nome_produto, fv.id_factura_venda, fv.data_factura_venda,fv.data_hora_registo, fp.descricao_forma_de_pagamento, quantidade_vendida, preco_venda, desconto FROM factura_venda_produto_detalhes fvd INNER JOIN produto pd ON fvd.id_produto = pd.id_produto  INNER JOIN factura_venda_produto fv ON fvd.id_factura_venda = fv.id_factura_venda  INNER JOIN forma_de_pagamento fp ON fv.id_forma_de_pagamento =fp.id_forma_de_pagamento  INNER JOIN cliente cl ON fv.id_cliente = cl.id_cliente  WHERE fv.data_factura_venda =? ORDER BY fv.id_factura_venda";
    private static final String SELECT_ALL_ENTRE_DATAS = "SELECT id_detalhes_venda, cl.numero_contribuinte, cl.nome_cliente, cl.sobrenome_cliente, pd.nome_produto, fv.id_factura_venda, fv.data_factura_venda,fv.data_hora_registo, fp.descricao_forma_de_pagamento, quantidade_vendida, preco_venda, desconto FROM factura_venda_produto_detalhes fvd INNER JOIN produto pd ON fvd.id_produto = pd.id_produto  INNER JOIN factura_venda_produto fv ON fvd.id_factura_venda = fv.id_factura_venda  INNER JOIN forma_de_pagamento fp ON fv.id_forma_de_pagamento =fp.id_forma_de_pagamento  INNER JOIN cliente cl ON fv.id_cliente = cl.id_cliente  WHERE fv.data_factura_venda Between ? and ? ORDER BY fv.id_factura_venda";
    private static final String SELECT_ALL_ENTRE_DATAS_E_PERIODO = "SELECT id_detalhes_venda, cl.numero_contribuinte, cl.nome_cliente, cl.sobrenome_cliente, pd.nome_produto, tp.id_tipo_de_produto,tp.descricao_tipo_de_produto, fv.id_factura_venda, fv.data_factura_venda,fv.data_hora_registo, fp.descricao_forma_de_pagamento, quantidade_vendida, preco_venda, desconto FROM factura_venda_produto_detalhes fvd INNER JOIN produto pd ON fvd.id_produto = pd.id_produto INNER JOIN tipo_de_produto tp ON pd.id_tipo_de_produto=tp.id_tipo_de_produto INNER JOIN factura_venda_produto fv ON fvd.id_factura_venda = fv.id_factura_venda  INNER JOIN forma_de_pagamento fp ON fv.id_forma_de_pagamento =fp.id_forma_de_pagamento  INNER JOIN cliente cl ON fv.id_cliente = cl.id_cliente  WHERE fv.data_hora_registo Between ? and ? ORDER BY fv.id_factura_venda";
    private static final String SELECT_ALL_POR_TIPO_ENTRE_DATAS="SELECT id_detalhes_venda, cl.numero_contribuinte, cl.nome_cliente, cl.sobrenome_cliente, pd.nome_produto, tp.id_tipo_de_produto,tp.descricao_tipo_de_produto, fv.id_factura_venda, fv.data_factura_venda,fv.data_hora_registo, fp.descricao_forma_de_pagamento, quantidade_vendida, preco_venda, desconto FROM factura_venda_produto_detalhes fvd INNER JOIN produto pd ON fvd.id_produto = pd.id_produto INNER JOIN tipo_de_produto tp ON pd.id_tipo_de_produto=tp.id_tipo_de_produto INNER JOIN factura_venda_produto fv ON fvd.id_factura_venda = fv.id_factura_venda  INNER JOIN forma_de_pagamento fp ON fv.id_forma_de_pagamento =fp.id_forma_de_pagamento  INNER JOIN cliente cl ON fv.id_cliente = cl.id_cliente  WHERE tp.id_tipo_de_produto = ? AND fv.data_factura_venda Between ? and ? ORDER BY fv.id_factura_venda";
    private static final String SELECT_ALL_POR_FORMA_PAGAMENTO_ENTRE_DATAS="SELECT id_detalhes_venda, cl.numero_contribuinte, cl.nome_cliente, cl.sobrenome_cliente, pd.nome_produto, fv.id_factura_venda, fv.data_factura_venda,fv.data_hora_registo, fp.descricao_forma_de_pagamento, quantidade_vendida, preco_venda, desconto FROM factura_venda_produto_detalhes fvd INNER JOIN produto pd ON fvd.id_produto = pd.id_produto  INNER JOIN factura_venda_produto fv ON fvd.id_factura_venda = fv.id_factura_venda  INNER JOIN forma_de_pagamento fp ON fv.id_forma_de_pagamento =fp.id_forma_de_pagamento  INNER JOIN cliente cl ON fv.id_cliente = cl.id_cliente  WHERE fp.id_forma_de_pagamento = ? AND fv.data_factura_venda Between ? and ? ORDER BY fv.id_factura_venda";
   
    private static final String SELECT_ALL_POR_PRODUTO_ENTRE_DATAS="SELECT id_detalhes_venda, cl.numero_contribuinte, cl.nome_cliente, cl.sobrenome_cliente, pd.nome_produto, fv.id_factura_venda, fv.data_factura_venda,fv.data_hora_registo, fp.descricao_forma_de_pagamento, quantidade_vendida, preco_venda, desconto FROM factura_venda_produto_detalhes fvd INNER JOIN produto pd ON fvd.id_produto = pd.id_produto  INNER JOIN factura_venda_produto fv ON fvd.id_factura_venda = fv.id_factura_venda  INNER JOIN forma_de_pagamento fp ON fv.id_forma_de_pagamento =fp.id_forma_de_pagamento  INNER JOIN cliente cl ON fv.id_cliente = cl.id_cliente  WHERE pd.id_produto = ? AND fv.data_factura_venda Between ? and ? ORDER BY fv.id_factura_venda";
    private static final String SELECT_BY_ID_FACTURA = "SELECT id_detalhes_venda, cl.numero_contribuinte, cl.nome_cliente, cl.sobrenome_cliente, pd.nome_produto, fv.id_factura_venda, fv.data_factura_venda, fv.data_hora_registo, fp.descricao_forma_de_pagamento, quantidade_vendida, preco_venda, desconto FROM factura_venda_produto_detalhes fvd INNER JOIN produto pd ON fvd.id_produto = pd.id_produto  INNER JOIN factura_venda_produto fv ON fvd.id_factura_venda = fv.id_factura_venda  INNER JOIN forma_de_pagamento fp ON fv.id_forma_de_pagamento =fp.id_forma_de_pagamento  INNER JOIN cliente cl ON fv.id_cliente = cl.id_cliente WHERE fv.id_factura_venda =?";
   
    private static final String SELECT_TOTAL_VENDAS_POR_PRODUTO ="SELECT pd.nome_produto, SUM(quantidade_vendida), SUM(quantidade_vendida*preco_venda) FROM factura_venda_produto_detalhes fvd  INNER JOIN produto pd ON fvd.id_produto = pd.id_produto INNER JOIN factura_venda_produto fv ON fvd.id_factura_venda = fv.id_factura_venda INNER JOIN forma_de_pagamento fp ON fv.id_forma_de_pagamento =fp.id_forma_de_pagamento INNER JOIN cliente cl ON fv.id_cliente = cl.id_cliente WHERE fv.data_factura_venda Between ? and ? GROUP BY pd.nome_produto ORDER BY  pd.nome_produto";
    private static final String SELECT_TOTAL_VENDAS_DO_DIA ="SELECT pd.nome_produto, SUM(quantidade_vendida), SUM(quantidade_vendida*preco_venda) FROM factura_venda_produto_detalhes fvd  INNER JOIN produto pd ON fvd.id_produto = pd.id_produto INNER JOIN factura_venda_produto fv ON fvd.id_factura_venda = fv.id_factura_venda INNER JOIN forma_de_pagamento fp ON fv.id_forma_de_pagamento =fp.id_forma_de_pagamento INNER JOIN cliente cl ON fv.id_cliente = cl.id_cliente WHERE fv.data_factura_venda = ? GROUP BY pd.nome_produto ORDER BY  pd.nome_produto";
    
    private static final String SELECT_TOTAL_VENDAS_POR_PRODUTO_E_DATA ="SELECT pd.nome_produto, fv.data_factura_venda, fp.descricao_forma_de_pagamento, SUM(quantidade_vendida), SUM(quantidade_vendida*preco_venda) FROM factura_venda_produto_detalhes fvd  INNER JOIN produto pd ON fvd.id_produto = pd.id_produto INNER JOIN factura_venda_produto fv ON fvd.id_factura_venda = fv.id_factura_venda INNER JOIN forma_de_pagamento fp ON fv.id_forma_de_pagamento =fp.id_forma_de_pagamento INNER JOIN cliente cl ON fv.id_cliente = cl.id_cliente WHERE fv.data_factura_venda Between ? and ? GROUP BY pd.nome_produto, fv.data_factura_venda, fp.descricao_forma_de_pagamento ORDER BY  pd.nome_produto";

    public boolean save(FacturaVendaProdutoDetalhes detalhe) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        if (detalhe == null) {
            System.err.println("FacturaVendaProdutoDetalhesDAO:save: O valor oassado nao pode ser nulo!");
        }
        try {

            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, detalhe.getProduto().getIdProduto());
            ps.setInt(2, detalhe.getFacturaVendaProduto().getIdFactura());
            ps.setInt(3, detalhe.getQuantidade());
            ps.setDouble(4, detalhe.getPrecoVenda());
            // ps.setDouble(5, detalhe.getDesconto());
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("ItemDAO:save:Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }

            return flagControlo;

        } catch (SQLException e) {
            System.out.println("ItemDAO:save:Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean update(FacturaVendaProdutoDetalhes detalhe) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        if (detalhe == null) {
            System.err.println("FacturaVendaProdutoDetalhesDAO:save: O valor oassado nao pode ser nulo!");
        }
        try {

            /*
            id_produto = ?, quantidade_vendida = ?, desconto = ?
             */
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);

            ps.setInt(1, detalhe.getProduto().getIdProduto());
            ps.setInt(2, detalhe.getQuantidade());
            ps.setDouble(3, detalhe.getDesconto());
            ps.setInt(4, detalhe.getIdFacturaVendaProdutoDetalhes());
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("ItemDAO:save:Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }

            return flagControlo;

        } catch (SQLException e) {
            System.out.println("ItemDAO:save:Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean delete(FacturaVendaProdutoDetalhes detalhe) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flagControlo = false;
        if (detalhe == null) {
            System.err.println("O campo anterior nao pode ser nulo");
        }

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, detalhe.getIdFacturaVendaProdutoDetalhes());
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados eliminados com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }
            return flagControlo;

        } catch (SQLException e) {
            System.out.println("Erro ao eliminar dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public FacturaVendaProdutoDetalhes findByIdFactura(FacturaVendaProdutoDetalhes idFactura) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        FacturaVendaProdutoDetalhes detalhe = new FacturaVendaProdutoDetalhes();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID_FACTURA);
            ps.setInt(1, idFactura.getFacturaVendaProduto().getIdFactura());
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("ItemDAO:findById: nenhum registo com o id: " + idFactura);
            }
            popularComDados(detalhe, rs);
        } catch (SQLException ex) {
            System.err.println("ItemDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return detalhe;
    }

    public List<FacturaVendaProdutoDetalhes> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<FacturaVendaProdutoDetalhes> items = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                FacturaVendaProdutoDetalhes item = new FacturaVendaProdutoDetalhes();
                popularComDados(item, rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return items;
    }

      public List<FacturaVendaProdutoDetalhes> findVendasDoDia(Date data) {
          
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<FacturaVendaProdutoDetalhes> items = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL_VENDAS_DO_DIA);
            ps.setDate(1, new java.sql.Date(data.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                FacturaVendaProdutoDetalhes item = new FacturaVendaProdutoDetalhes();
                popularComDados(item, rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            System.err.println("FacturaVendaProdutoDealhesDAO: findVendasDoDia:Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return items;
    }
  
      public List<FacturaVendaProdutoDetalhes> findAllPorIntervaloData(Date inicio, Date fim) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<FacturaVendaProdutoDetalhes> items = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
             ps = conn.prepareStatement(SELECT_ALL_ENTRE_DATAS);
            ps.setDate(1, new java.sql.Date(inicio.getTime()));
            ps.setDate(2, new java.sql.Date(fim.getTime()));
           
            rs = ps.executeQuery();
            while (rs.next()) {
                FacturaVendaProdutoDetalhes item = new FacturaVendaProdutoDetalhes();
                popularComDados(item, rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            System.err.println(":Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return items;
    }
    
      
      public List<FacturaVendaProdutoDetalhes> findAllPorIntervaloDataPeriodo(Date inicio, Date fim) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<FacturaVendaProdutoDetalhes> items = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
             ps = conn.prepareStatement(SELECT_ALL_ENTRE_DATAS_E_PERIODO);
            // ps.setDate(1, new java.sql.Date(inicio.getTime()));
            //  ps.setDate(2, new java.sql.Date(fim.getTime()));

            java.sql.Timestamp timeInicio = null;
            java.sql.Timestamp timeFim = null;

            timeInicio = new Timestamp(BigDecimal.valueOf(inicio.getTime() / 1000d).setScale(0, RoundingMode.HALF_UP).longValue() * 1000);
            timeFim = new Timestamp(BigDecimal.valueOf(fim.getTime() / 1000d).setScale(0, RoundingMode.HALF_UP).longValue() * 1000);
              //To Round Half Up from millisecond (d for double) to second (long so no d) because MySQL do this.

            ps.setTimestamp(1, timeInicio);
            ps.setTimestamp(2, timeFim);
            rs = ps.executeQuery();
            while (rs.next()) {
                FacturaVendaProdutoDetalhes item = new FacturaVendaProdutoDetalhes();
                popularComDados(item, rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            System.err.println(":Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return items;
    }
    
      
        public List<FacturaVendaTotalPorProduto> totalVendasPorProdutoEntreDatas(Date inicio, Date fim) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<FacturaVendaTotalPorProduto> items = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_TOTAL_VENDAS_POR_PRODUTO);
            ps.setDate(1, new java.sql.Date(inicio.getTime()));
            ps.setDate(2, new java.sql.Date(fim.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                FacturaVendaTotalPorProduto item = new FacturaVendaTotalPorProduto();
                popularTotalVendasPorProduto(item, rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            System.err.println(":Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return items;
    }
    
        public List<FacturaVendaTotalPorProduto> totalVendasDia(Date data) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<FacturaVendaTotalPorProduto> items = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_TOTAL_VENDAS_DO_DIA);
            ps.setDate(1, new java.sql.Date(data.getTime()));
          
            rs = ps.executeQuery();
            while (rs.next()) {
                FacturaVendaTotalPorProduto item = new FacturaVendaTotalPorProduto();
                popularTotalVendasPorProduto(item, rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            System.err.println(":Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return items;
    }
    
    public List<FacturaVendaProdutoDetalhes> findAllPorTipoIntervaloData(TipoProduto tipo, Date inicio, Date fim) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<FacturaVendaProdutoDetalhes> items = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL_POR_TIPO_ENTRE_DATAS);
            ps.setInt(1, tipo.getIdTipoProduto());
            ps.setDate(2, new java.sql.Date(inicio.getTime()));
            ps.setDate(3, new java.sql.Date(fim.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                FacturaVendaProdutoDetalhes item = new FacturaVendaProdutoDetalhes();
                popularComDados(item, rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            System.err.println(":Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return items;
    }
    
    public List<FacturaVendaProdutoDetalhes> findAllPorFormaPagamentoIntervaloData(FormaPagamento fp, Date inicio, Date fim) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<FacturaVendaProdutoDetalhes> items = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL_POR_FORMA_PAGAMENTO_ENTRE_DATAS);
            ps.setInt(1, fp.getIdFormaPagamento());
            ps.setDate(2, new java.sql.Date(inicio.getTime()));
            ps.setDate(3, new java.sql.Date(fim.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                FacturaVendaProdutoDetalhes item = new FacturaVendaProdutoDetalhes();
                popularComDados(item, rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            System.err.println(":Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return items;
    }
    
    
    public List<FacturaVendaProdutoDetalhes> findAllPorProdutoIntervaloData(Produto p, Date inicio, Date fim) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<FacturaVendaProdutoDetalhes> items = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL_POR_PRODUTO_ENTRE_DATAS);
            ps.setInt(1, p.getIdProduto());
            ps.setDate(2, new java.sql.Date(inicio.getTime()));
            ps.setDate(3, new java.sql.Date(fim.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                FacturaVendaProdutoDetalhes item = new FacturaVendaProdutoDetalhes();
                popularComDados(item, rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            System.err.println(":Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return items;
    }
    
    public void popularComDados(FacturaVendaProdutoDetalhes detalhe, ResultSet rs) {
        try {

            detalhe.setIdFacturaVendaProdutoDetalhes(rs.getInt("id_detalhes_venda"));
            Produto produto = new Produto();
            produto.setNomeProduto(rs.getString("pd.nome_produto"));
        
            FacturaVendaProduto factura = new FacturaVendaProduto();
            factura.setIdFactura(rs.getInt("fv.id_factura_venda"));
            factura.setDataFactura(rs.getDate("fv.data_factura_venda"));
            factura.setDataHoraRegisto(rs.getTimestamp("fv.data_hora_registo"));
            Cliente cliente = new Cliente();
            cliente.setNomeCliente(rs.getString("cl.nome_cliente"));
            cliente.setSobrenomeCliente(rs.getString("cl.sobrenome_cliente"));
            cliente.setNumeroContribuinte(rs.getString("cl.numero_contribuinte"));

            FormaPagamento formaPagamento = new FormaPagamento();
            formaPagamento.setDescricaoFormaPagamento(rs.getString("fp.descricao_forma_de_pagamento"));

            factura.setCliente(cliente);
            factura.setFormaPagamento(formaPagamento);
            detalhe.setProduto(produto);
            detalhe.setFacturaVendaProduto(factura);
            detalhe.setQuantidade(rs.getInt("quantidade_vendida"));
            detalhe.setPrecoVenda(rs.getDouble("preco_venda"));
            detalhe.setDesconto(rs.getDouble("desconto"));

        } catch (SQLException ex) {
            System.err.println("Error on fill data Item: " + ex.getLocalizedMessage());
        }

    }
    
    public void popularTotalVendasPorProduto(FacturaVendaTotalPorProduto detalhe, ResultSet rs) {
        try {
/*pd.nome_produto, SUM(quantidade_vendida), SUM(quantidade_vendida*preco_venda) */
            Produto produto = new Produto();
            produto.setNomeProduto(rs.getString(1));
            
            FacturaVendaProduto factura = new FacturaVendaProduto();
           // factura.setDataFactura(rs.getDate(2));
           
            FormaPagamento formaPagamento = new FormaPagamento();
         //   formaPagamento.setDescricaoFormaPagamento(rs.getString(3));

            detalhe.setProduto(produto);
          //  detalhe.setFacturaVendaProduto(factura);
          //  detalhe.setFormaPagamento(formaPagamento);
            
            detalhe.setQuantidadeVendida(rs.getInt(2));
            detalhe.setTotalVendas(rs.getDouble(3));
          

        } catch (SQLException ex) {
            System.err.println("Error on fill data Item: " + ex.getLocalizedMessage());
        }

    }
    
    
   
}
