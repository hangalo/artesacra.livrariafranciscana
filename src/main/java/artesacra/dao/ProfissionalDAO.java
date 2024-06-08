/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.CategoriaProfissional;
import artesacra.modelo.Profissional;
import artesacra.modelo.Municipio;
import artesacra.modelo.Sexo;
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
public class ProfissionalDAO {

    private static final String INSERIR = "INSERT INTO profissional (nome_profissional, sobrenome_profissional, data_nascimento_profissional, sexo_profissional, email_profissional, telefone_profissional, rua_profissional, casa_profissional, bairro_profissional, distritito_profissional, id_municipio, id_categoria_profissional) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ? )";
    private static final String ACTUALIZAR = "UPDATE funcionario SET nome_funcionario = ?, sobrenome_funcionario = ?, data_nascimento_funcionario = ?, sexo_funcionario =?, email_funcionario = ?, telefone_funcionario = ?, rua_funcionario = ?, casa_funcionario = ?, bairro_funcionario = ?, distritito_funcionario = ?, id_municipio = ? WHERE id_funcionario = ?";
    private static final String ELIMINAR = "DELETE FROM funcionario WHERE id_funcionario = ?";
    private static final String LIST_BY_ID = "SELECT id_profissional, nome_profissional,sobrenome_profissional,data_nascimento_profissional,sexo_profissional, email_profissional,telefone_profissional, rua_profissional, casa_profissional, bairro_profissional, distritito_profissional, nome_municipio, nome_categoria_profissional FROM profissional p INNER JOIN municipio m ON p.id_municipio = m.id_municipio INNER JOIN categoria_profissional cp ON p.id_categoria_profissional=cp.id_categoria_profissional WHERE id_profissional = ?";
    private static final String LIST_BY_USERNAME_E_SENHA = "SELECT id_profissional, nome_profissional,sobrenome_profissional,data_nascimento_profissional,sexo_profissional, email_profissional,telefone_profissional, rua_profissional, casa_profissional, bairro_profissional, distritito_profissional, nome_municipio, nome_categoria_profissional FROM profissional p INNER JOIN municipio m ON p.id_municipio = m.id_municipio INNER JOIN categoria_profissional cp ON p.id_categoria_profissional=cp.id_categoria_profissional WHERE username = ? AND password =?";
    private static final String BUSCAR_POR_NOME = "SELECT id_profissional, nome_profissional,sobrenome_profissional,data_nascimento_profissional,sexo_profissional, email_profissional,telefone_profissional, rua_profissional, casa_profissional, bairro_profissional, distritito_profissional, nome_municipio FROM profissional p INNER JOIN municipio m ON p.id_municipio = m.id_municipio; WHERE nome_profissionalLIKE ? OR ,sobrenom_profissional  LIKE ?";
    private static final String LISTAR_TUDO = "SELECT id_profissional, nome_profissional,sobrenome_profissional,data_nascimento_profissional,sexo_profissional, email_profissional,telefone_profissional, rua_profissional, casa_profissional, bairro_profissional, distritito_profissional, nome_municipio, nome_categoria_profissional FROM profissional p INNER JOIN municipio m ON p.id_municipio = m.id_municipio INNER JOIN categoria_profissional cp ON p.id_categoria_profissional=cp.id_categoria_profissional";
   
   
    
    
     public boolean save(Profissional p) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
          conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERIR);
             
            ps.setString(1, p.getNomeProfissional());
            ps.setString(2, p.getSobrenomeProfissional());
            ps.setDate(3, new java.sql.Date(p.getDataNascimento().getTime()));
            ps.setString(4, p.getSexoProfissional().getAbreviatura());
            ps.setString(5, p.getEmailProfissional());
            ps.setString(6, p.getTelefoneProfissional());
            ps.setString(7, p.getRuaProfissional());
            ps.setString(8, p.getCasaProfissional());
            ps.setString(9, p.getBairroProfissional());
            ps.setString(10, p.getDistrititoProfissional());
            ps.setInt(11, p.getMunicipio().getIdMunicipio());
            ps.setInt(12, p.getCategoriaProfissional().getIdCategoriaProfissional());
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

    
/*
    public void update(Profissional f) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = conexaoDB.ligarBB();
            ps = conn.prepareStatement(ACTUALIZAR);
            ps.setString(1, f.getNomeFuncionario());
            ps.setString(2, f.getSobrenomFuncionario());
            ps.setDate(3, new java.sql.Date(f.getDataNascimento().getTime()));
            ps.setString(4, f.getSexoFuncionario().getAbreviatura());
            ps.setString(5, f.getEmailFuncionario());
            ps.setString(6, f.getRuaFuncionario());
            ps.setString(7, f.getCasaFuncionario());
            ps.setString(8, f.getBairroFuncionario());
            ps.setString(9, f.getDistrititoFuncionario());
            ps.setInt(10, f.getMunicipio().getIdMunicipio());
            ps.setInt(11, f.getIdFuncionario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        } finally {

            ConnectionDB.fecharConexao(conn, ps);
        }

    }

    public void delete(Profissional f) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = conexaoDB.ligarBB();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, f.getIdFuncionario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        } finally {

            ConnectionDB.fecharConexao(conn, ps);
        }

    }*/
     
      public Profissional findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Profissional profissional = new Profissional();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LIST_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("PacienteDAO:findById: nenhum registo com o id: " + id);
            }
                        popularComDados(profissional, rs);
        } catch (SQLException ex) {
            System.err.println("PacienteDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return profissional;
    }
      
      
        public Profissional findByUsernamePassword(String username, String password) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Profissional profissional = new Profissional();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LIST_BY_USERNAME_E_SENHA);
            
            ps.setString(1, username);
             ps.setString(2, password);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("PacienteDAO:findById: nenhum registo com o user: " + username);
            }
                        popularComDados(profissional, rs);
        } catch (SQLException ex) {
            System.err.println("ProfissionalDAO:findByUsernamePassword: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return profissional;
    }

    public List<Profissional> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Profissional> profissionais = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Profissional profissional = new Profissional();
                popularComDados(profissional, rs);
                profissionais.add(profissional);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return profissionais;
    }

    public List<Profissional> findByNomeSobrenome(String valor) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Profissional> profissionais = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();

            ps = conn.prepareStatement(BUSCAR_POR_NOME);
            ps.setString(1, "%" + valor + "%");
            ps.setString(2, "%" + valor + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Profissional profissional = new Profissional();
                popularComDados(profissional, rs);
                profissionais.add(profissional);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return profissionais;
    }

    private void popularComDados(Profissional profissional, ResultSet rs) {
        try {
            profissional.setIdProfissional(rs.getInt("id_profissional"));
            profissional.setNomeProfissional(rs.getString("nome_profissional"));
            profissional.setSobrenomeProfissional(rs.getString("sobrenome_profissional"));
            profissional.setDataNascimento(rs.getDate("data_nascimento_profissional"));
            profissional.setSexoProfissional(Sexo.getAbreviatura(rs.getString("sexo_profissional")));
            profissional.setEmailProfissional(rs.getString("email_profissional"));
            profissional.setTelefoneProfissional(rs.getString("telefone_profissional"));
            profissional.setRuaProfissional(rs.getString("rua_profissional"));
            profissional.setCasaProfissional(rs.getString("casa_profissional"));
            profissional.setBairroProfissional(rs.getString("bairro_profissional"));
            profissional.setDistrititoProfissional(rs.getString("distritito_profissional"));
            Municipio municipio = new Municipio();
            municipio.setNomeMunicipio(rs.getString("nome_municipio"));
            profissional.setMunicipio(municipio);
            CategoriaProfissional categoriaProfissional = new CategoriaProfissional();
            categoriaProfissional.setNomeCategoriaProfissional(rs.getString("nome_categoria_profissional"));
            profissional.setCategoriaProfissional(categoriaProfissional);

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }

}
