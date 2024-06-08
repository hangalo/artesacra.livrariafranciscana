/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.dao;


import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.Provincia;
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
public class ProvinciaDAO {

    private static final String INSERIR = "INSERT INT Provincia(nome_provincia) VALUES (?)";
    private static final String ACTUALIZAR = "UPDATE provincia set nome_provincia = ? WHERE id_Provincia = ?";
    private static final String ELIMINAR = "DELETE FROM Provincia WHERE id_Provincia = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT id_provincia,nome_provincia FROM provincia WHERE id_Provincia = ?";
    private static final String LISTAR_TUDO = "SELECT id_provincia,nome_provincia FROM provincia ORDER BY nome_provincia ASC;";

  
    public void save(Provincia provincia) {
        PreparedStatement ps = null;
        Connection conn = null;
        if (provincia == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
           conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERIR);

            ps.setString(1, provincia.getNomeProvincia());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }

    }


    public void update(Provincia provincia) {
        PreparedStatement ps = null;
        Connection conn = null;
        if (provincia == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);
            ps.setString(1, provincia.getNomeProvincia());
            ps.setInt(2, provincia.getIdProvincia());
            ps.executeUpdate();

        } catch (Exception ex) {
            System.err.println("Erro ao actualizar dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }


    public void delete(Provincia provincia) {
        PreparedStatement ps = null;
        Connection conn = null;
        if (provincia == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, provincia.getIdProvincia());
            ps.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Erro ao eliminar dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps);
            {
            }
        }
    }


    public Provincia findById(Integer id) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Provincia provincia = new Provincia();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(provincia, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return provincia;
    }

   
   public List<Provincia> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Provincia> provincias = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Provincia provincia = new Provincia();
                popularComDados(provincia, rs);
                provincias.add(provincia);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return provincias;
    }

  
    public void popularComDados(Provincia provincia, ResultSet rs) {

        try {
            provincia.setIdProvincia(rs.getInt("id_provincia"));
            provincia.setNomeProvincia(rs.getString("nome_provincia"));

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }

    }

}
