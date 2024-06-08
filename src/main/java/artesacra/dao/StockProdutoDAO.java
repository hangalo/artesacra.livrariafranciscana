package artesacra.dao;

import artesacra.dbutil.ConnectionDB;
import artesacra.modelo.FamiliaProduto;
import artesacra.modelo.Produto;
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

public class StockProdutoDAO {

    private static final String INSERT = "INSERT INTO stock_produto(data_da_compra, quantidade, preco_de_compra, id_produto, id_profissional) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE stock_produto SET data_da_compra = ?, data_atualizacao = ?, quantidade = ?, preco_de_compra = ?, colocacao_localizacao = ?, precentagem_preco_venda = ?, id_produto = ? WHERE id_stock = ?";
    private static final String DELETE = "DELETE FROM stock_produto WHERE id_stock = ?";
    private static final String SELECT_BY_ID = "SELECT id_stock, data_da_compra, data_atualizacao, quantidade, preco_de_compra, stk.colocacao_localizacao, precentagem_preco_venda, pd.nome_produto, pd.quantidade_stock_produto, pd.preco_venda_produto, tp.descricao_tipo_de_produto FROM stock_produto stk  INNER JOIN produto pd ON stk.id_produto=pd.id_produto INNER JOIN tipo_de_produto tp ON pd.id_tipo_de_produto =tp.id_tipo_de_produto WHERE id_stock = ?";
    private static final String SELECT_BY_NOME = "SELECT id_stock, data_da_compra, data_atualizacao, quantidade, preco_de_compra, stk.colocacao_localizacao, precentagem_preco_venda, pd.nome_produto, pd.quantidade_stock_produto, pd.preco_venda_produto, tp.descricao_tipo_de_produto FROM stock_produto stk  INNER JOIN produto pd ON stk.id_produto=pd.id_produto INNER JOIN tipo_de_produto tp ON pd.id_tipo_de_produto =tp.id_tipo_de_produto WHERE pd.nome_produto LIKE ?  ORDER BY pd.nome_produto";
    private static final String SELECT_TUDO = "SELECT id_stock, data_da_compra, data_atualizacao, quantidade, preco_de_compra, stk.colocacao_localizacao, precentagem_preco_venda, pd.nome_produto, pd.quantidade_stock_produto, pd.preco_venda_produto, tp.descricao_tipo_de_produto FROM stock_produto stk  INNER JOIN produto pd ON stk.id_produto=pd.id_produto INNER JOIN tipo_de_produto tp ON pd.id_tipo_de_produto =tp.id_tipo_de_produto ORDER BY DATE(data_atualizacao) DESC";
    private static final String SELECT_BY_ID_PRODUTO = "SELECT id_stock, data_da_compra, data_atualizacao, quantidade, preco_de_compra, stk.colocacao_localizacao, precentagem_preco_venda, pd.id_produto, pd.nome_produto, pd.quantidade_stock_produto, pd.preco_venda_produto, tp.descricao_tipo_de_produto FROM stock_produto stk  INNER JOIN produto pd ON stk.id_produto=pd.id_produto INNER JOIN tipo_de_produto tp ON pd.id_tipo_de_produto =tp.id_tipo_de_produto WHERE pd.id_produto =?  ORDER BY pd.nome_produto";
    private static final String SELECT_ALL_ENTRE_DATAS = "SELECT id_stock, data_da_compra, data_atualizacao, quantidade, preco_de_compra, stk.colocacao_localizacao, precentagem_preco_venda, pd.nome_produto, pd.quantidade_stock_produto, pd.preco_venda_produto, tp.descricao_tipo_de_produto FROM stock_produto stk  INNER JOIN produto pd ON stk.id_produto=pd.id_produto INNER JOIN tipo_de_produto tp ON pd.id_tipo_de_produto =tp.id_tipo_de_produto  WHERE DATE(data_atualizacao) Between ? and ? ORDER BY pd.nome_produto";
    private static final String SELECT_BY_TIPO_PRODUTO = "SELECT id_stock, data_da_compra, data_atualizacao, quantidade, preco_de_compra, stk.colocacao_localizacao, precentagem_preco_venda, pd.id_produto, pd.nome_produto, pd.quantidade_stock_produto, pd.preco_venda_produto, tp.descricao_tipo_de_produto FROM stock_produto stk  INNER JOIN produto pd ON stk.id_produto=pd.id_produto INNER JOIN tipo_de_produto tp ON pd.id_tipo_de_produto =tp.id_tipo_de_produto WHERE tp.id_tipo_de_produto =?  ORDER BY pd.nome_produto";

    private static final String SELECT_STOCK_ACUMULADO_ENTRE_DATAS="SELECT pd.nome_produto,  SUM(quantidade)   FROM stock_produto stk INNER JOIN produto pd ON stk.id_produto=pd.id_produto WHERE DATE(data_atualizacao) Between ? and ?  GROUP BY pd.nome_produto  ORDER BY pd.nome_produto";
    
    /*SELECT pd.nome_produto,  SUM(quantidade)   FROM stock_produto stk INNER JOIN produto pd ON stk.id_produto=pd.id_produto WHERE DATE(data_atualizacao) Between ? and ?  GROUP BY pd.nome_produto  ORDER BY pd.nome_produto*/
    
