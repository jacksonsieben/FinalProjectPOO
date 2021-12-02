import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectSQLProduto extends ConnectSQL{
    
    public ConnectSQLProduto() {}

    public int getProdutoID() throws Exception {
        int produtoID = 0;
        Connection con = mySqlConnection();
        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("SELECT max(ProdutoID) FROM 'tb_produto';");
            while(rs.next()) {
                produtoID = rs.getInt("max(ProdutoID)");                
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}
        return produtoID+1;
    }

    public void inserirTbProduto(Produto produto) throws Exception{
        con = mySqlConnection();
        try {
            Statement stmt = con.createStatement();
            String query = ("INSERT INTO tb_produto (Descricao, CodigoBarras, PrecoCusto, PrecoVenda, QuantidadeEstoque)" +  
                            "VALUES ('" + produto.Descricao + "', '" + produto.CodigoBarras + 
                            "', '" + produto.PrecoCusto + "', '" + produto.PrecoVenda + "', '" + produto.QuantidadeEstoque  + "')");
            stmt.execute(query);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados " + e.getMessage()); }
    }

    public void atualizarTbProduto(Produto produto) throws Exception{
        con = mySqlConnection();
        try {
            Statement stmt = con.createStatement();
            String query = ("UPDATE tb_produto SET Descricao = '" + produto.Descricao + "', CodigoBarras = '" + produto.CodigoBarras + "', PrecoCusto = '" 
                            + produto.PrecoCusto +"', PrecoVenda = '" + produto.PrecoVenda +"', QuantidadeEstoque = '" + produto.QuantidadeEstoque
                            +"' WHERE ProdutoID = '" + produto.ProdutoID + "';");
            stmt.execute(query);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}
    }

    public Produto getTbProdutoPorID(int i) throws Exception{
        Produto produto = new Produto();
        con = mySqlConnection();

        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from tb_produto where ProdutoID = '" + i + "';");
            while (rs.next()){
                produto.setProdutoID(rs.getInt("ProdutoID")); 
                produto.setDescricao(rs.getString("Descricao"));
                produto.setCodigoBarras(rs.getInt("CodigoBarras"));
                produto.setPrecoCusto(rs.getFloat("PrecoCusto"));
                produto.setPrecoVenda(rs.getFloat("PrecoVenda"));      
                produto.setQuantidadeEstoque(rs.getInt("QuantidadeEstoque"));
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}

        return produto;
    }

    public List<Produto> getTbProduto() throws Exception{
        Produto produto = new Produto();
        List<Produto> produtos = new ArrayList<Produto>();
        con = mySqlConnection();

        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from tb_produto;");
            while (rs.next()){
                produto = new Produto();
                produto.setProdutoID(rs.getInt("ProdutoID")); 
                produto.setDescricao(rs.getString("Descricao"));
                produto.setCodigoBarras(rs.getInt("CodigoBarras"));
                produto.setPrecoCusto(rs.getFloat("PrecoCusto"));
                produto.setPrecoVenda(rs.getFloat("PrecoVenda"));      
                produto.setQuantidadeEstoque(rs.getInt("QuantidadeEstoque"));
                produtos.add(produto);
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}

        return produtos;
    }

    public void deletarProduto (int produtoID) throws Exception{
        con = mySqlConnection();
        try{    
            Statement stmt=con.createStatement();       
            String query1 = ("DELETE FROM tb_produto where ProdutoID = '" + produtoID + "';");
            stmt.execute(query1);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}
    }
}
