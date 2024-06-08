/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.Cliente;
import artesacra.modelo.FacturaVendaProduto;
import artesacra.modelo.FormaPagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author informatica
 */
public class FacturaVendaProdutoDAO {

    private static final String INSERT = "INSERT INTO factura_venda_produto(data_factura_venda,id_cliente, valor_total, id_forma_de_pagamento, id_profissional) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE = "";
    private static final String DELETE = "";
    private static final String SELECT_ALL = "SELECT id_factura_venda, data_factura_venda, c.nome_cliente, c.sobrenome_cliente, valor_total, fp.descricao_forma_de_pagamento, data_hora_registo"
            + " FROM factura_venda_produto fvp "
            + "INNER JOIN cliente c ON fvp.id_cliente=c.id_cliente "
            + "INNER JOIN forma_de_pagamento fp ON fvp.id_forma_de_pagamento=fp.id_forma_de_pagamento";
    private static final String SELECT_BY_ID = "";

    private static final String SELECT_MAX_ID_FACTURA = "SELECT MAX(id_factura_venda) FROM factura_venda_produto";

    public boolean save(FacturaVendaProduto factura) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        if (factura == null) {
            System.err.println("FacturaDAO:save: O valor oassado nao pode ser nulo!");
        }
        try {
            conn = ConnectionDB.getConnection();

            /*
            
            data_factura_venda, id_cliente, valor_total, id_forma_de_pagamento 
             */
            ps = conn.prepareStatement(INSERT);
            ps.setDate(1, new java.sql.Date(factura.getDataFactura().getTime()));
            ps.setInt(2, factura.getCliente().getIdCliente());
            ps.setDouble(3, factura.getValorTotal());
            ps.setInt(4, factura.getFormaPagamento().getIdFormaPagamento());
             ps.setInt(5, factura.getProfissional().getIdProfissional());
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("FacturaDAO:save:Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }

            return flagControlo;

        } catch (SQLException e) {
            System.out.println("FacturaDAO:save:Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean update(FacturaVendaProduto factura) {
        //implementar
        return false;
    }

    public boolean delete(FacturaVendaProduto factura) {
        //implementar
        return false;
    }

    public FacturaVendaProduto findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        FacturaVendaProduto factura = new FacturaVendaProduto();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("CategoriaDAO:findById: nenhum registo com o id: " + id);
            }
            popularComDados(factura, rs);
        } catch (SQLException ex) {
            System.err.println("CategoriaDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return factura;
    }

    public List<FacturaVendaProduto> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<FacturaVendaProduto> facturas = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                FacturaVendaProduto factura = new FacturaVendaProduto();
                popularComDados(factura, rs);
                facturas.add(factura);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return facturas;
    }

    public Integer buscaUltimaFactura() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        FacturaVendaProduto factura = new FacturaVendaProduto();
        Integer ultimo = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_MAX_ID_FACTURA);
            rs = ps.executeQuery();
            if (rs.next()) {

                ultimo = rs.getInt(1);
            }
             System.out.println("FacturaDAO: buscaUltimaFactura -> Maior factura" + ultimo);
        } catch (SQLException ex) {
            System.out.println("FacturaDAO: buscaUltimaFactura -> Erro ao carregar dados" + ex.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return ultimo;

    }

    public void popularComDados(FacturaVendaProduto factura, ResultSet rs) {
        try {

            factura.setIdFactura(rs.getInt("id_factura_venda"));
            factura.setDataFactura(rs.getDate("data_factura_venda"));
            factura.setDataHoraRegisto(rs.getTimestamp("data_hora_registo"));
            Cliente cliente = new Cliente();
            cliente.setNomeCliente(rs.getString("c.nome_cliente"));
            cliente.setSobrenomeCliente(rs.getString("c.sobrenome_cliente"));
            factura.setCliente(cliente);
            factura.setValorTotal(rs.getDouble("valor_total"));
            FormaPagamento formaPagamento = new FormaPagamento();
            formaPagamento.setDescricaoFormaPagamento(rs.getString("fp.descricao_forma_de_pagamento"));

        } catch (SQLException ex) {
            System.err.println("Error on fill data Factura: " + ex.getLocalizedMessage());
        }

    }
}
