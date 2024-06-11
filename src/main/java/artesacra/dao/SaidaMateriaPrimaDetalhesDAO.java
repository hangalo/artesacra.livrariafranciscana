/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.FacturaVendaProdutoDetalhes;
import artesacra.modelo.MateriaPrima;
import artesacra.modelo.Produto;
import artesacra.modelo.Profissional;
import artesacra.modelo.SaidaMateriaPrima;
import artesacra.modelo.SaidaMateriaPrimaDetalhes;
import artesacra.modelo.SaidaMateriaPrimaTotalPorMateriaPrima;
import artesacra.modelo.SectorProducao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaidaMateriaPrimaDetalhesDAO {

    private static final String INSERT = "INSERT INTO saida_materia_prima_detalhes (id_saida_materia_prima, id_materia_prima, quantidade_saida_materia_prima) VALUES (?, ?, ?)";
    private static final String SELECT_TOTAL_SAIDAS_POR_MATERIA_PRIMA = "SELECT mp.nome_materia_prima, SUM(quantidade_saida_materia_prima) FROM saida_materia_prima_detalhes smpd  INNER JOIN materia_prima mp ON smpd.id_materia_prima = mp.id_materia_prima INNER JOIN saida_materia_prima smp ON smpd.id_saida_materia_prima = smp.id_saida_materia_prima INNER JOIN sector_producao sp ON smp.id_sector_producao =sp.id_sector_producao INNER JOIN profissional pf ON smp.id_profissional = pf.id_profissional WHERE smp.data_saida_materia_prima Between ? and ? GROUP BY mp.nome_materia_prima ORDER BY  mp.nome_materia_prima";
    private static final String SELECT_ALL_ENTRE_DATAS = "SELECT pf.nome_profissional, pf.sobrenome_profissional, mp.nome_materia_prima, smp.id_saida_materia_prima, smp.data_saida_materia_prima, smp.data_registo, sp.nome_sector_producao, quantidade_saida_materia_prima FROM saida_materia_prima_detalhes smpd INNER JOIN materia_prima mp ON smpd.id_materia_prima = mp.id_materia_prima INNER JOIN saida_materia_prima smp ON smpd.id_saida_materia_prima = smp.id_saida_materia_prima INNER JOIN sector_producao sp ON smp.id_sector_producao =sp.id_sector_producao  INNER JOIN profissional pf ON smp.id_profissional = pf.id_profissional WHERE smp.data_saida_materia_prima Between ? and ?  ORDER BY mp.nome_materia_prima";

    public boolean save(SaidaMateriaPrimaDetalhes detalhe) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        if (detalhe == null) {
            System.err.println("FacturaVendaProdutoDetalhesDAO:save: O valor oassado nao pode ser nulo!");
        }
        try {

            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, detalhe.getSaidaMateriaPrima().getIdSaida());
            ps.setInt(2, detalhe.getMateriaPrima().getIdMateriaPrima());
            ps.setInt(3, detalhe.getQuanditadeSaida());

            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("ItemDAO:save:Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }

            return flagControlo;

        } catch (SQLException e) {
            System.out.println("ItemDAO:save:Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public List<SaidaMateriaPrimaDetalhes> findAllPorIntervaloData(Date inicio, Date fim) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<SaidaMateriaPrimaDetalhes> items = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL_ENTRE_DATAS);
            ps.setDate(1, new java.sql.Date(inicio.getTime()));
            ps.setDate(2, new java.sql.Date(fim.getTime()));

            rs = ps.executeQuery();
            while (rs.next()) {
                SaidaMateriaPrimaDetalhes item = new SaidaMateriaPrimaDetalhes();
                popularComDados(item, rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            System.err.println(":Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return items;
    }

    public List<SaidaMateriaPrimaTotalPorMateriaPrima> totalSaidasPorMateriaPrimaEntreDatas(Date inicio, Date fim) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<SaidaMateriaPrimaTotalPorMateriaPrima> items = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_TOTAL_SAIDAS_POR_MATERIA_PRIMA);
            ps.setDate(1, new java.sql.Date(inicio.getTime()));
            ps.setDate(2, new java.sql.Date(fim.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                SaidaMateriaPrimaTotalPorMateriaPrima item = new SaidaMateriaPrimaTotalPorMateriaPrima();
                popularTotalSaidasPorMateriaPrima(item, rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            System.err.println(":Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return items;
    }

    public void popularComDados(SaidaMateriaPrimaDetalhes detalhes, ResultSet rs) {
        try {
           
            MateriaPrima materiaPrima = new MateriaPrima();
            Profissional profissional = new Profissional();
            SaidaMateriaPrima saidaMateriaPrima = new SaidaMateriaPrima();
            SectorProducao sectorProducao = new SectorProducao();
            /*
            pf.nome_profissional,
            pf.sobrenome_profissional,
            mp.nome_materia_prima, 
            smp.id_saida_materia_prima,
            smp.data_saida_materia_prima, 
            sp.nome_sector_producao, 
            quantidade_saida_materia_prima
            smp.data_registo
             */
            profissional.setNomeProfissional(rs.getString("pf.nome_profissional"));
            profissional.setSobrenomeProfissional(rs.getString("pf.sobrenome_profissional"));
           
            materiaPrima.setNomeMateriaPrima(rs.getString("mp.nome_materia_prima"));
            
            saidaMateriaPrima.setIdSaida(rs.getInt("smp.id_saida_materia_prima"));
            saidaMateriaPrima.setDataSaida(rs.getDate("smp.data_saida_materia_prima"));
            saidaMateriaPrima.setDataRegisto(rs.getDate("smp.data_registo"));
            sectorProducao.setNomeSector(rs.getString("sp.nome_sector_producao"));
            
            detalhes.setQuanditadeSaida(rs.getInt("quantidade_saida_materia_prima"));

            saidaMateriaPrima.setProfissional(profissional);

            saidaMateriaPrima.setSectorProducao(sectorProducao);

            detalhes.setSaidaMateriaPrima(saidaMateriaPrima);
            detalhes.setMateriaPrima(materiaPrima);

        } catch (SQLException ex) {
            System.err.println("Error on fill data Item: " + ex.getLocalizedMessage());
        }

    }

    public void popularTotalSaidasPorMateriaPrima(SaidaMateriaPrimaTotalPorMateriaPrima detalhe, ResultSet rs) {
        try {
            MateriaPrima materiaPrima = new MateriaPrima();
            materiaPrima.setNomeMateriaPrima(rs.getString(1));
            detalhe.setMateriaPrima(materiaPrima);
            detalhe.setQuantidadeSaida(rs.getInt(2));

        } catch (SQLException ex) {
            System.err.println("Error on fill data Item: " + ex.getLocalizedMessage());
        }

    }

}
