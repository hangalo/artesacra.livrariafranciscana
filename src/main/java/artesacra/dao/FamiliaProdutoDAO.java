/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.FamiliaProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FamiliaProdutoDAO {
   
    private static final String INSERIR = "INSERT INTO familia_do_produto(descricao_familia_do_produto) VALUES( ?)";
    private static final String BUSCAR_POR_CODIGO = "SELECT id_familia_do_produto, descricao_familia_do_produto FROM familia_do_produto WHERE id_familia_do_produto = ?";
    private static final String LISTAR_TUDO = "SELECT id_familia_do_produto, descricao_familia_do_produto FROM familia_do_produto ORDER BY descricao_familia_do_produto";
    private static final String UPDATE = "UPDATE familia_do_produto SET descricao_familia_do_produto = ? WHERE id_familia_do_produto = ?";
     private static final String DELETE = "DELETE FROM familia_do_produto WHERE id_familia_do_produto = ?";
    
    
    
     public boolean save(FamiliaProduto familiaProduto) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERIR);
            ps.setString(1, familiaProduto.getDescricaoFamiliaProduto());
           
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

    public boolean update(FamiliaProduto familiaProduto) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);
             ps.setString(1, familiaProduto.getDescricaoFamiliaProduto());
            ps.setInt(2, familiaProduto.getIdFamiliaProduto());

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

    public boolean delete(FamiliaProduto familiaProduto) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
          ps = conn.prepareStatement(DELETE);
           
            ps.setInt(1, familiaProduto.getIdFamiliaProduto());
                    
           

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

   
    
    public FamiliaProduto findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        FamiliaProduto familiaProduto = new FamiliaProduto();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("TipoConsultaDAO:findById: nenhum registo com o id: " + id);
            }
            fillData(familiaProduto, rs);
        } catch (SQLException ex) {
            System.err.println("CclienteDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return familiaProduto;
    }

   

    public List<FamiliaProduto> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<FamiliaProduto> familiaProdutos= new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
               FamiliaProduto familiaProduto = new FamiliaProduto();
                fillData(familiaProduto, rs);
                familiaProdutos.add(familiaProduto);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return familiaProdutos;
    }

    private void fillData(FamiliaProduto familiaProduto, ResultSet rs) {
        try {
            
            familiaProduto.setIdFamiliaProduto(rs.getInt("id_familia_do_produto"));
          familiaProduto.setDescricaoFamiliaProduto(rs.getString("descricao_familia_do_produto"));
          

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados:=>FamiliaProdutoDAO::fillData " + ex.getLocalizedMessage());
        }
    }

    
    
}
