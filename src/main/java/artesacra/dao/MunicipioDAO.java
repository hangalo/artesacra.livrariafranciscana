/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.Municipio;
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
public class MunicipioDAO {

    private static final String BUSCAR_POR_CODIGO = "SELECT id_municipio, nome_municipio, nome_provincia FROM  municipio m INNER JOIN provincia p ON m.id_provincia = p.id_provincia WHERE m.id_municipio =?";
    private static final String LISTAR_TUDO = "SELECT id_municipio, nome_municipio, nome_provincia FROM  municipio m INNER JOIN provincia p ON m.id_provincia = p.id_provincia";
    private static final String SELECT_BY_ID_PROVINCIA = "SELECT id_municipio, nome_municipio, nome_provincia FROM  municipio m INNER JOIN provincia p ON m.id_provincia = p.id_provincia WHERE m.id_provincia = ?";

    ConnectionDB conexaoDB = new ConnectionDB();

    public Municipio findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Municipio municipio = new Municipio();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(municipio, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }

        return municipio;
    }

    public List<Municipio> findByIdProvincia(Provincia p) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        System.out.println("Provincia DAO <<<<<=====" + p.getNomeProvincia());

        List<Municipio> municipios = new ArrayList<>();
        try {
            conn = (Connection) ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID_PROVINCIA);
            ps.setInt(1, p.getIdProvincia());
            rs = ps.executeQuery();
            while (rs.next()) {
                Municipio municipio = new Municipio();
                popularComDados(municipio, rs);
                municipios.add(municipio);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return municipios;
    }

    public List<Municipio> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Municipio> municipios = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Municipio municipio = new Municipio();
                popularComDados(municipio, rs);
                municipios.add(municipio);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return municipios;
    }

    private void popularComDados(Municipio municipio, ResultSet rs) {
        try {

            municipio.setIdMunicipio(rs.getInt("id_municipio"));
            municipio.setNomeMunicipio(rs.getString("nome_municipio"));
            Provincia p = new Provincia();
            p.setNomeProvincia(rs.getString("nome_provincia"));
            municipio.setProvincia(p);

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }

}
