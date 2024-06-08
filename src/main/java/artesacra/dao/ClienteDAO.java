/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.Cliente;
import artesacra.modelo.Municipio;
import artesacra.modelo.Sexo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private static final String INSERT = "INSERT INTO cliente(nome_cliente, sobrenome_cliente, sexo_cliente, telefone_cliente, casa_cliente, rua_cliente, bairro_cliente, id_municipio) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE cliente SET numero_contribuinte = ?, nome_cliente = ?, sobrenome_cliente = ? , email_cliente = ?, telefone_cliente = ?, casa_cliente = ?, rua_cliente = ?, bairro_cliente = ?, distrito_cliente = ?, id_municipio = ? WHERE id_cliente = ?";
    private static final String DELETE = "DELETE FROM cliente WHERE id_cliente =?";
    private static final String SELECT_ALL = "SELECT id_cliente, numero_contribuinte, nome_cliente, sobrenome_cliente, sexo_cliente,  email_cliente, telefone_cliente, casa_cliente, rua_cliente, bairro_cliente, distrito_cliente, nome_municipio FROM cliente c INNER JOIN municipio m ON c.id_municipio = m.id_municipio";
    private static final String SELECT_BY_ID = "SELECT id_cliente, numero_contribuinte, nome_cliente, sobrenome_cliente, sexo_cliente, email_cliente, telefone_cliente, casa_cliente, rua_cliente, bairro_cliente, distrito_cliente, nome_municipio FROM cliente c INNER JOIN municipio m ON c.id_municipio = m.id_municipio WHERE id_cliente =?";

    public boolean save(Cliente cliente) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, cliente.getNomeCliente());
            ps.setString(2, cliente.getSobrenomeCliente());
            ps.setString(3, cliente.getSexo().getAbreviatura());
            ps.setString(4, cliente.getTelefoneCliente());
            ps.setString(5, cliente.getCasaCliente());
            ps.setString(6, cliente.getRuaCliente());
            ps.setString(7, cliente.getBairroCliente());
            ps.setInt(8, cliente.getMunicipio().getIdMunicipio());

            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }
            return flagControlo;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean save2(Cliente cliente) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);

            //  ps.setString(1, cliente.getNumeroContribuinte());
            ps.setString(1, cliente.getNomeCliente());
            ps.setString(2, cliente.getSobrenomeCliente());
//            ps.setString(4, cliente.getSexo().getAbreviatura());
            // ps.setString(4, cliente.getEmailCliente());
            ps.setString(3, cliente.getTelefoneCliente());
            ps.setString(4, cliente.getCasaCliente());
            ps.setString(5, cliente.getRuaCliente());
            ps.setString(6, cliente.getBairroCliente());
            /// ps.setString(10, cliente.getDistritoCliente());
            //ps.setInt(7, cliente.getMunicipio().getIdMunicipio());

            ps.executeUpdate();

            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }
            return flagControlo;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean update(Cliente cliente) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);

            ps.setString(1, cliente.getNumeroContribuinte());
            ps.setString(2, cliente.getNomeCliente());
            ps.setString(3, cliente.getSobrenomeCliente());
            ps.setString(4, cliente.getEmailCliente());
            ps.setString(5, cliente.getTelefoneCliente());
            ps.setString(6, cliente.getCasaCliente());
            ps.setString(7, cliente.getRuaCliente());
            ps.setString(8, cliente.getBairroCliente());
            ps.setString(9, cliente.getDistritoCliente());
            ps.setInt(10, cliente.getMunicipio().getIdMunicipio());
            ps.setInt(11, cliente.getIdCliente());

            ps.executeUpdate();

            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }
            return flagControlo;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean delete(Cliente cliente) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flagControlo = false;
        if (cliente == null) {
            System.err.println("O campo anterior nao pode ser nulo");
        }
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, cliente.getIdCliente());
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

    public Cliente findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Cliente cliente = new Cliente();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("ClienteDAO:findById: nenhum registo com o id: " + id);
            }
            popularComDados(cliente, rs);
        } catch (SQLException ex) {
            System.err.println("CclienteDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return cliente;
    }

    public List<Cliente> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                popularComDados(cliente, rs);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return clientes;
    }

    public void popularComDados(Cliente cliente, ResultSet rs) {
        try {

            cliente.setIdCliente(rs.getInt("id_cliente"));
            cliente.setNumeroContribuinte(rs.getString("numero_contribuinte"));
            cliente.setNomeCliente(rs.getString("nome_cliente"));
            cliente.setSobrenomeCliente(rs.getString("sobrenome_cliente"));
            cliente.setSexo(Sexo.getAbreviatura(rs.getString("sexo_cliente")));
            cliente.setEmailCliente(rs.getString("email_cliente"));
            cliente.setTelefoneCliente(rs.getString("telefone_cliente"));
            cliente.setCasaCliente(rs.getString("casa_cliente"));
            cliente.setRuaCliente(rs.getString("rua_cliente"));
            cliente.setBairroCliente(rs.getString("bairro_cliente"));
            cliente.setDistritoCliente(rs.getString("distrito_cliente"));
            Municipio municipio = new Municipio();
            municipio.setNomeMunicipio(rs.getString("nome_municipio"));
            cliente.setMunicipio(municipio);

        } catch (SQLException ex) {
            System.err.println("Error on fill data Cliente: " + ex.getLocalizedMessage());
        }

    }

}