    public boolean save(StockProduto stockProduto) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
/*
 data_da_compra, quantidade, preco_de_compra,id_produto
            */
            ps.setDate(1, new java.sql.Date(stockProduto.getDataCompra().getTime()));
            ps.setInt(2, stockProduto.getQuantidade());
            ps.setDouble(3, stockProduto.getPrecoCompra());                      
            ps.setInt(4, stockProduto.getProduto().getIdProduto());
            ps.setInt(5, stockProduto.getProfissional().getIdProfissional());
          
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
//            ps.setDouble(5, stockProduto.getPrecentagemPrecoVenda());
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

    public StockProduto findByIdProduto(Produto produto) {
                      
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        StockProduto stock = new StockProduto();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID_PRODUTO);
            ps.setInt(1, produto.getIdProduto());
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("ProdutoDAO:findByIdProduto: nenhum registo com o id: " + produto);
            }
            popularComDados(stock, rs);
        } catch (SQLException ex) {
            System.err.println("ProdutoDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return stock;
    }

    public StockProduto findByIdEspecifico(String codigo) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        StockProduto produto = new StockProduto();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
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

    public List<StockProduto> findByTipo(TipoProduto tipo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println(">>>>>>>>>>Entrada>>>>>>>>>>>>>>>>>>>" + tipo);

        List<StockProduto> produtos = new ArrayList();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_TIPO_PRODUTO);
            ps.setInt(1, tipo.getIdTipoProduto());

            rs = ps.executeQuery();
            while (rs.next()) {
                StockProduto produto = new StockProduto();
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

    public List<StockProduto> findByCodigo(String codigo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println(">>>>>>>>>>Entrada>>>>>>>>>>>>>>>>>>>" + codigo);

        List<StockProduto> produtos = new ArrayList();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setString(1, codigo);

            rs = ps.executeQuery();
            while (rs.next()) {
                StockProduto produto = new StockProduto();
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

    public List<StockProduto> findByNomes(String nome) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println(">>>>>>>>>>Entrada>>>>>>>>>>>>>>>>>>>" + nome);

        List<StockProduto> produtos = new ArrayList();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_NOME);

            ps.setString(1, "%" + nome + "%");
            ps.setString(2, "%" + nome + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                StockProduto produto = new StockProduto();
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

    public List<StockProduto> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<StockProduto> produtos = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                StockProduto produto = new StockProduto();
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

    public List<StockProduto> findStockProdutoPorIntervaloData(Date inicio, Date fim) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<StockProduto> items = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL_ENTRE_DATAS);
            ps.setDate(1, new java.sql.Date(inicio.getTime()));
            ps.setDate(2, new java.sql.Date(fim.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                StockProduto item = new StockProduto();
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
    
    
     public List<StockProdutoAcumulado> findStockAcumuladoEntreDatas(Date inicio, Date fim) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<StockProdutoAcumulado> items = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_STOCK_ACUMULADO_ENTRE_DATAS);
            ps.setDate(1, new java.sql.Date(inicio.getTime()));
            ps.setDate(2, new java.sql.Date(fim.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                StockProdutoAcumulado item = new StockProdutoAcumulado();
                popularStockProdutoAcumulado(item, rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            System.err.println(":Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return items;
    }
    
    
    private void popularComDados(StockProduto stock, ResultSet rs) {
        try {

            stock.setIdStock(rs.getInt("id_stock"));
            stock.setDataCompra(rs.getDate("data_da_compra"));

            stock.setDataActualizacao(rs.getTimestamp("data_atualizacao"));
            stock.setQuantidade(rs.getInt("quantidade"));

            stock.setPrecoCompra(rs.getDouble("preco_de_compra"));
            stock.setLocalizacao(rs.getString("colocacao_localizacao"));
           // stock.setPrecentagemPrecoVenda(rs.getDouble("precentagem_preco_venda"));

            Produto produto = new Produto();
          //  produto.setIdProduto(rs.getInt("pd.id_produto"));
            produto.setNomeProduto(rs.getString("pd.nome_produto"));
            produto.setPrecoVenda(rs.getDouble("pd.preco_venda_produto"));
            produto.setQuantidadeStock(rs.getInt("pd.quantidade_stock_produto"));
            TipoProduto tipoProduto = new TipoProduto();
            tipoProduto.setDescricaoTipoProduto(rs.getString("descricao_tipo_de_produto"));

            produto.setTipoProduto(tipoProduto);
            stock.setProduto(produto);

        } catch (SQLException ex) {
            System.err.println("StockProdutoDAO: popularComDados: Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }
    
    
     public void popularStockProdutoAcumulado(StockProdutoAcumulado stock, ResultSet rs) {
        try {
            Produto produto = new Produto();
            produto.setNomeProduto(rs.getString(1));
            stock.setProduto(produto);
            stock.setQuantidadeTotal(rs.getInt(2));
                 

        } catch (SQLException ex) {
            System.err.println("Error on fill data Item: " + ex.getLocalizedMessage());
        }

    }
    

}
