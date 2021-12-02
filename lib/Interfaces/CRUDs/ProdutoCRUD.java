import java.util.ArrayList;
import java.util.List;

public class ProdutoCRUD implements IRepositorioProduto{
    Produto produto = new Produto();
    ConnectSQLProduto Conexao = new ConnectSQLProduto();
    
    public ProdutoCRUD() {}    

    @Override
    public void inserirProduto(String descricao, long codigoBarras, int quantidadeEstoque, float precoCusto, float precoVenda) throws Exception{
        try {
            produto = new Produto(descricao, codigoBarras, quantidadeEstoque, precoCusto, precoVenda);
            Conexao.inserirTbProduto(produto);
        } catch (Exception e) {throw new Exception(e.getMessage());}
    }
    @Override
    public void atualizarProduto(int produtoID, String descricao, long codigoBarras, int quantidadeEstoque, float precoCusto, float precoVenda) throws Exception{
        try{
            produto = new Produto(produtoID, descricao, codigoBarras, quantidadeEstoque, precoCusto, precoVenda);
            Conexao.atualizarTbProduto(produto);
        }catch(Exception e){throw new Exception(e.getMessage());}        
    }
    @Override
    public void deletarProduto(int produtoID){
        try{
            Conexao.deletarProduto(produtoID);
        }catch(Exception e){System.out.println("Erro - " + e.getMessage());} 
    }
    @Override
    public Produto obterProdutoPorID(int produtoID){
        try{
            produto = Conexao.getTbProdutoPorID(produtoID);
        }catch(Exception e){System.out.println("Erro - " + e.getMessage());} 
        
        return produto;
    }
    @Override
    public List<Produto> obterProdutos(){
        List<Produto> ProdutoList = new ArrayList<Produto>();
        try{            
            ProdutoList = Conexao.getTbProduto();
                
        }catch(Exception e){System.out.println("Erro - " + e.getMessage());} 
        return ProdutoList;
    }

}
