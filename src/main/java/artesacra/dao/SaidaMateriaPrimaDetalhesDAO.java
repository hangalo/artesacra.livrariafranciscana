/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.FacturaVendaProdutoDetalhes;
import artesacra.modelo.SaidaMateriaPrimaDetalhes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author informatica
 */
public class SaidaMateriaPrimaDetalhesDAO {
     private static final String INSERT = "INSERT INTO saida_materia_prima_detalhes (id_saida_materia_prima, id_materia_prima, quantidade_saida_materia_prima) VALUES (?, ?, ?)";


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


}
