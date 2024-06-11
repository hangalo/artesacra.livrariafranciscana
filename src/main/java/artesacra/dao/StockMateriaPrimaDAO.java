package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.MateriaPrima;
import artesacra.modelo.Produto;
import artesacra.modelo.StockMateriaPrima;
import artesacra.modelo.StockMateriaPrimaAcumulado;
import artesacra.modelo.StockProduto;
import artesacra.modelo.StockProdutoAcumulado;
import artesacra.modelo.TipoProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockMateriaPrimaDAO {

    private static final String INSERT = "INSERT INTO stock_materia_prima (data_da_compra, quantidade, preco_de_compra, colocacao_localizacao, id_materia_prima, id_profissional) VALUES(?, ?,?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE stock_produto SET data_da_compra = ?, data_atualizacao = ?, quantidade = ?, preco_de_compra = ?, colocacao_localizacao = ?, precentagem_preco_venda = ?, id_produto = ? WHERE id_stock = ?";
    private static final String DELETE = "DELETE FROM stock_produto WHERE id_stock = ?";
    private static final String SELECT_BY_ID = "SELECT id_stock, data_da_compra, data_atualizacao, quantidade, preco_de_compra, stk.colocacao_localizacao, precentagem_preco_venda, pd.nome_produto, pd.quantidade_stock_produto, pd.preco_venda_produto, tp.descricao_tipo_de_produto FROM stock_produto stk  INNER JOIN produto pd ON stk.id_produto=pd.id_produto INNER JOIN tipo_de_produto tp ON pd.id_tipo_de_produto =tp.id_tipo_de_produto WHERE id_stock = ?";
    private static final String SELECT_BY_NOME = "SELECT id_stock, data_da_compra, data_atualizacao, quantidade, preco_de_compra, stk.colocacao_localizacao, precentagem_preco_venda, pd.nome_produto, pd.quantidade_stock_produto, pd.preco_venda_produto, tp.descricao_tipo_de_produto FROM stock_produto stk  INNER JOIN produto pd ON stk.id_produto=pd.id_produto INNER JOIN tipo_de_produto tp ON pd.id_tipo_de_produto =tp.id_tipo_de_produto WHERE pd.nome_produto LIKE ?  ORDER BY pd.nome_produto";
    private static final String SELECT_TUDO = "SELECT id_stock, data_da_compra, data_atualizacao, quantidade, preco_de_compra, stk.colocacao_localizacao, precentagem_preco_venda, pd.nome_produto, pd.quantidade_stock_produto, pd.preco_venda_produto, tp.descricao_tipo_de_produto FROM stock_produto stk  INNER JOIN produto pd ON stk.id_produto=pd.id_produto INNER JOIN tipo_de_produto tp ON pd.id_tipo_de_produto =tp.id_tipo_de_produto ORDER BY DATE(data_atualizacao) DESC";
    private static final String SELECT_BY_ID_PRODUTO = "SELECT id_stock, data_da_compra, data_atualizacao, quantidade, preco_de_compra, stk.colocacao_localizacao, precentagem_preco_venda, pd.id_produto, pd.nome_produto, pd.quantidade_stock_produto, pd.preco_venda_produto, tp.descricao_tipo_de_produto FROM stock_produto stk  INNER JOIN produto pd ON stk.id_produto=pd.id_produto INNER JOIN tipo_de_produto tp ON pd.id_tipo_de_produto =tp.id_tipo_de_produto WHERE pd.id_produto =?  ORDER BY pd.nome_produto";
    private static final String SELECT_ALL_ENTRE_DATAS = "SELECT id_stock, data_da_compra, data_atualizacao, quantidade, preco_de_compra, stk.colocacao_localizacao, mp.nome_materia_prima, mp.quantidade_stock_materia_prima, tp.descricao_tipo_de_produto FROM stock_materia_prima stk  INNER JOIN materia_prima mp ON stk.id_materia_prima =mp.id_materia_prima  INNER JOIN tipo_de_produto tp ON mp.id_tipo_de_produto =tp.id_tipo_de_produto WHERE DATE(data_atualizacao) Between ? and ? ORDER BY mp.nome_materia_prima";
    private static final String SELECT_BY_TIPO_PRODUTO = "SELECT id_stock, data_da_compra, data_atualizacao, quantidade, preco_de_compra, stk.colocacao_localizacao, precentagem_preco_venda, pd.id_produto, pd.nome_produto, pd.quantidade_stock_produto, pd.preco_venda_produto, tp.descricao_tipo_de_produto FROM stock_produto stk  INNER JOIN produto pd ON stk.id_produto=pd.id_produto INNER JOIN tipo_de_produto tp ON pd.id_tipo_de_produto =tp.id_tipo_de_produto WHERE tp.id_tipo_de_produto =?  ORDER BY pd.nome_produto";

    private static final String SELECT_STOCK_ACUMULADO_ENTRE_DATAS = "SELECT mp.nome_materia_prima,  SUM(quantidade)   FROM stock_materia_prima stk INNER JOIN materia_prima mp ON stk.id_materia_prima =mp.id_materia_prima  WHERE DATE(data_atualizacao) Between ? and ? GROUP BY mp.nome_materia_prima,  DATE(data_atualizacao)  ORDER BY mp.nome_materia_prima,  DATE(data_atualizacao)";

    /*SELECT pd.nome_produto,  SUM(quantidade)   FROM stock_produto stk INNER JOIN produto pd ON stk.id_produto=pd.id_produto WHERE DATE(data_atualizacao) Between ? and ?  GROUP BY pd.nome_produto  ORDER BY pd.nome_produto*/
    public boolean save(StockMateriaPrima stockMateriaPrima) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {

            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setDate(1, new java.sql.Date(stockMateriaPrima.getDataCompra().getTime()));
            ps.setInt(2, stockMateriaPrima.getQuantidade());
            ps.setDouble(3, stockMateriaPrima.getPrecoCompra());
            ps.setString(4, stockMateriaPrima.getColocacao());
            ps.setInt(5, stockMateriaPrima.getMateriaPrima().getIdMateriaPrima());
            ps.setInt(6, stockMateriaPrima.getProfissional().getIdProfissional());

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

    public boolean update(StockProduto stockProduto) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);

            ps.setDate(1, new java.sql.Date(stockProduto.getDataCompra().getTime()));
            ps.setDouble(2, stockProduto.getQuantidade());
            ps.setDouble(3, stockProduto.getPrecoCompra());
            ps.setString(4, stockProduto.getLocalizacao());
            ps.setInt(5, stockProduto.getProduto().getIdProduto());
            ps.setInt(6, stockProduto.getIdStock());

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

    public boolean delete(StockProduto stockProduto) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flagControlo = false;
        if (stockProduto == null) {
            System.err.println("O campo anterior nao pode ser nulo");
        }

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, stockProduto.getIdStock());
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

    public StockMateriaPrima findByIdMateriaPrima(MateriaPrima materiaPrima) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        StockMateriaPrima stock = new StockMateriaPrima();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID_PRODUTO);
            ps.setInt(1, materiaPrima.getIdMateriaPrima());
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("ProdutoDAO:findByIdProduto: nenhum registo com o id: " + materiaPrima);
            }
            popularComDados(stock, rs);
        } catch (SQLException ex) {
            System.err.println("ProdutoDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return stock;
    }

    public StockMateriaPrima findByIdEspecifico(String codigo) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        StockMateriaPrima stock = new StockMateriaPrima();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("ProdutoDAO:findById: nenhum registo com o id: " + codigo);
            }
            popularComDados(stock, rs);
        } catch (SQLException ex) {
            System.err.println("ProdutoDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return stock;
    }

    public List<StockMateriaPrima> findByTipo(TipoProduto tipo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println(">>>>>>>>>>Entrada>>>>>>>>>>>>>>>>>>>" + tipo);

        List<StockMateriaPrima> produtos = new ArrayList();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_TIPO_PRODUTO);
            ps.setInt(1, tipo.getIdTipoProduto());

            rs = ps.executeQuery();
            while (rs.next()) {
                StockMateriaPrima produto = new StockMateriaPrima();
                popularComDados(produto, rs);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("StockProdutoDAO: findByTipo: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return produtos;
    }

    public List<StockMateriaPrima> findByCodigo(String codigo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println(">>>>>>>>>>Entrada>>>>>>>>>>>>>>>>>>>" + codigo);

        List<StockMateriaPrima> produtos = new ArrayList();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setString(1, codigo);

            rs = ps.executeQuery();
            while (rs.next()) {
                StockMateriaPrima produto = new StockMateriaPrima();
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

    public List<StockMateriaPrima> findByNomes(String nome) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println(">>>>>>>>>>Entrada>>>>>>>>>>>>>>>>>>>" + nome);

        List<StockMateriaPrima> produtos = new ArrayList();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_NOME);

            ps.setString(1, "%" + nome + "%");
            ps.setString(2, "%" + nome + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                StockMateriaPrima produto = new StockMateriaPrima();
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

    public List<StockMateriaPrima> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<StockMateriaPrima> produtos = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                StockMateriaPrima produto = new StockMateriaPrima();
                popularComDados(produto, rs);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("StockProdutoDAO: findAll()- Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return produtos;
    }

    public List<StockMateriaPrima> findStockMateriaPrimaPorIntervaloData(Date inicio, Date fim) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<StockMateriaPrima> items = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL_ENTRE_DATAS);
            ps.setDate(1, new java.sql.Date(inicio.getTime()));
            ps.setDate(2, new java.sql.Date(fim.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                StockMateriaPrima item = new StockMateriaPrima();
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

    public List<StockMateriaPrimaAcumulado> findStockAcumuladoEntreDatas(Date inicio, Date fim) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<StockMateriaPrimaAcumulado> items = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_STOCK_ACUMULADO_ENTRE_DATAS);
            ps.setDate(1, new java.sql.Date(inicio.getTime()));
            ps.setDate(2, new java.sql.Date(fim.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                StockMateriaPrimaAcumulado item = new StockMateriaPrimaAcumulado();
                popularStockMateriaPrimaAcumulado(item, rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            System.err.println(":Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return items;
    }

    private void popularComDados(StockMateriaPrima stock, ResultSet rs) {
        try {
            stock.setIdStock(rs.getInt("id_stock"));
            stock.setDataCompra(rs.getDate("data_da_compra"));
            stock.setDataActualizacao(rs.getTimestamp("data_atualizacao"));
            stock.setQuantidade(rs.getInt("quantidade"));
            stock.setPrecoCompra(rs.getDouble("preco_de_compra"));
            stock.setColocacao(rs.getString("colocacao_localizacao"));
            MateriaPrima materiaPrima = new MateriaPrima();
            materiaPrima.setNomeMateriaPrima(rs.getString("nome_materia_prima"));
            materiaPrima.setQuantidadeStock(rs.getInt("mp.quantidade_stock_materia_prima"));
            TipoProduto tipoProduto = new TipoProduto();
            tipoProduto.setDescricaoTipoProduto(rs.getString("descricao_tipo_de_produto"));
            materiaPrima.setTipoProduto(tipoProduto);
            stock.setMateriaPrima(materiaPrima);

        } catch (SQLException ex) {
            System.err.println("StockProdutoDAO: popularComDados: Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }

    public void popularStockMateriaPrimaAcumulado(StockMateriaPrimaAcumulado stock, ResultSet rs) {
        try {
            MateriaPrima materiaPrima = new MateriaPrima();
            materiaPrima.setNomeMateriaPrima(rs.getString(1));
            stock.setMateriaPrima(materiaPrima);
            stock.setQuantidadeTotal(rs.getInt(2));

        } catch (SQLException ex) {
            System.err.println("Error on fill data Item: " + ex.getLocalizedMessage());
        }

    }

}
