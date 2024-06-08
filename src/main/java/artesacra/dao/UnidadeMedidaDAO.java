
package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.UnidadeMedida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UnidadeMedidaDAO {

    private static final String INSERT = "INSERT INTO unidade_de_medida(descricao_unidade_de_medida)VALUES(?);";
    private static final String UPDATE = "UPDATE unidade_de_medida SET descricao_unidade_de_medida = ? WHERE id_unidade_de_medida = ?";
    private static final String DELETE = "DELETE FROM unidade_de_medida WHERE id_unidade_de_medida =?";
    private static final String SELECT_ALL = "SELECT id_unidade_de_medida, descricao_unidade_de_medida FROM unidade_de_medida ORDER BY descricao_unidade_de_medida";
    private static final String SELECT_BY_ID = "SELECT id_unidade_de_medida, descricao_unidade_de_medida FROM unidade_de_medida WHERE id_unidade_de_medida = ?";


    public boolean save(UnidadeMedida unidadeMedida) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, unidadeMedida.getDescricaoUnidadeMedida());
           
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


    public boolean update(UnidadeMedida unidadeMedida) {
     PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);
              ps.setString(1, unidadeMedida.getDescricaoUnidadeMedida());
            ps.setInt(2,unidadeMedida.getIdUnidadeMedida());
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


    public boolean delete(UnidadeMedida unidadeMedida) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flagControlo = false;
        if (unidadeMedida == null) {
            System.err.println("O campo anterior nao pode ser nulo");
        }

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, unidadeMedida.getIdUnidadeMedida());
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

   
    public UnidadeMedida findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        UnidadeMedida unidadeMedida = new UnidadeMedida();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Unidade de MediaDAO:findById: nenhum registo com o id: " + id);
            }
            fillData(unidadeMedida, rs);
        } catch (SQLException ex) {
            System.err.println("Unidade de Medida DAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return unidadeMedida;
    }

  
    public List<UnidadeMedida> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<UnidadeMedida> unidadeMedidas = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                UnidadeMedida unidadeMedida = new UnidadeMedida();
                fillData(unidadeMedida, rs);
                unidadeMedidas.add(unidadeMedida);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados:>CategoriaItemDAO ->find All " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return unidadeMedidas;
    }


    public void fillData(UnidadeMedida unidadeMedida, ResultSet rs) {
        try {
            unidadeMedida.setIdUnidadeMedida(rs.getInt("id_unidade_de_medida"));
            unidadeMedida.setDescricaoUnidadeMedida(rs.getString("descricao_unidade_de_medida"));

        } catch (SQLException ex) {
            System.err.println("Error on fill data Categoria: " + ex.getLocalizedMessage());
        }

    }

}


