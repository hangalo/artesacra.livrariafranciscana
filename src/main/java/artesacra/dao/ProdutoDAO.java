package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.FamiliaProduto;
import artesacra.modelo.Produto;
import artesacra.modelo.TipoProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private static final String INSERT = "INSERT INTO produto(nome_produto, preco_venda_produto, id_tipo_de_produto, id_unidade_de_medida ) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE produto SET nome_produto = ?, preco_venda_produto = ?, id_tipo_de_produto = ? WHERE id_produto = ?";
    private static final String UPDATE_DIMINUIR_QUANTIDADE = "UPDATE produto SET quantidade_stock_produto = quantidade_stock_produto - ? WHERE id_produto = ?";
    private static final String UPDATE_AUMENTAR_QUANTIDADE = "UPDATE produto SET quantidade_stock_produto = quantidade_stock_produto + ? WHERE id_produto = ?";
    private static final String DELETE = "DELETE FROM produto WHERE id_produto =?";
    private static final String SELECT_BY_CODIGO = "SELECT id_produto, nome_produto, data_expira_produto, preco_venda_produto, quantidade_stock_produto, colocacao_localizacao, tp.id_tipo_de_produto, descricao_tipo_de_produto, descricao_familia_do_produto FROM produto p INNER JOIN tipo_de_produto tp ON p.id_tipo_de_produto=tp.id_tipo_de_produto INNER JOIN familia_do_produto fp on tp.id_familia_do_produto=fp.id_familia_do_produto WHERE id_produto = ?";
    private static final String SELECT_BY_NOME = "SELECT id_produto, nome_produto, data_expira_produto, preco_venda_produto, quantidade_stock_produto, colocacao_localizacao, tp.id_tipo_de_produto,descricao_tipo_de_produto, descricao_familia_do_produto FROM produto p INNER JOIN tipo_de_produto tp ON p.id_tipo_de_produto=tp.id_tipo_de_produto INNER JOIN familia_do_produto fp on tp.id_familia_do_produto=fp.id_familia_do_produto WHERE p.nome_produto LIKE ? ORDER BY nome_produto";
    private static final String SELECT_BY_TIPO_PRODUTO = "SELECT id_produto, nome_produto, data_expira_produto, preco_venda_produto, quantidade_stock_produto, colocacao_localizacao, tp.id_tipo_de_produto, tp.descricao_tipo_de_produto, descricao_familia_do_produto FROM produto p INNER JOIN tipo_de_produto tp ON p.id_tipo_de_produto=tp.id_tipo_de_produto INNER JOIN familia_do_produto fp on tp.id_familia_do_produto=fp.id_familia_do_produto WHERE tp.id_tipo_de_produto = ? ORDER BY nome_produto";
    private static final String SELECT_BY_TIPO_PRODUTO_QDE_MENOR_10 = "SELECT id_produto, nome_produto, data_expira_produto, preco_venda_produto, quantidade_stock_produto, colocacao_localizacao, tp.id_tipo_de_produto, tp.descricao_tipo_de_produto, descricao_familia_do_produto FROM produto p INNER JOIN tipo_de_produto tp ON p.id_tipo_de_produto=tp.id_tipo_de_produto INNER JOIN familia_do_produto fp on tp.id_familia_do_produto=fp.id_familia_do_produto WHERE tp.id_tipo_de_produto =? ORDER BY nome_produto";
    //private static final String SELECT_BY_TIPO_PRODUTO_QDE_MENOR_10 = "SELECT id_produto, nome_produto, data_expira_produto, preco_venda_produto, quantidade_stock_produto, colocacao_localizacao, tp.id_tipo_de_produto, tp.descricao_tipo_de_produto, descricao_familia_do_produto FROM produto p INNER JOIN tipo_de_produto tp ON p.id_tipo_de_produto=tp.id_tipo_de_produto INNER JOIN familia_do_produto fp on tp.id_familia_do_produto=fp.id_familia_do_produto WHERE quantidade_stock_produto <=10 AND tp.id_tipo_de_produto =? ORDER BY nome_produto";
    private static final String SELECT_BY_TIPO_STOCK_POSITIVO = "SELECT id_produto, nome_produto, data_expira_produto, preco_venda_produto, quantidade_stock_produto, colocacao_localizacao, tp.id_tipo_de_produto, tp.descricao_tipo_de_produto, descricao_familia_do_produto FROM produto p INNER JOIN tipo_de_produto tp ON p.id_tipo_de_produto=tp.id_tipo_de_produto INNER JOIN familia_do_produto fp on tp.id_familia_do_produto=fp.id_familia_do_produto WHERE quantidade_stock_produto >=1 AND tp.id_tipo_de_produto =? ORDER BY nome_produto";

    private static final String SELECT_TUDO = "SELECT id_produto, nome_produto, data_expira_produto, preco_venda_produto, quantidade_stock_produto, colocacao_localizacao, tp.id_tipo_de_produto, tp.descricao_tipo_de_produto, descricao_familia_do_produto"
            + " FROM produto p INNER JOIN tipo_de_produto tp ON p.id_tipo_de_produto=tp.id_tipo_de_produto "
            + "INNER JOIN familia_do_produto fp on tp.id_familia_do_produto=fp.id_familia_do_produto ORDER BY nome_produto";

    public boolean save(Produto produto) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);

            ps.setString(1, produto.getNomeProduto());

            //    ps.setDate(2, new java.sql.Date(produto.getValidadeProduto().getTime()));
            ps.setDouble(2, produto.getPrecoVenda());
            //    ps.setInt(4, produto.getQuantidadeStock());
            //  ps.setString(5, produto.getColocacaoEstante());
            ps.setInt(3, produto.getTipoProduto().getIdTipoProduto());
            ps.setInt(4, produto.getUnidadeMedida().getIdUnidadeMedida());

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

    public boolean update(Produto produto) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);

            ps.setString(1, produto.getNomeProduto());
            ps.setDouble(2, produto.getPrecoVenda());
            ps.setInt(3, produto.getTipoProduto().getIdTipoProduto());
            ps.setInt(4, produto.getIdProduto());

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

    public boolean updateDimunuirQuantidade(Produto produto, Integer quantidade) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {

            conn = ConnectionDB.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(UPDATE_DIMINUIR_QUANTIDADE);

            ps.setInt(1, quantidade);
            ps.setInt(2, produto.getIdProduto());
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

    public boolean updateAumentarQuantidade(Produto produto, Integer quantidade) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
             System.out.println("Quantidade 1>>>>>>>>>>"+quantidade);
            conn = ConnectionDB.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(UPDATE_AUMENTAR_QUANTIDADE);

            ps.setInt(1, quantidade);
            ps.setInt(2, produto.getIdProduto());
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

    public boolean delete(Produto produto) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flagControlo = false;
        if (produto == null) {
            System.err.println("O campo anterior nao pode ser nulo");
        }

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, produto.getIdProduto());
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

    public Produto findById(Produto idProduto) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Produto produto = new Produto();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_CODIGO);
            ps.setInt(1, idProduto.getIdProduto());
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("ProdutoDAO:findById: nenhum registo com o id: " + idProduto);
            }
            popularComDados(produto, rs);
        } catch (SQLException ex) {
            System.err.println("ProdutoDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return produto;
    }

    public Produto findByIdEspecifico(String codigo) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Produto produto = new Produto();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_CODIGO);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("ProdutoDAO:findById: nenhum registo com o id: " + codigo);
            }
            popularComDados(produto, rs);
        } catch (SQLException ex) {
            System.err.println("ProdutoDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return produto;
    }
    
    public Produto findById(Integer codigo) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Produto produto = new Produto();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_CODIGO);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("ProdutoDAO:findById: nenhum registo com o id: " + codigo);
            }
            popularComDados(produto, rs);
        } catch (SQLException ex) {
            System.err.println("ProdutoDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return produto;
    }


    public List<Produto> findByCodigo(String codigo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println(">>>>>>>>>>Entrada>>>>>>>>>>>>>>>>>>>" + codigo);

        List<Produto> produtos = new ArrayList();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_CODIGO);
            ps.setString(1, codigo);

            rs = ps.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                popularComDados(produto, rs);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return produtos;
    }

    public List<Produto> findByTipo(TipoProduto tipo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println(">>>>>>>>>>Entrada>>>>>>>>>>>>>>>>>>>" + tipo);

        List<Produto> produtos = new ArrayList();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_TIPO_PRODUTO);
            ps.setInt(1, tipo.getIdTipoProduto());

            rs = ps.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                popularComDados(produto, rs);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return produtos;
    }

    public List<Produto> findByTipoQuantidadeMenor10(TipoProduto tipo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_TIPO_PRODUTO_QDE_MENOR_10);
            ps.setInt(1, tipo.getIdTipoProduto());

            rs = ps.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                popularComDados(produto, rs);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return produtos;
    }

    public List<Produto> findByTipoStockPositivo(TipoProduto tipo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_TIPO_STOCK_POSITIVO);
            ps.setInt(1, tipo.getIdTipoProduto());

            rs = ps.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                popularComDados(produto, rs);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return produtos;
    }

    public List<Produto> findByNomes(String nome) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println(">>>>>>>>>>Entrou>>>>>>>>>>>>>>>>>>>" + nome);

        List<Produto> produtos = new ArrayList();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_NOME);

            //ps.setString(1, "'%'" + nome + "'%'");
            ps.setString(1, nome + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                popularComDados(produto, rs);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("ProdutoDAO: findByNomes: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return produtos;
    }

    public List<Produto> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                popularComDados(produto, rs);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return produtos;
    }

    private void popularComDados(Produto produto, ResultSet rs) {
        try {
            produto.setIdProduto(rs.getInt("id_produto"));
            produto.setNomeProduto(rs.getString("nome_produto"));

            produto.setValidadeProduto(rs.getDate("data_expira_produto"));
            produto.setPrecoVenda(rs.getDouble("preco_venda_produto"));

            produto.setQuantidadeStock(rs.getInt("quantidade_stock_produto"));
            produto.setColocacaoEstante(rs.getString("colocacao_localizacao"));

            TipoProduto tipoProduto = new TipoProduto();
            FamiliaProduto familiaProduto = new FamiliaProduto();
            familiaProduto.setDescricaoFamiliaProduto(rs.getString("descricao_familia_do_produto"));
            tipoProduto.setIdTipoProduto(rs.getInt("tp.id_tipo_de_produto"));
            tipoProduto.setDescricaoTipoProduto(rs.getString("descricao_tipo_de_produto"));
            tipoProduto.setFamiliaProduto(familiaProduto);
            produto.setTipoProduto(tipoProduto);

        } catch (SQLException ex) {
            System.err.println("ProdutoDAO: popularComDados: Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }

}
