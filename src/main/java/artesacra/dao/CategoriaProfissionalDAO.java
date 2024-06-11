/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.CategoriaProfissional;
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
public class CategoriaProfissionalDAO {
    
    private static final String INSERT = "INSERT INTO categoria_profissional (nome_categoria_profissional) VALUES (?)";
    private static final String UPDATE = "UPDATE categoria_medicamento SET categoria_medicamento = ? WHERE id_categoria_medicamento = ?";
    private static final String DELETE = "DELETE FROM categoria_medicamento WHERE id_categoria_medicamento =?";
    private static final String SELECT_ALL = "SELECT id_categoria_profissional, nome_categoria_profissional FROM  categoria_profissional";
    private static final String SELECT_BY_ID = "SELECT id_categoria_profissional, nome_categoria_profissional FROM  categoria_profissional WHERE id_categoria_profissional = ?";


    public boolean save(CategoriaProfissional categoria) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, categoria.getNomeCategoriaProfissional());
                 
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
    
    public CategoriaProfissional findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        CategoriaProfissional categoriaProfissional = new CategoriaProfissional();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("CategoriaDAO:findById: nenhum registo com o id: " + id);
            }
            fillData(categoriaProfissional, rs);
        } catch (SQLException ex) {
            System.err.println("CategoriaDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return categoriaProfissional;
    }

  
    public List<CategoriaProfissional> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<CategoriaProfissional> categorias = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                CategoriaProfissional categoriaProfissional = new CategoriaProfissional();
                fillData(categoriaProfissional, rs);
                categorias.add(categoriaProfissional);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados:>CategoriaItemDAO ->find All " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return categorias;
    }


    public void fillData(CategoriaProfissional categoriaProfissional, ResultSet rs) {
        try {
            categoriaProfissional.setIdCategoriaProfissional(rs.getInt("id_categoria_profissional"));
            categoriaProfissional.setNomeCategoriaProfissional(rs.getString("nome_categoria_profissional"));

        } catch (SQLException ex) {
            System.err.println("Error on fill data Categoria: " + ex.getLocalizedMessage());
        }

    }



}
