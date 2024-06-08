
package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.MateriaPrima;
import artesacra.modelo.SectorProducao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SectorProducaoDAO {
    private static final String INSERT = "INSERT INTO sector_producao(nome_sector_producao)VALUES( ?)";
    private static final String ACTUALIZAR = "UPDATE sector_producao SET nome_sector_producao = ? WHERE id_sector_producao = ?";
    private static final String ELIMINAR = "DELETE FROM sector_producao WHERE id_sector_producao = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT id_sector_producao, nome_sector_producao FROM sector_producao WHERE id_sector_producao = ? ORDER BY nome_sector_producao ASC";
    private static final String LISTAR_TUDO = "SELECT id_sector_producao, nome_sector_producao FROM sector_producao ORDER BY nome_sector_producao ASC";

  
   


        public boolean save(SectorProducao sectorProducao) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, sectorProducao.getNomeSector());
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
    
    
    public void update(SectorProducao sectorProducao) {
        PreparedStatement ps = null;
        Connection conn = null;
        if (sectorProducao == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);
             ps.setString(1, sectorProducao.getNomeSector());
            ps.setInt(2, sectorProducao.getIdSector());
            ps.executeUpdate();

        } catch (Exception ex) {
            System.err.println("Erro ao actualizar dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }


    public void delete(SectorProducao sectorProducao) {
        PreparedStatement ps = null;
        Connection conn = null;
        if (sectorProducao == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, sectorProducao.getIdSector());
            ps.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Erro ao eliminar dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps);
            {
            }
        }
    }


    public SectorProducao findById(Integer id) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        SectorProducao sectorProducao = new SectorProducao();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(sectorProducao, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return sectorProducao;
    }

   
   public List<SectorProducao> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<SectorProducao> sectorProducoes = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                SectorProducao sectorProducao = new SectorProducao();
                popularComDados(sectorProducao, rs);
                sectorProducoes.add(sectorProducao);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return sectorProducoes;
    }

  
    public void popularComDados(SectorProducao sectorProducao, ResultSet rs) {

        try {
            sectorProducao.setIdSector(rs.getInt("id_sector_producao"));
            sectorProducao.setNomeSector(rs.getString("nome_sector_producao"));

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }

    }
}
