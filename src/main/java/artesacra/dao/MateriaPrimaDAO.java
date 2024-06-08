/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.MateriaPrima;
import artesacra.modelo.Produto;
import artesacra.modelo.SaidaMateriaPrima;
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
public class MateriaPrimaDAO {
    private static final String INSERT = "INSERT INTO materia_prima(nome_materia_prima,data_expira_materia_prima,id_tipo_de_produto)VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE materia_prima SET nome_materia_prima = ?, data_expira_materia_prima = ?, quantidade_stock_materia_prima = ?, id_tipo_de_produto = ? WHERE id_materia_prima = ?";
    private static final String UPDATE_DIMINUIR_QUANTIDADE = "UPDATE materia_prima SET quantidade_stock_materia_prima = quantidade_stock_materia_prima - ? WHERE id_materia_prima = ?";
    private static final String UPDATE_AUMENTAR_QUANTIDADE = "UPDATE materia_prima SET quantidade_stock_materia_prima = quantidade_stock_materia_prima + ? WHERE id_materia_prima = ?";
    private static final String DELETE = "DELETE FROM produto WHERE id_produto =?";
    private static final String SELECT_BY_CODIGO = "SELECT id_produto, nome_produto, data_expira_produto, preco_venda_produto, quantidade_stock_produto, colocacao_localizacao, tp.id_tipo_de_produto, descricao_tipo_de_produto, descricao_familia_do_produto FROM produto p INNER JOIN tipo_de_produto tp ON p.id_tipo_de_produto=tp.id_tipo_de_produto INNER JOIN familia_do_produto fp on tp.id_familia_do_produto=fp.id_familia_do_produto WHERE id_produto = ?";
    private static final String SELECT_BY_NOME = "SELECT id_materia_prima, nome_materia_prima, data_expira_materia_prima, quantidade_stock_materia_prima, descricao_tipo_de_produto FROM materia_prima mp INNER JOIN tipo_de_produto tp ON mp.id_tipo_de_produto = tp.id_tipo_de_produto WHERE nome_materia_prima LIKE ? ORDER BY nome_materia_prima";
    private static final String SELECT_ALL = "SELECT id_materia_prima, nome_materia_prima, data_expira_materia_prima, quantidade_stock_materia_prima, descricao_tipo_de_produto FROM materia_prima mp INNER JOIN tipo_de_produto tp ON mp.id_tipo_de_produto = tp.id_tipo_de_produto ORDER BY nome_materia_prima";
    private static final String SELECT_BY_TIPO_PRODUTO = "SELECT id_materia_prima, nome_materia_prima, data_expira_materia_prima, quantidade_stock_materia_prima, tp.id_tipo_de_produto, tp.descricao_tipo_de_produto FROM materia_prima mp INNER JOIN tipo_de_produto tp ON mp.id_tipo_de_produto=tp.id_tipo_de_produto  WHERE tp.id_tipo_de_produto = ? ORDER BY nome_materia_prima";
    

    
     public boolean save(MateriaPrima materiaPrima) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, materiaPrima.getNomeMateriaPrima());
            ps.setDate(2, new java.sql.Date(materiaPrima.getDataExpiracao().getTime()));
            ps.setInt(3, materiaPrima.getTipoProduto().getIdTipoProduto());
          
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
     
     
     
    public boolean updateDimunuirQuantidade(Integer quantidade, MateriaPrima materiaPrima) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {

            conn = ConnectionDB.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(UPDATE_DIMINUIR_QUANTIDADE);

            ps.setInt(1, quantidade);
            ps.setInt(2, materiaPrima.getIdMateriaPrima());
            int retorno = ps.executeUpdate();
            conn.commit();
            if (retorno > 0) {
                System.out.println("ProdutdoDAO: Dados quantidade dimuida com sucesso com sucesso: " + ps.getUpdateCount());
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
    
     public boolean updateAumentarQuantidade(Integer quantidade, MateriaPrima materiaPrima) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
             System.out.println("Quantidade 1>>>>>>>>>>"+quantidade);
            conn = ConnectionDB.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(UPDATE_AUMENTAR_QUANTIDADE);

            ps.setInt(1, quantidade);
            ps.setInt(2, materiaPrima.getIdMateriaPrima());
            int retorno = ps.executeUpdate();
            conn.commit();
            if (retorno > 0) {
                
                System.out.println("StockProdutoDAO:update AumentarQuantidade Dados quantidade aumentada com sucesso com sucesso: " + ps.getUpdateCount());
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
     
      public List<MateriaPrima> findByTipoProduto(TipoProduto tipo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
              List<MateriaPrima> materiaPrimas = new ArrayList();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_TIPO_PRODUTO);
            ps.setInt(1, tipo.getIdTipoProduto());

            rs = ps.executeQuery();
            while (rs.next()) {
                MateriaPrima materiaPrima = new MateriaPrima();
                popularComDados(materiaPrima, rs);
                materiaPrimas.add(materiaPrima);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return materiaPrimas;
    }
     
     
     public List<MateriaPrima> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<MateriaPrima> materiaPrimas = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                MateriaPrima materiaPrima = new MateriaPrima();
                popularComDados(materiaPrima, rs);
                materiaPrimas.add(materiaPrima);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return materiaPrimas;
    }
     
     
      private void popularComDados(MateriaPrima materiaPrima, ResultSet rs) {
        try {
            
           
            materiaPrima.setIdMateriaPrima(rs.getInt("id_materia_prima"));
            materiaPrima.setNomeMateriaPrima(rs.getString("nome_materia_prima"));
            materiaPrima.setDataExpiracao(rs.getDate("data_expira_materia_prima"));
            materiaPrima.setQuantidadeStock(rs.getInt("quantidade_stock_materia_prima"));
            TipoProduto tipoProduto = new TipoProduto();
            tipoProduto.setDescricaoTipoProduto(rs.getString("descricao_tipo_de_produto"));
            materiaPrima.setTipoProduto(tipoProduto);

        } catch (SQLException ex) {
            System.err.println("ProdutoDAO: popularComDados: Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }

}
