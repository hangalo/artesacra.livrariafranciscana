/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.FamiliaProduto;
import artesacra.modelo.TipoProduto;
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
public class TipoProdutoDAO {

    private static final String INSERT = "INSERT INTO tipo_de_produto(descricao_tipo_de_produto, id_familia_do_produto)VALUES(?, ?);";
    private static final String UPDATE = "UPDATE tipo_de_produto SET descricao_tipo_de_produto = ?, id_familia_do_produto = ?  WHERE id_tipo_de_produto = ?";
    private static final String DELETE = "DELETE FROM tipo_de_produto WHERE id_tipo_de_produto=?";
    private static final String SELECT_ALL = "SELECT id_tipo_de_produto, descricao_tipo_de_produto, descricao_familia_do_produto  FROM tipo_de_produto tp INNER JOIN familia_do_produto fp ON tp.id_familia_do_produto=fp.id_familia_do_produto ORDER BY descricao_tipo_de_produto";
    private static final String SELECT_BY_ID = "SELECT id_tipo_de_produto, descricao_tipo_de_produto, descricao_familia_do_produto  FROM tipo_de_produto tp INNER JOIN familia_do_produto fp ON tp.id_familia_do_produto=fp.id_familia_do_produto WHERE id_tipo_de_produto = ?";


    public boolean save(TipoProduto tipoProduto) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, tipoProduto.getDescricaoTipoProduto());
            ps.setInt(2, tipoProduto.getFamiliaProduto().getIdFamiliaProduto());

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


    public boolean update(TipoProduto tipoProduto) {
     PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);
              ps.setString(1, tipoProduto.getDescricaoTipoProduto());
            ps.setInt(2, tipoProduto.getFamiliaProduto().getIdFamiliaProduto());
            ps.setInt(3,tipoProduto.getIdTipoProduto());
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados actualizados com sucesso: " + ps.getUpdateCount());
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


    public boolean delete(TipoProduto tipoProduto) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flagControlo = false;
        if (tipoProduto == null) {
            System.err.println("O campo anterior nao pode ser nulo");
        }

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, tipoProduto.getIdTipoProduto());
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

   
    public TipoProduto findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        TipoProduto tipoProduto = new TipoProduto();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("CategoriaDAO:findById: nenhum registo com o id: " + id);
            }
            fillData(tipoProduto, rs);
        } catch (SQLException ex) {
            System.err.println("CategoriaDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return tipoProduto;
    }

  
    public List<TipoProduto> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<TipoProduto> tipoProdutos = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoProduto tipoProduto = new TipoProduto();
                fillData(tipoProduto, rs);
                tipoProdutos.add(tipoProduto);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados:>CategoriaItemDAO ->find All " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return tipoProdutos;
    }


    public void fillData(TipoProduto tipoProduto, ResultSet rs) {
        try {
            tipoProduto.setIdTipoProduto(rs.getInt("id_tipo_de_produto"));
            tipoProduto.setDescricaoTipoProduto(rs.getString("descricao_tipo_de_produto"));
            FamiliaProduto familiaProduto= new FamiliaProduto();
            familiaProduto.setDescricaoFamiliaProduto(rs.getString("descricao_familia_do_produto"));
            tipoProduto.setFamiliaProduto(familiaProduto);

        } catch (SQLException ex) {
            System.err.println("Error on fill data Categoria: " + ex.getLocalizedMessage());
        }

    }

}


