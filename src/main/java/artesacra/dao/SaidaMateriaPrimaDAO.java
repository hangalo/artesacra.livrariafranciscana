/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.Cliente;
import artesacra.modelo.FacturaVendaProduto;
import artesacra.modelo.FormaPagamento;
import artesacra.modelo.SaidaMateriaPrima;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaidaMateriaPrimaDAO {

    private static final String INSERT = "INSERT INTO saida_materia_prima(data_saida_materia_prima, id_profissional, id_sector_producao) VALUES(?, ?, ?)";
    private static final String UPDATE = "";
    private static final String DELETE = "";
    private static final String SELECT_ALL = "";
    private static final String SELECT_BY_ID = "";

    private static final String SELECT_MAX_ID_REGISTO = "SELECT MAX(id_saida_materia_prima) FROM saida_materia_prima";

    public boolean save(SaidaMateriaPrima saidaMateriaPrima) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        if (saidaMateriaPrima == null) {
            System.err.println("FacturaDAO:save: O valor oassado nao pode ser nulo!");
        }
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setDate(1, new java.sql.Date(saidaMateriaPrima.getDataSaida().getTime()));
            ps.setInt(2, saidaMateriaPrima.getProfissional().getIdProfissional());
            ps.setDouble(3, saidaMateriaPrima.getSectorProducao().getIdSector());

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

    public Integer buscaUltimoRegisto() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        FacturaVendaProduto factura = new FacturaVendaProduto();
        Integer ultimo = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_MAX_ID_REGISTO);
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
